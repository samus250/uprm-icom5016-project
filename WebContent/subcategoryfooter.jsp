<%@page import="java.util.*"%>
<%@page import="machete.db.model.dto.*"%>
<%
		Integer shoppingCartItemCount = (Integer)request.getSession().getAttribute("shoppingCartItemCount");
		Double shoppingCartTotal = (Double)request.getSession().getAttribute("shoppingCartTotal");
		
		if(shoppingCartItemCount == null)
			shoppingCartItemCount = 0;
		if(shoppingCartTotal == null)
			shoppingCartTotal = 0.0;
		
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
     ArrayList<SubcategoryOption> subcategoryOptions = (ArrayList<SubcategoryOption>)request.getSession().getAttribute("subcategoryOptions");
     	if(!subcategoryOptions.isEmpty()) { %>
     	<div class="title_box">Look by options</div>

				<ul class="left_menu">
					<%
						// add loop-dee-loops here
					%>
					<%@page import="java.util.*"%>
					<%@page import="machete.db.model.dto.*"%>
					<%
						
						for (int i = 0; i < subcategoryOptions.size(); i++) {
							String optionName = subcategoryOptions.get(i).getOptionName();
							Long subcategoryId = subcategoryOptions.get(i).getSubcategoryId();
							Long subcategoryOptionId = subcategoryOptions.get(i).getSubcategoryOptionId();
							String link = "/ICOM5016_Project/showSubcategory?subcategory_id=" + subcategoryId + "&option_id=" + subcategoryOptionId;
							
							
							String par = "odd";
							if (i % 2 == 0)
								par = "even";
					%>
					<li class="<%=par%>"><a href="<%=link%>"><%=optionName%></a></li>
					<%
						}
					%>
				</ul>   
				<% } // end of boxed if%>    
     
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