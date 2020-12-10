package com.demo.application.resources;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.SimpleCollector;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddUserResource extends HttpServlet {

    private final Gauge numberOfUserCounter;
    private final Counter addUserRequestCounter;

    public AddUserResource(SimpleCollector numberOfUserCounter) {
        this.numberOfUserCounter = (Gauge) numberOfUserCounter;
        this.addUserRequestCounter = null;
    }

    public AddUserResource(SimpleCollector numberOfUserCounter, SimpleCollector addUserRequestCounter) {
        this.numberOfUserCounter = (Gauge) numberOfUserCounter;
        this.addUserRequestCounter = (Counter) addUserRequestCounter;
    }

    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp)
            throws IOException {
        resp.getWriter().println("Added User Successfully!");
        this.numberOfUserCounter.inc();
        if(addUserRequestCounter != null) {
            addUserRequestCounter.inc();
        }
    }

}
