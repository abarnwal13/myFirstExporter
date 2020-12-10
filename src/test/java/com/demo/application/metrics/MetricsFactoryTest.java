package com.demo.application.metrics;

import io.prometheus.client.Counter;
import io.prometheus.client.Gauge;
import io.prometheus.client.SimpleCollector;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MetricsFactoryTest {

    @Test
    public void testGetMetricReturnInstanceofCounter() {
        SimpleCollector collector = MetricsFactory.getMetric("count", "count_demo_metric_name", "demo metric description");
        assertTrue(collector instanceof Counter);
    }

    @Test
    public void testGetMetricReturnInstanceofGauge() {
        SimpleCollector collector = MetricsFactory.getMetric("gauge", "gauge_demo_metric_name", "demo metric description");
        assertTrue(collector instanceof Gauge);
    }

    @Test
    public void testGetMetricReturnNull() {
        SimpleCollector collector = MetricsFactory.getMetric("invalidType", "invlaid_demo_metric_name", "demo metric description");
        assertEquals(null, collector);
    }
}
