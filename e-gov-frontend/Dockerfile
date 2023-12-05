FROM node:15-alpine

WORKDIR ./e-gov-frontend

COPY package*.json ./

RUN npm install

COPY . .

RUN npm run build

EXPOSE 3000

CMD ["npm", "start"]