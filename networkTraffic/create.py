#!/usr/bin/env python
# -*- coding: utf-8 -*-
import rrdtool
import time

cur_time = str(int(time.time()))
"""
 --step 300s (Data write frequency, one data every 5 minute)
DS:Data Source; COUNTER:increment; 600:heart rate; 0:minimum; U:unknown
[RRA:CF:xff:steps:rows] CF: AVERAGE, MAX, MIN
xff:0.5 if half values of PDP are unknown, the CDP is marked as unknown
store average value every 5 minutes(1*300s), the total number is 600(2.08days)
store average value every 30 minutes(6*300s), the total number is 700(2week, 14.58days)
store average value every 2 hours(24*300s), the total number is 775(2 months, 4.58days)
store average value every 24 hours(288*300s), the total number is 797(2years, 797days)
"""
rrd = rrdtool.create('NetworkFlow.rrd', '--step', '60', '--start', cur_time,
                     'DS:wlan0_in:COUNTER:600:0:U',
                     'DS:wlan0_out:COUNTER:600:0:U',
                     'RRA:AVERAGE:0.5:1:1440', #1 day
                     'RRA:AVERAGE:0.5:5:2016', #1 week
                     'RRA:AVERAGE:0.5:60:775', #1 month
                     'RRA:AVERAGE:0.5:720:797', # 1 year
                     'RRA:MAX:0.5:1:1440',
                     'RRA:MAX:0.5:5:2016',
                     'RRA:MAX:0.5:60:775',
                     'RRA:MAX:0.5:720:797',
                     'RRA:MIN:0.5:1:1440',
                     'RRA:MIN:0.5:5:2016',
                     'RRA:MIN:0.5:60:775',
                     'RRA:MIN:0.5:720:797')
if rrd:
    print(rrdtool.error())




