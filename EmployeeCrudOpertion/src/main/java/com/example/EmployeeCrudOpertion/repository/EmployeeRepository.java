package com.example.EmployeeCrudOpertion.repository;

import com.example.EmployeeCrudOpertion.model.Employee;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Praveenkumar on 5/30/2021.
 */
@Repository
public interface EmployeeRepository extends CrudRepository<Employee, Long> {
}
