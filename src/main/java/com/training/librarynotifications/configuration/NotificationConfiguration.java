package com.training.librarynotifications.configuration;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class NotificationConfiguration {

    @Value("${library.notification.queue.server.address:localhost}")
    private String queueAddressHost;

    @Value("${library.notification.queue.server.port:5672}")
    private int port;

    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(queueAddressHost);
        connectionFactory.setPort(port);
        connectionFactory.setUsername("diego");
        connectionFactory.setPassword("pippo1234!");
        return connectionFactory;
    }
}
