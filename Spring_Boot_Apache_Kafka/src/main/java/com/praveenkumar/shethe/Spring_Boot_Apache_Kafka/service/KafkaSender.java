package com.praveenkumar.shethe.Spring_Boot_Apache_Kafka.service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by Praveenkumar on 5/17/2021.
 */
@Service
public class KafkaSender {

    String kafkaTopic = "message-topic";

    private final Logger logger = LogManager.getLogger(KafkaSender.class);
    //  https://www.javainuse.com/spring/spring-boot-apache-kafka-hello-world

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void send(String message) {
        logger.info("Service: Inside the producer method");
        kafkaTemplate.send(kafkaTopic, message);
    }
}