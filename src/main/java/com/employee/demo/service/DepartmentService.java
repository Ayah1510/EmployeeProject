package com.employee.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.employee.demo.model.Department;
import com.employee.demo.repository.DepartmentRepository;

@ComponentScan("com.employee.demo.repository")
@Service
public class DepartmentService {

	@Autowired
	DepartmentRepository departmentRepository;
	
	public List<Department> findAll(){
		return departmentRepository.findAll();
	}
	
	public Department findById(String name) {
		if(departmentRepository.findById(name).isPresent()) {
			return departmentRepository.findById(name).get();
		}
		return null;
	}

	public void save(Department department) {
		departmentRepository.save(department);
	}
	
	public void delete(String name) {
		Department department = findById(name);
		departmentRepository.delete(department);
	}
}
