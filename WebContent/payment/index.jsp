<jsp:include page="../header.jsp"/>
<div class="center_content">
<h1 class="title">

	<center>Payment</center>

</h1>
<center>
	<h2 class="title">Enter customer information:</h2>
	<form name="add" action="../paymentServlet">
		<h3>
			Credit Card Id: <input type="text" name="id" /> <br> <br> User Id: <input
				type="text" name="user" /> <br> <br> Credit Card Number: <input
				type="text" name="number" /> <br> <br> Billing Address: <input
				type="text" name="address" /> <br>
		</h3>
		<input type="submit" name="submit" value="Submit">
	</form>
</center>

</div>

<jsp:include page="../footer.jsp" />