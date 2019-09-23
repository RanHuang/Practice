# coding:utf-8
import platform
import sys

# 自定义迭代器，需实现iter、next方法
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

# 将列表、元组转换为迭代器
it=iter([2,4,6,8,10])
for i in it:
	print(i)

# 扩展字典
class ValueDict(dict):
	"""docstring for ValueDict"""
	def __init__(self, *args, **kwargs):
		super().__init__(*args, **kwargs)

	# 根据value获取key值列表
	def getkeys(self, value):
		result = []
		for k, v in self.items():
			if v == value:
				result.append(k)
		return result
tDict = ValueDict(语文=90, 数学=96, 英语=90, 物理=94, 化学=96)
print(tDict.getkeys(90))
tDict['历史'] = 94
print(tDict.getkeys(94))
		