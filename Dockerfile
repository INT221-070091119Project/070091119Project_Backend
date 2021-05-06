FROM maven:3.6.0-jdk-11 AS build
COPY src /usr/src/app/src
COPY pom.xml /usr/src/app
RUN mvn -f usr/src/app/pom.xml clean package

FROM openjdk:11
COPY --from=build usr/src/app/target/*.jar usr/app/int221-1.0.0-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/app/int221-1.0.0-SNAPSHOT.jar"]