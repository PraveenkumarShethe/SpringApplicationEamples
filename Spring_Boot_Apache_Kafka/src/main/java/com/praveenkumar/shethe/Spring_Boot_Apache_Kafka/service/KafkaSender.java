package com.praveenkumar.shethe.Spring_Boot_Apache_Kafka.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by Praveenkumar on 5/17/2021.
 */
@Service
public class KafkaSender {

//  https://www.javainuse.com/spring/spring-boot-apache-kafka-hello-world
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    String kafkaTopic = "message-topic";

    public void send(String message) {

        kafkaTemplate.send(kafkaTopic, message);
    }
}