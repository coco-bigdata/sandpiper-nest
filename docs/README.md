```shell
cd sandpiper-web/
mvn package
mvn clean install

https://mvnrepository.com/artifact/oracle/ojdbc6/11.2.0.3
wget --no-check-certificate http://www.datanucleus.org/downloads/maven2/oracle/ojdbc6/11.2.0.3/ojdbc6-11.2.0.3.jar
mvn install:install-file -Dfile=ojdbc6-11.2.0.3.jar -DgroupId=com.oracle -DartifactId=ojdbc6 -Dversion=11.2.0.3 -Dpackaging=jar

mvn install:install-file -Dfile=sqljdbc4.jar -DgroupId=com.microsoft.sqlserver -DartifactId=sqljdbc4 -Dversion=4.0 -Dpackaging=jar

cd build/
tar -zxvf web-2.1.2.tar.gz
cd web-2.1.2
./bin/install.sh
./packages/admin/bin/flinkx-admin.sh start
tail -f packages/admin/bin/console.out

cd docker

sudo docker-compose build
sudo docker-compose down
sudo docker-compose up
sudo docker-compose up -d

sudo docker-compose up sandpiper-mysql80
sudo docker-compose up -d sandpiper-mysql80
sudo docker-compose stop sandpiper-mysql80

sudo docker-compose up sandpiper
sudo docker-compose up -d sandpiper
sudo docker-compose stop sandpiper
sudo docker-compose rm sandpiper

mysql -h127.0.0.1 -uroot -p
root
create database flinkx_web default character set utf8mb4 collate utf8mb4_unicode_ci;
use flinkx_web;
create user 'flinkx_web'@'127.0.0.1' identified by 'flinkx_web123456';
grant all privileges on flinkx_web.* to 'flinkx_web'@'127.0.0.1';
flush privileges;

mysql -h127.0.0.1 -uroot -p flinkx_web < sandpiper-web/bin/db/flinkx_web.sql

sudo docker network create --subnet=172.20.0.0/16 sandpiper-network
```

```shell
sudo useradd sandpiper
sudo chmod 777 /home/sandpiper/
mkdir /home/sandpiper/data
sudo chmod -R 777 /home/sandpiper/
cp -r flinkx1.12/ /home/sandpiper/data
```

```shell
git clone git@github.com:DTStack/flinkx.git
wget --no-check-certificate https://dlcdn.apache.org/maven/maven-3/3.8.4/binaries/apache-maven-3.8.4-bin.tar.gz

../apache-maven-3.8.4/bin/mvn clean package -DskipTests

wget https://github.com/dtstack/maven-repository/raw/master/com/pingcap/ticdc/cdc/ticdc-decoder/5.2.0-SNAPSHOT/ticdc-decoder-5.2.0-SNAPSHOT.jar

git clone git@github.com:dtstack/maven-repository

mvn install:install-file -Dfile=ticdc-decoder-5.2.0-SNAPSHOT.jar -DgroupId=com.pingcap.ticdc.cdc -DartifactId=ticdc-decoder -Dversion=5.2.0-SNAPSHOT -Dpackaging=jar
```

```
https://github.com/DTStack/flinkx/blob/master/docs/quickstart.md
```