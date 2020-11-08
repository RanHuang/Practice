#!/usr/bin/env bash
#########################################################################
# Script Name: kafka_manage.sh
# Script Description: 分布式Kafka管理脚本
# Script Author:
# Script Date: 📅
#########################################################################
set -euo pipefail
# set -x
echo "$*"

hosts=("192.168.0.6" "192.168.0.7" "192.168.0.8")
key_file=~/.vagrant.d/insecure_private_key

mill=$(date "+%N")
tdate=$(date "+%Y-%m-%d %H:%M:%S,${mill:0:3}")
echo [$tdate] INFO [Kafka Cluster] begins to execute the $1 operation.

while [ $# -gt 0 ]; do
    case "$1" in
        -h|-help)
            echo "$package - attempt to capture frames"
            echo " "
            echo "$package [options] application [arguments]"
            echo " "
            echo "options:"
            echo "-h, -help                show brief help"
            echo "-hosts                   kafka broker ip"
            echo "-op                      etcd addresses"
            exit 0
            ;;
        -hosts)
            shift
            if test $# -gt 0; then
                export name=$1
            else
                echo "no host specified"
                exit 1
            fi
            shift;;
        -op)
            shift
            if test $# -gt 0; then
                export datadir=$1
            else
                echo "no operation specified"
                exit 1
            fi
            shift;;
        *) break;;
    esac
done
