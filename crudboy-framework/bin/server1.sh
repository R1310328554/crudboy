#!/bin/bash




app='crudboy-framework.jar'
args='  -Xms2g -Xmx2g -Dspring.profiles.active=dev '
args='  -Xms2g -Xmx2g -Dspring.profiles.active=dev -Dyudao.demo=false  -Daj.captcha.water-mark=ASC_AI_BOT  -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=30505  '
cmd=$1
preArg='-cp yudao-module-yjgl-biz-1.8.0-snapshot.jar  '
preArg=''

echo ps -ef \| grep java \|grep $app


pid=`ps -ef|grep java|grep $app|awk '{print $2}'`


startup(){
  if [ -f  "yudao-module-yjgl-biz-1.8.0-snapshot.jar"  -a  -d "BOOT-INF/lib/" ]; then
      echo ==== mv -f yudao-module-yjgl-biz-1.8.0-snapshot.jar BOOT-INF/lib/ and rejar ===
      mv -f yudao-module-yjgl-biz-1.8.0-snapshot.jar BOOT-INF/lib/
      jar cf0M crudboy-framework.jar BOOT-INF META-INF org
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


