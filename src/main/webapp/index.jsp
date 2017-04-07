<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//WSC//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Register</title>
</head>
<body>
Register:
<form action="add" method="get">
<label>Login:<input type="text" id="username" name="username"/></label><br/>
<label>Hasło:<input type="text" id="password" name="password"/></label><br/>
<label>E-mail:<input type="text" id="email" name="email"/></label><br/>
<label>Premium:<input type="text" id="premium" name="premium"/></label><br/>
<label>Admin:<input type="text" id="admin" name="admin"/></label><br/>
<input type="submit" value="wyslij"/>
</form>
</body>
</html>
