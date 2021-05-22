package com.praveenkumar.shethe.Spring_boot_Apache_Kafka_listener.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Praveenkumar on 5/22/2021.
 */
@RestController
@RequestMapping(value = "/kafkamessage")
public class KafkaController {

    private final Logger logger = LogManager.getLogger(KafkaController.class);

//    https://www.baeldung.com/spring-kafka

    @GetMapping(value = "/consumer")
    public String producer(@RequestParam("message") String message) {
        logger.info("Controller: Inside the producer method");
        return "Message receiver to the Kafka Topic message-topic Successfully";
    }
}
