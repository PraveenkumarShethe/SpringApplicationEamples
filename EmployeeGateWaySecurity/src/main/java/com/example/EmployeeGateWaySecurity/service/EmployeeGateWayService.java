package com.example.EmployeeGateWaySecurity.service;

import com.example.EmployeeGateWaySecurity.model.Employee;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

/**
 * Created by Praveenkumar on 6/3/2021.
 */
@Service
public class EmployeeGateWayService {

    public void createNewEmployee(Employee employee) {
        WebClient webClient = WebClient.create("http://localhost:8088");
        Flux<Employee> createEmployee = webClient.post().uri("/employee").exchange()
                .flatMapMany(clientResponse -> clientResponse.bodyToFlux(Employee.class));
//                .expectStatus().isCreated()
//                .expectHeader().valueEquals("Content-Type", "application/json")
//                .expectBody().jsonPath("field").isEqualTo("value");

        System.out.println("====================================================================");
    }

    public Flux<Employee> getAllEmployee() {
        WebClient webClient = WebClient.create("http://localhost:8088");
        Flux<Employee> listEmployee = webClient.get().uri("/employee").retrieve().bodyToFlux(Employee.class);
        return listEmployee;
    }
}
