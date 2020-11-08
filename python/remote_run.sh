#!/usr/bin/env bash

# 将Python脚本拷贝到远程主机执行
#set -x
set -euo pipefail

cur_dir=$(cd $(dirname $0); pwd)
echo $cur_dir

key=""
host=""
script=""
while [ $# -gt 0 ]; do
  case "$1" in
    -h|--help)
        echo " "
        echo "usage: $0 [--key arg] --host arg --script arg"
        echo " "
        echo "options:"
        echo "-h,--help              show brief help"
        echo "--key                  key file for ssh"
        echo "--host                 remote host"
        echo "--script               python script"
        exit 0
        ;;
    --key) shift
        if test $# -gt 0;then
            key=$1
        else
            echo "no key file specified"
            exit 1
        fi
        shift;;
    --host) shift
        if [ $# -gt 0 ];then
            host=$1
        else
            echo "no host specified"
            exit 1
        fi
        shift;;
    --script) shift
        if test $# -gt 0;then
            script=$1
        else
            echo "no python script specified"
            exit 1
        fi
        shift;;
    *) break
      ;;
  esac
done

remote_cmd="cd ~ && echo; ./$script; rm $script"
if [ -z "$key" ]; then
    scp $script ${host}:~/
    ssh $host bash -c $remote_cmd
else
    scp -i $key $script ${host}:~/
    ssh -i $key $host bash -c $remote_cmd
fi
