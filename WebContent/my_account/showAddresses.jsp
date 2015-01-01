<jsp:include page="../header.jsp" />
<div class="center_content">
<h1 class="title">

	<center>Address Book</center>
	
</h1>
<center>
	<h3 class="title">Your Addresses:
	
	</h3>
</center>

<%@page import="java.util.*"%>
<%@page import="machete.db.model.dto.Address"%>
<%
	ArrayList<Address> addresses = (ArrayList<Address>)request.getSession().getAttribute("addresses");
	for(Address address : addresses) {
		long addressId = address.getAddressId();
		long userId = address.getUserId();
		String line1 = address.getAddressLine1();
		String line2 = address.getAddressLine2();
		String line3 = address.getAddressLine3();
		String city = address.getAddressCity();
		String state = address.getAddressState();
		String zip = address.getAddressZip();
		String country = address.getAddressCountry();
		String alias = address.getAddressAlias();
%>

<table width=100% align="center">

<div class="center_title_bar"><%=alias %> - <a href="/ICOM5016_Project/editAddressForm?addressId=<%=addressId %>">Edit</a></div>

<tr>
<td align="center">Address ID:</td><td align="center"><%=addressId %></td>
</tr>

<tr>
<td align="center">Line 1:</td><td align="center"><%=line1 %></td>
</tr>

<tr>
<td align="center">Line 2:</td><td align="center"><%=line2 %></td>
</tr>

<tr>
<td align="center">Line 3:</td><td align="center"><%=line3 %></td>
</tr>

<tr>
<td align="center">City:</td><td align="center"><%=city %></td>
</tr>

<tr>
<td align="center">State:</td><td align="center"><%=state %></td>
</tr>

<tr>
<td align="center">Zip:</td><td align="center"><%=zip %></td>
</tr>

<tr>
<td align="center">Country:</td><td align="center"><%=country %></td>
</tr>
</table>
<br>
<%} %>

<a href="/ICOM5016_Project/my_account/newAddress.jsp" class="buttoms">Add New Address</a>
<a href="/ICOM5016_Project/showAddresses" class="buttoms">Address Book</a>
<a href="/ICOM5016_Project/showCreditCards" class="buttoms">Credit Cards</a>

</div>
<!-- end of center content -->
<jsp:include page="../footer.jsp" />