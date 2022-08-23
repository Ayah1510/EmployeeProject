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
<title>Projects</title>
</head>
<body>
	<div align="center">
		<table border="1" cellpadding="5">
			<tr>
				<th>Name</th>
				<th>Details</th>
				<th>Manager</th>
				<th>Tasks</th>
			</tr>
			<tr>
				<form:form action="${contextRoot}/AddProject" method="post"
					modelAttribute="project">
					<tr>
						<td><form:input path="projName" /></td>
						<td><form:textarea path="details" /></td>
						<td><select name=manager>
								<option selected>${manager.id}</option>
								<c:forEach var="newManager" items="${newManagers}">
									<option>${newManager.id}</option>
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
					</tr>
					<button type="submit">Submit</button>
				</form:form>
			</tr>
		</table>
	</div>


</body>
</html>
