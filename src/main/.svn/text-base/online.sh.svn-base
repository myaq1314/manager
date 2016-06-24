#!/bin/bash
if [ $# -ne 0 ];then
        echo "Pls input nothing!!"
        exit 2
fi
 
# 上次上线脚本可以从日志中获取
LAST_VERSION=`tail -1 online.log`
echo "上次上线版本为：$LAST_VERSION" 
 
# 本次上线版本手动输入，输入的即为第一部分介绍的release到产品库的四位版本号2.0.0.13424
read -p "请输入本次上线版本:" version
echo "--------------------------------" > online.log
 
# 这里就是执行上线了，archer可以用./noah-deploy.pl代替，目前百度所有机器都默认安装了archer
# buildid随便输入一个数字即可，一般可以用此次构建编号
# serverlist.yaml和noah-deploy.yaml即为我们配置的脚本了
# module即为scm上看到的模块，RD申请scm模块后产品代码都在该模块下
# version即刚刚手动输入的2.0.0.13424
# control为启停tomcat的脚本，等会介绍
# token为OP申请的，archer门户有介绍，一会介绍
# 其他都无需更改
archer --buildid=001 --serverlist=/home/work/archer/asset/noahdes/serverlist.des -f /home/work/archer/asset/noahdes/deploy.des --scm --module=fpu/activty-service/asset --version=$version --buildplatform=64 --control='bin/asset_control' --fullamount --token='pQkLFPD50eAZrcMnOdhsCcflo1wvKSYB' |tee -a online.log|grep PROCESS_SERVER_URL
date +%F/%T >> online.log
echo $version >> online.log
