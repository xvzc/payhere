FROM alpine

RUN apk update
RUN apk add openjdk11

ARG JAR_FILE=./build/libs/*SNAPSHOT.jar
COPY ${JAR_FILE} app.jar

COPY wait-for .
RUN ["chmod", "+x", "./wait-for"]

EXPOSE 8080

