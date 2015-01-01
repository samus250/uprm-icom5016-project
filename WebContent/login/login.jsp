<jsp:include page="../header.jsp" />
<div class="center_content">
<h1 class="title">

	<center>Login</center>

</h1>
<br>
<center>
	<form name="login" action="/ICOM5016_Project/nReadCustomerServlet" method="POST">
		<h3>
			Username: <input type="text" name="userName" /> <br>
		</h3>
		<h3>
			Password: <input type="password" name="password" />
		</h3>
		<br> <input type="submit" name="submit" value="Submit">
	</form>
	<br>
	<h2>
		<a href="../register/">Register</a>
	</h2>
</center>

</div>
<!-- end of center content -->
<jsp:include page="../footer.jsp" />