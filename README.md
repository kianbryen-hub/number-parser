# Number Parser

The number parser project offers up an API to convert a dialled number into international format through the use of a lookup keyed on the country code of the user's number.

## Usage
The number parser is a spring boot service which offers up a REST API to retrieve the formatted number, this can be started through the use of the below maven command:
```
mvn spring-boot:run
```
This will start the service locally on port 8080, the user can then access the API by making a GET request, such as below:
```
curl -X GET 'http://localhost:8080/internationalNumber?dialledNumber=07277822334&userNumber=%2B447866866886'
```