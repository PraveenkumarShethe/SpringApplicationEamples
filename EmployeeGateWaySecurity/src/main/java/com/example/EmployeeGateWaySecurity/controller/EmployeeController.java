package com.example.EmployeeGateWaySecurity.controller;

import com.example.EmployeeGateWaySecurity.model.Employee;
import com.example.EmployeeGateWaySecurity.service.EmployeeGateWayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by Praveenkumar on 6/4/2021.
 */
@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    Logger logger = LoggerFactory.getLogger(GateWayController.class);

    @Autowired
    private EmployeeGateWayService employeeGateWayService;

    @RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public Flux<Employee> getAllEmployee() {
        return employeeGateWayService.getAllEmployee();
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.OK)
    public void employeeLogin(@RequestBody Employee employee) {
        logger.info(" Employee login invoked");
        logger.info(employee.getUsername() + "  ===============================  " + employee.getPassword());
        employeeGateWayService.createNewEmployee(employee);
        logger.info(employee.getUsername() + "  ===============================  " + employee.getPassword());
    }

}
