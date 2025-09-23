
<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 9/23/2025
  Time: 9:11 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Thêm tin tức</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
    <script>
        function validateForm() {
            let maTT = document.getElementById("maTT").value.trim();
            let tieuDe = document.getElementById("tieuDe").value.trim();
            let noiDung = document.getElementById("noiDungTT").value.trim();
            let lienKet = document.getElementById("lienKet").value.trim();
            let maDM = document.getElementById("maDM").value.trim();

            // Regex kiểm tra liên kết
            let regexLink = /^http:\/\/.+/;
            // Nội dung <= 255 ký tự
            let regexNoiDung = /^.{1,255}$/;

            if (maTT === "" || tieuDe === "" || noiDung === "" || lienKet === "" || maDM === "") {
                alert("Vui lòng nhập đầy đủ thông tin.");
                return false;
            }

            if (!regexLink.test(lienKet)) {
                alert("Liên kết phải bắt đầu bằng http://");
                return false;
            }

            if (!regexNoiDung.test(noiDung)) {
                alert("Nội dung không được quá 255 ký tự.");
                return false;
            }

            return true;
        }
    </script>
</head>
<body class="container mt-4">
<h2>Thêm tin tức</h2>
<form action="tintucform" method="post" onsubmit="return validateForm()">
    <div class="mb-3">
        <label class="form-label">Mã tin tức</label>
        <input type="number" name="maTT" id="maTT" class="form-control" required>
    </div>
    <div class="mb-3">
        <label class="form-label">Tiêu đề</label>
        <input type="text" name="tieuDe" id="tieuDe" class="form-control" required>
    </div>
    <div class="mb-3">
        <label class="form-label">Nội dung</label>
        <textarea name="noiDungTT" id="noiDungTT" class="form-control" rows="4" cols="30" required></textarea>
    </div>
    <div class="mb-3">
        <label class="form-label">Liên kết</label>
        <input type="text" name="lienKet" id="lienKet" class="form-control" required>
    </div>
    <div class="mb-3">
        <label class="form-label">Danh mục</label>
        <input type="number" name="maDM" id="maDM" class="form-control"
               value="${param.maDM}" required>
    </div>
    <button type="submit" class="btn btn-primary">Thêm</button>
    <a href="danhsachtintuc?maDM=${param.maDM}" class="btn btn-secondary">Hủy</a>
</form>
</body>
</html>

