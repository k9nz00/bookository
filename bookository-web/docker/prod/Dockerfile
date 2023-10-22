FROM node:16.17-alpine
WORKDIR /app
#RUN #npm global add pm2

#RUN mkdir /configs
#COPY ./docker/prod/pm2.config.js /configs

COPY ./app/package.json ./
RUN npm install
#RUN #npm run build

COPY ./app .

#RUN npm run build

# on container UP:
# 1. Generate env file (yarn public-env)
# 2. start web-server
CMD npm run dev
