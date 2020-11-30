# -*- coding: utf-8 -*-
"""SR 协议发送端"""

import socket
import select
import time
import random

# 测试开关
DEBUG_RANDOM_THROW_ON = True
DEBUG_LOST_RATE = 0.3

# 协议参数
BUFFER_SIZE = 1024
SEQUENCE_LENGTH = 256
WINDOW_SIZE = 6  # 发送窗口大小
TIME_LIMIT = 3  # 计时器的阈值


class SrSender:
    """SR 协议的发送端"""
    def __init__(self, name: str, addr):
        self.__name = name
        self.__base = 0
        self.__next_seq_num = 0
        self.__timer = [-1] * SEQUENCE_LENGTH
        self.__data_seq = [b'0'] * SEQUENCE_LENGTH
        self.__ack_flags = [False] * SEQUENCE_LENGTH
        self.__socket = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
        self.__socket.bind(addr)

    @staticmethod
    def __make_msg_pkt(seq_number: int, message: str):
        """生成数据报文格式"""
        return "MSG {} {}".format(seq_number, message).encode()

    def __in_window(self, num: int):
        """判断 num 是否在滑动窗口中"""
        head, tail = self.__base, (self.__base + WINDOW_SIZE) % SEQUENCE_LENGTH
        return head <= num < tail if head <= tail else (num >= head
                                                        or num < tail)

    def __check(self, addr):
        """接收 ACK 报文并更新滑动窗口"""
        rlist, _, _ = select.select([self.__socket], [], [], 1)
        if rlist:
            msg_bytes, _ = self.__socket.recvfrom(BUFFER_SIZE)
            message: str = msg_bytes.decode()
            # 对确认报文处理
            if message.startswith("ACK "):
                messages = message.split()
                ack_number = int(messages[1])
                # 模拟 ACK 报文丢失
                if DEBUG_RANDOM_THROW_ON and random.random() < DEBUG_LOST_RATE:
                    print("{} lose ack packet {}.".format(
                        self.__name, ack_number))
                    return
                print("{} receive ACK message {}".format(self.__name, message))
                if self.__in_window(ack_number):
                    self.__ack_flags[ack_number] = True
            while self.__ack_flags[self.__base]:
                self.__ack_flags[self.__base] = False
                self.__data_seq[self.__base] = b'0'
                self.__timer[self.__base] = -1
                self.__base = (self.__base + 1) % SEQUENCE_LENGTH
        else:  # 未收到 ACK 报文
            i = self.__base
            while i != self.__next_seq_num:
                if not self.__ack_flags[i] and self.__timer[i] != -1:
                    if time.time() - self.__timer[i] > TIME_LIMIT:
                        # 超时处理
                        print("{} timeout on pkt {}.".format(self.__name, i))
                        self.__socket.sendto(self.__data_seq[i], addr)
                        print(
                            "{} resend message {} to {}.\nMessage: {}".format(
                                self.__name, i, addr,
                                self.__data_seq[i].decode()))
                        self.__timer[i] = time.time()
                i = (i + 1) % SEQUENCE_LENGTH

    def __send(self, addr, message: str):
        # 滑动窗口已满，无法发送数据
        if not self.__in_window(self.__next_seq_num):
            return False
        pkt = self.__make_msg_pkt(self.__next_seq_num, message)
        self.__socket.sendto(pkt, addr)
        self.__timer[self.__next_seq_num] = time.time()
        self.__data_seq[self.__next_seq_num] = pkt
        self.__ack_flags[self.__next_seq_num] = False
        print("{} send message {} to {}.\nMessage: {}".format(
            self.__name, self.__next_seq_num, addr, message))
        self.__next_seq_num = (self.__next_seq_num + 1) % SEQUENCE_LENGTH

        self.__check(addr)
        return True

    def begin_send(self, addr, messages: list):
        """向特定地址发送消息"""
        for message in messages:
            while not self.__send(addr, message):
                self.__check(addr)
        while True:
            self.__check(addr)
            flag = True
            i = self.__base
            while i != self.__next_seq_num:
                if not self.__ack_flags[i] and self.__timer[i] != -1:
                    flag = False
                    if time.time() - self.__timer[i] > TIME_LIMIT:
                        print("{} timeout on pkt {}.".format(self.__name, i))
                        self.__socket.sendto(self.__data_seq[i], addr)
                        print(
                            "{} resend message {} to {}.\nMessage: {}".format(
                                self.__name, i, addr,
                                self.__data_seq[i].decode()))
                        self.__timer[i] = time.time()
                i = (i + 1) % SEQUENCE_LENGTH
            if flag:
                break
        print("Sender {} end.".format(self.__name))
