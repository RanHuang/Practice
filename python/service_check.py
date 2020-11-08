#!/usr/bin/env python3
import subprocess

if __name__ == "__main__":
    key_file="/Users/nick/.vagrant.d/insecure_private_key"
    host_ip="192.168.0.6"

    zk_home="/home/zookeeper/zookeeper"
    java_home="/opt/jdk/jdk1.8"

    zk_out_bytes = subprocess.check_output(
        'ssh -i {key_file} root@{host_ip} \''
        'export JAVA_HOME={java_home} '
        '&& export ZK_HOME={zk_home}'
        '&& export PATH=$JAVA_HOME/bin:$ZK_HOME/bin:$PATH '
        '&& zkServer.sh status'
        '\''.format(key_file=key_file, host_ip=host_ip,
                    java_home=java_home, zk_home=zk_home) , shell=True)
    print(zk_out_bytes.decode('utf-8'))
