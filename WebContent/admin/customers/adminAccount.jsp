<jsp:include page="../headera.jsp" />
<div class="center_content">
<h1 class="title">

	<center>Admin Account</center>
	
</h1>
<center>
	<h3 class="title">Account:
	
	</h3>
</center>

<%@page import="java.util.*"%>
<%@page import="machete.db.model.dto.Administrator"%>
<%
	Administrator admin = (Administrator)request.getSession().getAttribute("admin");
	String username = admin.getUserName();
	String email = admin.getUserEmail();
	String level = admin.getAdministratorLevel();
	long userIdNotPadded = admin.getUserId();
	String adminId = String.format("%05d", userIdNotPadded);
	
%>

<table width=100% align="center">
<tr>
<td align="center">Admin ID:</td><td align="center"><%=adminId %></td>
</tr>

<tr>
<td align="center">User Name:</td><td align="center"><%=username %></td>
</tr>

<tr>
<td align="center">e-Mail:</td><td align="center"><%=email %></td>
</tr>

<tr>
<td align="center">Administrator Level:</td><td align="center"><%=level %></td>
</tr>

</table>

</div>
<!-- end of center content -->
<jsp:include page="../footera.jsp" />