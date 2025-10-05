<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 9/15/2025
  Time: 1:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <title>${product.model} - Chi tiết sản phẩm</title>
    <!-- Tailwind CSS -->
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">
<div class="max-w-4xl mx-auto p-6">
    <!-- Link quay lại -->
    <p class="mb-4">
        <a href="${pageContext.request.contextPath}/products"
           class="text-blue-600 hover:underline">&larr; Quay lại danh sách</a>
    </p>

    <!-- Thông tin sản phẩm -->
    <div class="bg-white rounded-lg shadow p-6 flex gap-6">
        <!-- Ảnh sản phẩm -->
        <div class="w-1/3">
            <img src="${pageContext.request.contextPath}/images/${product.imgURL}"
                 alt="${product.model}"
                 class="w-full h-auto rounded border"
                 onerror="this.src='https://via.placeholder.com/300'">
        </div>

        <!-- Nội dung chi tiết -->
        <div class="w-2/3">
            <h1 class="text-2xl font-bold text-gray-800 mb-2">${product.model}</h1>
            <p class="text-gray-600 mb-4">${product.description}</p>
            <p class="text-lg text-red-600 font-semibold mb-2">Giá: ${product.price} $</p>
            <p class="text-sm text-gray-500 mb-4">Còn lại: ${product.quantity} sản phẩm</p>

            <!-- Form thêm vào giỏ -->
            <form action="${pageContext.request.contextPath}/cart" method="post" class="flex items-center gap-3">
                <input type="number" name="quantity" min="1" value="1"
                       class="w-20 border rounded px-2 py-1">
                <input type="hidden" name="id" value="${product.id}">
                <input type="hidden" name="price" value="${product.price}">
                <input type="hidden" name="model" value="${product.model}">
                <input type="hidden" name="action" value="add">
                <button type="submit"
                        class="bg-green-600 text-white px-4 py-2 rounded hover:bg-green-700">
                    Thêm vào giỏ
                </button>
            </form>
        </div>
    </div>
</div>
</body>
</html>



