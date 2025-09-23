<%--
  Created by IntelliJ IDEA.
  User: Student
  Date: 9/22/2025
  Time: 2:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Employee Information</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-3">
    <img src="${pageContext.request.contextPath}/images/HRbanner.jpg" height="200px" width="100%">

    <h2 class="mt-3">Employee Form</h2>
    <form action="${pageContext.request.contextPath}/employees" method="post">
        <!-- hidden id (nếu edit) -->
        <input type="hidden" name="id" value="${employee.id}"/>

        <div class="mb-3">
            <label class="form-label">Name:</label>
            <input type="text" class="form-control" name="name" value="${employee.name}"/>
        </div>

        <div class="mb-3">
            <label class="form-label">Salary:</label>
            <input type="text" class="form-control" name="salary"
                   value="<fmt:formatNumber value='${employee.salary}' type='number' pattern='#########'/>"/>
        </div>

        <div class="mb-3">
            <label class="form-label">Department:</label>
            <select name="departmentId" class="form-select">
                <c:forEach var="dep" items="${departments}">
                    <option value="${dep.id}"
                            <c:if test="${employee != null && employee.departmentId == dep.id}">
                                selected
                            </c:if>>
                            ${dep.name}
                    </option>
                </c:forEach>
            </select>
        </div>

        <input type="submit" class="btn btn-primary" value="Save"/>
        <a class="btn btn-secondary" href="${pageContext.request.contextPath}/employees">Cancel</a>
    </form>
</div>
</body>
</html>