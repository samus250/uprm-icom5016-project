<jsp:include page="../header.jsp"/>
<div class="center_content">
<h1 class="title">

	<center>Payment</center>

<%@page import="java.util.*"%>
<%@page import="machete.db.model.dto.Invoice"%>
<%@page import="machete.db.model.dto.OrderItem"%>
<%
	Invoice invoice = (Invoice)request.getSession().getAttribute("invoice");
	ArrayList<OrderItem> orderItems = (ArrayList<OrderItem>)request.getSession().getAttribute("orderItems");
	
	// display total or something
	long invoiceId = invoice.getInvoiceId();
	double price = invoice.getInvoiceTprice();
	String totalPrice = String.format("%.2f", price);
	long nitems = orderItems.size();

%>
</h1>
<center>
	<h2>
	Payment processed!<br><br>
	Order Id: <%=invoiceId %><br>
	Total Items: <%=nitems %><br>
	Total Price: <%=totalPrice %><br>
	</h2>
</center>

</div>

<jsp:include page="../footer.jsp" />