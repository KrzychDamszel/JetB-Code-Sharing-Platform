<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>Create</title>
</head>
<body>
    <textarea id="code_snippet" rows=10 cols=50>
//write your code here
    </textarea>
    <br>
    <form>
        <br>
        Time restriction:<input id="time_restriction" type="text" value="0"><br><br>
        Views restriction:<input id="views_restriction" type="text" value="0"><br><br>
    </form>
    <button id="send_snippet" type="submit" onclick="send()">Submit</button>
    <script src="/js/send.js"></script>
</body>
</html>