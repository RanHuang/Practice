import itertools as iter

words = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
r = iter.product(words, repeat = 3)
dict = open("dict.txt", "w")
for i in r:
    dict.write("".join(i))

dict.close()
