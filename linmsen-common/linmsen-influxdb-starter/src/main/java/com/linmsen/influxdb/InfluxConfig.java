package com.linmsen.influxdb;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * influxdb 配置实体
 * @author lgs
 *
 */
@ConfigurationProperties(prefix = "linmsen.influx")
public class InfluxConfig {
    private String user;

    private String password;

    private String url;

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "InfluxConfig{" +
                "user='" + user + '\'' +
                ", password='" + password + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}