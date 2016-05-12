#!/usr/bin/env python
# -*- coding: utf-8 -*-

import socket

# 设定并获取默认的套接字超时时间(socket库的属性默认值)
def test_socket_timeout():
    # 创建 socket 对象
    # Address Family: AF_INET (TCP/IP)
    # socket type: SOCK_STREAM SOCK_DGRAM
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    print "Default socket timeout: %s" %s.gettimeout()

    # None: cancel the socket timeout
    # 处理阻塞式套接字时使用； 参数为 second（非负浮点数）
    s.settimeout(100)
    print "Current socket timeout: %s" %s.gettimeout()

def test():
    print __name__
    test_socket_timeout()

if __name__ == '__main__':
    test()
