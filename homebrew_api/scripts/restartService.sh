#!/bin/bash

clear

echo "Trying to kill service..."
pkill -ef 'java.*homebrew-api'

echo ""
echo "Starting new package..."
nohup java -jar target/homebrew-api-1.0-SNAPSHOT.jar server dev_conf.yaml &