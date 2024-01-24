<%--
  Created by IntelliJ IDEA.
  User: Hamza
  Date: 8/8/2023
  Time: 10:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Course Statistics</title>
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
            color: #fff;
            text-align: center;
            padding: 1rem 0;
        }

        main {
            padding: 2rem;
            max-width: 800px;
            margin: 0 auto;
        }

        form {
            background-color: #fff;
            padding: 1.5rem;
            border-radius: 5px;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

        label {
            display: block;
            margin-bottom: 0.5rem;
            font-weight: bold;
        }

        input[type="text"] {
            width: 100%;
            padding: 0.5rem;
            border: 1px solid #ccc;
            border-radius: 3px;
            margin-bottom: 1rem;
        }

        button[type="submit"] {
            background-color: #35495e;
            color: #fff;
            border: none;
            padding: 0.5rem 1rem;
            border-radius: 3px;
            cursor: pointer;
        }

        button[type="submit"]:hover {
            background-color: #2c3e50;
        }

        section {
            margin-bottom: 2rem;
        }

        footer {
            background-color: #35495e;
            color: #fff;
            text-align: center;
            padding: 1rem 0;
        }
    </style>
</head>
<body>
<header>
    <h1>Course Statistics</h1>
</header>
<main>
    <section>
        <form action="${pageContext.request.contextPath}/StatisticsServlet" method="post">
            <label for="course_id">Enter Course ID: </label>
            <input type="text" name="course_id" id="course_id">
            <button type="submit">Get Course Statistics</button>
        </form>
    </section>
    <section>
        <h2>Statistics of the Selected Course:</h2>
        <p>${statistics}</p>
    </section>
</main>
<footer>
    <p>hi</p>
</footer>
</body>
</html>
