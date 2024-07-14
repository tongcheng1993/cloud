
#!/bin/bash

echo "nohup java -jar -Dspring.profiles.active=prod -Djasypt.encryptor.password=mytc"


nohup java -jar -Dspring.profiles.active=prod cloud-gateway-web-0.0.1-SNAPSHOT.jar >/dev/null 2>&1 &
nohup java -jar -Dspring.profiles.active=prod cloud-gateway-manage-0.0.1-SNAPSHOT.jar >/dev/null 2>&1 &
nohup java -jar -Dspring.profiles.active=prod cloud-server-sys-0.0.1-SNAPSHOT.jar >/dev/null 2>&1 &
nohup java -jar -Dspring.profiles.active=prod cloud-server-websocket-0.0.1-SNAPSHOT.jar >/dev/null 2>&1 &
nohup java -jar -Dspring.profiles.active=prod cloud-server-business-0.0.1-SNAPSHOT.jar >/dev/null 2>&1 &
