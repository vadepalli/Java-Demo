<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Hello Azure!</title>

</head>
<body>
<h2> This is the homepage for Java App Demo</h2>

<!--生成页面分隔section-->
<div style="border-bottom: 1px solid #000000; margin-bottom: 1em;">
    <!--生成用户输入框，将用户输入提交到servlet ValidateID做输入验证，并把servlet的返回信息打印在页面上-->
    <form action="./ValidateID" method="post">
        <input type="text" name="id" />
        <input type="submit" value="Submit" />
    </form>
</div>

<ul>
<li> The url for the Servlet is <a href="./HelloWorldServlet">./HelloWorldServlet</a> </li>

<li> If you modify the sample, <b>/[.war file name]</b> is the path to the homepage, <br>
     the url for the Servlet is <b>/[.war file name]/[Servlet name]</b> </li>
</ul>

</body>
</html>
