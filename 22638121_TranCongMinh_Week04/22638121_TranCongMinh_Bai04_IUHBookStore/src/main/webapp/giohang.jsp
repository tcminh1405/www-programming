<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 9/15/2025
  Time: 10:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Giỏ Hàng - IUH Bookstore</title>
  <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 font-sans">

<!-- Header -->
<header class="bg-cover bg-center h-28 flex items-center justify-between px-8 shadow"
        style="background-image: url('${pageContext.request.contextPath}/images/header-bg.png');">
  <h1 class="text-3xl font-extrabold text-white tracking-wide drop-shadow-lg">
    IUH BOOKSTORE
  </h1>
  <nav>
    <ul class="flex space-x-8 text-white font-medium text-lg">
      <li><a href="${pageContext.request.contextPath}/books" class="hover:text-yellow-300">Home</a></li>
      <li><a href="#" class="hover:text-yellow-300">Examples</a></li>
      <li><a href="#" class="hover:text-yellow-300">Services</a></li>
      <li><a href="${pageContext.request.contextPath}/books" class="hover:text-yellow-300">Products</a></li>
      <li><a href="#" class="hover:text-yellow-300">Contact</a></li>
      <!-- Cart link -->
      <a href="${pageContext.request.contextPath}/cart"
         class="ml-6 px-4 py-2 bg-yellow-400 text-black font-semibold rounded-lg hover:bg-yellow-500">
        Cart (${cart.items.size()})
      </a>
    </ul>
  </nav>
</header>

<!-- Main content -->
<div class="max-w-7xl mx-auto mt-8 grid grid-cols-1 md:grid-cols-4 gap-6 px-4">
  <!-- Sidebar -->
  <aside class="md:col-span-1 bg-gray-200/60 p-6 rounded-xl shadow-md">
    <h4 class="text-xl font-bold mb-3 border-b pb-2">About Us</h4>
    <p class="text-sm text-gray-700 mb-6">
      IUH Bookstore cung cấp sách chất lượng cho sinh viên và giảng viên. <br>
      <a href="#" class="text-blue-600 hover:underline">Read More →</a>
    </p>

    <h4 class="text-xl font-bold mb-3 border-b pb-2">Search Site</h4>
    <form action="books" method="get">
      <input type="text" name="search" placeholder="Nhập tên sách..."
             class="w-full border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-400 mb-3">
      <button class="w-full bg-blue-600 text-white py-2 rounded-lg font-semibold hover:bg-blue-700">
        Tìm kiếm
      </button>
    </form>
  </aside>

  <!-- Cart section -->
  <main class="md:col-span-3">
    <h2 class="text-2xl font-bold mb-6 text-gray-800">Giỏ Hàng</h2>

    <c:if test="${empty cart.items}">
      <p class="text-gray-600">Giỏ hàng của bạn đang trống.</p>
      <a href="books" class="mt-4 inline-block px-4 py-2 bg-blue-600 text-white rounded-lg hover:bg-blue-700">
        Tiếp tục mua sắm
      </a>
    </c:if>

    <c:if test="${not empty cart.items}">
      <div class="overflow-x-auto">
        <table class="min-w-full bg-white rounded-lg shadow">
          <thead class="bg-gray-100 border-b">
          <tr class="text-left text-gray-700 font-semibold">
            <th class="px-4 py-3">Tên sách</th>
            <th class="px-4 py-3">Giá</th>
            <th class="px-4 py-3">Số lượng</th>
            <th class="px-4 py-3">Thành tiền</th>
            <th class="px-4 py-3">Xóa</th>
          </tr>
          </thead>
          <tbody>
          <c:forEach var="item" items="${cart.items}">
            <tr class="border-b hover:bg-gray-50">
              <td class="px-4 py-3">${item.book.title}</td>
              <td class="px-4 py-3 text-red-600 font-medium">${item.book.price} VNĐ</td>
              <td class="px-4 py-3">
                <form action="cart" method="post" class="flex items-center space-x-2">
                  <input type="hidden" name="bookId" value="${item.book.id}">
                  <input type="number" name="quantity" value="${item.quantity}" min="1"
                         class="w-16 border border-gray-300 rounded-lg px-2 py-1 text-center">
                  <input type="hidden" name="action" value="update">
                  <button type="submit"
                          class="px-3 py-1 bg-blue-500 text-white rounded-lg hover:bg-blue-600 text-sm">
                    Update
                  </button>
                </form>
              </td>
              <td class="px-4 py-3 font-semibold">${item.subtotal} VNĐ</td>
              <td class="px-4 py-3">
                <form action="cart" method="post">
                  <input type="hidden" name="bookId" value="${item.book.id}">
                  <input type="hidden" name="action" value="remove">
                  <button type="submit"
                          class="px-3 py-1 bg-red-500 text-white rounded-lg hover:bg-red-600 text-sm">
                    Remove
                  </button>
                </form>
              </td>
            </tr>
          </c:forEach>
          <tr class="bg-gray-50 font-bold">
            <td colspan="3" class="px-4 py-3 text-right">Tổng cộng:</td>
            <td class="px-4 py-3 text-red-600">${cart.total} VNĐ</td>
            <td></td>
          </tr>
          </tbody>
        </table>
      </div>

      <!-- Buttons -->
      <div class="mt-6 flex space-x-4">
        <form action="cart" method="post">
          <input type="hidden" name="action" value="clear">
          <button type="submit"
                  class="px-4 py-2 bg-yellow-400 text-black font-semibold rounded-lg hover:bg-yellow-500">
            Clear Cart
          </button>
        </form>
        <a href="thanhtoan.jsp"
           class="px-4 py-2 bg-green-600 text-white font-semibold rounded-lg hover:bg-green-700">
          Checkout
        </a>
        <a href="books"
           class="px-4 py-2 bg-gray-500 text-white rounded-lg hover:bg-gray-600">
          Continue Shopping
        </a>
      </div>
    </c:if>
  </main>
</div>

</body>
</html>

