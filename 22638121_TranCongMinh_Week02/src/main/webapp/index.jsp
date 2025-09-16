<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <!-- Tailwind CDN -->
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
<div class="bg-white p-8 rounded-2xl shadow-lg w-full max-w-md">
    <h1 class="text-2xl font-bold text-blue-600 mb-4"> Upload multi-files</h1>
    <form action="${pageContext.request.contextPath}/uploadMultiFiles" method="post" enctype="multipart/form-data">
        File #1: <input type="file" name="file1"/><br/>
        File #2: <input type="file" name="file2"/><br/>
        File #3: <input type="file" name="file3"/><br/>
        File #4: <input type="file" name="file4"/><br/>
        File #5: <input type="file" name="file5"/><br/>
        <div class="flex justify-center pt-4 space-x-4">
            <button type="submit" class="px-4 py-2 bg-gray-300 text-gray-700">Upload</button>
            <button type="reset" class="px-4 py-2 bg-gray-300 text-gray-700">Reset</button>
        </div>
    </form>
</div>
</body>
</html>