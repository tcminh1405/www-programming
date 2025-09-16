<%--
  Created by IntelliJ IDEA.
  User: Student
  Date: 8/18/2025
  Time: 2:40 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h2>Login</h2>
<form action="login" method="post">
    <label>Username:</label>
    <input type="text" name="username" required /><br/>
    <label>Password:</label>
    <input type="password" name="password" required /><br/>
    <input type="submit" value="Login" />
</form>

<p style="color:red;">
    ${param.error == 'true' ? 'Invalid credentials!' : ''}
</p>
</body>
</html>
