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
  <title>Chi Tiết Sách - IUH Bookstore</title>
  <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100">

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
      <!-- Link tới giỏ hàng -->
      <a href="${pageContext.request.contextPath}/cart"
         class="ml-6 px-4 py-2 bg-yellow-400 text-black font-semibold rounded-lg hover:bg-yellow-500">
        Cart
      </a>
    </ul>
  </nav>
</header>

<!-- Main content -->
<div class="max-w-7xl mx-auto mt-6 grid grid-cols-1 md:grid-cols-4 gap-6 px-4">
  <!-- Sidebar -->
  <aside class="md:col-span-1 bg-white/50 bg-opacity-50 p-6 rounded-xl shadow-md">
    <h4 class="text-xl font-bold mb-3 border-b pb-2">About Us</h4>
    <p class="text-sm text-gray-600 mb-6">
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

  <!-- Book details -->
  <main class="md:col-span-3">
    <c:if test="${book != null}">
      <h2 class="text-2xl font-bold mb-4">${book.title} - Tác giả: ${book.author}</h2>
      <div class="bg-white rounded-lg shadow p-6 flex flex-col md:flex-row gap-6">
        <!-- Image -->

        <div class="flex-shrink-0">
          <img src="${pageContext.request.contextPath}/images/${book.image}"
               alt="${book.title}"
               class="w-72 h-96 object-contain rounded-lg shadow">
        </div>

        <!-- Info -->
        <div class="flex flex-col justify-between">
          <div>
            <p class="text-gray-700"><span class="font-semibold">Author:</span> ${book.author}</p>
            <p class="text-gray-700"><span class="font-semibold">Price (VNĐ):</span>
              <span class="text-red-600 font-bold">${book.price}</span>
            </p>
            <p class="text-gray-700"><span class="font-semibold">Quantity:</span> ${book.quantity}</p>
            <p class="text-gray-700 mt-2"><span class="font-semibold">Description:</span> ${book.description}</p>
          </div>

          <!-- Buttons -->
          <div class="mt-6 flex space-x-4">
            <a href="books"
               class="px-4 py-2 bg-gray-500 text-white rounded-lg hover:bg-gray-600">
              Back to Product List
            </a>
            <form action="cart" method="post">
              <input type="hidden" name="action" value="addBook">
              <input type="hidden" name="bookId" value="${book.id}">
              <button type="submit"
                      class="px-4 py-2 bg-green-600 text-black font-semibold rounded-lg hover:bg-green-700">
                Add to Cart
              </button>
            </form>
          </div>
        </div>
      </div>
    </c:if>

    <c:if test="${book == null}">
      <p class="text-red-600">Book not found.</p>
    </c:if>
  </main>
</div>

</body>
</html>
