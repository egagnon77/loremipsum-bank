FROM openjdk:8-jdk-alpine
ARG TARGET=bank/target
COPY ${TARGET}/*jar app/bank.jar
ENTRYPOINT ["java","-jar","app/bank.jar"]