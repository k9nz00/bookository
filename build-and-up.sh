#!/bin/bash

sh build-images.sh $1
docker-compose -f "$(pwd)"/bookository-env/docker-compose.yaml up -d