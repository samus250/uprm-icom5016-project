<jsp:include page="../headera.jsp" />
<div class="center_content">
<h1 class="title">

	<center>Add Category</center>

</h1>
<br>

<center>
	<h2 class="title">Enter Category Information:</h2>
	<form name="add"  action="/ICOM5016_Project/addCategory" method="post">
		<h3>
			Category Name: <input type="text" name="categoryname" /><br><br> 
		</h3>
		<input type="submit" value="Submit">
	</form>
</center>
</div>
<!-- end of center content -->
<jsp:include page="../footera.jsp" />