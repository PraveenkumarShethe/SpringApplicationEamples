package com.example.EmployeeGateWaySecurity.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Praveenkumar on 5/31/2021.
 */
@RestController
@RequestMapping(value = "/auth")
public class GateWayController {

    Logger logger = LoggerFactory.getLogger(GateWayController.class);

}
