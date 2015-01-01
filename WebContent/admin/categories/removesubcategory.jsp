<jsp:include page="../headera.jsp" />
<div class="center_content">
<h1 class="title">

	<center>Remove Subcategory</center>

</h1>
<br>

<center>
	<h2 class="title">Enter Subcategory Id:</h2>
	<form name="remove"  action="/ICOM5016_Project/removeSubcategory" method="post">
		<h3>
			Subcategory Id: <input type="text" name="subcategoryId" /><br><br> 
		</h3>
		<input type="submit" value="Submit">
	</form>
</center>
</div>
<!-- end of center content -->
<jsp:include page="../footera.jsp" />