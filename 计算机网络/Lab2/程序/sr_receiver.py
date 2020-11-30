# -*- coding: utf-8 -*-
"""SR 协议接收端"""

import socket
import random
import time
import select

# 测试开关
DEBUG_DELAY_ON = True
DEBUG_RANDOM_THROW_ON = True
DEBUG_LOST_RATE = 0.25

# 协议参数
BUFFER_SIZE = 1024
SEQUENCE_LENGTH = 256
WINDOW_SIZE = 6  # 发送窗口大小


class SrReceiver:
    """SR 协议接收端"""
    def __init__(self, name: str, addr):
        self.__name = name
        self.__base = 0
        self.__data_seq = [''] * SEQUENCE_LENGTH
        self.__ack_flags = [False] * SEQUENCE_LENGTH
        self.__socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        self.__socket.bind(addr)

    @staticmethod
    def __make_ack_pkt(seq_number: int):
        return "ACK {}".format(seq_number).encode()

    def __in_window(self, num: int):
        head, tail = self.__base, (self.__base + WINDOW_SIZE) % SEQUENCE_LENGTH
        return head <= num < tail if head <= tail else (num >= head
                                                        or num < tail)

    def __receive(self, addr):
        if DEBUG_DELAY_ON:
            # 模拟网络延迟
            time.sleep(random.random())
        rlist, _, _ = select.select([self.__socket], [], [], 1)
        if not rlist:
            return ''
        msg_bytes, _ = self.__socket.recvfrom(BUFFER_SIZE)
        message: str = msg_bytes.decode()
        if not message.startswith("MSG "):
            return ''
        messages = message.split(maxsplit=2)
        pkt_number, data = int(messages[1]), messages[2]
        if DEBUG_RANDOM_THROW_ON and random.random() < DEBUG_LOST_RATE:
            # 模拟报文丢失
            print("{} lose packet {}.".format(self.__name, pkt_number))
            return ''
        ack_pkt = self.__make_ack_pkt(pkt_number)
        self.__socket.sendto(ack_pkt, addr)
        if (not self.__in_window(pkt_number)) or self.__ack_flags[pkt_number]:
            print("{} have received packet {}, resend ACK message {} to {}.".
                  format(self.__name, pkt_number, ack_pkt.decode(), addr))
        else:
            print("{} send ack message {} to {}.".format(
                self.__name, pkt_number, addr))
            self.__ack_flags[pkt_number] = True
            self.__data_seq[pkt_number] = data

        data = ''
        while self.__ack_flags[self.__base]:
            data += self.__data_seq[self.__base]

            self.__data_seq[self.__base] = ''
            self.__ack_flags[self.__base] = False
            self.__base = (self.__base + 1) % SEQUENCE_LENGTH

        return data

    def begin_receive(self, addr):
        """接收特定地址发送的消息"""
        message = ""
        while True:
            data = self.__receive(addr)
            message += data
            if data.endswith('EOF'):
                break
        message = message[:-3]
        final = "\n#############\nFinally {} recevied message:\n{}\n#############\n"
        print(final.format(self.__name, message))

        while True:
            self.__receive(addr)
