<jsp:include page="../header.jsp" />
<div class="center_content">
<h1 class="title">

	<center>Add New Credit Card</center>

</h1>
<br>
<%@page import="java.util.*"%>
<%@page import="machete.db.model.dto.Address"%>
<%@page import="machete.db.model.dto.Creditcard"%>
<%
	ArrayList<Address> addresses = (ArrayList<Address>)request.getSession().getAttribute("addresses");
%>
<center>
	<h2 class="title">Enter credit card information:</h2>
	<form name="add" action="/ICOM5016_Project/newCreditCard" method="POST">
		<h3>
			Alias (Peronal Name): <input type="text" name="cardAlias" /><br><br>
			Card Number: <input type="text" name="cardNumber" /><br><br>
			Security Code: <input type="text" name="cardSecurityNumber" /><br><br>
			Expiration Month: <input type="text" name="cardMonth" /><br><br>
			Expiration Year: <input type="text" name="cardYear" /><br><br>
			Billing Address:<br>
					<select name="address">
						<% for(Address address : addresses) {
							long addressId = address.getAddressId();
							String addressAlias = address.getAddressAlias();
						%>
						<option value="<%=addressId%>"><%=addressAlias %></option>
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