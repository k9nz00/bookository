#!/bin/bash
docker-compose -f "$(pwd)"/bookository-env/docker-compose.yaml down -v --remove-orphans