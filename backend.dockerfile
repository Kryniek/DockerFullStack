FROM maven:3.6.3-openjdk-11
COPY ./Backend /usr/src/Backend
WORKDIR /usr/src/Backend
EXPOSE 8080
ENTRYPOINT ["mvn", "spring-boot:run"]
