package com.example.EmployeeCrudOpertion.service;

import com.example.EmployeeCrudOpertion.model.Employee;
import com.example.EmployeeCrudOpertion.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Praveenkumar on 5/30/2021.
 */
@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployee(){
        return (List<Employee>) employeeRepository.findAll();
    }

    public void saveEmployee(Employee employee) {
        employeeRepository.save(employee);
    }
}
