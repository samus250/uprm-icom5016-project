<jsp:include page="../header.jsp" />
<div class="center_content">
<h1 class="title">

	<center>Edit Credit Card</center>

</h1>
<br>
<%@page import="java.util.*"%>
<%@page import="machete.db.model.dto.Address"%>
<%@page import="machete.db.model.dto.Creditcard"%>
<%
	ArrayList<Address> addresses = (ArrayList<Address>)request.getSession().getAttribute("addresses");
	Creditcard creditCard = (Creditcard)request.getSession().getAttribute("creditcard");
	
	long creditCardId = creditCard.getCreditcardId();
	long addressId = creditCard.getAddressId();
	long userId = creditCard.getUserId();
	String number = creditCard.getCreditcardNumber();
	String securityNumber = creditCard.getCreditcardSecuritynumber();
	String month = creditCard.getCreditcardMonth();
	String year = creditCard.getCreditcardYear();
	String alias = creditCard.getCreditcardAlias();
	
%>
<center>
	<h2 class="title">Enter credit card information:</h2>
	<form name="add" action="/ICOM5016_Project/editCreditCard" method="POST">
		<h3>
			<input type="hidden" name="creditcardId" value="<%=creditCardId %>" />
			Alias (Peronal Name): <input type="text" name="cardAlias" value="<%=alias %>"/><br><br>
			Card Number: <input type="text" name="cardNumber" value="<%=number %>" /><br><br>
			Security Code: <input type="text" name="cardSecurityNumber" value="<%=securityNumber %>"/><br><br>
			Expiration Month: <input type="text" name="cardMonth" value="<%=month %>"/><br><br>
			Expiration Year: <input type="text" name="cardYear" value="<%=year %>"/><br><br>
			Billing Address:<br>
					<select name="address">
						<% for(Address address : addresses) {
							long aId = address.getAddressId();
							String addressAlias = address.getAddressAlias();
						%>
						<option value="<%=aId%>"><%=addressAlias %></option>
						<%} %>
					</select>
				<br>
		</h3>
		<input type="submit" name="submit" value="Submit">
	</form>
</center>
</div>
<!-- end of center content -->
<jsp:include page="../footer.jsp" />