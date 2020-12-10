package com.demo.application.metrics;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.SimpleCollector;

public class MetricsFactory {

    public static SimpleCollector getMetric(String metricType, String metricName, String metricDescription) {

        SimpleCollector metric = null;
        switch (metricType) {
            case "count" :
                metric = Counter.build()
                        .name(metricName)
                        .help(metricDescription)
                        .register();
                break;
            case "gauge" :
                metric = Gauge.build()
                        .name(metricName)
                        .help(metricDescription)
                        .register();
                break;
            default:
                break;
        }

        return metric;
    }
}
