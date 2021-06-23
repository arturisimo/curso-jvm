sudo docker container create -p 3306:3306 --name miMariaDB \
    -e MYSQL_ROOT_PASSWORD=password  \
    -e MYSQL_DATABASE=miTestDB \
    -e MYSQL_USER=usuario  \
    -e MYSQL_PASSWORD=password  \
    mariadb

sudo docker start miMariaDB

echo "username=weblogic" >> domain.properties
echo "password=password1" >> domain.properties

sudo docker login
sudo docker container create -p 7001:7001 -p 9002:9002 -e DOMAIN_NAME=base_domain --name miWeblogic\
      -v $PWD:/u01/oracle/properties store/oracle/weblogic:12.2.1.3

sudo docker start miWeblogic