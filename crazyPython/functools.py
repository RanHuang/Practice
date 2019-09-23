import functools
import time

# reduce
print(functools.reduce(lambda x,y: x+y, range(6)))
print(functools.reduce(lambda x,y: x+y, range(6), 10))


# import sys   
# sys.setrecursionlimit(100000) # 设置递归深度
@functools.lru_cache(maxsize=50, typed=True)
def fib(n):
	if n<1:
		raise ValueError("参数需为正整数")
	if n == 1 or n == 2:
		return 1
	else:
		return fib(n-1) + fib(n-2)

start = time.time()
print(fib(40))
print(time.time() - start)
# 未加缓存前fib(40)执行时间: 23.612776041030884
# 增加缓存后fib(40)执行时间: 00014090538024902344


# @functools.wraps 使包装函数看上去就像被包装函数
def decorator(fun):
	"""函数decorator(fun)"""
	@functools.wraps(fun)
	def wrapper(*args, **kwds):
		print("execute inner()")
		fun(*args, **kwds)
	return wrapper

@decorator
def test(*args, **kwds):
	"""函数test()"""
	print("---actual execute test()")
	print(args)
	print(kwds)
	print("---actual execute test()")
test(1,2,3, a=1,b=2,c=3)
print(test.__name__)
print(test.__doc__)
