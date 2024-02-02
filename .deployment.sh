#!/bin/bash

echo "git pull"
# sudo git pull

echo "build morning.auth"
./gradlew build

echo "kill morning.auth"
pkill -9 -f auth

echo "execute sample"
nohup java -jar build/libs/auth-0.0.1-SNAPSHOT.jar
