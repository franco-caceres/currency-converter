# currency-converter
A three-tier web-based application to convert arbitrary amounts between currencies

## How to build
Run the following command in the root project directory:
```bash
mvn package
```

## How to run
Start the server by executing the hw4-1.0.jar.jar JAR located in .\target:
```bash
java -jar target\hw4-1.0.jar
```
By default, the server will listen for requests on port 8080.

## Database details
The database is created automatically by the application in an H2 in-memory instance.

## Browsing the application
To make a conversion go to the root path "/". To access the administrator dashboard go to "/admin".
