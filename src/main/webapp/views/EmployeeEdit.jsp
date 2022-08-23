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
<title>Employee Edit</title>
<style type="text/css">
#test {
	
}
</style>
</head>
<body>

	<div align="center">
		<table border="1" cellpadding="5">

			<tr>
				<th>ID</th>
				<th>Name</th>
				<th>Birth Date</th>
				<th>Phone Number</th>
				<th>Gender</th>
				<th>Department</th>
				<th>Tasks</th>
				<th>Qualifications</th>
				<th>Projects</th>
			</tr>
			<form:form action="${contextRoot}/EditEmployee" method="post"
				modelAttribute="employee">
				<tr>
					<td><form:input path="id"/></td>
					<td><form:input path="name"
							placeholder="Enter the employee's name" /></td>
					<td><form:input path="birthDate"
							placeholder="Enter the birth date" /></td>
					<td><form:input path="phone"
							placeholder="Enter the phone number" /></td>
					<td><form:input path="gender" placeholder="Enter the gender" /></td>
					<td><select name="department">
							<option selected>${selDepartment.depName}</option>
							<c:forEach var="dep" items="${newDepartments}">
								<option>${dep.depName}</option>
							</c:forEach>
					</select></td>
					<td><select name="tasks" multiple="multiple">
							<c:forEach var="task" items="${tasks}">
								<option selected>${task.taskName}</option>
							</c:forEach>
							<c:forEach var="newTask" items="${newTasks}">
								<option>${newTask.taskName}</option>
							</c:forEach>
					</select></td>
					<td><select name="qualifications" multiple="multiple">
							<c:forEach var="qualification" items="${qualifications}">
								<option selected>${qualification.qualificationName}</option>
							</c:forEach>
							<c:forEach var="qualification" items="${newQualifications}">
								<option>${qualification.qualificationName}</option>
							</c:forEach>
					</select></td>
					<td><select name="projects" multiple="multiple">
							<c:forEach var="project" items="${projects}">
								<option selected>${project.projName}</option>
							</c:forEach>
							<c:forEach var="newProject" items="${newProjects}">
								<option>${newProject.projName}</option>
							</c:forEach>
					</select></td>
				</tr>
				<button type="submit">Submit</button>
			</form:form>

		</table>
	</div>



</body>

</html>
