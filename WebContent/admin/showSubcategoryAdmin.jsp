<jsp:include page="headera.jsp" />
<div class="center_content">
<jsp:useBean id = "subcategory" class="machete.db.model.dto.Subcategory" scope = "session"/>
<h1 class="title">
		<center><jsp:getProperty property="subcategoryName" name="subcategory"/></center>
</h1>
<p>
<center>

<table width="100%">
<%@page import="java.util.*"%>
<%@page import="machete.db.model.dto.Product" %>
<%
	ArrayList<Product> products = (ArrayList<Product>)request.getSession().getAttribute("products");
	for(int i = 0; i < products.size(); i++) {
		String productId = ""+products.get(i).getProductId();
		String link = "./showProductAdmin?product_id=" + productId;
		String photoLink = "/ICOM5016_Project/photos/" + products.get(i).getProductPhoto();
		String productName = products.get(i).getProductName();
		String productPrice = ""+products.get(i).getProductPrice();
%>		
		<div class="prod_box">
        	<div class="top_prod_box"></div>
            <div class="center_prod_box">            
                 <div class="product_title"><a href="<%=link%>"><%=productName %></a></div>
                 <div class="product_img"><a href="<%=link%>"><img src="<%=photoLink %>" alt="" title="" border="0" width="120"/></a></div>
                 <div class="prod_price"><span class="price"><%=productPrice %></span></div>                        
            </div>
            <div class="bottom_prod_box"></div>             
            <div class="prod_details_tab">
            <a href="<%=link%>" class="prod_details">details</a>            
            </div>                     
        </div>
<%	}

%>
</table>
</center>
</div>
<!-- end of center content -->
<jsp:include page="footera.jsp" />
