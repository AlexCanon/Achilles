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

It's a shame, but the calculation of the number of requests
was made via a "bicycle" - 
Through the "EfficiencyLogger" aspect I calculate the average number of requests. I tried through 
Spring Actuator, but  I still didn't understand how to
make it short and "pleasant".


