package com.employee.demo.model;

import java.sql.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "employee")
public class Employee {

	@Id
	//@GeneratedValue(strategy=GenerationType.AUTO) 
	@Column(name = "id", unique=true, nullable = false)
    @Basic(fetch = FetchType.EAGER)
	private  int id;
	@Column(name = "name")
	private String name;
	@Column(name = "birthDate")
	private Date birthDate;
	@Column(name = "phone")
	private int phone;
	@Column(name = "gender")
	private String gender;
	@ManyToMany()
	private List<Task> tasks;
	@ManyToMany()
	private List<Qualification> qualifications;
	@ManyToOne()
	@JoinColumn(name = "department", nullable=false)
	private Department department;
	@OneToMany(mappedBy = "manager", cascade=CascadeType.ALL)
	private List<Project> projects;

	@Override
	public String toString() {
		List<String> tasksList = tasks.stream().map(tasks -> tasks.getTaskName()).collect(Collectors.toList());
		List<String> qualList = qualifications.stream().map(qualifications -> qualifications.getQualificationName())
				.collect(Collectors.toList());
		List<String> projList = projects.stream().map(projects -> projects.getProjName()).collect(Collectors.toList());
		return "Employee [id=" + id + ", name=" + name + ", birthDate=" + birthDate + ", phone=" + phone + ", gender="
				+ gender + ", task_Names=" + tasksList + ", qualification_Names=" + qualList + ", department="
				+ department.getDepName() + ", project_Names=" + projList + "]";
	}

}
