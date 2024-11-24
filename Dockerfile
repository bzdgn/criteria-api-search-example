FROM openjdk:17-jdk-slim

WORKDIR /app

COPY target/criteria-search-api.jar criteria-search-api.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "criteria-search-api.jar"]