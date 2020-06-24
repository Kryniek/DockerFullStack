FROM maven:3.6.3-openjdk-11
WORKDIR /usr/src/Backend
COPY ./Backend .
EXPOSE 8080
RUN mvn clean install -DskipTests=true
ENTRYPOINT ["mvn", "spring-boot:run"]