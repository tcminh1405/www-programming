<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="vi">
<head>
  <meta charset="UTF-8">
  <title>Danh Sách Sách - IUH Bookstore</title>
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
      <!-- Link tới giỏ hàng -->
      <a href="${pageContext.request.contextPath}/cart"
         class="ml-6 px-4 py-2 bg-yellow-400 text-black font-semibold rounded-lg hover:bg-yellow-500">
        Cart
      </a>
    </ul>

  </nav>
</header>

<!-- Main content -->
<main class="container mx-auto mt-8 px-4 grid grid-cols-12 gap-8">

  <!-- Sidebar -->
  <aside class="col-span-12 md:col-span-3 bg-white/50 bg-opacity-50 p-6 rounded-xl shadow-md">
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



  <!-- Danh sách sách -->
  <section class="col-span-12 md:col-span-9 grid grid-cols-1 sm:grid-cols-2 lg:grid-cols-3 gap-6">
    <c:forEach var="book" items="${books}">
      <div class="bg-white rounded-xl shadow hover:shadow-xl transition transform hover:-translate-y-1 flex flex-col">
        <div class="p-4 flex flex-col flex-grow">
          <h5 class="font-bold text-lg mb-1 line-clamp-2">${book.title}</h5>
          <!-- Ảnh cố định cùng size -->
          <img src="${pageContext.request.contextPath}/images/${book.image}"
               alt="${book.title}" class="w-full h-64 object-contain rounded-t-xl">
          <p class="text-sm text-gray-500 mb-1">Author: ${book.author}</p>
          <p class="text-red-600 font-semibold mb-2">Price: ${book.price} VNĐ</p>
          <p class="text-sm text-gray-600 mb-3">Quantity: ${book.quantity}</p>

          <div class="mt-auto flex justify-between items-center">
            <a href="book?id=${book.id}"
               class="px-4 py-2 bg-blue-500 text-white rounded-lg text-sm hover:bg-blue-600">
              Product Details
            </a>
            <form action="cart" method="post">
              <input type="hidden" name="action" value="add">
              <input type="hidden" name="id" value="${book.id}">
              <button type="submit"
                      class="px-4 py-2 bg-green-600 text-white rounded-lg text-sm hover:bg-green-700">
                Add to Cart
              </button>
            </form>
          </div>
        </div>
      </div>
    </c:forEach>
  </section>

</main>

</body>
</html>
