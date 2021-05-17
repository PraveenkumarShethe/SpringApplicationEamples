package com.praveenkumar.shethe.Spring_Boot_Apache_Kafka;

import com.praveenkumar.shethe.Spring_Boot_Apache_Kafka.service.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Praveenkumar on 5/17/2021.
 */
@RestController
@RequestMapping(value = "/javainuse-kafka/")
public class KafkaController{

    @Autowired
    KafkaSender kafkaSender;

    @GetMapping(value = "/producer")
    public String producer(@RequestParam("message") String message) {
        kafkaSender.send(message);

        return "Message sent to the Kafka Topic java_in_use_topic Successfully";
    }

}
