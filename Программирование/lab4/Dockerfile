FROM gradle:6.9.3-jdk11-alpine AS build

ENV APP_HOME=/usr/app/
WORKDIR $APP_HOME

COPY settings.gradle $APP_HOME
COPY app/build.gradle $APP_HOME/app/build.gradle
COPY gradle $APP_HOME/gradle
COPY --chown=gradle:gradle . .

RUN gradle customFatJar

FROM eclipse-temurin:11-jre-alpine

ENV APP_HOME=/usr/app
WORKDIR $APP_HOME

COPY --from=build $APP_HOME/app/build/libs/DemoFat.jar .
CMD ["java", "-jar", "DemoFat.jar"]