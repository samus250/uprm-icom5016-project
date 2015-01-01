<jsp:include page="../headera.jsp" />   
<%@page import="java.util.*"%>
   
   <div class="center_content">
   
   <h1><center>Reports</h1>   
   
   
   <form name="report" action="/ICOM5016_Project/showReport">
   <h2>General Reports</h2>
<select name="report">
			<option value="total_sales">Total Sales</option>
			<option value="total_revenue">Total Revenue</option>
</select>
<select name="report_type">
			<option value="day">By Day</option>
			<option value="month">By Month</option>
			<option value="year">By Year</option>
</select>

<% // include all other parameters
Enumeration names = request.getParameterNames();
while(names.hasMoreElements()) {
	String parameterName = (String)names.nextElement();
	String parameterValue = request.getParameter(parameterName);
%>
<input type="hidden" name="<%=parameterName %>" value="<%=parameterValue %>">
<% }//endwhile %>
<input type="submit" value="Go">
</form>

<form name="report" action="/ICOM5016_Project/showReport">
   <h2>Reports By Product</h2>
<select name="report">
			<option value="total_sales_by_product">Total Sales</option>
			<option value="total_revenue_by_product">Total Revenue</option>
</select>
<select name="report_type">
			<option value="day">By Day</option>
			<option value="month">By Month</option>
			<option value="year">By Year</option>
</select>

ProductId: <input type="text" name="product_id" />

<% // include all other parameters
Enumeration names2 = request.getParameterNames();
while(names2.hasMoreElements()) {
	String parameterName = (String)names2.nextElement();
	String parameterValue = request.getParameter(parameterName);
%>
<input type="hidden" name="<%=parameterName %>" value="<%=parameterValue %>">
<% }//endwhile %>
<input type="submit" value="Go">
</form>
   </center>
   
   <!--
    <form name="menuform">
	<select name="menu2"
		onChange="top.location.href = this.form.menu2.options[this.form.menu2.selectedIndex].value;
return false;">
   <option value="">Select a Report</option>
   <option value="sales.jsp">Sales</option>
   <option value="returns.jsp">Returns</option>
   <option value="revenue.jsp">Total revenue</option>
   <option value="totalsales.jsp">Total sales</option>
   <option value="salesproduct.jsp">Total Sales by Product</option>
   <option value="revenueproduct.jsp">Total Revenue by Product</option>
   </select>
   </form>-->
   
   </div><!-- end of center content -->
  <jsp:include page="../footera.jsp" />