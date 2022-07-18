# syntax=docker/dockerfile:experimental
FROM maven:3.8.6-jdk-11

WORKDIR /app
ADD . /app

RUN mvn clean install
RUN chmod 0777 -R /app

EXPOSE 8080

CMD ["mvn", "spring-boot:run", "-pl", "web"]
