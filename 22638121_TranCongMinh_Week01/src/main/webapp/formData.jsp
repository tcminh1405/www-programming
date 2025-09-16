<%--
  Created by IntelliJ IDEA.
  User: Student
  Date: 8/18/2025
  Time: 1:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FormData</title>
</head>
<body>
<div>
    <h2>Register</h2>
    <form action="${pageContext.request.contextPath}/processFormUpload" method="post">
        <p>
            <label>Name <span style="color: red">*</span></label><br />
            <input type="text" name="firstName" placeholder="First" required />
            <input type="text" name="lastName" placeholder="Last" required />
        </p>

        <p>
            <label>Gender <span style="color: red">*</span></label><br />
            <input type="radio" name="gender" value="Male" checked /> Male
            <input type="radio" name="gender" value="Female" /> Female
        </p>

        <p>
            <label>Date of Birth</label><br />
            <input type="date" name="dob" />
        </p>

        <p>
            <label>Username <span style="color: red">*</span></label><br />
            <input type="text" name="username" required />
        </p>

        <p>
            <label>E-mail <span style="color: red">*</span></label><br />
            <input type="email" name="email" required />
        </p>

        <p>
            <label>Password <span style="color: red">*</span></label><br />
            <input type="password" name="password" required />
        </p>

        <p>
            <label>Facebook</label><br />
            <input type="text" name="facebook" placeholder="Enter your Facebook profile URL" />
        </p>

        <p>
            <label>Short Bio</label><br />
            <textarea name="shortBio" rows="4" placeholder="Share a little information about yourself."></textarea>
        </p>

        <p>
            <input type="submit" value="Submit" />
        </p>
    </form>
</div>


</body>
</html>