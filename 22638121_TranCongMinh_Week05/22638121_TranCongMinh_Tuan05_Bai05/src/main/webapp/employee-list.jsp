<%--
  Created by IntelliJ IDEA.
  User: Student
  Date: 9/22/2025
  Time: 2:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
    <title>Employees</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-3">
    <img src="${pageContext.request.contextPath}/images/HRbanner.jpg" height="200px" width="100%">
    <h2 class="mt-3">Employees List</h2>
    <a class="btn btn-success mb-2" href="${pageContext.request.contextPath}/employees?action=new">Add Employee</a>

    <table class="table table-bordered table-striped">
        <tr class="table-dark">
            <th>ID</th>
            <th>Name Employee</th>
            <th>Salary</th>
            <th>Dept</th>
            <th>Action</th>
        </tr>

        <c:forEach var="emp" items="${employees}">
            <tr>
                <td>${emp.id}</td>
                <td>${emp.name}</td>
                <td><fmt:formatNumber value="${emp.salary}" type="number" pattern="#,###"/> VNĐ</td>
                <td>${emp.departmentId}</td>
                <td>
                    <a class="btn btn-sm btn-primary"
                       href="${pageContext.request.contextPath}/employees?action=edit&id=${emp.id}">Edit</a>
                    <a class="btn btn-sm btn-danger"
                       href="${pageContext.request.contextPath}/employees?action=delete&id=${emp.id}"
                       onclick="return confirm('Are you sure to delete this employee?')">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>

    <a class="btn btn-secondary" href="${pageContext.request.contextPath}/departments">Departments</a>
</div>
</body>
</html>

