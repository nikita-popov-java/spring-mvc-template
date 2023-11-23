<html>
<head>
    <meta charset="UTF-8">
    <title>Dental Clinic</title>
    <style>
        * {
            margin: 0 auto;
            text-align: center;
        }
        button {
            width: 160px;
            height: 40px;
        }
        body {width: 100%;}
        a {font-size: 18px;}
        p {font-size: 24px;}
    </style>
</head>
<body>
<h1>Hello!</h1>
<br>
<p>You there are in start page. Choose needed option:</p>
<br>
<button>
    <a href="${pageContext.request.contextPath}/clients">See clients list</a>
</button>
<button>
    <a href="${pageContext.request.contextPath}/clients/new">Add a client</a>
</button>
</body>
</html>