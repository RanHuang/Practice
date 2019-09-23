# -*- coding: utf-8 -*-
"""
    Author: Nick Huang
    Version: 2016-05-11
    Description:
"""
import platform
import sys

def showPlatformInfo():
    pythonVersion = platform.python_version()
    print("Current python version is %s"% (pythonVersion))
    
    print("Sys Version: %s" % sys.version)

    uname = platform.uname()
    print("uname: ", uname)

    arch = platform.architecture()
    print("Architecture: ", arch)

    machine = platform.machine()
    print("Machine: ", machine)

    node = platform.node()
    print("Node: ", node)

    print("PlatformInfo: ", platform.platform())
    print("Processor: ", platform.processor())
    print("System: ", platform.system())
    print("Version: ", platform.version())

if __name__ == '__main__':
    showPlatformInfo()
