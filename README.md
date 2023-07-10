# RestAssured

The APIs used for these classes to practice is based on very easy to use Book Store API. It gives a real world live project experience while using the sandbox environment of swagger tool to test the APIs.

Endpoints
Account
POST /Account/v1/Authorized
POST /Account/v1/GenerateToken
POST /Account/v1/User
BookStore
GET /BookStore/v1/Books
POST /BookStore/v1/Books
DELETE /BookStore/v1/Book
PUT /BookStore/v1/Books/{ISBN}
Framework
Structure
This project is a standard Maven Java project with src folders and pom.xml

Tests
src/test/java/ package request package which hosts all the tests discussed in the video series.

Installation
mvn clean install To install the project and it's dependencies

Help
