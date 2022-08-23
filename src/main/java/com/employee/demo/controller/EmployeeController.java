package com.employee.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.employee.demo.model.Department;
import com.employee.demo.model.Employee;
import com.employee.demo.model.Project;
import com.employee.demo.model.Qualification;
import com.employee.demo.model.Task;
import com.employee.demo.service.DepartmentService;
import com.employee.demo.service.EmployeeService;
import com.employee.demo.service.ProjectService;
import com.employee.demo.service.QualificationService;
import com.employee.demo.service.TaskService;

import lombok.extern.log4j.Log4j2;

@Controller
@ComponentScan("com.employee.demo.service")
@ComponentScan("com.employee.demo.service.ProjectService")
@ComponentScan("com.employee.demo.service.TaskService")

public class EmployeeController {

	@Autowired
	EmployeeService employeeService;

	@Autowired
	ProjectService projectService;

	@Autowired
	TaskService taskService;

	@Autowired
	QualificationService qualificationService;

	@Autowired
	DepartmentService departmentService;

	@RequestMapping("/")
	public ModelAndView home() {
		ModelAndView mav = new ModelAndView("homePage");
		mav.addObject("message", "List of Employees:");
		List<Employee> employees = employeeService.findAll();
		mav.addObject("employees", employees);
		// System.out.println(employees);
		return mav;

	}

	@RequestMapping("/Employee")
	public ModelAndView viewEmployee() {
		ModelAndView mav = new ModelAndView("Employee");
		List<Employee> employees = employeeService.findAll();
		mav.addObject("employees", employees);
		return mav;
	}

	@RequestMapping("/Project")
	public ModelAndView viewProject() {
		ModelAndView mav = new ModelAndView("Project");
		List<Project> projects = projectService.findAll();
		mav.addObject("projects", projects);
		// System.out.println(projects);
		return mav;
	}

	@RequestMapping("/Task")
	public ModelAndView viewTask() {
		ModelAndView mav = new ModelAndView("Task");
		List<Task> tasks = taskService.findAll();
		mav.addObject("tasks", tasks);
		return mav;
	}

	@RequestMapping("/Qualification")
	public ModelAndView viewQualification() {
		ModelAndView mav = new ModelAndView("Qualification");
		List<Qualification> qualifications = qualificationService.findAll();
		mav.addObject("qualifications", qualifications);
		// System.out.println(qualifications);
		return mav;
	}

	@RequestMapping("/Department")
	public ModelAndView viewDepartment() {
		ModelAndView mav = new ModelAndView("Department");
		List<Department> departments = departmentService.findAll();
		mav.addObject("departments", departments);
		// System.out.println(departments);
		return mav;
	}

	@RequestMapping(value = "/ProjectEmployee/{id}")
	public ModelAndView findProjectsFromEmployees(@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("Project");
		Employee employee = employeeService.findById(id);
		List<Project> projects = employee.getProjects();
		mav.addObject("projects", projects);
		return mav;
	}

	@RequestMapping(value = "/TaskEmployee/{id}")
	public ModelAndView findTasksFromEmployees(@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("Task");
		Employee employee = employeeService.findById(id);
		List<Task> tasks = employee.getTasks();
		mav.addObject("tasks", tasks);
		return mav;
	}

	@RequestMapping(value = "/QualificationEmployee/{id}")
	public ModelAndView findQualificationsFromEmployees(@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("Qualification");
		Employee employee = employeeService.findById(id);
		List<Qualification> qualifications = employee.getQualifications();
		mav.addObject("qualifications", qualifications);
		return mav;
	}

	@RequestMapping(value = "/EmployeeTask/{taskName}")
	public ModelAndView findEmployeesFromTask(@PathVariable("taskName") String taskName) {
		ModelAndView mav = new ModelAndView("Employee");
		Task task = taskService.findById(taskName);
		List<Employee> employees = task.getEmployees();
		mav.addObject("employees", employees);
		return mav;
	}

