<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="contextRoot" value="${pageContext.request.contextPath}" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="/css/styles.css">
<title>Tasks</title>
</head>
<body>
	<div align="center">
		<table border="1" cellpadding="5">
			<tr>
				<th>Name</th>
				<th>Main Task</th>
				<th>Status</th>
				<th>Project</th>
				<th>Employees</th>
				<th>Qualifications</th>
			</tr>
			<tr>
				<form:form action="${contextRoot}/AddTask" method="post"
					modelAttribute="task">
					<tr>
						<td><form:input path="taskName" /></td>
						<td><select name=mainTask>
								<option selected>${mainTask.taskName}</option>
								<c:forEach var="newMainTask" items="${newMainTasks}">
									<option>${newMainTask.taskName}</option>
								</c:forEach>
						</select></td>
						<td><form:input path="status" /></td>
						<td><select name=project>
								<option selected>${project.projName}</option>
								<c:forEach var="newProject" items="${newProjects}">
									<option>${newProject.projName}</option>
								</c:forEach>
						</select></td>
						<td><select name="employees" multiple="multiple">
								<c:forEach var="employee" items="${employees}">
									<option selected>${employee.id}</option>
								</c:forEach>
								<c:forEach var="newEmployee" items="${newEmployees}">
									<option>${newEmployee.id}</option>
								</c:forEach>
						</select></td>
						<td><select name="qualifications" multiple="multiple">
								<c:forEach var="qualification" items="${qualifications}">
									<option selected>${qualification.qualificationName}</option>
								</c:forEach>
								<c:forEach var="newQualification" items="${newQualifications}">
									<option>${newQualification.qualificationName}</option>
								</c:forEach>
						</select></td>
					</tr>
					<button type="submit">Submit</button>
				</form:form>
			</tr>
		</table>
	</div>


</body>
</html>
