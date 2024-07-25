
#!/bin/bash

echo "hello zs  "



cd /etc/minio
MINIO_ROOT_USER=minioadmin MINIO_ROOT_PASSWORD=minioadmin nohup ./minio server /mnt/data --console-address ":9001" >/dev/null 2>&1 &


cd /etc/nacos
sh bin/startup.sh -m standalone

systemctl start elasticsearch


-Xms512m -Xmx512m -Xmn256m
-XX:+UseConcMarkSweepGC -XX:+UseCMSCompactAtFullCollection
-XX:NewSize=128m -XX:MaxNewSize=256m -XX:NewRatio=2
-Dspring.config.additional-location=file:./conf/ 
-Dspring.profiles.active=prod


cd /home/java


nohup java -jar -Xms512m -Xmx512m -Xmn256m  -Dspring.profiles.active=prod cloud-server-manage-0.0.1-SNAPSHOT.jar >/dev/null 2>&1 &
nohup java -jar -Xms512m -Xmx512m -Xmn256m  -Dspring.profiles.active=prod cloud-server-sys-0.0.1-SNAPSHOT.jar >/dev/null 2>&1 &
nohup java -jar -Xms512m -Xmx512m -Xmn256m  -Dspring.profiles.active=prod cloud-server-websocket-0.0.1-SNAPSHOT.jar >/dev/null 2>&1 &
nohup java -jar -Xms512m -Xmx512m -Xmn256m  -Dspring.profiles.active=prod cloud-server-business-0.0.1-SNAPSHOT.jar >/dev/null 2>&1 &
nohup java -jar -Xms512m -Xmx512m -Xmn256m  -Dspring.profiles.active=prod cloud-gateway-manage-0.0.1-SNAPSHOT.jar >/dev/null 2>&1 &
nohup java -jar -Xms512m -Xmx512m -Xmn256m  -Dspring.profiles.active=prod cloud-gateway-web-0.0.1-SNAPSHOT.jar >/dev/null 2>&1 &