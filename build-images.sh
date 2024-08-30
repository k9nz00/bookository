#!/bin/bash
IMAGE_VERSION=$1
if [ -z "$IMAGE_VERSION" ]
then
  IMAGE_VERSION=br_dev
fi
echo "IMAGE_VERSION=${IMAGE_VERSION}" > bookository-env/.env

mvn clean install -DskipTests -PwithDocker -Ddocker.image.version="$IMAGE_VERSION"
