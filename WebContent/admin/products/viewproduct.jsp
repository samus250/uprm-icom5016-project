<jsp:include page="../headera.jsp" />
<div class="center_content">
<h1 class="title">

	<center>View Product</center>

</h1>
<br>

<center>
	<h2 class="title">Enter Product Id:</h2>
	<form name="view"  action="/ICOM5016_Project/showProductAdmin">
		<h3>
			Product Id: <input type="text" name="product_id" /><br><br> 
		</h3>
		<input type="submit" value="Go">
	</form>
</center>
</div>
<!-- end of center content -->
<jsp:include page="../footera.jsp" />