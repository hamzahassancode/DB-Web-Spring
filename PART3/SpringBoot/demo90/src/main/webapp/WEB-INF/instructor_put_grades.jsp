<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Grade Entry Form</title>
</head>
<body>
<div class="form-container">
    <h2>Grade Entry</h2>
    <form action="/putgrades" method="post">
        <label for="courseId">Course ID:</label>
        <input type="number" id="courseId" name="courseId" required><br>

        <label for="studentId">Student ID:</label>
        <input type="number" id="studentId" name="studentId" required><br>

        <label for="grade">Grade:</label>
        <input type="number" step="0.1" id="grade" name="grade" required><br>

        <button type="submit">Submit</button>
    </form>
</div>
</body>
</html>
