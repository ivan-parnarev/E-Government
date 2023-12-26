FROM node:18.16.0-alpine

WORKDIR ./app

COPY package.json ./

RUN npm install -g npm@9.5.1

COPY . .

EXPOSE 3000

CMD ["npm", "start"]