	@RequestMapping(value = "/EmployeeDepartment/{depName}")
	public ModelAndView findEmployeesFromDepartment(@PathVariable("depName") String depName) {
		ModelAndView mav = new ModelAndView("Employee");
		Department department = departmentService.findById(depName);
		List<Employee> employees = department.getEmployees();
		mav.addObject("employees", employees);
		return mav;
	}

	@RequestMapping(value = "/QualificationTask/{taskName}")
	public ModelAndView findQualificationsFromTask(@PathVariable("taskName") String taskName) {
		ModelAndView mav = new ModelAndView("Qualification");
		Task task = taskService.findById(taskName);
		List<Qualification> qualifications = task.getQualifications();
		mav.addObject("qualifications", qualifications);
		return mav;
	}

	@RequestMapping(value = "/TaskProject/{projName}")
	public ModelAndView findTasksFromProject(@PathVariable("projName") String projName) {
		ModelAndView mav = new ModelAndView("Task");
		Project project = projectService.findById(projName);
		List<Task> tasks = project.getTasks();
		mav.addObject("tasks", tasks);
		return mav;
	}

	@RequestMapping(value = "/EditEmployee/{id}")
	public ModelAndView editEmployee(@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("EmployeeEdit");
		Employee employee = employeeService.findById(id);
		List<Qualification> qualifications = employee.getQualifications();
		List<Qualification> allQualifications = qualificationService.findAll();
		List<Qualification> newQualifications = allQualifications.stream()
				.filter(str -> qualifications.contains(str) ^ allQualifications.contains(str))
				.collect(Collectors.toList());
		Department department = employee.getDepartment();
		List<Department> allDepartments = departmentService.findAll();
		List<Department> newDepartments = allDepartments;
		if (department != null)
			newDepartments = allDepartments.stream()
					.filter(str -> allDepartments.contains(str) ^ department.equals(str)).collect(Collectors.toList());
		List<Project> projects = employee.getProjects();
		List<Project> allProjects = projectService.findAll();
		List<Project> newProjects = allProjects.stream()
				.filter(str -> projects.contains(str) ^ allProjects.contains(str)).collect(Collectors.toList());
		List<Task> tasks = employee.getTasks();
		List<Task> allTasks = taskService.findAll();
		List<Task> newTasks = allTasks.stream().filter(str -> tasks.contains(str) ^ allTasks.contains(str))
				.collect(Collectors.toList());

		Predicate<Project> manage = project -> project.getManager() == null;
		newProjects = newProjects.stream().filter(manage).collect(Collectors.toList());

		mav.addObject("employee", employee);
		mav.addObject("qualifications", qualifications);
		mav.addObject("newQualifications", newQualifications);
		mav.addObject("selDepartment", department);
		mav.addObject("newDepartments", newDepartments);
		mav.addObject("projects", projects);
		mav.addObject("newProjects", newProjects);
		mav.addObject("tasks", tasks);
		mav.addObject("newTasks", newTasks);
		return mav;
	}

	@RequestMapping(value = "/EditTask/{taskName}")
	public ModelAndView editTask(@PathVariable("taskName") String taskName) {
		ModelAndView mav = new ModelAndView("TaskEdit");
		Task task = taskService.findById(taskName);
		List<Employee> employees = task.getEmployees();
		List<Employee> allEmployees = employeeService.findAll();
		List<Employee> newEmployees = allEmployees.stream()
				.filter(str -> employees.contains(str) ^ allEmployees.contains(str)).collect(Collectors.toList());
		Project project = task.getProject();
		List<Project> allProjects = projectService.findAll();
		List<Project> newProjects = allProjects;
		if (project != null)
			allProjects.stream().filter(str -> project.equals(str) ^ allProjects.contains(str))
					.collect(Collectors.toList());
		Task mainTask = task.getMainTask();
		List<Task> allMainTasks = taskService.findAll();
		List<Task> newMainTasks = allMainTasks;
		if (mainTask != null)
			allMainTasks.stream().filter(str -> mainTask.equals(str) ^ allMainTasks.contains(str))
					.collect(Collectors.toList());
		List<Qualification> qualifications = task.getQualifications();
		List<Qualification> allQualifications = qualificationService.findAll();
		List<Qualification> newQualifications = allQualifications.stream()
				.filter(str -> qualifications.contains(str) ^ allQualifications.contains(str))
				.collect(Collectors.toList());


//		for (Task temp : newMainTasks) {
//			while (temp.getMainTask() != null) {
//				if (mainTask == temp.getMainTask())
//					newMainTasks.remove(temp);
//				temp = temp.getMainTask();
//			}
//		}
		newMainTasks.remove(task);
		mav.addObject("task", task);
		mav.addObject("employees", employees);
		mav.addObject("newEmployees", newEmployees);
		mav.addObject("project", project);
		mav.addObject("newProjects", newProjects);
		mav.addObject("mainTask", mainTask);
		mav.addObject("newMainTasks", newMainTasks);
		mav.addObject("qualifications", qualifications);
		mav.addObject("newQualifications", newQualifications);
		return mav;
	}

