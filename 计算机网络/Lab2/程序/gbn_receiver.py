# -*- coding: utf-8 -*-
"""GBN 协议接收端"""

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


class GbnReceiver:
    """GBN 协议接收端"""
    def __init__(self, name: str, addr):
        self.__name = name
        self.__expected_seq_num = 0
        self.__sendpkt = self.__make_ack_pkt(SEQUENCE_LENGTH - 1)
        self.__socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        self.__socket.bind(addr)

    @staticmethod
    def __make_ack_pkt(seq_number: int):
        return "ACK {}".format(seq_number).encode()

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
        if pkt_number == self.__expected_seq_num:
            print("{} send ack message {} to {}.".format(
                self.__name, pkt_number, addr))
            self.__sendpkt = self.__make_ack_pkt(self.__expected_seq_num)
            self.__expected_seq_num = (self.__expected_seq_num +
                                       1) % SEQUENCE_LENGTH
        else:
            print(
                "{} received unexpected message {}, resend ack message {} to {}."
                .format(self.__name, pkt_number, self.__expected_seq_num,
                        addr))
            data = ''

        self.__socket.sendto(self.__sendpkt, addr)
        return data

    def begin_receive(self, addr):
        """接收特定地址发送的消息"""
        message = ""
        while True:
            data = self.__receive(addr)
            if data == 'EOF':
                break
            message += data
        final = "\n#############\nFinally {} recevied message:\n{}\n#############\n"
        print(final.format(self.__name, message))

        while True:
            self.__receive(addr)
