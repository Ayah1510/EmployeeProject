package com.employee.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.employee.demo.model.Employee;
import com.employee.demo.repository.EmployeeRepository;

@ComponentScan("com.employee.demo.repository")
@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;
	
	public List<Employee> findAll(){
		return employeeRepository.findAll();
	}
	
	public Employee findById(int id) {
		if(employeeRepository.findById(id).isPresent()) {
			return employeeRepository.findById(id).get();
		}
		return null;
	}
	
	public void save(Employee employee) {
		employeeRepository.save(employee);
	}
	
	public void delete(int id) {
		Employee employee = findById(id);
		employeeRepository.delete(employee);
	}

}
