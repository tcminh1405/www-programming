<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Danh sách Loại Thuốc</title>
</head>
<body>
<jsp:include page="index.jsp"/>
<h2>Danh sách Loại Thuốc</h2>
<table border="1" cellpadding="5">
  <tr>
    <th>Mã loại</th>
    <th>Tên loại</th>
    <th>Xem thuốc</th>
  </tr>
  <c:forEach var="l" items="${loaiThuocs}">
    <tr>
      <td>${l.maLoai}</td>
      <td>${l.tenLoai}</td>
      <td><a href="thuoc?action=byloai&maloai=${l.maLoai}">Xem</a></td>
    </tr>
  </c:forEach>
</table>
<br>
<a href="index.jsp">Quay lại trang chủ</a>
</body>
</html>
