<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
    <form action="LoginController" method="post">
        Enter username : <input type="text" name="username"> <BR>
        Enter password : <input type="password" name="password"> <BR>
        <input type="submit" />
    </form>
    
    <form action="Testing" method="post">
        <input type="submit" name="buttonClicked" value="MyButton"/>
        <input type="submit" name="buttonClicked" value="OtherButton"/>
        <input type="submit" name="buttonClicked" value="YetAnotherButton"/>
    </form>
</body>
</html>