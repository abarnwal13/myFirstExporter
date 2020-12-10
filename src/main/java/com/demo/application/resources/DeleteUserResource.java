package com.demo.application.resources;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.SimpleCollector;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteUserResource extends HttpServlet {

    private final Gauge numberOfUserCounter;
    private final Counter deleteUserRequestCounter;

    public DeleteUserResource(SimpleCollector numberOfUserCounter) {
        this.numberOfUserCounter = (Gauge) numberOfUserCounter;
        this.deleteUserRequestCounter = null;
    }

    public DeleteUserResource(SimpleCollector numberOfUserCounter, SimpleCollector deleteUserRequestCounter) {
        this.numberOfUserCounter = (Gauge) numberOfUserCounter;
        this.deleteUserRequestCounter = (Counter) deleteUserRequestCounter;
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
            throws IOException {
        resp.getWriter().println("Deleted User Successfully!");
        this.numberOfUserCounter.dec();
        if(deleteUserRequestCounter != null) {
            deleteUserRequestCounter.inc();
        }
    }

}
