<jsp:include page="../header.jsp" />
<div class="center_content">
<h1 class="title">

	<center>Add New Address</center>

</h1>
<br>

<center>
	<h2 class="title">Enter new Address:</h2>
	<form name="add" action="/ICOM5016_Project/newAddress" method="POST">
		<h3>
			Alias (Personal Name): <input type="text" name="addressAlias" /><br><br>
			Line 1: <input type="text" name="addressLine1" /><br><br>
			Line 2: <input type="text" name="addressLine2" /><br><br>
			Line 3: <input type="text" name="addressLine3" /><br><br>
			City: <input type="text" name="addressCity" /><br><br>
			State: <input type="text" name="addressState" /><br><br>
			Zip: <input type="text" name="addressZip" /><br><br>
			Country: <input type="text" name="addressCountry" /><br><br>
		</h3>
		<input type="submit" name="submit" value="Submit">
	</form>
</center>
</div>
<!-- end of center content -->
<jsp:include page="../footer.jsp" />