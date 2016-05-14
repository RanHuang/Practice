#!/usr/bin/env python
# -*- coding:utf-8 -*-
import time
import rrdtool

title = "Network traffic flow (" + time.strftime('%Y-%m-%d', time.localtime(time.time())) + ")"
# “MINUTE：12” -- 每隔12分钟绘制一根次要格线
# “HOUR：1” -- 每隔1小时绘制一根主要格线
# “HOUR：1” -- 每隔1小时标记一个label标签
# “0：%H” -- 0表示数字对齐格线，%H表示标签以小时显示
rrdtool.graph("NetworkFlow.png", "--start", "-1d", "--vertical-label=Bytes/s",
              "--x-grid", "MINUTE:12:HOUR:1:HOUR:1:0:%H",
              "--width", "650", "--height", "230", "--title", title,
              "DEF:inoctets=NetworkFlow.rrd:wlan0_in:AVERAGE",
              "DEF:outoctets=NetworkFlow.rrd:wlan0_out:AVERAGE",#出流量DS和CF
              "CDEF:total=inoctets,outoctets,+", #通过CDEF计算总流量
              "LINE1:total#FF8833:Total Traffic", #线条绘制总流量
              "AREA:inoctets#00FF00:In Traffic", #面积表示人流量
              "LINE1:outoctets#0000FF:Out Traffic", #线条表示出流量
              "HRULE:6144#FF0000:Alarm value\\r", #绘制水平警戒线，阈值为6.1K
              "CDEF:inbits=inoctets,8,*",
              "CDEF:outbits=outoctets,8,*",
              "COMMENT:\\r",
              "COMMENT:\\r", #网格下方输出换行符
              "GPRINT:inbits:AVERAGE:Avg In Traffic\: %6.2lf %Sbps",
              "COMMENT:  ",
              "GPRINT:inbits:MAX:Max In Traffic\: %6.2lf %Sbps",
              "COMMENT:  ",
              "GPRINT:inbits:MIN:Min In Traffic\: %6.2lf %Sbps\\r",
              "COMMENT:  ", #网格下方输出换行符
              "GPRINT:outbits:AVERAGE:Avg Out Traffic\: %6.2lf %Sbps",
              "COMMENT:  ",
              "GPRINT:outbits:MAX:Max Out Traffic\: %6.2lf %Sbps",
              "COMMENT:  ",
              "GPRINT:outbits:MIN:Min Out Traffic\: %6.2lf %Sbps\\r")
