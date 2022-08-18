package com.employee.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import com.employee.demo.model.Employee;

@EnableJpaRepositories(basePackages = {"com.example.demo"})
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer>{

}
