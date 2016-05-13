#! /usr/bin/env python3.4
# -*- coding: utf-8 -*-
"""
$ sudo apt-get install python3-tk
$ sudo pip install turtle
"""
import time
import turtle

box_size = 200
caught = False
score = 0

#functions that are called on keypresses
def up():
    mouse.forward(10)
    checkbound()
def left():
    mouse.left(45)
    checkbound()
def right():
    mouse.right(45)
    checkbound()
def down():
    mouse.backward(10)
    checkbound()

def quitTurtles():
    window.bye()

#老鼠不能跑出box_size圈定的区域
def checkbound():
    global box_size
    if mouse.xcor() > box_size:
        mouse.goto(box_size, mouse.ycor())
    if mouse.xcor() < -box_size:
        mouse.goto(-box_size, mouse.ycor())
    if mouse.ycor() > box_size:
        mouse.setpos(mouse.xcor(), box_size)
    if mouse.ycor() < -box_size:
        mouse.setpos(mouse.xcor(), -box_size)

window = turtle.Screen()
#使用两个鼠标标志分别表示猫和鼠
mouse = turtle.Turtle()
cat = turtle.Turtle()
cat.color("red")
mouse.penup()
mouse.goto(100, 100)

# add key listener
window.onkey(up, "Up")
window.onkey(down, "Down")
window.onkey(left, "Left")
window.onkey(right, "Right")
window.onkey(quitTurtles, "Escape")

#difficulty = 2
difficulty = window.numinput("Difficulty",
        "Enter a difficulty from easy(1) to hard(5)",
        1, minval = 1, maxval = 5)
window.listen()

#main loop
def test():
    global caught
    global score
    while not caught:
        cat.setheading(cat.towards(mouse))
        cat.forward(8+difficulty)
        score = score + 1
        if cat.distance(mouse) < 5:
            caught = True
        time.sleep(0.2 - (0.01*difficulty))

    window.textinput("Game Over!", 
        "Well Done. You scored: " + str(score*difficulty))
    window.bye()

if __name__ == "__main__":
    test()

