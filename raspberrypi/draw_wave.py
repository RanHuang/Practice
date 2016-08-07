#!/usr/bin/env python
# -*- coding: utf-8 -*-
import Tkinter
import math
"""
Tkinter模块（“Tk接口”）是Python的标准Tk GUI工具包的接口，是Python的内置模块
"""


class Wave:
    def __init__(self, points=400, formula=None):
        self.data = [0.0]*points
        self.points = points
        if formula:
            for i in range(points):
                x = i*math.pi*2/points
                self.data[i] = eval(formula)

    def __add__(self, other):
        target = Wave(points=self.points)
        for i in range(self.points):
            target.data[i] = self.data[i] + other.data[i]
        return target

    def __mul__(self, other):
        target = Wave(points=self.points)
        if type(other) == type(1) or type(other) == type(1.1):
            for i in range(self.points):
                target.data[i] = self.data[i] * other
        else:
            for i in range(self.points):
                target.data[i] = self.data[i] * other.data[i]
        return target

    def __sub__(self, other):
        target = Wave(self.points)
        for i in range(self.points):
            target.data[i] = self.data[i] - other.data[i]
        return target

    # 求积分
    def integral(self):
        ans = 0.0
        for pt in self.data:
            ans += pt
        return ans*2*math.pi/self.points

    def plotOne(self, canvas, pixWidth, scale, offset):
        for x in range(pixWidth):
            y = offset - self.data[x]*scale
            if x:
                canvas.create_line(x-1, yprev, x, y)
            yprev = y

    def plot(self, title="??", pixHeight=None, maxY=None, others=[]):
        if not pixHeight:
            pixHeight = self.points*2/3
        pixWidth = self.points

        if not maxY:
            maxY = max(max(self.data), -min(self.data))

        offset = pixHeight/2
        scale = offset/maxY

        window = Tkinter.Tk()
        window.title(title)
        canvas = Tkinter.Canvas(window, width=pixWidth, height=pixHeight)
        # create zero line
        canvas.create_line(0, offset, pixWidth, offset)
        canvas.pack()

        self.plotOne(canvas, pixWidth, scale, offset)
        for i in range(len(others)):
            others[i].plotOne(canvas, pixWidth, scale, offset)
        window.mainloop()

    def fft(self):
        work = self * 1  # harmonics 谐波
        for harm in range(1, 10):
            formula = "-math.sin(%d*x)" % harm
            area = (Wave(formula=formula)*work).integral()
            amplitude = area/-math.pi  # 振幅
            if amplitude > 0.000001:
                print "Harmonic=", harm, " Amplitude=%.04f" % amplitude
            takeAway = Wave(formula="math.sin(%d*x)*%f" % (harm, amplitude))
            work = work - takeAway

def test():
    p1 = Wave(formula="math.sin(x)/1")
    p3 = Wave(formula="math.sin(3*x)/3")
    p5 = Wave(formula="math.sin(5*x)/5")
    mys = p1+p3+p5
    mys.fft()

    a = Wave(formula="math.sin(x)")
    b = Wave(formula=".5*math.sin(2*x)")
    a.plot(maxY=1.2, pixHeight=200, title="Sin(x) and 0.5*sin(2*x)", others=[b])

    c = a + b
    c.plot(maxY=1.5, pixHeight=200, title="Sin(x)+0.5*Sin(2*x)")

if __name__ == '__main__':
    test()
