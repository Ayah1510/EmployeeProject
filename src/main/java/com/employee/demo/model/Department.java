package com.employee.demo.model;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name = "department")
public class Department {

	@Id
	@Column(name = "depname")
	private String depName;
	@OneToMany(mappedBy = "department", cascade=CascadeType.ALL)
	private List<Employee> employees;
	// mapped by: to let the program know that this field already exists in the
	// employee table.
	// cascade type all -> since the relation is composition, thus when we remove
	// the department, the employees need to be removed.
	// -> it includes operations like: PERSIST, REMOVE, REFRESH, MERGE, DETACH.
	@Override
	public String toString() {
		List<Integer> list = employees.stream().map(employees -> employees.getId()).collect(Collectors.toList());
		return "Department [depName=" + depName + ", employee_Ids=" + list + "]";
	}
	
	
}
