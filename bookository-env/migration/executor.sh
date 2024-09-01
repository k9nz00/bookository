#!/bin/bash

for migrationScripts in /opt/scripts/*.sh; do

  echo Выполнение миграционного скрипта: "$(cd "$(dirname -- "$migrationScripts")" >/dev/null; pwd -P)/$(basename -- "$migrationScripts")"
  bash "$migrationScripts"

done
