<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Student Registration Form</title>
    <!-- Thêm Tailwind qua CDN -->
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body class="bg-gray-100 min-h-screen flex items-center justify-center">
<div class="bg-white shadow-lg rounded-lg w-full max-w-5xl p-8">
    <h1 class="text-center text-3xl font-bold text-blue-600">
        Student Registration Form
    </h1>
    <form action="student" method="get" class="space-y-6">

        <!-- Name -->
        <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
            <div>
                <label class="block mb-1 font-medium">First Name</label>
                <input type="text" name="firstname"
                       class="w-full border rounded-lg px-3 py-2 focus:ring focus:ring-blue-200">
            </div>
            <div>
                <label class="block mb-1 font-medium">Last Name</label>
                <input type="text" name="lastname"
                       class="w-full border rounded-lg px-3 py-2 focus:ring focus:ring-blue-200">
            </div>
        </div>

        <!-- DOB + Mobile -->
        <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
            <div>
                <label class="block mb-1 font-medium">Date of Birth</label>
                <input type="date" name="dob" class="w-full border rounded-lg px-3 py-2 focus:ring focus:ring-blue-200">
            </div>
            <div>
                <label class="block mb-1 font-medium">Mobile</label>
                <input type="text" name="phone"
                       class="w-full border rounded-lg px-3 py-2 focus:ring focus:ring-blue-200">
            </div>
        </div>

        <!-- Email -->
        <div>
            <label class="block mb-1 font-medium">Email</label>
            <input type="email" name="email" class="w-full border rounded-lg px-3 py-2 focus:ring focus:ring-blue-200">
        </div>

        <!-- Gender -->
        <div>
            <label class="block mb-1 font-medium">Gender</label>
            <div class="flex items-center gap-4">
                <label><input type="radio" name="gender" value="Male" class="mr-2"> Male</label>
                <label><input type="radio" name="gender" value="Female" class="mr-2"> Female</label>
            </div>
        </div>

        <!-- Address -->
        <div>
            <label class="block mb-1 font-medium">Address</label>
            <textarea name="address" rows="3"
                      class="w-full border rounded-lg px-3 py-2 focus:ring focus:ring-blue-200"></textarea>
        </div>

        <!-- City / Pin / State / Country -->
        <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
            <div>
                <label class="block mb-1 font-medium">City</label>
                <input type="text" name="city" class="w-full border rounded-lg px-3 py-2">
            </div>
            <div>
                <label class="block mb-1 font-medium">Pin Code</label>
                <input type="text" name="pincode" class="w-full border rounded-lg px-3 py-2">
            </div>
        </div>
        <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
            <div>
                <label class="block mb-1 font-medium">State</label>
                <input type="text" name="state" class="w-full border rounded-lg px-3 py-2">
            </div>
            <div>
                <label class="block mb-1 font-medium">Country</label>
                <input type="text" name="country" value="VietNam" class="w-full border rounded-lg px-3 py-2">
            </div>
        </div>

        <!-- Hobbies -->
        <div>
            <label class="block mb-1 font-medium">Hobbies</label>
            <div class="flex flex-wrap gap-4">
                <label><input type="checkbox" name="hobbies" value="Drawing" class="mr-2"> Drawing</label>
                <label><input type="checkbox" name="hobbies" value="Singing" class="mr-2"> Singing</label>
                <label><input type="checkbox" name="hobbies" value="Dancing" class="mr-2"> Dancing</label>
                <label><input type="checkbox" name="hobbies" value="Sketching" class="mr-2"> Sketching</label>
                <label><input type="checkbox" name="hobbies" value="Others" class="mr-2"> Others</label>
            </div>
        </div>
        <!-- Courses -->
        <div>
            <label class="block mb-1 font-medium">Course applies for</label>
            <div class="flex flex-wrap gap-4">
                <label><input type="radio" name="course" value="BCA" class="mr-2"> BCA</label>
                <label><input type="radio" name="course" value="B.Com" class="mr-2"> B.Com</label>
                <label><input type="radio" name="course" value="B.Sc" class="mr-2"> B.Sc</label>
                <label><input type="radio" name="course" value="B.A" class="mr-2"> B.A</label>
            </div>
        </div>

        <!-- Buttons -->
        <div class="flex justify-center gap-4">
            <button type="submit" class="bg-blue-600 text-white px-6 py-2 rounded-lg hover:bg-blue-700">Submit</button>
            <button type="reset" class="bg-gray-500 text-white px-6 py-2 rounded-lg hover:bg-gray-600">Reset</button>
        </div>
    </form>
</div>


</body>
</html>