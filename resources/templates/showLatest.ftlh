<!DOCTYPE html>
<html lang="en">
<head>
    <title>Latest</title>
    <link href="/css/style.css" rel="stylesheet">
    <link rel="stylesheet"
           href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
</head>
<body>
<#list codes as code>
    <span id="load_date">${code.date}</span>
    <pre id="code_snippet"><code>${code.code}</code></pre>
</#list>
</body>
</html>