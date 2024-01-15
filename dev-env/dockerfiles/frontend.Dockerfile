FROM node:18.16.0-alpine

WORKDIR ./app

COPY ../../frontend/package.json ./

RUN npm install --legacy-peer-deps

COPY ../../frontend .

EXPOSE 3000

CMD ["npm", "start"]