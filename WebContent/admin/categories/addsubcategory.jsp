<jsp:include page="../headera.jsp" />
<div class="center_content">
<h1 class="title">

	<center>Add Subcategory</center>

</h1>
<br>

<center>
	<h2 class="title">Enter Category Information:</h2>
	<form name="add"  action="../../addSubCategory" method="post">
		<h3>
			Category Id: <input type="text" name="categoryId" /><br><br> 
			Subcategory Name: <input type="text" name="subcategoryname" /><br><br> 
		</h3>
		<input type="submit" name="submit" value="Submit">
	</form>
</center>
</div>
<!-- end of center content -->
<jsp:include page="../footera.jsp" />