Achilles project
===========
### Instruments:
spring-boot, spring-actuator, rest-template,
logback, postgreSQL, spring-data-jpa
### Have 2 parts: "client" and "server"

## Client:
### Configs:
application.yaml contains parameters 
for configuring the client;

### Endpoints:
GET: client/api/v1/run to run client;

GET: client/api/v1/stop to stop client;

## Service:
### Configs:
application.yaml contains parameters for: datasource; port; logging;

### DB:
The Spring-jpa and postgres

, pessimistic-write lock

### Logging:
writes to the log file "var/logs/balance.log" 
results of api calls

### Metrics:
The calculation of the number of requests was made
using the "Efficiency Logger" aspect
, which calculates the average number of requests.

Spring Actuator with Prometheus...
I tried to use this thing to get a metric 
of the requests speed to the server.

### Planned improvements
In the near future it is planned
to replace the rest client with WebFlux (reactive)
