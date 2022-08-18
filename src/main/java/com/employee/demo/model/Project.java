package com.employee.demo.model;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "project")
public class Project {

	@Id
	@Column(name = "proj_name")
	private String proj_name;
	@Column(name = "details")
	private String details;
	@ManyToOne
	@JoinColumn(name = "manager")
	private Employee manager;
	@OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
	private List<Task> tasks;

	@Override
	public String toString() {
		List<String> tasksList = tasks.stream().map(tasks -> tasks.getTaskName()).collect(Collectors.toList());
		return "Project [proj_name=" + proj_name + ", details=" + details + ", manager=" + manager.getId()
				+ ", task_Names=" + tasksList + "]";
	}

}
