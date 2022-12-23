Achilles project
===========
### Instruments:
spring-boot, spring reactive,
logback, postgreSQL
### Have 2 parts: "client" and "server"

## Client:
### Configs:
application.yaml contains parameters 
for configuring the client;
also have 2 endpoints to manage client.
### Endpoints:
GET: client/api/v1/run to run client;

GET: client/api/v1/stop to stop client;

## Service:
### Configs:
application.yaml contains parameters for:
-datasource
-port
-logging

### DB:
Uses spring-jdbc for connect to DataBase

### Logging:
writes to the log file "var/logs/balance.log" 
results of api calls

