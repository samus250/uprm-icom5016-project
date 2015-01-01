<jsp:include page="../header.jsp" />
<div class="center_content">
<h1 class="title">

	<center>Register</center>

</h1>
<br>

<center>
	<h2 class="title">Enter customer information:</h2>
	<form name="add" action="/ICOM5016_Project/nAddCustomerServlet" method="POST">
		<h3>
			First Name: <input type="text" name="firstname" /><br><br>
			Middle Name: <input type="text" name="middlename" /><br><br>
			Last Name: <input type="text" name="lastname" /><br><br>
			User Name: <input type="text" name="username" /><br><br>
			Email: <input type="email" name="email" /><br><br>
			Password: <input type="password" name="password" /><br><br>
			Verify Password: <input type="password" name="verify" /><br><br>
				<!--
				<br> Mailing Address: <input type="text" name="mailingAddress" /><br>
				<br> Billing Address: <input type="text" name="billingAddress" /><br>
				<br> Credit Card Number: <input type="text" name="creditCardNumber" /><br>
				-->
				<br>
		</h3>
		<input type="submit" name="submit" value="Submit">
	</form>
</center>
</div>
<!-- end of center content -->
<jsp:include page="../footer.jsp" />