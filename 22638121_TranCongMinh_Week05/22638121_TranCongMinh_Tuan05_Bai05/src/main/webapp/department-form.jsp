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
    <title>Form phòng ban</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">
<img src="${pageContext.request.contextPath}/images/HRbanner.jpg" class="w-full h-48 object-cover mx-10 mt-4">
<div class="max-w-md mx-auto mt-10 bg-white p-6 rounded shadow">
    <h2 class="text-lg font-bold mb-4">
        <c:if test="${department != null}">Chỉnh sửa phòng ban</c:if>
        <c:if test="${department == null}">Thêm phòng ban</c:if>
    </h2>

    <form action="departments" method="post" class="space-y-4">
        <!-- Hidden ID -->
        <input type="hidden" name="id" value="${department != null ? department.id : ''}"/>

        <!-- Tên phòng ban -->
        <div>
            <label class="block mb-1">Tên phòng ban</label>
            <input
                    type="text"
                    name="name"
                    value="${department != null ? department.name : ''}"
                    class="w-full border rounded px-2 py-1"
                    required
            />
        </div>

        <!-- Buttons -->
        <div class="flex justify-between">
            <a href="departments" class="px-3 py-1 bg-gray-300 rounded">Quay lại</a>
            <button type="submit" class="px-3 py-1 bg-blue-500 text-white rounded">Lưu</button>
        </div>
    </form>
</div>

</body>
</html>
