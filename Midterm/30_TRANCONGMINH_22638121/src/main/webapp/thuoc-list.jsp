<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Danh sách Thuốc</title>
</head>
<body>
<jsp:include page="index.jsp"/>
<h2>Danh sách Thuốc</h2>
<table border="1" cellpadding="5">
  <tr>
    <th>Mã thuốc</th>
    <th>Tên thuốc</th>
    <th>Giá</th>
    <th>Năm SX</th>
    <th>Mã loại</th>
    <th>Hành động</th>
  </tr>
  <c:forEach var="t" items="${thuocs}">
    <tr>
      <td>${t.maThuoc}</td>
      <td>${t.tenThuoc}</td>
      <td>${t.gia}</td>
      <td>${t.namSX}</td>
      <td>${t.maLoai}</td>
      <td>
        <a href="thuoc?action=edit&id=${t.maThuoc}">Sửa</a> |
        <a href="thuoc?action=delete&id=${t.maThuoc}"
           onclick="return confirm('Bạn có chắc muốn xóa thuốc này?');">Xóa</a>
      </td>
    </tr>
  </c:forEach>
</table>

</body>
</html>