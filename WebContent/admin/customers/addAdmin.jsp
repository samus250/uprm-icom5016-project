<jsp:include page="../headera.jsp" />
<div class="center_content">
<h1 class="title">

	<center>Add Administrator</center>

</h1>
<br>

<center>
	<h2 class="title">Enter customer information:</h2>
	<form name="add" action="/ICOM5016_Project/addAdministrator" method="POST">
		<h3>
			User Name: <input type="text" name="username" /><br><br>
			Email: <input type="email" name="email" /><br><br>
			Password: <input type="password" name="password" /><br><br>
			Verify Password: <input type="password" name="verify" /><br><br>
				<br>
		</h3>
		<input type="submit" name="submit" value="Submit">
	</form>
</center>
</div>

<jsp:include page="../footera.jsp" />