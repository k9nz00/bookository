FROM node:16.17-alpine
WORKDIR /app

RUN npm install pm2 -g

RUN mkdir /configs
COPY ./docker/prod/pm2.config.js /configs

COPY ./app/package.json ./
RUN npm install

COPY ./app .

RUN npm run build

# on container UP:
# 1. Generate env file (yarn public-env)
# 2. start web-server
CMD npm run public-env \
    && pm2-runtime /configs/pm2.config.js
