<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>MacheteDB Admin Panel Login</title>
<link rel="stylesheet" href="admin/css/style.css" type="text/css"
	media="screen" title="default" />
<!--  jquery core -->
<script src="admin/js/jquery/jquery-1.4.1.min.js" type="text/javascript"></script>

<!-- Custom jquery scripts -->
<script src="admin/js/jquery/custom_jquery.js" type="text/javascript"></script>

<!-- MUST BE THE LAST SCRIPT IN <HEAD></HEAD></HEAD> png fix -->
<script src="admin/js/jquery/jquery.pngFix.pack.js" type="text/javascript"></script>
<script type="text/javascript">
	$(document).ready(function() {
		$(document).pngFix();
	});
</script>
</head>
<body id="login-bg">

	<!-- Start: login-holder -->
	<div id="login-holder">

		<!-- start logo -->
		<div id="logo-login">
			<a href="/ICOM5016_Project/"><img src="/ICOM5016_Project/images/machetelogo.png"
				width="200" height="100" alt="" /></a>
		</div>
		<!-- end logo -->

		<div class="clear"></div>

		<!--  start loginbox ................................................................................. -->
		<div id="loginbox">

			<!--  start login-inner -->
			<div id="login-inner">
				<form name="loginAdmin" action="/ICOM5016_Project/aReadCustomerServlet" method="post">
					<h3>
						Username: <input type="text" class="login-inp" name="userName" />
						<br>
					</h3>
					<h3>
						Password: <input type="password" name="password"
							onfocus="this.value=''" class="login-inp" />
					</h3>
					<br><input type="submit" class="submit-login" name="submit" value="Submit">
				</form>
			</div>
			<!--  end login-inner -->
			<div class="clear"></div>
			<a href="" class="forgot-pwd">Forgot Password?</a>
		</div>
		<!--  end loginbox -->

		<!--  start forgotbox ................................................................................... -->
		<div id="forgotbox">
			<div id="forgotbox-text">Please send us your email and we'll
				reset your password.</div>
			<!--  start forgot-inner -->
			<div id="forgot-inner">
				<table border="0" cellpadding="0" cellspacing="0">
					<tr>
						<th>Email address:</th>
						<td><input type="text" value="" class="login-inp" /></td>
					</tr>
					<tr>
						<th></th>
						<td><a href=""><input type="submit" class="submit-login" /></a></td>
					</tr>
				</table>
			</div>
			<!--  end forgot-inner -->
			<div class="clear"></div>
			<a href="" class="back-login">Back to login</a>
		</div>
		<!--  end forgotbox -->

	</div>
	<!-- End: login-holder -->
</body>
</html>