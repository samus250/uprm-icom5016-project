<jsp:include page="../header.jsp" />
<div class="center_content">
<h1 class="title">

	<center>Shopping Cart</center>

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
<br>Your Total Is: <%=String.format("%.2f", total) %><br><br>
<h3><a href="/ICOM5016_Project/checkout">Checkout</a></h3>
</center>
</div>
<!-- end of center content -->
<jsp:include page="../footer.jsp" />