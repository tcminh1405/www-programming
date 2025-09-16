<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 8/25/2025
  Time: 2:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Send Mail</title>

</head>
<body>
<form action="sendMail" method="post" enctype="multipart/form-data">
    Người nhận: <input type="email" name="to" required><br>
    Tiêu đề: <input type="text" name="subject" required><br>
    Nội dung:<br>
    <textarea name="content" rows="5" cols="40"></textarea><br>
    File đính kèm: <input type="file" name="file"><br>
    <input type="submit" value="Gửi mail">
</form>
</body>
</html>
