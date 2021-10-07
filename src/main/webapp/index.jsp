<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Laborator 1 - Java</title>
</head>
<h1>Please provide the following parameters</h1>
<body>
<a href="first-laboratory"></a>
<br>
<form action="first-laboratory">
    <label for="key">Key:</label><br>
    <input type="text" id="key" name="key"><br>
    <label for="value">Value:</label><br>
    <input type="number" id="value" name="value"><br>
    <p>Please select the value for mock:</p>
    <input type="radio" id="true_mock" name="mock" value="true">
    <label for="true_mock">TRUE</label><br>
    <input type="radio" id="false_mock" name="mock" value="false">
    <label for="false_mock">FALSE</label><br>
    <p>Please select the value for sync:</p>
    <input type="radio" id="true_sync" name="sync" value="true">
    <label for="true_sync">TRUE</label><br>
    <input type="radio" id="false_sync" name="sync" value="false">
    <label for="false_sync">FALSE</label><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>