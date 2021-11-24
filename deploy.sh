#!/bin/bash
# 发布服务器地址
host=101.200.177.3
# ssh 端口
port=22
# 发布服务器用户名
user=root
# 源文件夹
source_dir=./target

target_dir=/dev/jkcrm
app_name=blade-api.jar

# some colors
NC='\033[0m'
GREEN='\033[0;32m'
PURPLE='\033[0;35m'
RED='\033[0;31m'

function ec() {
    COLOR=${NC}
    END_COLOR=${NC}
    if [ -n "$2" ]; then
        COLOR="$2"
    fi
    if [ -n "$3" ]; then
        END_COLOR="$3"
    fi
    echo -e "${COLOR}$1${END_COLOR}"
}

ec "clean and package" ${PURPLE}
mvn clean package

cd $source_dir

ec "copy ${app_name} to ${host}:${target_dir}" ${PURPLE} ${PURPLE}
scp -P ${port} ${app_name} ${user}@${host}:${target_dir}
if [ "$?" != "0" ]; then
  ec "文件上传失败" ${RED}
  exit 1
fi

ec "start to deploy" ${PURPLE}
ssh -p ${port} ${user}@${host} "
cd ${target_dir}/back
mv ${app_name} ${app_name}.bak
mv ../${app_name} ./
kill -9 \$(cat app.pid)
nohup java -jar -Xmx500m ${app_name} &
echo \$! > app.pid ;
"

ec "OK" ${GREEN}


