# -*- coding: utf-8 -*-

import os, time
import socket
import requests
import yaml, re


class ProxyServer:
    def __init__(self,
                 address=("127.0.0.1", 19267),
                 cache_dir="cache/",
                 config_file="config.yaml"):
        self.address = address
        self.main_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        self.main_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

        self.MAX_LISTEN = 5
        self.main_socket.bind(address)
        self.main_socket.listen(self.MAX_LISTEN)
        print("[INFO] Start Proxy Server on ", address[0], address[1])

        self.BUFFER_SIZE = 1024
        self.__cache_dir = cache_dir
        self.__make_cache_dir()

        with open(config_file) as file:
            config = yaml.load(file, Loader=yaml.FullLoader)
            self.ban_ip_list = config["ban_user"]
            self.ban_web_list = config["ban_web"]
            self.fishing_dict = config["fishing"]

    def __make_cache_dir(self):
        """建立缓存目录
        """
        if not os.path.exists(self.__cache_dir):
            os.mkdir(self.__cache_dir)

    def _read_from_cache(self, url):
        """读取缓存文件
        """
        from urllib.parse import urlparse
        parsed = urlparse(url)
        path = (parsed.hostname + parsed.path).rstrip('/').replace('/', '-')
        cache_file = self.__cache_dir + path

        if os.path.exists(cache_file):
            last_time = os.stat(cache_file).st_mtime
            headers = {
                'If-Modified-Since':
                time.strftime('%a, %d %b %Y %H:%M:%S GMT',
                              time.gmtime(last_time))
            }
            response = requests.get(parsed.geturl(), headers=headers)
            if response.status_code == 304:
                # no need to modify
                print("[CACHE INFO] Read from file", cache_file)
                with open(cache_file, 'rb') as f:
                    data = f.read()
                return True, data
        return False, cache_file

    def ban_ip(self, ip):
        """判断 IP 地址是否为需过滤的 IP 地址
        """
        return self.ban_ip_list and any(
            [re.match(regex, ip) for regex in self.ban_ip_list])

    def ban_hostname(self, hostname):
        """判断主机名是否为需要过滤的主机命
        """
        return self.ban_web_list and any(
            [re.match(regex, hostname) for regex in self.ban_web_list])

    def fishing(self, hostname):
        """返回主机名对应的引导地址
        """
        return self.fishing_dict and self.fishing_dict.get(hostname, None)

    def proxy_connect(self, proxy_socket: socket.socket, address):
        """进行代理连接
        """
        message = proxy_socket.recv(self.BUFFER_SIZE).decode('utf-8', 'ignore')
        print(message)

        # 将报文进行划分
        headers = message.split('\r\n')

        # 获取目的服务器的主机名和端口
        hostname, port = None, 80
        for header in headers:
            if header[:4].lower() == 'host':
                arr = header[6:].split(':')
                hostname = arr[0]
                if len(arr) == 2:
                    port = int(arr[1])
                break

        if not hostname:
            print("[ERROR] Can't parse the hostname from headers.")
            print("[ERROR] Full Message: ", message)
            proxy_socket.close()
            return

        if self.ban_hostname(hostname):
            print("[REJECT HOSENAME]", hostname)
            response_line = "HTTP/1.1 404 Not Found\r\n"
            response_header = "Server:PWS/1.1\r\nother:ok\r\nContent-Type:text/html;charset=utf-8\r\n"
            response_body = "<center>该网站无法访问</center>"
            response_data = (response_line + response_header + "\r\n" +
                             response_body).encode('utf-8', 'ignore')
            proxy_socket.sendall(response_data)
            return

        if self.ban_ip(address[0]):
            print("[REJECT USER]", address[0])
            response_line = "HTTP/1.1 404 Not Found\r\n"
            response_header = "Server:PWS/1.1\r\nother:ok\r\nContent-Type:text/html;charset=utf-8\r\n"
            response_body = "<center>你被禁止访问网络</center>"
            response_data = (response_line + response_header + "\r\n" +
                             response_body).encode('utf-8', 'ignore')
            proxy_socket.sendall(response_data)
            return

        # 解析出 URL 地址
        request_line = headers[0].strip().split()
        url = request_line[1].rstrip('\\')
        from urllib.parse import urlparse
        parsed = urlparse(url)

        if not parsed.scheme:
            parsed = parsed._replace(scheme='http')

        url = parsed.geturl()

        # 将目的主机名替换为钓鱼网址
        fish_hostname = self.fishing(hostname)
        if fish_hostname:
            print("[FISHING]", hostname, fish_hostname)
            proxy_socket.sendall(
                requests.get(url.replace(hostname, fish_hostname, 1)).content)
            proxy_socket.close()
            return

        # 读取缓存
        flag, cache_file = self._read_from_cache(url)
        if flag:
            # 存在对应的缓存文件
            print("[CACHE] Get {} from cache file.".format(url))
            proxy_socket.sendall(cache_file)
        else:
            # 从目的主机接收报文
            print("[INFO] Try to connet to {}:{}".format(hostname, port))
            out_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
            out_socket.connect((hostname, port))
            out_socket.sendall(message.encode())
            file = open(cache_file, 'wb')
            # busy loop
            while True:
                buffer = out_socket.recv(self.BUFFER_SIZE)
                if not buffer:
                    file.close()
                    out_socket.close()
                    break
                file.write(buffer)
                proxy_socket.sendall(buffer)
            proxy_socket.close()
