package com.employee.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.employee.demo.model.Employee;
import com.employee.demo.model.Qualification;
import com.employee.demo.model.Task;
import com.employee.demo.repository.QualificationRepository;

@ComponentScan("com.employee.demo.repository")
@Service
public class QualificationService {

	@Autowired
	QualificationRepository qualificationRepository;
	
	public List<Qualification> findAll(){
		return qualificationRepository.findAll();
	}
	
	public Qualification findById(String name) {
		if(qualificationRepository.findById(name).isPresent()) {
			return qualificationRepository.findById(name).get();
		}
		return null;
	}
	
	public void delete(String name) {
		Qualification qualification = findById(name);
		qualificationRepository.delete(qualification);
	}
	
	public void save(Qualification qualification) {
		qualificationRepository.save(qualification);
	}

}
