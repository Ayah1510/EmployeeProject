package com.employee.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import com.employee.demo.model.Employee;
import com.employee.demo.model.Task;
import com.employee.demo.repository.TaskRepository;

@ComponentScan("com.employee.demo.repository")
@Service
public class TaskService {

	@Autowired
	TaskRepository taskRepository;
	
	public List<Task> findAll(){
		return taskRepository.findAll();
	}
	
	public Task findById(String name) {
		if(taskRepository.findById(name).isPresent()) {
			return taskRepository.findById(name).get();
		}
		return null;
	}
	
	public void save(Task task) {
		taskRepository.save(task);
	}
	
	public void delete(String name) {
		Task task = findById(name);
		taskRepository.delete(task);
	}

}
