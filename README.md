# TudorJava
Homework in Java for ELTE Software Development in Practice course

This application is a Client-Server application with a Java Spring Boot REST API component and a React JS frontend.
> Implementation of some functionalities are missing. This is an experiment with Java Spring Boot technology.

## How to build and run

_Tudor_ REST API:
  You can open the project using the pom.xml.
  In the application.properties you need to define a database connection and set the _spring.jpa.hibernate.ddl-auto_ parameter to _update-drop_.
  After these steps, you can build and run the application. It will create the required database structure in the database.
  
_tudor-client_ React JS app:
  You can edit this thin client with any text editor.
  To use the application, you need to install npm to you computer.
  After that you need to restore the npm packager for the app.
  Define the URL of the REST API in the api.js file.
  You can try out the application with _npm start_ command.
  
  
