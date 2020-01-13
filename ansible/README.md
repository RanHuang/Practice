# 虚拟机
1. 配置文件`inventory/Vagrantfile`  
2. 操作命令  
```
cd inventory
# 启动虚拟机
vagrant up
# 关闭虚拟机
vagrant halt
# 删除虚拟机
vagrant destroy
```
# 主机信息
inventory/hosts
# 服务组件部署
## JDK
```
ansible jdk -m ping
```
