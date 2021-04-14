 #!/bin/sh
 
## 应用选项
source /etc/profile
 
export BUILD_ID=DontKillMe
 
CUR_DIR=$(pwd)
 
APP_DIR=/home/services
 
APP_NAME=micro-service-gateway-0.0.1-SNAPSHOT
 
APP_PORT=8074
 
JVM_OPTIONS="-Xms256m -Xmx256m"
 
JAR_NAME=$APP_NAME\.jar
 
 cd $APP_DIR
 
## 检查进程是否存在
 PID=$(ps -ef | grep -w "$APP_NAME" | grep -v "grep" | awk '{print $2}')
  if [ "$PID" == "" ]; then
    echo "应用：$APP_NAME 端口：$APP_PORT 进程不存在。"
            else
    echo "应用：$APP_NAME 端口：$APP_PORT 存在进程：$PID"
    kill -15 $PID
    sleep 3
            ## 如果仍然存在则强行停止
            CHECK_PID=$(ps -ef | grep -w "$APP_NAME" | grep -w "java"| grep -w "$APP_PORT" | grep -v "grep" | awk '{print $2}')
            if [ "$CHECK_PID" == "" ]; then
    echo "应用：$APP_NAME 端口：$APP_PORT 进程：$PID 停止成功。"
            else
    echo "应用：$APP_NAME 端口：$APP_PORT 进程：$PID 强行停止！"
    kill -9 $PID
            fi
    fi
 
    sleep 1
 
    echo "应用：$APP_NAME 端口：$APP_PORT 正在启动..."
    nohup java $JVM_OPTIONS -jar $APP_DIR/$JAR_NAME --server.port=$APP_PORT >/home/services/logs/gateway.txt &
    echo "应用启动命令已执行，稍候请检查服务是否可用。"
 
    cd $CUR_DIR