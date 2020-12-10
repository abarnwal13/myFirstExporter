package com.demo.application;

import com.demo.application.metrics.MetricsFactory;
import io.prometheus.client.SimpleCollector;
import io.prometheus.client.exporter.MetricsServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import com.demo.application.resources.AddUserResource;
import com.demo.application.resources.DeleteUserResource;
import com.demo.application.resources.HealthCheckResource;

public class MyApplicationServer {

    public static void main(String[] args) throws Exception {
        Server server = new Server(1111);
        ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");
        server.setHandler(context);

        // configure resources
        setUpResources(context);

        server.start();
        server.join();
    }

    private static void setUpResources(ServletContextHandler context) {
        // setup health check resource
        SimpleCollector healthCheckCounterMetric = MetricsFactory.getMetric("count", "health_check_count", "Number of health check request");
        context.addServlet(new ServletHolder(new HealthCheckResource(healthCheckCounterMetric)), "/healthcheck");

        //setup add and delete resource
        SimpleCollector userCounterMetric = MetricsFactory.getMetric("gauge", "number_of_users_available", "Number of Users available");

        SimpleCollector addUserCounterMetric = MetricsFactory.getMetric("count", "add_user_request_count", "Number of add user request");
        context.addServlet(new ServletHolder(new AddUserResource(userCounterMetric, addUserCounterMetric)), "/adduser");

        SimpleCollector deleteUserCounterMetric = MetricsFactory.getMetric("count", "delete_user_request_count", "Number of delete user request");
        context.addServlet(new ServletHolder(new DeleteUserResource(userCounterMetric, deleteUserCounterMetric)), "/deleteuser");

        // setup scrape resource for Prometheus metrics
        context.addServlet(new ServletHolder(new MetricsServlet()), "/metrics");
    }
}
