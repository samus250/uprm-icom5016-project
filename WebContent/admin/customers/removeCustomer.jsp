<jsp:include page="../headera.jsp" />
<div class="center_content">
<h1 class="title">

	<center>Remove Customer</center>

</h1>
<br>

<center>
	<h2 class="title">Enter Customer Id:</h2>
	<form name="remove"  action="/ICOM5016_Project/removeCustomer" method="post">
		<h3>
			Customer Id: <input type="text" name="customerId" /><br><br> 
		</h3>
		<input type="submit" value="Submit">
	</form>
</center>
</div>
<!-- end of center content -->
<jsp:include page="../footera.jsp" />