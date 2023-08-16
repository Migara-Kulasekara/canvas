FROM openjdk:8-jre
RUN mkdir /app
WORKDIR /app
COPY target/canvas-1.0-SNAPSHOT.jar /app/app.jar
ENTRYPOINT ["java","-jar","/app/app.jar"]