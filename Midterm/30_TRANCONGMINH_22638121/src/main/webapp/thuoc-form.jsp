<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <title>Form Thuốc</title>
</head>
<body>
<jsp:include page="index.jsp"/>
<h2><c:choose>
  <c:when test="${thuoc != null}">Cập nhật Thuốc</c:when>
  <c:otherwise>Thêm mới Thuốc</c:otherwise>
</c:choose></h2>

<form action="thuoc" method="post">
  <!-- hidden để phân biệt thêm / sửa -->
  <input type="hidden" name="id" value="${thuoc.maThuoc}"/>

  <label for="ten">Tên thuốc:</label>
  <input type="text" id="ten" name="ten" value="${thuoc.tenThuoc}" required/><br/><br/>

  <label for="gia">Giá:</label>
  <input type="number" step="0.01" id="gia" name="gia" value="${thuoc.gia}" required/><br/><br/>

  <label for="namsx">Năm sản xuất:</label>
  <input type="number" id="namsx" name="namsx"
         value="<c:if test='${thuoc != null}'>${thuoc.namSX}</c:if>" required/><br/><br/>

  <label for="maloai">Loại thuốc:</label>
  <select id="maloai" name="maloai" required>
    <c:forEach var="l" items="${loaiThuocs}">
      <option value="${l.maLoai}"
              <c:if test="${thuoc != null && thuoc.maLoai == l.maLoai}">selected</c:if>>
          ${l.tenLoai}
      </option>
    </c:forEach>
  </select><br/><br/>

  <input type="submit" value="Lưu"/>
  <a href="thuoc?action=list">Hủy</a>
</form>
</body>
</html>
