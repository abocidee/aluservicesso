#!/bin/bash
docker-compose down 
docker rmi alu-service-sso
mvn clean package

docker-compose up -d
 
