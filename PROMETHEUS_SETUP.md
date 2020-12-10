## Steps to install and run Prometheus server on Mac

This document is to guide how to install and setup Prometheus server on Mac.

1. Execute below command to install prometheus 
   ```bash
   brew install prometheus
   ```
2. Verify prometheus installed
   ```bash
   prometheus --version
   ```
   ```
   prometheus, version 2.23.0 (branch: non-git, revision: non-git)
   build user:       brew@Catalina
   build date:       20201126-23:51:27
   go version:       go1.15.5
   platform:         darwin/amd64
   ```
3. Run `/usr/local/bin/prometheus_brew_services` to start prometheus server which runs on port 9090.
4. Goto browser and browse `localhost:9090`, this will bring the prometheus query UI to search for metrics.
5. Update `prometheus.yml` with target application host to report metrics captured by Prometheus client.
   ```editorconfig
   global:
     scrape_interval: 15s
   
   scrape_configs:
     - job_name: "myfirstprometheus"
       static_configs:
       - targets: ["localhost:1111"] # this is demo application host running on port 1111
   ```
6. Restart the prometheus server to scrape metrics from demo application
7. You are all set, search for metrics captured in demo application. 