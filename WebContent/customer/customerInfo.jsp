<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Customer Information</title>
</head>
<body>
<H1>Customer Information</H1>
<jsp:useBean id = "customer" class="machete.db.model.dto.Customer" scope = "session"/>
Name: <jsp:getProperty property="firstName" name="customer"/> Last name: <jsp:getProperty property="lastName" name="customer"/><br>
Id: <jsp:getProperty property="id" name="customer"/> <br>
Email: <jsp:getProperty property="email" name="customer"/> <br>
Password: <jsp:getProperty property="password" name="customer"/>
Account Number: <jsp:getProperty property="accountNumber" name="customer"/>
</body>
</html>