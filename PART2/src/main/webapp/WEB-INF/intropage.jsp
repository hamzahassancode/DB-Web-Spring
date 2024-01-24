<%--
  Created by IntelliJ IDEA.
  User: Hamza
  Date: 8/9/2023
  Time: 10:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f0f0f0;
            margin: 0;
            padding: 0;
            display: flex;
            align-items: center;
            justify-content: center;
            min-height: 100vh;
        }

        .container {
            background-color: #ffffff;
            border-radius: 10px;
            padding: 20px;
            box-shadow: 0px 0px 10px rgba(0, 0, 0, 0.2);
            text-align: center;
        }

        .title {
            font-size: 24px;
            margin-bottom: 20px;
            color: #333333;
        }

        .options {
            font-size: 18px;
            margin-top: 10px;
        }

        .option {
            margin: 10px;
            padding: 5px 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
            display: inline-block;
            cursor: pointer;
            color: #333333;
            background-color: #f0f0f0;
            transition: background-color 0.3s ease-in-out;
        }

        .option:hover {
            background-color: #dddddd;
        }
    </style>
    <title>Student Grading System</title>
</head>
<body>
<div class="container">
    <div class="title">Welcome to the Student Grading System</div>
    <div class="options">
        <form action="intropage" method="post">
            <button class="option" type="submit" name="option" value="student">STUDENT</button>
        </form>
        <form action="intropage" method="post">
            <button class="option" type="submit" name="option" value="instructor">INSTRUCTOR</button>
        </form>
        <form action="intropage" method="post">
            <button class="option" type="submit" name="option" value="admin">ADMIN</button>
        </form>
    </div>
</div>
</body>
</html>

