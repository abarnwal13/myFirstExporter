package com.demo.application.resources;

import io.prometheus.client.Counter;
import io.prometheus.client.SimpleCollector;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HealthCheckResource extends HttpServlet {

    private final Counter requestCounter;

    public HealthCheckResource() {
        this.requestCounter = Counter.build()
                .name("health_check_request_count")
                .help("Number of health check request.").register();
    }

    public HealthCheckResource(SimpleCollector requestCounter) {
        this.requestCounter = (Counter) requestCounter;
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
            throws IOException {
        resp.getWriter().println("I am healthy!");
        this.requestCounter.inc();
    }
}
