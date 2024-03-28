#!/bin/bash


# mysqladmin -u root -p root crudboy  --default-character-set=utf8mb4   --default-collation=utf8mb4_general_ci
# mysql -u root -proot -D crudboy < /home/lanmao/llm/py/_localhost-2024_03_08_11_41_30-dump.sql
# service nginx restart
# service nginx status 
# service nginx stop   
 UPDATE infra_file  SET url = REPLACE(url, 'http://127.0.0.1:48080/', 'https://87906a3f97.goho.co/')  WHERE url LIKE 'http://127.0.0.1:48080/%';
 UPDATE infra_file  SET url = REPLACE(url, 'https://87906a3f97.goho.co/', 'http://127.0.0.1:48080/')  WHERE url LIKE 'https://87906a3f97.goho.co/%';
  select * from infra_file  ;
  
  
   sudo apt update
   sudo apt install software-properties-common
   
      sudo add-apt-repository ppa:deadsnakes/ppa
	  
	     sudo apt update
		 
		 
		    sudo apt install python3.9
			
   
   
app='crudboy-server.jar'
args='  -Xms2g -Xmx2g -Dspring.profiles.active=dev '
args='  -Xms2g -Xmx2g -Dspring.profiles.active=dev -Dyudao.demo=false  -Daj.captcha.water-mark=ASC_AI_BOT  -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=30505  '
cmd=$1
preArg='-cp crudboy-module-asc-biz-1.8.0-snapshot.jar  '
preArg=''

echo ps -ef \| grep java \|grep $app


pid=`ps -ef|grep java|grep $app|awk '{print $2}'`


startup(){
  if [ -f  "crudboy-module-asc-biz-1.8.0-snapshot.jar"  -a  -d "BOOT-INF/lib/" ]; then
      echo ==== mv -f crudboy-module-asc-biz-1.8.0-snapshot.jar BOOT-INF/lib/ and rejar ===
      mv -f crudboy-module-asc-biz-1.8.0-snapshot.jar BOOT-INF/lib/
      jar cf0M crudboy-server.jar BOOT-INF META-INF org
  fi


  nohup java $preArg -jar $args $app  > all.log  2>&1    &
 
  echo ==== java $preArg  -jar $args $app   > all.log    2>&1
 tail -f  all.log
}


if [ ! $cmd ]; then
  echo "Please specify args 'start|restart|stop'"
  exit
fi

if [ $cmd == 'start' ]; then
  if [ ! $pid ]; then
    startup
  else
    echo "$app is running! pid=$pid"
  fi
fi

if [ $cmd == 'restart' ]; then
  if [ $pid ]
    then
      echo "$pid will be killed after 3 seconds!"
      sleep 3
      kill -9 $pid
  fi
  sleep 2
  startup
fi

if [ $cmd == 'stop' ]; then
  if [ $pid ]; then
    echo "$pid will be killed after 3 seconds!"
    sleep 3
    kill -9 $pid
  fi
  echo "$app is stopped"
fi


