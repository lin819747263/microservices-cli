package com.linmsen.influxdb;

import okhttp3.OkHttpClient;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;

/**
 * @author lgs
 */
public class InfluxConnectFactory {

    private InfluxConfig influxConfig;


    public InfluxConnectFactory(InfluxConfig influxConfig) {
        this.influxConfig = influxConfig;
    }


    public InfluxDB getInfluxConnection() {
        return InfluxDBFactory.connect(influxConfig.getUrl(),influxConfig.getUser(),influxConfig.getPassword());
    }

    public InfluxDB getInfluxConnection(OkHttpClient.Builder builder) {
        return InfluxDBFactory.connect(influxConfig.getUrl(), influxConfig.getUser(), influxConfig.getPassword(), builder);
    }
}
