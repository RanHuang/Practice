安装pip：
    $ wget https://bootstrap.pypa.io/get-pip.py
    $ sudo python get-pip.py
    $ sudo pip install -U pip
使用pip安装软件包：
    $ sudo pip install ntplib

将Vim配置成Python IDE：
	$ curl -O https://raw.githubusercontent.com/vince67/v7_config/master/vim.sh
	$ bash vim.sh
l#########################################################
newton_raphson_method.py
    牛顿-拉夫逊方法，采用逐次逼近思想求解非线性方程组。
    如果一个值guess是一个多项式的近似根，那么guess-f(guess)/f'(guess)是一个更好的近似，其中f'是f的一阶导数。
    e.g: 求解方程的近似根
    寻找x，满足 x**2 - n 在(0, epsilon)

Directory: itcpup
	Introduction to Computation and Programming Using Python

Directory: pnpc
	Python Network Programming Cookbook
	《Python 网络编程攻略》

Directory: basicPython
	Beginning Python
	《Python基础教程》
