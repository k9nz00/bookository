#!/bin/bash
IMAGE_VERSION=$1
if [ -z "$IMAGE_VERSION" ]
then
  IMAGE_VERSION=br_dev
fi
echo "IMAGE_VERSION=${IMAGE_VERSION}" > bookository-env/.env

mvn clean install -PwithDocker -Ddocker.image.version="$IMAGE_VERSION"

docker build --file ./bookository-env/dockerfiiles/Dockerfile-executor-migration-scripts --tag bookository/migration-scripts-executor:"${IMAGE_VERSION}" .