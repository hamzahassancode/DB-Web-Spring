
<%--
  Created by IntelliJ IDEA.
  User: Hamza
  Date: 8/6/2023
  Time: 10:22 PM
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Grade Viewer</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
            color: #333;
        }
        header {
            background-color: #35495e;
            color: white;
            padding: 20px;
            text-align: center;
        }
        .container {
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            background-color: white;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        h2 {
            margin-top: 0;
        }
        h3 {
            margin: 15px 0;
        }
        form {
            margin-top: 15px;
        }
        input[type="text"] {
            padding: 8px;
            width: 100%;
            border: 1px solid #ccc;
            border-radius: 3px;
            box-sizing: border-box;
        }
        button {
            padding: 10px 20px;
            background-color: #35495e;
            color: white;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }
    </style>
</head>
<body>
<header>
    <h1>Student page </h1>
</header>
<div class="container">
    <h2>Choose an Option:</h2>
    <form action="/marks" method="post">
        <input type="hidden" name="username">
        <h3>1. Show your grades in all courses</h3>
       <h3>2. Show your grade in a specific course and get the statistics of this course</h3>
        <h3>3. Exit</h3>
        <input type="text" name="choice">
        <button  type="submit" name="choice">Submit</button>
    </form>
    <div>
        ${marks}
    </div>
</div>
</body>
</html>
