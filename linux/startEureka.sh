#!/bin/bash
#这里可替换为你自己的执行程序，其他代码无需更改
APP_NAME="/root/cloud-eureka-0.0.1-SNAPSHOT.jar"
#启动方法
start(){
	pid=`ps -ef|grep $APP_NAME|grep -v grep|grep -v start|awk '{print $2}'`
      	if [ "$pid" ]; then
		echo "$APP_NAME is already running. pid=$pid ."
	else
		nohup java -Xmx128m -Xms128m -jar $APP_NAME >/dev/null 2>&1 & 
		echo "$APP_NAME now is running. pid=$pid ."
	fi
}
#停止方法
stop(){
	pid=`ps -ef|grep $APP_NAME|grep -v grep|grep -v start.sh|awk '{print $2}'`
	if [ "$pid" ]; then
		kill -9 $pid
		echo "Pid:$pid stopped"
	else
		echo "$APP_NAME is not running"
	fi
}
#输出运行状态
status(){
	pid=`ps -ef|grep $APP_NAME|grep -v grep|grep -v start.sh|awk '{print $2}'`
	if [ "$pid" ]; then
		echo "$APP_NAME is running. Pid is ${pid}"
	else
		echo "$APP_NAME is NOT running."
	fi
}
#根据输入参数，选择执行对应方法，不输入则执行使用说明
case "$1" in
	start)
		start
		;;
	stop)
		stop
		;;
	status)
		status
		;;
	restart)
		stop
		sleep 5
		start
		;;
	*)
		echo "Usage:{start|stop|status|restart}"
		;;
esac
exit 0
