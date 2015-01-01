<jsp:include page="header.jsp" />   
<%@page import="java.util.*"%>
<%@page import="machete.db.model.dto.Product" %>
<%ArrayList<String> productOptionValues = (ArrayList<String>)request.getSession().getAttribute("productOptionValues"); %>

   <div class="center_content">
   
   <h1><center>Choose option values</center></h1> 
	
	<h2>
	<%for(int i = 0; i < productOptionValues.size(); i++) {
		String value = productOptionValues.get(i);
		String link = "/ICOM5016_Project/showSubcategory?option_value=" + value;

		Enumeration names = request.getParameterNames();
		while(names.hasMoreElements()) {
			String parameterName = (String)names.nextElement();
			String parameterValue = request.getParameter(parameterName);
			//!TODO ESCAPE QUOTES FROM VALUE... VALIDATION IS VERY IMPORTANT
			link += "&" + parameterName + "=" + parameterValue;
		}
		
	%>
		<center><a href="<%=link %>"><%=value %></a></center>
	<% }%>
	</h2>
   </div>
  <jsp:include page="/footer.jsp" />