<jsp:include page="header.jsp" />

<div class="center_content">
	<H1>Product Information</H1>
	<jsp:useBean id="product" class="machete.db.model.dto.Product"
		scope="session" />

   	<div class="center_title_bar"><jsp:getProperty property="productName" name="product" /></div>
    
    	<div class="prod_box_big">
        	<div class="top_prod_box_big"></div>
            <div class="center_prod_box_big">            
                 
                 <div class="product_img_big">
                 <% String productPhotoLink = "photos/" + product.getProductPhoto(); %>
                 <a href="javascript:popImage('<%=productPhotoLink %>','<%=product.getProductName() %>')" title="header=[Zoom] body=[&nbsp;] fade=[on]"><img src="<%=productPhotoLink %>" alt="" title="" border="0" width="120px"/></a>
                 <!-- <div class="thumbs">
                 <a href="#" title="header=[Thumb1] body=[&nbsp;] fade=[on]"><img src="images/thumb1.gif" alt="" title="" border="0" /></a>
                 <a href="#" title="header=[Thumb2] body=[&nbsp;] fade=[on]"><img src="images/thumb1.gif" alt="" title="" border="0" /></a>
                 <a href="#" title="header=[Thumb3] body=[&nbsp;] fade=[on]"><img src="images/thumb1.gif" alt="" title="" border="0" /></a>
                 </div>-->
                 </div>
                     <div class="details_big_box">
                         <div class="product_title_big"><%=product.getProductName() %></div>
                         <div class="specifications">
                         Product Name: <span class="blue">
	<jsp:getProperty property="productName" name="product" /></span>
	<br> Product Id:
	<span class="blue"><jsp:getProperty property="productId" name="product" /></span>
	<br>
	Product Description: <span class="blue"><jsp:getProperty property="productDescription" name="product" />
	</span><br>
	Product Brand: <span class="blue"><jsp:getProperty property="productBrand" name="product" />
	<br></span>
	Product Model: <span class="blue"><jsp:getProperty property="productModel" name="product" />
	</span><br>
       
                         </div>
                         <div class="prod_price_big"> <span class="price">$<jsp:getProperty property="productPrice" name="product" /></span></div>
                         
                         <a href="/ICOM5016_Project/addToCart?product_id=<%=product.getProductId() %>&quantity=1" class="addtocart">add to cart</a>
                     </div>                        
            </div>
            <div class="bottom_prod_box_big"></div>                                
        </div>

</div>
<!-- end of center content -->
<jsp:include page="footer.jsp" />