<%--
  Created by IntelliJ IDEA.
  User: Hamza
  Date: 8/9/2023
  Time: 10:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f5f5f5;
        }

        header {
            background-color: #333;
            color: white;
            text-align: center;
            padding: 1rem 0;
        }

        main {
            max-width: 800px;
            margin: 2rem auto;
            padding: 1rem;
            background-color: white;
            box-shadow: 0 0 5px rgba(0, 0, 0, 0.1);
        }

        h1, h2 {
            margin: 0;
        }

        section {
            margin-bottom: 1.5rem;
        }

        footer {
            text-align: center;
            padding: 1rem 0;
            background-color: #333;
            color: white;
        }
    </style></head>
<body>
<header>
    <h1>Course Statistics</h1>
</header>
<main>
    <section>
        <h2>Statistics of the Selected Course:</h2>
        <p>${statistics}</p>
    </section>
</main>
<footer>
    <!-- Your footer content here -->
</footer>
</body>
</html>
