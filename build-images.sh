#!/bin/bash
IMAGE_VERSION=$1
if [ -z "$IMAGE_VERSION" ]
then
  IMAGE_VERSION=br_it
fi

mvn clean install -DskipTests -PwithDocker -Ddocker.image.version="$IMAGE_VERSION"
echo "IMAGE_VERSION=${IMAGE_VERSION}" > env/.env

#Creating image DB with migration scripts
docker build --file ./env/dockerfiles/Dockerfile-postgres --tag bookository/migration-postgres:"${IMAGE_VERSION}" .
