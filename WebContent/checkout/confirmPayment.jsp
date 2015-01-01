<jsp:include page="../header.jsp" />
<div class="center_content">
<h1 class="title">

	<center>Confirm Payment</center>

</h1>
<br>
<center>
<table border="1">
<tr>
<td>Photo</td>
<td>Title</td>
<td>Price</td>
<td>Quantity</td>
<td>Subtotal</td>
<td>Update</td>
<td>Remove?</td>
</tr>
<%@page import="java.util.*"%>
<%@page import="machete.db.model.dto.Product" %>
<%@page import="machete.db.model.dto.Creditcard" %>
<%@page import="machete.db.model.dto.Address" %>

<%@page import="machete.db.Pair" %>
<%
	ArrayList<Pair<Product, Long>> pairs = (ArrayList<Pair<Product, Long>>) request.getSession().getAttribute("productQuantityPairs");
	double total = 0;
	for(int i = 0; i < pairs.size(); i++) {
		Product product = pairs.get(i).getFirst();
		long quantity = pairs.get(i).getSecond();
		long productId = product.getProductId();
		long subcategoryId = product.getSubcategoryId();
		String productName = product.getProductName();
		String productDescription = product.getProductDescription();
		double productPrice = product.getProductPrice();
		String productPriceString = String.format("%.2f", productPrice);
		String productBrand = product.getProductBrand();
		String productModel = product.getProductModel();
		String productPhoto = product.getProductPhoto();
		String subTotal = String.format("%.2f", productPrice * quantity);
		total += Double.parseDouble(subTotal);
%>
<tr>
<td><center><a href="showProduct?product_id=<%=productId %>">
<img width=100px src="/ICOM5016_Project/photos/<%=productPhoto %>" /></a></center></td>
<td><center><%=productName %></center></td>
<td><center><%=productPriceString %></center></td>
<td><center><%=quantity %></center></td>
<td><center><%=subTotal %></center></td>
<td>
<center>
<form name="update" action="/ICOM5016_Project/updateCart" method="GET">
<input type="hidden" name="product_id" value="<%=productId%>">
<input type="text" name="quantity" size="3">
</form>
<center></center>
</td>
<td>
<form name="remove" action="/ICOM5016_Project/updateCart" method="GET">
<input type="hidden" name="product_id" value="<%=productId%>">
<input type="hidden" name="quantity" value="0">
<input type="submit" value="Remove">
</form>
</td>
</tr>

<%} %>
</table>

<!--  put total below here -->
<p>
<br><h3>Your Total Is: <%=String.format("%.2f", total) %></h3><br><br>

<%
	// credit card info
	Creditcard creditCard = (Creditcard)request.getSession().getAttribute("creditCard");
	long creditCardId = creditCard.getCreditcardId();
	String number = creditCard.getCreditcardNumber();
	String securityNumber = creditCard.getCreditcardSecuritynumber();
	String month = creditCard.getCreditcardMonth();
	String year = creditCard.getCreditcardYear();
	String creditCardAlias = creditCard.getCreditcardAlias();
	
	// shipping address info
	Address address = (Address)request.getSession().getAttribute("address");
	long addressId = address.getAddressId();
	String line1 = address.getAddressLine1();
	String line2 = address.getAddressLine2();
	String line3 = address.getAddressLine3();
	String city = address.getAddressCity();
	String state = address.getAddressState();
	String country = address.getAddressCountry();
	String zip = address.getAddressZip();
	String addressAlias = address.getAddressAlias();
%>

<h3>Pay With:</h3>
<table width=100% align="center">

<div class="center_title_bar"><%=creditCardAlias %></div>

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
</table>
<br><br>

<h3>Ship To:</h3>
<table width=100% align="center">

<div class="center_title_bar"><%=addressAlias %></div>

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

<h1><a href="/ICOM5016_Project/payment">Confirm Payment</a></h1>
</center>
</div>
<!-- end of center content -->
<jsp:include page="../footer.jsp" />