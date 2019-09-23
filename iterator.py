# coding:utf-8
import platform
import sys


class Fibs:
	"""docstring for Fibs"""
	def __init__(self, len):
		self._len = len
		self.first = 0
		self.second = 1

	def __next__(self):
		if self._len == 0:
			raise StopIteration
		self.first, self.second = self.second, self.first + self.second
		self._len -= 1
		return self.first

	def __iter__(self):
		return self

fib = Fibs(10)
for item in fib:
	print(item)

it=iter([2,4,6,8,10])
for i in it:
	print(i)
