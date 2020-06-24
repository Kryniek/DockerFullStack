FROM node:8.16.2
RUN npm install -g @angular/cli@7.3.10
WORKDIR /home/node/Frontend
COPY ./Frontend .
EXPOSE 4200
ENTRYPOINT ["ng", "serve", "--host", "0.0.0.0"]