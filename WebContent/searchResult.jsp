<jsp:include page="header.jsp" />
<%@page import="java.util.*"%>
<%@page import="machete.db.model.dto.Product" %>
<%
	ArrayList<Product> products = (ArrayList<Product>)request.getSession().getAttribute("searchProducts"); 
	String searchString = (String)request.getSession().getAttribute("searchString");
%>

<div class="center_content">

<jsp:useBean id = "subcategory" class="machete.db.model.dto.Subcategory" scope = "session"/>
<h1 class="title">
		<center>Search results for: <%=searchString %>
		<br>Found <%=products.size() %> Matching Products</center>
</h1>
<center>
<form name="sort" action="/ICOM5016_Project/Search">
<select name="sort_by">
			<option value="name">Order by Name</option>
			<option value="brand">Order by Brand</option>
			<option value="price">Order by Price</option>
</select>
<select name="decreasing">
			<option value="false">Increasing</option>
			<option value="true">Decreasing</option>
</select>

<% // include all other parameters
Enumeration names = request.getParameterNames();
while(names.hasMoreElements()) {
	String parameterName = (String)names.nextElement();
	if(parameterName.equals("decreasing") || parameterName.equals("sort_by"))
		continue;
	String parameterValue = request.getParameter(parameterName);
%>
<input type="hidden" name="<%=parameterName %>" value="<%=parameterValue %>">
<% }//endwhile %>
<input type="submit" value="Go">
</form>
</center>
<p>
<center>

<%
	for(int i = 0; i < products.size(); i++) {
		String productId = ""+products.get(i).getProductId();
		String link = "./showProduct?product_id=" + productId;
		String photoLink = "/ICOM5016_Project/photos/" + products.get(i).getProductPhoto();
		String productName = products.get(i).getProductName();
		String productPrice = ""+products.get(i).getProductPrice();
%>		
		<div class="prod_box">
        	<div class="top_prod_box"></div>
            <div class="center_prod_box">            
                 <div class="product_title"><a href="<%=link%>"><%=productName %></a></div>
                 <div class="product_img"><a href="<%=link%>"><img src="<%=photoLink %>" alt="" title="" border="0" height="100px" /></a></div>
                 <div class="prod_price"><span class="price">$<%=productPrice %></span></div>                        
            </div>
            <div class="bottom_prod_box"></div>             
            <div class="prod_details_tab">
            <a href="./addToCart?product_id=<%=productId %>&quantity=1" title="header=[Add to cart] body=[&nbsp;] fade=[on]"><img src="images/cart.gif" alt="" title="" border="0" class="left_bt" /></a>
            <a href="<%=link%>" class="prod_details">details</a>            
            </div>                     
        </div>
<%	
}

%>

</center>
</div>
<!-- end of center content -->
<jsp:include page="footer.jsp" />
