<jsp:include page="../headera.jsp" />
<div class="center_content">
<h1 class="title">

	<center>Remove Category</center>

</h1>
<br>

<center>
	<h2 class="title">Enter Category Id:</h2>
	<form name="remove"  action="/ICOM5016_Project/removeCategory" method="post">
		<h3>
			Category Id: <input type="text" name="categoryId" /><br><br> 
		</h3>
		<input type="submit" value="Submit">
	</form>
</center>
</div>
<!-- end of center content -->
<jsp:include page="../footera.jsp" />