<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252" />
<title>Machete DB</title>
<link rel="stylesheet" type="text/css" href="/ICOM5016_Project/style.css" />
<!--[if IE 6]>
<link rel="stylesheet" type="text/css" href="/ICOM5016_Project/iecss.css" />
<![endif]-->

<script>
	PositionX = 100;
	PositionY = 100;

	defaultWidth = 500;
	defaultHeight = 500;
	var AutoClose = true;

	if (parseInt(navigator.appVersion.charAt(0)) >= 4) {
		var isNN = (navigator.appName == "Netscape") ? 1 : 0;
		var isIE = (navigator.appName.indexOf("Microsoft") != -1) ? 1 : 0;
	}
	var optNN = 'scrollbars=no,width=' + defaultWidth + ',height='
			+ defaultHeight + ',left=' + PositionX + ',top=' + PositionY;
	var optIE = 'scrollbars=no,width=150,height=100,left=' + PositionX
			+ ',top=' + PositionY;
	function popImage(imageURL, imageTitle) {
		if (isNN) {
			imgWin = window.open('about:blank', '', optNN);
		}
		if (isIE) {
			imgWin = window.open('about:blank', '', optIE);
		}
		with (imgWin.document) {
			writeln('<html><head><title>Loading...</title><style>body{margin:0px;}</style>');
			writeln('<sc'+'ript>');
			writeln('var isNN,isIE;');
			writeln('if (parseInt(navigator.appVersion.charAt(0))>=4){');
			writeln('isNN=(navigator.appName=="Netscape")?1:0;');
			writeln('isIE=(navigator.appName.indexOf("Microsoft")!=-1)?1:0;}');
			writeln('function reSizeToImage(){');
			writeln('if (isIE){');
			writeln('window.resizeTo(300,300);');
			writeln('width=300-(document.body.clientWidth-document.images[0].width);');
			writeln('height=300-(document.body.clientHeight-document.images[0].height);');
			writeln('window.resizeTo(width,height);}');
			writeln('if (isNN){');
			writeln('window.innerWidth=document.images["George"].width;');
			writeln('window.innerHeight=document.images["George"].height;}}');
			writeln('function doTitle(){document.title="' + imageTitle + '";}');
			writeln('</sc'+'ript>');
			if (!AutoClose)
				writeln('</head><body bgcolor=ffffff scroll="no" onload="reSizeToImage();doTitle();self.focus()">')
			else
				writeln('</head><body bgcolor=ffffff scroll="no" onload="reSizeToImage();doTitle();self.focus()" onblur="self.close()">');
			writeln('<img name="George" src='+imageURL+' style="display:block"></body></html>');
			close();
		}
	}
</script>

<script type="text/javascript" src="/ICOM5016_Project/js/boxOver.js"></script>
</head>
<body>

	<div id="main_container">
		<div id="header">

			<div id="logo">
				<a href="/ICOM5016_Project/admin"><img src="/ICOM5016_Project/images/machetelogo.png" alt="" title=""
					border="0" width="277" height="140" /></a>
			</div>

			<div class="oferte_content">
				<div class="top_divider">
					<img src="images/header_divider.png" alt="" title="" width="1"
						height="164" />
				</div>
				<div class="oferta">

					<div class="oferta_content">
						<!-- <img src="/ICOM5016_Project/images/laptop.png" width="94"
							height="92" border="0" class="oferta_img" />-->

						<div class="oferta_details">
							<div class="oferta_title">Welcome to the Administrator Panel</div>
							<div class="oferta_text">Machete DB Enterprise</div>
							<a href="/ICOM5016_Project/myAccountAdmin" class="details">My Account</a>
						</div>
					</div>

				</div>
				<div class="top_divider">
					<img src="/ICOM5016_Project/images/header_divider.png" alt="" title="" width="1"
						height="164" />
				</div>

			</div>
			<!-- end of oferte_content-->


		</div>

		<div id="main_content">

			<div id="menu_tab">
				<div class="left_menu_corner"></div>
				<ul class="menu">
					<li><a href="/ICOM5016_Project/admin" class="nav1"> Home </a></li>
					<li class="divider"></li>
					<li><a href="/ICOM5016_Project/admin/products" class="nav2">Products</a></li>
					<li class="divider"></li>
					<li><a href="/ICOM5016_Project/admin/categories" class="nav3">Categories</a></li>
					<li class="divider"></li>
					<li><a href="/ICOM5016_Project/admin/customers" class="nav4">Customers</a></li>
					<li class="divider"></li>
					<li><a href="/ICOM5016_Project/" class="nav5">Go To Site</a></li>
					<li class="divider"></li>
					<li><a href="/ICOM5016_Project/logoutAdmin" class="nav5">Log Out</a></li>
					<li class="divider"></li>
					<!-- 
                         <li><a href="contact.html" class="nav6">Contact Us</a></li>
                         <li class="divider"></li>
                         <li class="currencies">Currencies
                         <select>
                         <option>US Dollar</option>
                         <option>Euro</option>
                         </select>
                         </li>
                         -->
                         <li><div class="search_text">
				</div>
				<form name="search" action="/ICOM5016_Project/ProductSearch">
				<input type="text" name="search" class="search_input" /> <input type="submit" value ="go" class="search_bt"/>
				</form></li></ul>
				

				<div class="right_menu_corner"><!--<div class="top_search">
				 <div class="search_text">
					<a href="#">Advanced Search</a>
				</div>
				<input type="text" class="search_input" name="search" /> <input
					type="image" src="/ICOM5016_Project/images/search.gif" class="search_bt" />
			</div>--></div>
			</div>
			<!-- end of menu tab -->

			<!-- <div class="crumb_navigation">
				Navigation: <a href="/ICOM5016_Project/">Home</a> &lt; <span class="current">Category
					name</span>

			</div>-->


			<div class="left_content">
				<div class="title_box">Categories</div>

				<ul class="left_menu">
				<%// add loop-dee-loops here %>
					<%@page import="java.util.*"%>
					<%@page import="machete.db.model.dto.Category"%>
					<%
					ArrayList<Category> categories = (ArrayList<Category>)request.getSession().getAttribute("categories");
					for(int i = 0; i < categories.size(); i++) {
					String link = "/ICOM5016_Project/showCategoryAdmin?category_id=" + categories.get(i).getCategoryId();
					String categoryName = categories.get(i).getCategoryName();
					String par = "odd";
					if(i%2!=0)par="even";
				
					%>
					<li class="<%=par %>"><a href="<%=link %>"><%=categoryName %></a></li>
					<%
					}
					%>
				</ul>


			</div>
			<!-- end of left content -->