<jsp:include page="../header.jsp" />
<div class="center_content">
<h1 class="title">

	<center>Credit Cards</center>
	
</h1>
<center>
	<h3 class="title">Your Credit Cards:
	
	</h3>
</center>

<%@page import="java.util.*"%>
<%@page import="machete.db.model.dto.*"%>
<%@page import="machete.db.Pair" %>
<%
	// should get a pair, each credit card with its address...
	ArrayList<Pair<Creditcard, Address>> creditCardAddressPairs = (ArrayList<Pair<Creditcard, Address>>)request.getSession().getAttribute("creditCardAddressPairs");

	for(Pair<Creditcard, Address> pair : creditCardAddressPairs) {
		Creditcard creditCard = pair.getFirst();
		Address address = pair.getSecond();
		long creditCardId = creditCard.getCreditcardId();
		long addressId = creditCard.getAddressId();
		long userId = creditCard.getUserId();
		String number = creditCard.getCreditcardNumber();
		String securityNumber = creditCard.getCreditcardSecuritynumber();
		String month = creditCard.getCreditcardMonth();
		String year = creditCard.getCreditcardYear();
		String alias = creditCard.getCreditcardAlias();
		
		// add string of address information
		String line1 = address.getAddressLine1();
		String line2 = address.getAddressLine2();
		String line3 = address.getAddressLine3();
		String city = address.getAddressCity();
		String state = address.getAddressState();
		String zip = address.getAddressZip();
		String country = address.getAddressCountry();
%>



<table width=100% align="center">

<div class="center_title_bar"><%=alias %> - <a href="/ICOM5016_Project/editCreditCardForm?creditcardId=<%=creditCardId %>">Edit</a></div>

<tr>
<td align="center">Credit Card ID:</td><td align="center"><%=creditCardId %></td>
</tr>

<tr>
<td align="center">Number:</td><td align="center"><%=number %></td>
</tr>

<tr>
<td align="center">Security Code:</td><td align="center"><%=securityNumber %></td>
</tr>

<tr>
<td align="center">Exp. Month</td><td align="center"><%=month %></td>
</tr>

<tr>
<td align="center">Exp. Year</td><td align="center"><%=year %></td>
</tr>

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

<a href="/ICOM5016_Project/newCreditCardForm" class="buttoms">Add New Credit Card</a>
<a href="/ICOM5016_Project/showAddresses" class="buttoms">Address Book</a>
<a href="/ICOM5016_Project/showCreditCards" class="buttoms">Credit Cards</a>

</div>
<!-- end of center content -->
<jsp:include page="../footer.jsp" />