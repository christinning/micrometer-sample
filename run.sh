#!/bin/bash

./gradlew bootJar
docker-compose build
docker-compose up