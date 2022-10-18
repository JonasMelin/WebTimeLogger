FROM gradle:7-jdk17 AS build
#RUN chown gradle:gradle /home/gradle
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN gradle build --no-daemon -x test
RUN ls -l ./build/libs

FROM openjdk:17-alpine

RUN mkdir /app

COPY --from=build /home/gradle/src/build/libs/ /app/

ENTRYPOINT ["java","-jar","/app/webTimeLogger-0.0.1-SNAPSHOT.jar"]
