FROM maven:3.5.0-alpine
MAINTAINER thibaut.mottet@pupscan.fr

RUN apk add --update docker py-pip
RUN pip install runlike

WORKDIR /workspace
COPY . .
RUN mvn install

EXPOSE 8080

CMD ["java", "-Dspring.profiles.active=prod", "-jar", "./target/deploy-0.0.1-SNAPSHOT.jar"]