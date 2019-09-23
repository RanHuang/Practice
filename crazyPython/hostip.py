import socket
# 获取本机IP地址-1
client = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
client.connect(('8.8.8.8', 80))
ip = client.getsockname()[0]
client.close()
print(ip)

# 获取本机IP地址-2
# print(socket.gethostname())
hostname = socket.getfqdn(socket.gethostname())
# print(hostname)
ip = socket.gethostbyname(hostname)
print(ip)