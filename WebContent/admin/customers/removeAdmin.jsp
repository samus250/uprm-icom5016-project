<jsp:include page="../headera.jsp" />
<div class="center_content">
<h1 class="title">

	<center>Remove Admin</center>

</h1>
<br>

<center>
	<h2 class="title">Enter Admin Id:</h2>
	<form name="remove"  action="/ICOM5016_Project/removeAdmin">
		<h3>
			Admin Id: <input type="text" name="adminId" /><br><br> 
		</h3>
		<input type="submit" value="Submit">
	</form>
</center>
</div>
<!-- end of center content -->
<jsp:include page="../footera.jsp" />