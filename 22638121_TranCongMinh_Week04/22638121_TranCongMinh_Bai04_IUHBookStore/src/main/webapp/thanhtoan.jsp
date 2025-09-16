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
  <title>Thanh Toán - IUH Bookstore</title>
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

  <!-- Checkout form -->
  <main class="md:col-span-3 bg-white p-6 rounded-xl shadow-md">
    <h2 class="text-2xl font-bold mb-6 text-gray-800">Thanh Toán</h2>

    <form action="thanhtoan" method="post" class="space-y-4">
      <div>
        <label class="block text-gray-700 font-medium mb-1">Họ và tên</label>
        <input type="text" name="fullname" required
               class="w-full border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-400">
      </div>

      <div>
        <label class="block text-gray-700 font-medium mb-1">Địa chỉ giao hàng</label>
        <input type="text" name="address" required
               class="w-full border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-400">
      </div>

      <div>
        <label class="block text-gray-700 font-medium mb-1">Tổng tiền</label>
        <input type="text" name="total" value="${cart.total}" readonly
               class="w-full border border-gray-300 rounded-lg px-4 py-2 bg-gray-100 text-gray-700">
      </div>

      <div>
        <label class="block text-gray-700 font-medium mb-1">Phương thức thanh toán</label>
        <select name="paymentMethod"
                class="w-full border border-gray-300 rounded-lg px-4 py-2 focus:outline-none focus:ring-2 focus:ring-blue-400">
          <option value="paypal">PayPal</option>
          <option value="atm">ATM/Debit/Visa/Master card</option>
        </select>
      </div>

      <div class="flex space-x-4 mt-4">
        <button type="submit"
                class="px-4 py-2 bg-green-600 text-white font-semibold rounded-lg hover:bg-green-700">
          Thanh toán
        </button>
        <a href="cart"
           class="px-4 py-2 bg-gray-500 text-white rounded-lg hover:bg-gray-600">
          Hủy
        </a>
      </div>
    </form>
  </main>
</div>

</body>
</html>
