<jsp:include page="../headera.jsp" />
<div class="center_content">
<h1 class="title">

	<center>Remove Product</center>

</h1>
<br>

<center>
	<h2 class="title">Enter Product Id:</h2>
	<form name="remove"  action="/ICOM5016_Project/removeProduct" method="post">
		<h3>
			Product Id: <input type="text" name="productId" /><br><br> 
		</h3>
		<input type="submit" name="submit" value="Submit">
	</form>
</center>
</div>
<!-- end of center content -->
<jsp:include page="../footera.jsp" />