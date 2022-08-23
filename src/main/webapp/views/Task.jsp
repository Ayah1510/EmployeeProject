<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/css/styles.css">
<title>Tasks</title>
</head>
<body>
	<div class="menu">
		<ul>
			<li><a href="${ContextRoot}/Employee/${employee.id}">Employees</a></li>
			<li><a href="${ContextRoot}/Project/${employee.id}">Projects</a></li>
			<li><a href="${ContextRoot}/Task/${employee.id}">Tasks</a></li>
			<li><a href="${ContextRoot}/Department/${employee.id}">Departments</a></li>
			<li><a href="${ContextRoot}/Qualification/${employee.id}">Qualifications</a></li>
		</ul>
	</div>
	<div align="center">
		<h1>List of Tasks</h1>
		<table border="1" cellpadding="5">
			<tr>
				<th>Name</th>
				<th>Main Task</th>
				<th>Status</th>
				<th>Project</th>
				<th>Employees</th>
				<th>Qualifications</th>
				<th>Edit</th>
				<th>Delete</th>

			</tr>
			<c:forEach var="task" items="${tasks}">
				<tr>
					<td><c:out value="${task.taskName}" /></td>
					<td><c:out default="-" value="${task.mainTask.getTaskName()}" /></td>
					<td><c:out value="${task.status}" /></td>
					<td><c:out default="-" value="${task.project.getProjName()}" /></td>
					<td><a href="${ContextRoot}/EmployeeTask/${task.taskName}">Employees</a></td>
					<td><a
						href="${ContextRoot}/QualificationTask/${task.taskName}">Qualifications</a></td>
					<td><a href="${ContextRoot}/EditTask/${task.taskName}">Edit</a></td>
					<td><a href="${ContextRoot}/DeleteTask/${task.taskName}">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
		<button><a href="${contextRoot}/AddTask">Add New Task</a></button>
	
</body>
</html>