# Decerto
Spring boot application with spring data
Database: H2

To run: 
mvn clean spring-boot:run

to check urls:

Add Quote
curl -X POST localhost:8080/quotes -H 'Content-type:application/json' -d '{"authorFirstName" : "Tomasz", "authorLastName": "Debski", "content" : "Java"}'

Find all quotes
curl localhost:8080/quotes

Update quote
curl -X PUT localhost:8080/quotes/1 -H 'Content-type:application/json' -d '{"authorFirstName" : "Tomasz", "authorLastName": "Debski","content" : "new Java"}'

Remove quote
curl -X DELETE localhost:8080/quotes/1
