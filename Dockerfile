FROM openjdk:8
ADD target/employee-record-application.jar employee-record.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","employee-record.jar"]