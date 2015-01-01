<%@page import="java.util.*"%>
<%@page import="machete.db.model.dto.*"%>
<%
		Integer shoppingCartItemCount = (Integer)request.getSession().getAttribute("shoppingCartItemCount");
		Double shoppingCartTotal = (Double)request.getSession().getAttribute("shoppingCartTotal");
		String shoppingCartTotalString = String.format("%.2f", shoppingCartTotal);
%>
   <div class="right_content">
   		<div class="shopping_cart">
        	<div class="cart_title">Shopping cart</div>
            
            <div class="cart_details">
            <%=shoppingCartItemCount %> items <br />
            <span class="border_cart"></span>
            Total: <span class="price">$<%=shoppingCartTotalString %></span>
            </div>
            
            <div class="cart_icon"><a href="/ICOM5016_Project/shoppingCart" title="header=[Shopping Cart] body=[&nbsp;] fade=[on]"><img src="/ICOM5016_Project/images/shoppingcart.png" alt="" title="" width="48" height="48" border="0" /></a></div>
        
        </div>
   
   <%
				Product newestProduct = (Product)request.getSession().getAttribute("newestProduct");
   				long productId = newestProduct.getProductId();
				String productName = newestProduct.getProductName();
				String productPhoto = newestProduct.getProductPhoto();
				double productPrice = newestProduct.getProductPrice();
				String beatPriceString = String.format("%.2f", productPrice * 1.10); // ten percent off
				String productPriceString = String.format("%.2f", productPrice);
%>
     <div class="title_box">What's new</div>  
     <div class="border_box">
         <div class="product_title"><a href="/ICOM5016_Project/showProduct?product_id=<%=productId %>"><%=productName %></a></div>
         <div class="product_img"><a href="/ICOM5016_Project/showProduct?product_id=<%=productId %>"><img src="/ICOM5016_Project/photos/<%=productPhoto %>" alt="" title="" border="0" width=100px /></a></div>
         <div class="prod_price"><span class="reduce">$<%=beatPriceString %></span> <span class="price">$<%=productPriceString %></span></div>
     </div>    
     
     <div class="banner_adds">
     
     <a href="#"><img src="/ICOM5016_Project/images/bann1.jpg" alt="" title="" border="0" /></a>
     </div>        
     
   </div><!-- end of right content -->   
   
            
   </div><!-- end of main content -->
   
   
   
   <div class="footer">
   

         <div class="left_footer">
       <!-- <img src="/ICOM5016_Project/images/footer_logo.png" alt="" title="" width="170" height="49"/>
        --></div>
        
        <div class="center_footer">
        Copyright © 2011 Team Machete<br />
        </div>
        
        <div class="right_footer"> 
        <a href="/ICOM5016_Project/loginAdmin">Admin Panel</a>
       <!-- <a href="details.jsp">about</a>
        <a href="details.jsp">sitemap</a>
        <a href="details.jsp">rss</a>
        <a href="contact.html">contact us</a>-->
        </div>   
   
   </div>                 


</div>
<!-- end of main_container -->
</body>
</html>