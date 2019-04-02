# NATI Challenge Spring Boot Web Application

## The Challenge
Build a web application to build a university grade using spring boot 2 as backend ( API Rest ) and another module frontend using html, css and javascript, that consume the backend by ajax request.

This project uses as base the petclinic spring boot sample. [Petclinic-Sample](https://github.com/spring-projects/spring-petclinic)  
To execute this project, you need do this steps:

```
git clone https://github.com/ericmbf/natiChallenge.git
cd natiChallenge
./mvnw package
java -jar target/*.jar
```

Or you can run it from Maven directly using the Spring Boot Maven plugin. If you do this it will pick up changes that you make in the project immediately (changes to Java source files require a compile as well - most people use an IDE for this):

```
./mvnw spring-boot:run
```
This first step, only was implemented the rest API, without control access.

You can test the REST using this postman package with the routes and JSON body's.  
[![Run in Postman](https://run.pstmn.io/button.svg)](https://app.getpostman.com/run-collection/1908ff5967475dd8c621)

## Appication Initialization

This project was build to using a mysql database. To facilite, there is a docker-compose.yml file that can be used initialize the database. You need run this command to run the app in background in the compose file directory.

```
docker-compose up -d
```

If you need clean the database, run:
```
docker-compose down --volumes
```

