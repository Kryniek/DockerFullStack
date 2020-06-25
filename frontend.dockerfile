FROM node:8.16.2
RUN npm install -g @angular/cli@7.3.10
WORKDIR /home/node/Frontend
COPY ./Frontend .
RUN npm install
COPY ./frontendEntrypoint.sh .
RUN chmod 777 frontendEntrypoint.sh
EXPOSE 4200
ENTRYPOINT ["sh", "./frontendEntrypoint.sh"]