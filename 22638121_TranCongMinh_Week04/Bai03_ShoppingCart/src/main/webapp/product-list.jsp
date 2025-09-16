<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 9/15/2025
  Time: 1:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Danh sách sản phẩm</title>
    <!-- Tailwind CSS CDN -->
    <script src="https://cdn.tailwindcss.com"></script>
    <style>
        .product-card {
            width: 250px; /* kích thước cố định */
            min-height: 380px;
        }
        .product-img {
            height: 180px;
            object-fit: contain;
            border-radius: 0.5rem;
        }
    </style>
</head>
<body class="bg-gray-100">
<div class="container mx-auto p-4">
    <p class="mb-4">
        <a href="${pageContext.request.contextPath}/cart"
           class="text-blue-600 hover:text-blue-800 font-medium">
            Xem giỏ hàng
        </a>
    </p>
    <div class="flex flex-wrap justify-start gap-4">
        <c:forEach items="${products}" var="p">
            <div class="product-card bg-white rounded-lg shadow-md p-4 flex flex-col">
                <b class="text-lg text-gray-800">${p.model}</b>
                <img src="images/${p.imgURL}" class="product-img w-full mt-2">
                <span class="mt-2 text-gray-700 font-medium">Giá: ${p.price} $</span>
                <form action="${pageContext.request.contextPath}/cart" method="post" class="mt-3">
                    <input type="number" min="1" value="1" name="quantity"
                           class="w-16 border rounded px-1 py-1">
                    <input type="hidden" name="id" value="${p.id}">
                    <input type="hidden" name="price" value="${p.price}">
                    <input type="hidden" name="model" value="${p.model}">
                    <input type="hidden" name="action" value="add">
                    <input type="submit" name="addToCart" value="Thêm vào giỏ"
                           class="bg-green-600 text-white px-3 py-1 rounded mt-2 hover:bg-green-700 w-full">
                </form>
                <a href="${pageContext.request.contextPath}/product?id=${p.id}"
                   class="text-blue-600 hover:text-blue-800 block mt-3 text-center">
                    Chi tiết sản phẩm
                </a>
            </div>
        </c:forEach>
    </div>
</div>
</body>
</html>
