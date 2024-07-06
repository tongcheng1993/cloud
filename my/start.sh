
#!/bin/bash

echo "hello zs  "



cd /etc/minio
MINIO_ROOT_USER=minioadmin MINIO_ROOT_PASSWORD=minioadmin nohup ./minio server /mnt/data --console-address ":9001" >/dev/null 2>&1 &


cd /etc/nacos
sh bin/startup.sh -m standalone


cd /home/java
nohup java -jar xxx.jar >/dev/null 2>&1 & 