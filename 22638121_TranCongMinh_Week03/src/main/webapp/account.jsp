<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 9/15/2025
  Time: 12:38 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.*, iuh.fit.se.trancongminh_week03.model.Account" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Danh sách tài khoản</title>
  <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
</head>
<body class="container mt-5">

<h2 class="mb-4">Danh sách tài khoản đã đăng ký</h2>

<table class="table table-bordered table-striped">
  <thead class="table-dark">
  <tr>
    <th>Họ</th>
    <th>Tên</th>
    <th>Email</th>
    <th>Mật khẩu</th>
    <th>Ngày sinh</th>
  </tr>
  </thead>
  <tbody>
  <%
    List<Account> accounts = (List<Account>) request.getAttribute("accounts");
    if (accounts != null && !accounts.isEmpty()) {
      for (Account acc : accounts) {
  %>
  <tr>
    <td><%= acc.getFirstname() %></td>
    <td><%= acc.getLastname() %></td>
    <td><%= acc.getEmail() %></td>
    <td><%= acc.getPassword() %></td>
    <td><%= acc.getDateOfBirth() %></td>
  </tr>
  <%
    }
  } else {
  %>
  <tr>
    <td colspan="5" class="text-center">Chưa có tài khoản nào</td>
  </tr>
  <%
    }
  %>
  </tbody>
</table>

<a href="register.jsp" class="btn btn-primary">Thêm tài khoản mới</a>

</body>
</html>

