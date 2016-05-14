#!/usr/bin/env python
# -*- coding:utf-8 -*-
import time
import psutil
import rrdtool


input_traffic = psutil.network_io_counters()[1]
output_traffic = psutil.network_io_counters()[0]

start_time = int(time.time())

update = rrdtool.updatev('/home/nick/program/networkTraffic/NetworkFlow.rrd',
                         '%s:%s:%s' % (str(start_time), str(input_traffic), str(output_traffic)))
#使用crontab执行 update.py 需要指定rrd文件的完整路径，环境变量发生变化
#print("Input: " + str(input_traffic))
#print("Output:" + str(output_traffic))
print(update)
