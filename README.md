# Simple Prometheus Exporter in Java

A Prometheus Java exporter for HTTP Server request metrics. This is a simple Java HTTP application built using maven. 
There are three resources created to capture their metrics using Prometheus client lib. Each time these endpoints get called their metrics will be captured. For simplicity adduser/deleteuser methods created as GET, not POST/DELETE.

| Resource Path | Description | Method |
| ----- | ----- | ----- |
| /healthcheck | health check request | GET |
| /adduser | add user | GET |
| /deleteuser | delete user | GET |
| /metrics | scrape endpoint use by Prometheus to fetch metrics | GET |

### Build
#### Environment Prerequisite
* Java version 7 or above should be installed. java8 is used to build this sample project.
* Maven should be installed

#### Build
Create an executable jar file `myFirstExporter-1.0-jar-with-dependencies.jar` with dependency in the `target` directory.
```bash
mvn clean install
```

### Execution and Verification

#### Execution
Run java command to start the application server. After the application server started, go to the browser and open 
`localhost:1111/metrics`. This will display all the available metrics reported by Application server in Prometheus format.
```bash
java -jar target/myFirstExporter-1.0-SNAPSHOT-jar-with-dependencies.jar
```

#### Verification
##### Metrics after the Application server started.
```html
# HELP number_of_users_available Number of Users available
# TYPE number_of_users_available gauge
number_of_users_available 0.0
# HELP add_user_request_count Number of add user request
# TYPE add_user_request_count counter
add_user_request_count 0.0
# HELP delete_user_request_count Number of delete user request
# TYPE delete_user_request_count counter
delete_user_request_count 0.0
# HELP health_check_count Number of health check request
# TYPE health_check_count counter
health_check_count 0.0
```

##### Metrics after calling below endpoints in the browser.
* `localhost:1111/healthcheck`
* `localhost:1111/adduser`
* `localhost:1111/adduser`
* `localhost:1111/deleteuser`

```html
# HELP number_of_users_available Number of Users available
# TYPE number_of_users_available gauge
number_of_users_available 1.0
# HELP add_user_request_count Number of add user request
# TYPE add_user_request_count counter
add_user_request_count 2.0
# HELP delete_user_request_count Number of delete user request
# TYPE delete_user_request_count counter
delete_user_request_count 1.0
# HELP health_check_count Number of health check request
# TYPE health_check_count counter
health_check_count 1.0
```

### List of Exported Application Metrics

| Metric | Description | Type |
| ------ | ------ | ------ |
| health_check_count | Number of health check request | Counter |
| add_user_request_count | Number of add user request | Counter |
| delete_user_request_count | Number of delete user request | Counter |
| number_of_users_available | Number of Users available | Gauge |

### Update Prometheus Server Config file to add target host
The application server is running on localhost(127.0.0.1) on port 1111, update the same in the scrape configs targets to scrape the metrics.
```yaml
global:
  # Override scrape_interval as per requirement
  scrape_interval: 15s

scrape_configs:
  - job_name: "myfirstprometheus"
    static_configs:
    - targets: ["localhost:1111"]
```