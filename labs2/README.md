# Aplicacion JEE en tomcat

Tomcat con uso de cache. que persiste objetos en la OLD durante un tiempo X. Si el sistema de cache está bien implementado deberia persistir solo si hay espacio.

En el aplicacionews JEE tenemos Sessions (objeto que persiste informacion del usuario en una zona de la RAM) La sesión se indentifica mediante un JSESSIONID que esta en la cookie en el navegador.

Una estrategia podría ser si no hay espacio en la RAM liberar espacio de la session.

# Tomcat + MariaDB contenedores

**container tomcat:8.5**

# descarga imagen
sudo docker pull bitnami/tomcat:8.5

# crear contenedor
sudo docker container create --name mitomcat -e TOMCAT_USERNAME=tomcat -e TOMCAT_PASSWORD=password -e TOMCAT_ALLOW_REMOTE_MANAGEMENT=1 bitnami/tomcat:8.5

# ejecuta contenedor
sudo docker start mitomcat

# eliminar contenedor
sudo docker rm -f mitomcat

# ssh contenedor
sudo docker exec -it mitomcat bash

# path tomcat
/opt/bitnami/tomcat

# copia fichero tomcat-users.xml

sudo docker cp mitomcat:/opt/bitnami/tomcat/conf/tomcat-users.xml .

sudo docker container create --name mitomcat -e TOMCAT_USERNAME=tomcat -e TOMCAT_PASSWORD=password -e TOMCAT_ALLOW_REMOTE_MANAGEMENT=1 -v /home/java/curso/curso-jvm/install/tomcat-users.xml:/opt/bitnami/tomcat/conf/tomcat-user.xml bitnami/tomcat:8.5

bitnami ya tiene el driver de MariaDB; si no estuviera se puede llevar al contenedor con el uso de volumenes -v

# contenedor mariadb

sudo docker container create --name mimariadb \
    -p 3306:3306 -e MARIADB_DATABASE=prueba -e MARIADB_USER=usuario \
    -e MARIADB_PASSWORD=password -e MARIADB_ROOT_PASSWORD=password mariadb

sudo docker start mimariadb

sudo docker exec -it mimariadb mysql -u root -p    

MariaDB [(none)]> use prueba;

MariaDB [(none)]> show tables;