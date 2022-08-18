package com.employee.demo.model;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "task")
public class Task {

	@Id
	@Column(name = "taskname")
	private String taskName;
	@ManyToMany(mappedBy ="tasks")  //so instead of creating 2 new tables, it creates only 1.
	private List<Employee> employees;
	@ManyToMany
	private List<Qualification> qualifications;
	@ManyToOne(cascade = CascadeType.ALL)
	private Task mainTask;
	@Column(name = "status")
	private String status;
	@ManyToOne
	private Project project;
	@Override
	public String toString() {
		List<Integer> empList = employees.stream().map(employees -> employees.getId()).collect(Collectors.toList());
		List<String> qualList = qualifications.stream().map(qualifications -> qualifications.getQualificationName())
				.collect(Collectors.toList());
		
		return "Task [taskName=" + taskName + ", employee_IDs=" + empList + ", qualification_Names=" + qualList
				+ ", mainTask_Name=" + mainTask.getTaskName() + ", status=" + status + ", project_Name=" + project.getProj_name() + "]";
	}
	
	
}
