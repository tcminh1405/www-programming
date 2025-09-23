<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 9/23/2025
  Time: 9:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<html>
<head>
    <title>Danh sách tin tức</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container mt-4">

<h1 class="mb-4">Danh sách tin tức theo danh mục</h1>

<!-- Danh sách danh mục -->
<h3>Danh mục</h3>
<ul class="list-group mb-4">
    <c:forEach var="dm" items="${dsDanhMuc}">
        <li class="list-group-item">
            <a href="danhsachtintuc?maDM=${dm.maDM}">
                    ${dm.tenDM}
            </a>
        </li>
    </c:forEach>
</ul>

<!-- Nếu đã chọn danh mục thì hiển thị tin tức -->
<c:if test="${not empty listTinTuc}">
    <h3 class="text-primary">Tin tức thuộc danh mục</h3>

    <a href="TinTucForm.jsp?maDM=${param.maDM}" class="btn btn-success mb-3">
        + Thêm tin tức</a>

    <table class="table table-bordered">
        <thead class="table-dark">
        <tr>
            <th>Mã TT</th>
            <th>Tiêu đề</th>
            <th>Nội dung</th>
            <th>Liên kết</th>
            <th>Hành động</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="tt" items="${listTinTuc}">
            <tr>
                <td>${tt.maTT}</td>
                <td>${tt.tieuDe}</td>
                <td>${tt.noiDungTT}</td>
                <td><a href="${tt.lienKet}" target="_blank">${tt.lienKet}</a></td>
                <td>
                    <a href="quanlytintuc?maTT=${tt.maTT}&maDM=${param.maDM}"
                       class="btn btn-danger btn-sm"
                       onclick="return confirm('Bạn có chắc muốn xóa tin tức này?');">
                        Xóa
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</c:if>

</body>
</html>

