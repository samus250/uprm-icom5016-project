<jsp:include page="../headera.jsp" />
<div class="center_content">
<h1 class="title">

	<center>Edit Category</center>

</h1>
<br>

<center>
	<h2 class="title">Enter Category Id:</h2>
	<form name="view"  action="/ICOM5016_Project/editCategory">
		<h3>
			Category Id: <input type="text" name="category_id" value=""/><br><br> 
		</h3>
		<input type="submit" value="Go">
	</form>
</center>
</div>
<!-- end of center content -->
<jsp:include page="../footera.jsp" />