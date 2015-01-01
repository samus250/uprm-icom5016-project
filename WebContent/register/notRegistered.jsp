<jsp:include page="../header.jsp" />
<div class="center_content">
<h1 class="title">

	<center>Not Registered</center>

</h1>
<br>

<center>
	<h2>
		You were not registered.<br>
<% String errorString = (String)request.getSession().getAttribute("errorString"); %>
<%=errorString %><br>
	</h2>
</center>

</div>
<!-- end of center content -->
<jsp:include page="../footer.jsp" />