package com.employee.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.employee.demo.model.Employee;
import com.employee.demo.service.EmployeeService;

@Controller
@ComponentScan("com.employee.demo.service")
public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@RequestMapping("/")
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView("homePage");
		mav.addObject("message", "List of Employees:");
		List<Employee> employees = employeeService.findAll();
		mav.addObject("employees", employees);
		System.out.println(employees);
		return mav;
	}

}
