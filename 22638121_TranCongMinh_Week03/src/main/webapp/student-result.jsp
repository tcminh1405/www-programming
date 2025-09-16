<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 9/8/2025
  Time: 1:04 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Student Result</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 min-h-screen flex items-center justify-center">
<div class="bg-white shadow-lg rounded-lg w-full max-w-5xl p-8">
    <h1 class="text-center text-3xl font-bold text-blue-600">
        Student Infomation
    </h1>
    <p>Họ tên: ${student.firstname} ${student.lastname}</p>
    <p>Ngày sinh: ${student.dateofbirth}</p>
    <p>Email: ${student.email}</p>
    <p>Sđt: ${student.phone}</p>
    <p>Giới tính: ${student.gender}</p>
    <p>Địa chỉ: ${student.address}</p>
    <p>Thành phố: ${student.city}</p>
    <p>Pin Code: ${student.pincode}</p>
    <p>State: ${student.state}</p>
    <p>Quốc gia: ${student.country}</p>
    <p>Sở thích:</p>
    <ul>
        <c:forEach var="h" items="${student.hobbies}">
            <li>${h}</li>
        </c:forEach>
    </ul>
    <p>Khóa học đăng kí: ${student.course}</p>
</div>
</body>
</html>
