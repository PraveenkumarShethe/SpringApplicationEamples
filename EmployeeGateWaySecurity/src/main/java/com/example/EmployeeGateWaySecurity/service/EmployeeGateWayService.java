package com.example.EmployeeGateWaySecurity.service;

import com.example.EmployeeGateWaySecurity.model.Employee;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Created by Praveenkumar on 6/3/2021.
 */
@Service
public class EmployeeGateWayService {

    public void createNewEmployee(Employee employee) {
        WebClient webClient = WebClient.create("http://localhost:8088");
        Mono<Employee> createEmployee = webClient.post().uri("/employee")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(Mono.just(employee), Employee.class).retrieve().bodyToMono(Employee.class);
    }

    public Flux<Employee> getAllEmployee() {
        WebClient webClient = WebClient.create("http://localhost:8088");
        Flux<Employee> listEmployee = webClient.get().uri("/employee").retrieve().bodyToFlux(Employee.class);
        return listEmployee;
    }
}