	@RequestMapping(value = "/EditProject/{projName}")
	public ModelAndView editProject(@PathVariable("projName") String projName) {
		ModelAndView mav = new ModelAndView("ProjectEdit");
		Project project = projectService.findById(projName);
		List<Task> tasks = project.getTasks();
		List<Task> allTasks = taskService.findAll();
		List<Task> newTasks = allTasks.stream().filter(str -> tasks.contains(str) ^ allTasks.contains(str))
				.collect(Collectors.toList());

		Employee manger = project.getManager();
		List<Employee> allManagers = employeeService.findAll();
		List<Employee> newManagers = allManagers;
		if (manger != null)
			newManagers = allManagers.stream().filter(str -> manger.equals(str) ^ allManagers.contains(str))
					.collect(Collectors.toList());
		mav.addObject("project", project);
		mav.addObject("tasks", tasks);
		mav.addObject("newTasks", newTasks);
		mav.addObject("manager", manger);
		mav.addObject("newManagers", newManagers);
		return mav;
	}

	@RequestMapping(value = "/EditDepartment/{depName}")
	public ModelAndView editDepartment(@PathVariable("depName") String depName) {
		ModelAndView mav = new ModelAndView("DepartmentEdit");
		Department department = departmentService.findById(depName);
		List<Employee> employees = department.getEmployees();
		List<Employee> allEmployees = employeeService.findAll();
		List<Employee> newEmployees = allEmployees.stream()
				.filter(str -> employees.contains(str) ^ allEmployees.contains(str)).collect(Collectors.toList());
		mav.addObject("department", department);
		mav.addObject("employees", employees);
		mav.addObject("newEmployees", newEmployees);
		return mav;
	}

	@RequestMapping(value = "/EditEmployee", method = RequestMethod.POST)
	public String saveEmployee(@ModelAttribute("Employee") Employee employee) {
		employeeService.save(employee);
		return "redirect:/";
	}
	@RequestMapping(value = "/AddEmployee", method = RequestMethod.POST)
	public String saveEmployee2(@ModelAttribute("Employee") Employee employee) {
		employeeService.save(employee);
		return "redirect:/";
	}

