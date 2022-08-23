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
<title>Departments</title>
</head>
<body>
	<div align="center">
		<table border="1" cellpadding="5">
			<tr>
				<th>Name</th>
				<th>Employees</th>
			</tr>
			<tr>
				<form:form action="${contextRoot}/EditDepartment" method="post"
					modelAttribute="department">
					<tr>
						<td><form:hidden path="depName"/>${depName}</td>
						<td><select name="employees" multiple="multiple">
								<c:forEach var="employee" items="${employees}">
									<option selected>${employee.id}</option>
								</c:forEach>
								<c:forEach var="newEmployee" items="${newEmployees}">
									<option>${newEmployee.id}</option>
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
