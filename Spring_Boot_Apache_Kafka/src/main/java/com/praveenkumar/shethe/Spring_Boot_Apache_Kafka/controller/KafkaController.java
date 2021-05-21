package com.praveenkumar.shethe.Spring_Boot_Apache_Kafka.controller;

import com.praveenkumar.shethe.Spring_Boot_Apache_Kafka.service.KafkaSender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Created by Praveenkumar on 5/17/2021.
 */
@RestController
@RequestMapping(value = "/kafkamessage")
public class KafkaController {

    @Autowired
    KafkaSender kafkaSender;

    private final Logger logger = LogManager.getLogger(KafkaController.class);

    @GetMapping(value = "/producer")
    public String producer(@RequestParam("message") String message) {
        logger.info("Controller: Inside the producer method");
        kafkaSender.send(message);
        return "Message sent to the Kafka Topic message-topic Successfully";
    }

}
