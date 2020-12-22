# movie-rental-api
Spring Boot, Spring Security + JWT + Spring Data JPA + MySQL API project

Prerequisites to test application

  1) Installed Java Jdk 1.8 or higher
  2) Installed maven
  3) Installed any IDE, preferent (Eclipse, STS, VS Code with Spring Tools installed)
  4) Installed MySQL Database server
  5) Git (Optional)

Setup the project

  1) Clone or download this project
  2) Install Dependencies with Maven (mvn clean install)
  3) Import project to IDE
  4) Import sql script from src/main/resources into MySQL server

Note : Inside the project in the application.properties file, modify for your local MySQL serverthe properties:
  1) spring.datasource.username=
  2) spring.datasource.password= 
 
Run the project

  1) mvn spring-boot:run

Additional Notes: You can generate Javadoc documentation for reference using the command:
  1) mvn javadoc:javadoc