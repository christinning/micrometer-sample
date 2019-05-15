#!/bin/bash

./gradlew clean bootJar
docker-compose up --build -d

progress="..."
until curl -s http://localhost:8080/actuator/health 1> /dev/null
do 
    echo -ne "Waiting on service to start${progress}\r"
    progress="${progress}."
    sleep 1; 
done
echo ""
