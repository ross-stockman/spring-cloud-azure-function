# spring-cloud-azure-function-java11

This is a demo project to show how to create simple serverless functions using `java.util.function` classes and run them locally as both spring cloud functions and as azure functions.

## The serverless functions
Serverless functions are implemented using simple `java.util.function` classes. For this demo, the following functions are implemented:

---
```java
public Function<String, String> hello() {
    return (name) -> name + " was here!";
}
```
Ths `java.util.function.Function` function has a string input and will return a string output.

___

```java
public Supplier<String> time() {
    return () -> "The time is " + LocalTime.now();
}
```
This `java.util.function.Supplier` function takes no input arguments and will return a string output.

___

```java
public Consumer<String> publish() {
    return (message) -> System.out.println("Received event >> " + message);
}
```
This `java.util.function.Consumer` function takes a string input but returns void, so no output.

___

## As Spring Boot serverless functions

```bash
mvn clean package spring-boot:run
```

This will start spring boot and expose the following three http endpoints:
```
[POST] http://localhost:8080/hello
[GET]  http://localhost:8080/time
[POST] http://localhost:8080/publish
```

Sample requests [here](src/test/resources/serverless-functions-demo.http)

## As Azure functions

```bash
mvn clean package azure-functions:run
```

This will start the functions on the azure runtime and expose the following three http endpoints:
```
[POST] http://localhost:7071/api/hello
[GET]  http://localhost:7071/api/time
[POST] http://localhost:7071/api/publish
```

Sample requests [here](src/test/resources/azure-functions-demo.http)

> Note: For the azure functions to work and map to the correct util functions, the `@FunctionName` value needs to match the spring `@Bean` name. The azure functions class also needs to extend `FunctionInvoker` so that the spring boot context can be started properly. This FunctionInvoker also provides the `handleRequest(..)` method that all of these samples are using to pass the input string to the util functions and returns the output string back to the response builder. See screenshot ![screenshot](/.github/image1.png)
