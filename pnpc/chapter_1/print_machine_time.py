#!/usr/bin/env python
# -*- coding: utf-8 -*-

# 建NTP客户端，向NTP服务器pool.ntp.org发起NTP请求，使用函数ctime()打印出时间
# NTP - Network Time Protocol
# $pip install ntplib
import ntplib
from time import ctime

def print_current_time():
    ntp_client = ntplib.NTPClient()
    response = ntp_client.request('pool.ntp.org')
    print ctime(response.tx_time)

if __name__ == '__main__':
   print_current_time() 
