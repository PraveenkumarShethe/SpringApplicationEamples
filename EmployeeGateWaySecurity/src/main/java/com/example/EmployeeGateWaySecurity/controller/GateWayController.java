package com.example.EmployeeGateWaySecurity.controller;

import com.example.EmployeeGateWaySecurity.model.Employee;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Created by Praveenkumar on 5/31/2021.
 */
@RestController
@RequestMapping(value = "/auth")
public class GateWayController {

    Logger logger = LoggerFactory.getLogger(GateWayController.class);

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void employeeLogin(@RequestBody Employee employee){
        logger.info(" Employee login invoked");
        logger.info(employee.getUsername() + "  ===============================  " + employee.getPassword());

    }
}
