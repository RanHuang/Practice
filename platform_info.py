#!/usr/bin/python
# -*- coding: utf-8 -*-
"""
    Author: Nick Huang
    Version: 2016-05-11
    Description: 
"""

import platform

pythonVersion = platform.python_version()
uname = platform.uname();

print "Current python version is %s"%(pythonVersion)
print "uname: ", uname
