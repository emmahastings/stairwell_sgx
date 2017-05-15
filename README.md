# stairwell_sgx
Climbing stairs technical solution


### Building this Project

Before building make sure you have [maven](http://maven.apache.org) installed. Clone the project and follow either option below.

```bash
cd stairs/
mvn spring-boot:run
```

In addition you can build a jar file 

```bash
cd stairs/
mvn clean package
java -jar target/stairwell_sgx.jar
```

### Usage

```bash
curl -H "Content-Type: application/json" -X POST -d '{"flights":["17"],"stepsPerStride":"3"}' localhost:8080
```

### Authors and Contributors
Emma Hastings (@emmahastings)