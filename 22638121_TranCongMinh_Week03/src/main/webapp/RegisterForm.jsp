<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 9/8/2025
  Time: 11:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Register Form</title>
    <!-- Thêm Tailwind qua CDN -->
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 min-h-screen flex items-center justify-center">
<div class="bg-white shadow-lg rounded-lg w-full max-w-5xl p-8">
    <h1 class="text-3xl text-center font-bold text-blue-600 mb-6">
        User Registration Form
    </h1>
    <form action="${pageContext.request.contextPath}/registerform" method="post" class="space-y-6">
        <!-- First + Last Name -->
        <div class="flex space-x-2">
            <input type="text" name="firstname" placeholder="First Name"
                   class="w-1/2 border rounded px-3 py-2 focus:outline-none focus:ring focus:ring-blue-200"/>
            <input type="text" name="lastname" placeholder="Last Name"
                   class="w-1/2 border rounded px-3 py-2 focus:outline-none focus:ring focus:ring-blue-200"/>
        </div>
        <!-- Email -->
        <input type="email" name="email" placeholder="Email"
               class="w-full border rounded px-3 py-2 focus:outline-none focus:ring focus:ring-blue-200"/>

        <!-- Password -->
        <input type="password" name="password" placeholder="Password"
               class="w-full border rounded px-3 py-2 focus:outline-none focus:ring focus:ring-blue-200"/>

        <!-- Birthday -->
        <!-- Password -->
        <input type="date" name="dateofbirth" placeholder="Day of birth"
               class="w-full border rounded px-3 py-2 focus:outline-none focus:ring focus:ring-blue-200"/>
        <!-- Gender -->
        <div class="flex items-center space-x-4">
            <label><input type="radio" name="gender" value="Female" class="mr-1"/> Female</label>
            <label><input type="radio" name="gender" value="Male" class="mr-1"/> Male</label>
        </div>
        <!-- Submit -->
        <button type="submit"
                class="w-full bg-blue-600 text-white font-semibold py-2 rounded hover:bg-blue-700 transition">
            Sign Up
        </button>
    </form>
</div>

</body>
</html>
