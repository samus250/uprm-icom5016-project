<jsp:include page="../header.jsp" />
<div class="center_content">
<h1 class="title">

	<center>My Account</center>
	
</h1>
<center>
	<h3 class="title">Your Account:
	
	</h3>
</center>

<%@page import="java.util.*"%>
<%@page import="machete.db.model.dto.Customer"%>
<%
	Customer customer = (Customer)request.getSession().getAttribute("customer");
	String firstName = customer.getCustomerFirstname();
	String middleName = customer.getCustomerMiddlename();
	String lastName = customer.getCustomerLastname();
	String email = customer.getUserEmail();
	long userIdNotPadded = customer.getUserId();
	String userId = String.format("%05d", userIdNotPadded);
	
%>

<table width=100% align="center">
<tr>
<td align="center">First Name:</td><td align="center"><%=firstName %></td>
</tr>

<%if(middleName != null) { %>
<tr>
<td align="center">Middle Name:</td><td align="center"><%=middleName %></td>
</tr>
<%} %>
<tr>
<td align="center">Last Name:</td><td align="center"><%=lastName %></td>
</tr>

<tr>
<td align="center">e-Mail:</td><td align="center"><%=email %></td>
</tr>

<tr>
<td align="center">Account ID:</td><td align="center"><%=userId %></td>
</tr>

</table>

<a href="/ICOM5016_Project/showAddresses" class="buttoms">Address Book</a>
<a href="/ICOM5016_Project/showCreditCards" class="buttoms">Credit Cards</a>

</div>
<!-- end of center content -->
<jsp:include page="../footer.jsp" />