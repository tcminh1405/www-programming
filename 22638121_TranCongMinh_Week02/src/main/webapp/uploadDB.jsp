<%--
  Created by IntelliJ IDEA.
  User: ADMIN
  Date: 8/25/2025
  Time: 2:52 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Upload DB</title>
</head>
<body>
<form action="uploadDtb" method="post" enctype="multipart/form-data">
  <h1>Upload files to database</h1>
  First Name: <input type="text" name="firstName"> <br>
  Last Name: <input type="text" name="lastName"> <br>
  Portrait Photo: <input type="file" name="portrait" accept="image/*"> <br>
  <input type="submit" value="Save">
</form>

</body>
</html>
