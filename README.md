# SpringBootAssignment
Develop a web application with one endpoint, eg GET http://localhost:8080/users

## Getting Started 
These instructions will get you a copy of the project up and running on your local machine for development and testing purposes. 

## Tools and Libraries
* Java 1.8
* Maven 3.6.1
* MySQL
* Spring Tool Suite 3
* Spring Boot
* Spring Batch
* JSON

##  Prerequisites and Setup
* Install and start up MYSQL Sever 
* Install and create a connection in MYSQL Workbench 
    * Connection Method: Standard(TCP/IP)
    * Hostname: 127.0.0.1
    * Port: 3306
    * username: root
    * password: password

## Running the application

If you do not have Maven installed in your environment yet, you will need to install it as we will run the project using Maven command.
```sh
$ brew install maven
```
Locate the path of the root folder then run the project.
```sh
$ cd <path of the root folder>
$ mvn spring-boot:run
```

## Testing api endpoints
```sh
$ curl localhost:8080/users
```






