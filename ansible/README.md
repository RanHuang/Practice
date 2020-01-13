# 部署主机信息
inventory/hosts
## 虚拟机
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

# 部署服务组件
## JDK
1. 部署命令  
```
# 测试部署节点
ansible jdk -m ping
# 部署JDK1.8
ansible-playbook playbooks/jdk_install.yml
# 卸载JDK
ansible-playbook playbooks/jdk_uninstall.yml
```  
2. JDK软件包    
`jdk-8u231-linux-x64.tar.gz`     
3. 部署路径   
```
root@stretch:~# ll /opt/jdk/
lrwxrwxrwx 1 root root   21 Jan 13 03:01 jdk1.8 -> /opt/jdk/jdk1.8.0_231
drwxr-xr-x 7 uucp  143 4.0K Oct  5 10:13 jdk1.8.0_231
```
