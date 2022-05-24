<!DOCTYPE html>
<html lang="en">
<head>
    <title>Code</title>
    <link href="/css/style.css" rel="stylesheet">
    <link rel="stylesheet"
           href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
    <span id="load_date">${oneCode.date}</span><br>
    <span id="views_restriction">${oneCode.views}</span> more views allowed<br>
    The code will be available for <span id="time_restriction"> ${oneCode.time}</span> seconds
    <pre id="code_snippet"><code>${oneCode.code}</code></pre>
</body>
</html>