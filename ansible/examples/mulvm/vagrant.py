#!/usr/bin/env python
# 使用Paramiko库解析vagrant ssh-config输出,需在Python2下运行
import subprocess
import paramiko

cmd = "vagrant ssh-config debian1"
p = subprocess.Popen(cmd.split(), stdout=subprocess.PIPE)
config = paramiko.SSHConfig()
config.parse(p.stdout)
result = config.lookup("debian1")
print result
