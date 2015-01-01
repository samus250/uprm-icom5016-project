<jsp:include page="headera.jsp" />
<div class="center_content">
	<h1>
		<center><jsp:getProperty
					property="categoryName" name="category" /></center>
	</h1>
	<p>
		<jsp:useBean id="category" class="machete.db.model.dto.Category"
			scope="session" />
	<center>
		<h2>
			<%@page import="java.util.*"%>
			<%@page import="machete.db.model.dto.Subcategory"%>
			<%
				ArrayList<Subcategory> subcategories = (ArrayList<Subcategory>) request
						.getSession().getAttribute("subcategories");
				for (int i = 0; i < subcategories.size(); i++) {
					String link = "./showSubcategoryAdmin?subcategory_id="
							+ subcategories.get(i).getSubcategoryId();

					String subcategoryName = subcategories.get(i)
							.getSubcategoryName();
			%>
			<a href="<%=link%>"><%=subcategoryName%></a><br>
			<br>
			<%
				}
			%>
			
			
			
			
		</h2>
	</center>
</div>
<!-- end of center content -->
<jsp:include page="footera.jsp" />
