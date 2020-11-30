# -*- coding: utf-8 -*-
import proxy_server, threading

server = proxy_server.ProxyServer()
while True:
    proxy_socket, address = server.main_socket.accept()
    # print(address)
    threading.Thread(target=server.proxy_connect,
                     args=(proxy_socket, address)).start()
