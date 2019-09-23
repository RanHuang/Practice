# str 追求输出内容的可读性，适合终端输出
# repr 追求输出内容的明确性，包含对象的数据类型信息，适合开发、调试

print(str(123))
print(repr(123))
print(str('123'))
print(repr('123'))
print("========")
from datetime import datetime
dt = datetime.now()
print(dt)
print(str(dt))
print(repr(dt))