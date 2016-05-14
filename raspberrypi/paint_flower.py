#! /usr/bin/env python3.4
# -*- coding: utf-8 -*-
"""
$ sudo apt-get install python3-tk
$ sudo pip install turtle
"""
import turtle

def test():
    window = turtle.Screen()
    #创建Turtle对象
    babbage = turtle.Turtle()
    #draw a line
    # turn left degree
    babbage.color("green", "black")
    babbage.left(90)
    babbage.forward(100)
    babbage.right(90)
    #draw a cirlce
    #(color1-pen, color2-fill)
    babbage.color("black", "black")
    babbage.begin_fill()
    babbage.circle(10)
    babbage.end_fill()

    # draw petals
    for i in range(0, 23):
        if babbage.color() == ("red", "black"):
            babbage.color("orange", "black")
        elif babbage.color() == ("orange", "black"):
            babbage.color("yellow", "black")
        else:
            babbage.color("red", "black")
        babbage.left(15)
        babbage.forward(50)
        babbage.left(157)
        babbage.forward(50)
    #隐藏鼠标
    babbage.hideturtle()

    window.exitonclick()

if __name__ == "__main__":
    test()

