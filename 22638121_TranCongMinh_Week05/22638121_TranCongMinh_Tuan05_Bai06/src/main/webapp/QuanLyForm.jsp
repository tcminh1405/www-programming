
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 9/23/2025
  Time: 9:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Quản lý tin tức</h2>
<c:forEach var="tt" items="${listTinTuc}">
    <p>
            ${tt.tieuDe}
    <form action="quanlytintuc" method="post" style="display:inline">
        <input type="hidden" name="maTT" value="${tt.maTT}"/>
        <input type="hidden" name="maDM" value="${param.maDM}"/>
        <button type="submit">Xóa</button>
    </form>
    </p>
</c:forEach>

</body>
</html>
