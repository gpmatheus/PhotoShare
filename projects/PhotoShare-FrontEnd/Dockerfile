FROM node:latest AS node
WORKDIR /app
COPY package*.json ./
RUN npm install
COPY . .
RUN npm run build --prod

FROM nginx:alpine
COPY --from=node /app/dist/photo-share-front-end /usr/share/nginx/html