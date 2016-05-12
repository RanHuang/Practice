#!/usr/bin/env python
# -*- coding: utf-8 -*-

# 不使用任何第三方库编写一个简单的SNTP客户端
import socket
import struct
import sys
import time

NTP_SERVER = "pool.ntp.org"
TIME1970 = 2208988800L

def sntp_client():
    client = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    data = '\x1b' + 47 * '\0'
    client.sendto(data, (NTP_SERVER, 123))
    data, address = client.recvfrom(1024)
    
    if data:
        print "Response received from:", address
        # print "Response: ", data
        t = struct.unpack('!12I', data)[10]
        t -= TIME1970
        print '\tTime = %s' % time.ctime(t)

if __name__ == '__main__':
    sntp_client()
