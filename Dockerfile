FROM openjdk:8-jre-alpine

COPY ./target/scala-2.12/amortization.jar /dist/amortization.jar

WORKDIR /dist
ENTRYPOINT java -jar amortization.jar
EXPOSE 8080

