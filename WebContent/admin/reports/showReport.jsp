<jsp:include page="../headera.jsp" />
<%@page import="java.util.*"%>
<%@page import="machete.db.model.dto.Report"%>
<%
	ArrayList<Report> reports = (ArrayList<Report>) request
			.getSession().getAttribute("reports");
	Integer productId = (Integer) request.getSession().getAttribute(
			"product_id");
	String report_type = (String) request.getSession().getAttribute(
			"report_type");
	String report = (String) request.getSession()
			.getAttribute("report");
%>

<div class="center_content">

	<jsp:useBean id="subcategory" class="machete.db.model.dto.Subcategory"
		scope="session" />
	<h1 class="title">
		<%
			String title = report + " by " + report_type;
			if (report.contains("product"))
				title += " for " + Integer.toString(productId);
			int j = 0;
		%>
		<center><%=title%>
	</h1>
	<center>
		<table border="1">
			<%
				if (report_type.equals("day")) {
			%>
			<tr>
				<td>Date</td>
				<td>Quantity</td>
			</tr>
			<%
				j = 0;
				}

				else if (report_type.equals("month")) {
			%>
			<tr>
				<td>Month</td>
				<td>Year</td>
				<td>Quantity</td>
			</tr>
			<%
				j = 1;
				}

				else {
			%>
			<tr>
				<td>Year</td>
				<td>Quantity</td>
			</tr>
			<%
				j = 2;
				}

				for (int i = 0; i < reports.size(); i++) {
					if (j == 0) {
						Date date = reports.get(i).getDate();
						double quantity = reports.get(i).getR();
					%>
					<tr>
						<td><%=date%></td>
						<td><%=quantity%></td>
					</tr>
				<%
					}
					
					else if (j == 1) {
						long month = reports.get(i).getMonth();
						long year = reports.get(i).getYear();
						double quantity = reports.get(i).getR();
					%>
					<tr>
						<td><%=month%></td>
						<td><%=year%></td>
						<td><%=quantity%></td>
					</tr>
				<%
					}
					
					else{
						long year = reports.get(i).getYear();
						double quantity = reports.get(i).getR();
					%>
					<tr>
						<td><%=year%></td>
						<td><%=quantity%></td>
					</tr>
				<%
						
					}
				}
			%>

		</table>

	</center>
</div>
<!-- end of center content -->
<jsp:include page="../footera.jsp" />
