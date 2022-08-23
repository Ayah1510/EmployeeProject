package com.employee.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.employee.demo.model.Employee;
import com.employee.demo.model.Project;
import com.employee.demo.repository.ProjectRepository;

@ComponentScan("com.employee.demo.repository")
@Service
public class ProjectService {


	@Autowired
	ProjectRepository projectRepository;
	
	public List<Project> findAll(){
		return projectRepository.findAll();
	}
	
	public Project findById(String name) {
		if(projectRepository.findById(name).isPresent()) {
			return projectRepository.findById(name).get();
		}
		return null;
	}
	
	public void save(Project project) {
		projectRepository.save(project);
	}
	
	public void delete(String name) {
		Project project = findById(name);
		projectRepository.delete(project);
	}

}
