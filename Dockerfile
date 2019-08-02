
FROM java:11-jdk-alpine

COPY ./target/Musix-App-Assignment-0.0.1-SNAPSHOT.jar app.jar

EXPOSE 8090

RUN sh -c 'touch app.jar'

ENTRYPOINT ["java","-jar","app.jar"]
