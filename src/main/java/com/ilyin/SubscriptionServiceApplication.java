package com.ilyin;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SubscriptionServiceApplication {
    private static final Logger log = LoggerFactory.getLogger(SubscriptionServiceApplication.class);

    public static void main(String[] args) {
        try {
            log.info("=========== Starting SubscriptionService application... ===========");
            SpringApplication.run(SubscriptionServiceApplication.class, args);
            log.info("=========== SubscriptionService successfully started. ===========");
        } catch (Exception e) {
            log.error("=========== Error starting SubscriptionService application! ===========", e);
            throw e; // выбрасывание исключения, чтобы приложение завершилось с ошибкой
        }
    }
}