FROM eclipse-temurin:17-jdk-alpine

# the executable spring boot app
ARG JAR_FILE=target/kalah-0.0.1-SNAPSHOT.jar
ADD ${JAR_FILE} app.jar

# the spring profile to run the app with
ARG SPRING_PROFILES=default
ENV SPRING_PROFILES_ACTIVE=${SPRING_PROFILES}

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom","--enable-preview", "-jar","/app.jar"]