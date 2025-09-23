<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 9/22/2025
  Time: 9:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>Danh sách phòng ban</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">

<div class="max-w-6xl mx-auto mt-4 bg-white p-6 rounded shadow">
    <img src="${pageContext.request.contextPath}/images/HRbanner.jpg" class="w-full h-48 object-cover mx-auto mt-4">

    <h2 class="text-lg font-bold mb-4">Department List</h2>

    <!-- Nút thêm -->
    <div class="mb-3">
        <a href="departments?action=new"
           class="px-3 py-1 bg-green-600 text-white rounded">Thêm phòng ban</a>
    </div>

    <!-- Form tìm kiếm -->
    <form action="departments" method="get" class="flex space-x-2 mb-4">
        <input type="hidden" name="action" value="searchbyname"/>
        Tìm phòng ban:
        <input type="text" name="deptName" placeholder="Nhập tên phòng ban"
               class="flex-1 border rounded px-2 py-1"/>
        <button type="submit" class="px-3 py-1 bg-blue-500 text-white rounded">Tìm</button>
    </form>

    <!-- Bảng danh sách -->
    <table class="w-full border-collapse border border-gray-600 ">
        <thead>
        <tr class="bg-gray-900 text-white">
            <th class="border border-gray-300 px-2 py-1">ID</th>
            <th class="border border-gray-300 px-2 py-1">Tên phòng ban</th>
            <th class="border border-gray-300 px-2 py-1">Hành động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="dept" items="${departments}">
            <tr class="text-center">
                <td class="border border-gray-300 px-2 py-1">${dept.id}</td>
                <td class="border border-gray-300 px-2 py-1">${dept.name}</td>
                <td class="border border-gray-300 px-2 py-1 space-x-2">
                    <a href="departments?action=edit&id=${dept.id}"
                       class="px-2 py-1 bg-blue-400 text-white rounded">Sửa</a>
                    <a href="departments?action=delete&id=${dept.id}"
                       class="px-2 py-1 bg-red-500 text-white rounded"
                       onclick="return confirm('Bạn có chắc muốn xóa?')">Xóa</a>
                    <a href="employees?action=viewbyid&deptId=${dept.id}"
                       class="px-2 py-1 bg-green-600 text-white rounded">Employees</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>


