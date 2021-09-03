# Decerto
Spring boot application with spring data
Database: H2
Spring data pagination to limit the number of records

To run: 
mvn clean spring-boot:run

to check urls:

Add Quote:
curl -X POST localhost:8080/quotes -H 'Content-type:application/json' -d '{"authorFirstName" : "Tomasz", "authorLastName": "Debski", "content" : "Java"}'

Find all quotes (pageNo - What part of the records will be returned, pageSize = how many elements will be returned)
curl  "localhost:8080/quotes?pageNo=0&pageSize=10"

Update quote:
curl -X PUT localhost:8080/quotes/1 -H 'Content-type:application/json' -d '{"authorFirstName" : "Tomasz", "authorLastName": "Debski","content" : "new Java"}'

Remove quote:
curl -X DELETE localhost:8080/quotes/1
