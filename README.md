completed requirements 
======================
create contacts
cretae contacts success message triger in to kafka topic 
instead of GET method used POST to retive list of contaxcts 
filtering contacts list implemented only two scenarios as mentioned in the assignment document 
To handle exception used @RestController Advise 
Junit test 
Data base used MYSQL
Docker file and Docker compose created (docker-compose working fine)  

Command for running Zookeeper and Kafka 
========================================
start zookeeper - .\bin\windows\zookeeper-server-start.bat .\config\zookeeper.properties
start kafka - .\bin\windows\kafka-server-start.bat .\config\server.properties

Commands to run Docker file 
============================
create mysql docker image 
=========================
docker run -p 3306:3306 --name mysqldb -e MYSQL_ROOT_PASSWORD=root -e MYSQL_DATABASE=test mysql:5.5.41
create Docker network 
=========================
Docker network create teg-assignment-network

create dockerimage of our application 
======================================
docker build -t springboot-teg-assignment-docker .

docker ps 
docker network ls 

connect created network to db container 
========================================
docker network connect teg-assignment-net mysqldb

ispect the connection between network and db
==============================================
docker network inspect teg-assignment-network 


add our image in to the docker container 
==========================================
docker run -p 9090:8080 --name springboot-teg-assignment-docker --net teg-assignment-net -e MYSQL_HOST=mysqldb -e MYSQL_USER=root -e MYSQL_PASSWORD=root -e MYSQL_PORT=3306 springboot-teg-assignment-docker

Command for docker-compose 
==========================
docker-compose up -d
docker-compose down

Request PayLoads
==================
create contacts 
POST- http://localhost:8080/hellow/createContacts
      {
      "name":"Alby"
      }
Retrive contacts -first scenarios 
POST- http://localhost:8080/hellow/contacts
      {
       "nameFilter":"^(?!A).*$",
       "page"   :0
      }
Retrive contacts -second scenarios
 http://localhost:8080/hellow/contacts
      {
       "nameFilter":"^[^aeiAEI]+$",
       "page"   :0
      }
Note : if your regular expression pattren is wrong , will get empty list 
