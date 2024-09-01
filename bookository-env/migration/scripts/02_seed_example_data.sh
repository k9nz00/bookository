#!/bin/bash

url='http://bookository-migration:8030/api/v1/migration/seed-data/update'
request='{"jdbcUrl":"jdbc:postgresql://postgres:5432/bookository","database":"bookository","username":"postgres","password":"postgres"}'

echo выполняется запрос по следующему адресу - "$url" '\n'
echo тело запроса "$request"

for i in {0..10}; do
  echo "Попытка отправки запроса: $i"
  responseCode=$(curl   --insecure \
                        --silent \
                        --connect-timeout 5 \
                        --max-time 180 \
                        --retry 60 \
                        --retry-delay 3 \
                        --retry-max-time 40 \
                        --retry-connrefused \
                        --request POST \
                        --url "$url" \
                        --header 'Content-Type: application/json' \
                        --write-out '%{http_code}' \
                        --data "$request")
  echo "Код ответа = $responseCode"

  if [ "$responseCode" != 204 ]; then
    echo "Ошибка в скрипте миграции. Будет выполнена еще одна попытка";
  else
    break;
  fi
  sleep 5
done