<jsp:include page="../headera.jsp" />
<div class="center_content">
<h1 class="title">

	<center>Customer Not added</center>

</h1>
<br>

<center>
	<h2>
		Customer was not added.<br>
<% String errorString = (String)request.getSession().getAttribute("errorString"); %>
<%=errorString %><br>
	</h2>
</center>

</div>
<!-- end of center content -->
<jsp:include page="../footera.jsp" />