	@RequestMapping(value = "/EditDepartment", method = RequestMethod.POST)
	public String saveDepartment(@ModelAttribute("Department") Department department) {
		departmentService.save(department);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/AddDepartment", method = RequestMethod.POST)
	public String saveDepartment2(@ModelAttribute("Department") Department department) {
		departmentService.save(department);
		return "redirect:/";
	}

	@RequestMapping(value = "/EditProject", method = RequestMethod.POST)
	public String saveProject(@ModelAttribute("Project") Project project) {
		projectService.save(project);
		return "redirect:/";
	}
	@RequestMapping(value = "/AddProject", method = RequestMethod.POST)
	public String saveProject2(@ModelAttribute("Project") Project project) {
		projectService.save(project);
		return "redirect:/";
	}

	@RequestMapping(value = "/EditTask", method = RequestMethod.POST)
	public String saveTask(@ModelAttribute("Task") Task task) {
		taskService.save(task);
		return "redirect:/";
	}
	@RequestMapping(value = "/AddTask", method = RequestMethod.POST)
	public String saveTask2(@ModelAttribute("Task") Task task) {
		taskService.save(task);
		return "redirect:/";
	}

	@RequestMapping(value = "/AddQualification", method = RequestMethod.POST)
	public String saveQualification(@ModelAttribute("Qualification") Qualification qualification) {
		qualificationService.save(qualification);
		return "redirect:/";
	}
	
	@RequestMapping(value = "/DeleteEmployee/{id}")
	public String DeleteEmployee(@PathVariable("id") int id) {
		employeeService.delete(id);
		return "redirect:/";
	}

	@RequestMapping(value = "/DeleteDepartment/{depName}")
	public String DeleteDepartment(@PathVariable("depName") String depName) {
		departmentService.delete(depName);
		return "redirect:/";
	}

	@RequestMapping(value = "/DeleteTask/{taskName}")
	public String DeleteTask(@PathVariable("taskName") String taskName) {
		taskService.delete(taskName);
		return "redirect:/";
	}

	@RequestMapping(value = "/DeleteQualification/{qualificationName}")
	public String DeleteQualification(@PathVariable("qualificationName") String qualificationName) {
		qualificationService.delete(qualificationName);
		return "redirect:/";
	}

	@RequestMapping(value = "/DeleteProject/{projName}")
	public String DeleteProject(@PathVariable("projName") String projName) {
		projectService.delete(projName);
		return "redirect:/";
	}
	
	@RequestMapping("/AddEmployee")
	public ModelAndView addEmployee() {
		ModelAndView mav = new ModelAndView("EmployeeEdit");
		mav.addObject("employee", new Employee());
		List<Qualification> allQualifications = qualificationService.findAll();
		List<Department> allDepartments = departmentService.findAll();
		List<Project> allProjects = projectService.findAll();
		List<Task> allTasks = taskService.findAll();
		Predicate<Project> manage = project -> project.getManager() == null;
		allProjects = allProjects.stream().filter(manage).collect(Collectors.toList());
		mav.addObject("newQualifications", allQualifications);
		mav.addObject("newDepartments", allDepartments);
		mav.addObject("newProjects", allProjects);
		mav.addObject("newTasks", allTasks);
		return mav;
	}
	@RequestMapping("/AddProject")
	public ModelAndView addProject() {
		ModelAndView mav = new ModelAndView("ProjectAdd");
		mav.addObject("project", new Project());
		List<Task> allTasks = taskService.findAll();
		List<Employee> allManagers = employeeService.findAll();
		mav.addObject("newTasks", allTasks);
		mav.addObject("newManagers", allManagers);
		return mav;
	}
	@RequestMapping("/AddDepartment")
	public ModelAndView addDepatment() {
		ModelAndView mav = new ModelAndView("DepartmentAdd");
		mav.addObject("department", new Department());
		List<Employee> allEmployees = employeeService.findAll();
		mav.addObject("newEmployees", allEmployees);
		return mav;
	}
	@RequestMapping("/AddTask")
	public ModelAndView addTask() {
		ModelAndView mav = new ModelAndView("TaskAdd");
		mav.addObject("task", new Task());
		List<Employee> allEmployees = employeeService.findAll();
		List<Project> allProjects = projectService.findAll();
		List<Task> allMainTasks = taskService.findAll();
		List<Qualification> allQualifications = qualificationService.findAll();

		mav.addObject("newEmployees", allEmployees);
		mav.addObject("newProjects", allProjects);
		mav.addObject("newMainTasks", allMainTasks);
		mav.addObject("newQualifications", allQualifications);
		return mav;
	}
	@RequestMapping("/AddQualification")
	public ModelAndView addQualification() {
		ModelAndView mav = new ModelAndView("QualificationAdd");
		mav.addObject("qualification", new Qualification());
		return mav;
	}
	
	
}
