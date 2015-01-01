<jsp:include page="../header.jsp" />
<div class="center_content">
<h1 class="title">

	<center>Edit Address</center>

</h1>
<br>

<%@page import="java.util.*"%>
<%@page import="machete.db.model.dto.Address"%>
<%
	Address address = (Address) request.getSession().getAttribute("address");
	long addressId = address.getAddressId();
	long userId = address.getUserId();
	String line1 = address.getAddressLine1();
	String line2 = address.getAddressLine2();
	String line3 = address.getAddressLine3();
	String city = address.getAddressCity();
	String state = address.getAddressState();
	String zip = address.getAddressZip();
	String country = address.getAddressCountry();
	String alias = address.getAddressAlias();
%>

<center>
	<h2 class="title">Enter information:</h2>
	<form name="add" action="/ICOM5016_Project/editAddress" method="POST">
		<h3>
			Alias (Personal Name): <input type="text" name="addressAlias" value="<%=alias %>" /><br><br>
			Line 1: <input type="text" name="addressLine1" value="<%=line1 %>" /><br><br>
			Line 2: <input type="text" name="addressLine2" value="<%=line2 %>" /><br><br>
			Line 3: <input type="text" name="addressLine3" value="<%=line3 %>" /><br><br>
			City: <input type="text" name="addressCity" value="<%=city %>" /><br><br>
			State: <input type="text" name="addressState" value="<%=state %>" /><br><br>
			Zip: <input type="text" name="addressZip" value="<%=zip %>" /><br><br>
			Country: <input type="text" name="addressCountry" value="<%=country %>" /><br><br>
			<input type="hidden" name="addressId" value="<%=addressId %>">
		</h3>
		<input type="submit" name="submit" value="Submit">
	</form>
</center>
</div>
<!-- end of center content -->
<jsp:include page="../footer.jsp" />