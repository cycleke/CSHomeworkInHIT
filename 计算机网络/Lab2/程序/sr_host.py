# -*- coding: utf-8 -*-
"""SR 协议的主机端"""

from threading import Thread
from sr_sender import SrSender
from sr_receiver import SrReceiver


class SrHost:
    """SR 协议的主机端"""
    def __init__(self, name: str, in_addr, out_addr):
        self.name = name
        self.__sender = SrSender(name, out_addr)
        self.__receiver = SrReceiver(name, in_addr)

    def receive(self, addr):
        """接收来自 addr 的消息"""
        self.__receiver.begin_receive(addr)

    def send(self, addr, messages: list):
        """向特定地址发送消息"""
        self.__sender.begin_send(addr, messages)


def main():
    """测试代码"""
    alice_in, alice_out = ("", 19267), ("", 19268)
    host_alice = SrHost("Alice", alice_in, alice_out)
    bob_in, bob_out = ("", 19269), ("", 19270)
    host_bob = SrHost("Bob", bob_in, bob_out)

    Thread(target=host_bob.receive, args=(alice_out, )).start()
    Thread(target=host_alice.send, args=(bob_in, ["Alice", "EOF"])).start()
    Thread(target=host_alice.receive, args=(bob_out, )).start()
    Thread(target=host_bob.send,
           args=(alice_in,
                 list(map('Bob{} '.format, range(10))) + ["EOF"])).start()


if __name__ == '__main__':
    main()
