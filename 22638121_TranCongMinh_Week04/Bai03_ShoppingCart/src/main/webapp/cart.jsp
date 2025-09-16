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
    <title>Giỏ hàng</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">
<div class="container mx-auto p-4">
    <h1 class="text-2xl font-bold mb-4">Giỏ hàng</h1>

    <!-- Empty Cart State -->
    <c:if test="${empty sessionScope.cart.items}">
        <div class="bg-white p-6 rounded-lg shadow-md text-center">
            <p class="text-gray-600 text-lg">Giỏ hàng trống!</p>
            <a href="${pageContext.request.contextPath}/products"
               class="mt-4 inline-block text-blue-600 hover:text-blue-800 underline">
                Tiếp tục mua
            </a>
        </div>
    </c:if>

    <!-- Cart with Items -->
    <c:if test="${not empty sessionScope.cart.items}">
        <div class="bg-white p-6 rounded-lg shadow-md">
            <table class="w-full text-left">
                <thead>
                <tr class="border-b">
                    <th class="py-2 px-4">Model</th>
                    <th class="py-2 px-4">Số lượng</th>
                    <th class="py-2 px-4">Giá</th>
                    <th class="py-2 px-4">Tổng phụ</th>
                    <th class="py-2 px-4">Hành động</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="item" items="${sessionScope.cart.items}">
                    <tr class="border-b">
                        <td class="py-2 px-4">${item.product.model}</td>
                        <td class="py-2 px-4">
                            <form action="${pageContext.request.contextPath}/cart" method="post" class="inline">
                                <input type="number" name="quantity" value="${item.quantity}"
                                       class="w-16 p-1 border rounded" min="1">
                                <input type="hidden" name="productId" value="${item.product.id}">
                                <input type="hidden" name="action" value="update">
                                <button type="submit" class="ml-2 bg-blue-500 text-white px-2 py-1 rounded hover:bg-blue-600">
                                    Cập nhật
                                </button>
                            </form>
                        </td>
                        <td class="py-2 px-4">${item.product.price} $</td>
                        <td class="py-2 px-4">${item.subtotal} $</td>
                        <td class="py-2 px-4">
                            <form action="${pageContext.request.contextPath}/cart" method="post" class="inline">
                                <input type="hidden" name="productId" value="${item.product.id}">
                                <input type="hidden" name="action" value="remove">
                                <button type="submit" class=" bg-red-500 text-white rounded px-2 py-1 hover:bg-red-600">Xóa</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
                <tfoot>
                <tr>
                    <td colspan="3" class="py-2 px-4 font-bold">Tổng:</td>
                    <td class="py-2 px-4">${sessionScope.cart.total} $</td>
                    <td></td>
                </tr>
                </tfoot>
            </table>

            <div class="mt-4 flex justify-between">
                <form action="${pageContext.request.contextPath}/cart" method="post" class="inline">
                    <input type="hidden" name="action" value="clear">
                    <button type="submit" class="bg-red-500 text-white px-4 py-2 rounded hover:bg-red-600">
                        Xóa hết giỏ hàng
                    </button>
                </form>
                <a href="${pageContext.request.contextPath}/products"
                   class="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600">
                    Tiếp tục mua
                </a>
            </div>
        </div>
    </c:if>
</div>
</body>
</html>