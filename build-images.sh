#!/bin/bash
IMAGE_VERSION=$1
if [ -z "$IMAGE_VERSION" ]
then
  IMAGE_VERSION=br_it
elif [ "$IMAGE_VERSION" = "mvn" ]
then
    IMAGE_VERSION=$(mvn -q \
        -Dexec.executable=echo \
        -Dexec.args='${project.version}' \
        --non-recursive \
        exec:exec)
fi
mvn clean install -DskipTests
#mvn clean install -DskipTests -PwithDocker -Ddocker.image.version="$IMAGE_VERSION"

echo "IMAGE_VERSION=${IMAGE_VERSION}" > env/.env

docker build --file ./env/dockerfiles/Dockerfile-postgres --tag bookository.migration/postgres:"${IMAGE_VERSION}" .
