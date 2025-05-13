# Part 1: Build the app using Maven
#FROM maven:3.9.9-eclipse-temurin-21
# Uses temurin 21
FROM gradle:8-jdk21-alpine as build

## download dependencies
#ADD pom.xml /
#RUN mvn -e verify clean
### build after dependencies are down so it wont redownload unless the POM changes
#ADD . /
#RUN mvn package
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon

# Part 2: use the JAR file used in the first part and copy it across ready to RUN
FROM eclipse-temurin:21-jdk as runner
RUN mkdir /app
## COPY packaged JAR file and rename as app.jar 
## → this relies on your MAVEN package command building a jar 
## that matches *-jar-with-dependencies.jar with a single match
COPY --from=build /home/gradle/src/build/libs/ /app/
RUN ls /app
ENTRYPOINT ["java","-jar","/app/service-template-example-0.0.2-SNAPSHOT.jar"]