<jsp:include page="../header.jsp" />
<div class="center_content">
	<h1 class="title">

		<center>Checkout</center>

	</h1>
	<center>
		<h2 class="title">Enter payment information:</h2>
		
<%@page import="java.util.*"%>
<%@page import="machete.db.model.dto.Address"%>
<%@page import="machete.db.model.dto.Creditcard"%>
<%
	ArrayList<Creditcard> creditCards = (ArrayList<Creditcard>)request.getSession().getAttribute("creditCards");
	ArrayList<Address> addresses = (ArrayList<Address>)request.getSession().getAttribute("addresses");
%>
		<form name="add" action="/ICOM5016_Project/confirmPayment" method="post">
			<h3>
				Choose Card:<br>
					<select name="creditcard">
						<% for(Creditcard creditCard : creditCards) {
							long creditcardId = creditCard.getCreditcardId();
							String creditcardAlias = creditCard.getCreditcardAlias();
						%>
						<option value="<%=creditcardId %>"><%=creditcardAlias %></option>
						<%} %>
					</select><br><br>
				Choose Shipping Address:<br>
					<select name="address">
						<% for(Address address : addresses) {
							long addressId = address.getAddressId();
							String addressAlias = address.getAddressAlias();
						%>
						<option value="<%=addressId%>"><%=addressAlias %></option>
						<%} %>
					</select><br><br>
			</h3>
			<input type="submit" name="submit" value="Submit">
		</form>
	</center>

</div>

<jsp:include page="../footer.jsp" />