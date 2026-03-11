package com.dispatcher.adapters.odoo.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource("classpath:odoo.properties")
@ConfigurationProperties(prefix = "odoo")
public class OdooProperties {

    //    @Value("${odoo.url}")
    private String url;

    //    @Value("${odoo.host}")
    private String host;

    //    @Value("${odoo.port}")
    private String port;

    //    @Value("${odoo.db}")
    private String database;

    //    @Value("${odoo.username}")
    private String username;

    //    @Value("${odoo.password}")
    private String password;
}
