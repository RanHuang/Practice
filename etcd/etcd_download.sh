#!/bin/bash
# URL: https://github.com/etcd-io/etcd/releases
set -eux

ETCD_VER=v3.3.18
# choose either URL
GOOGLE_URL=https://storage.googleapis.com/etcd
GITHUB_URL=https://github.com/etcd-io/etcd/releases/download
DOWNLOAD_URL=${GOOGLE_URL}

# Linux
if [ ! -f etcd-${ETCD_VER}-linux-amd64.tar.gz ];then
    curl -L ${DOWNLOAD_URL}/${ETCD_VER}/etcd-${ETCD_VER}-linux-amd64.tar.gz -o etcd-${ETCD_VER}-linux-amd64.tar.gz
fi
# MacOS
if [ ! -f etcd-${ETCD_VER}-darwin-amd64.zip ];then
    curl -L ${DOWNLOAD_URL}/${ETCD_VER}/etcd-${ETCD_VER}-darwin-amd64.zip -o etcd-${ETCD_VER}-darwin-amd64.zip
fi

# mkdir -p /tmp/etcd-download-test
# tar xzvf etcd-${ETCD_VER}-linux-amd64.tar.gz -C /tmp/etcd-download-test --strip-components=1
# /tmp/etcd-download-test/etcd --version
# /tmp/etcd-download-test/etcdctl version
# rm -rf /tmp/etcd-download-test

# rm -f etcd-${ETCD_VER}-linux-amd64.tar.gz
