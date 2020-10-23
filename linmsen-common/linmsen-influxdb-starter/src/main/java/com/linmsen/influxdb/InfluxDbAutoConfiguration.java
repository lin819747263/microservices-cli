package com.linmsen.influxdb;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(InfluxConfig.class)
public class InfluxDbAutoConfiguration {

    private final InfluxConfig influxConfig;

    public InfluxDbAutoConfiguration(InfluxConfig influxConfig) {
        this.influxConfig = influxConfig;
    }

    @Bean
    @ConditionalOnMissingBean
    @ConditionalOnProperty("influx.url")
    public InfluxConnectFactory influxDb() {
        return new InfluxConnectFactory(influxConfig);
    }

}
