package com.employee.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;

@Data
@Entity
@Table(name = "qualification")
public class Qualification {

	@Id
	@Column(name = "qualificationname")
	private String qualificationName;
}
