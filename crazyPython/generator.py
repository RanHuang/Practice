def genIn(num, step):
	sum = 0
	for i in range(0, num):
		sum += i*step
		yield sum
gen = genIn(10, 2)
print(next(gen))
for val in gen:
	print(val)

print(list(genIn(10, 2)))
print(tuple(genIn(10, 2)))

def genSquare(val):
	i = 0
	out_val = None
	while True:
		out_val = (yield out_val**2) if out_val is not None else (yield i**2)
		if out_val is not None:
			print("===Out Value==%d" % out_val)
		else:
			i += 1
		

genS = genSquare(3)
# print(genS.send(None))
print(next(genS))
print(next(genS))
print(genS.send(7))
print(next(genS))
print(next(genS))
print(next(genS))
print(next(genS))
print(next(genS))
print(next(genS))