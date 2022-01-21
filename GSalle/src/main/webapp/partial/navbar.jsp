<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div
		class="text-center navbar-brand-wrapper d-flex align-items-center justify-content-center">
		<a class="navbar-brand brand-logo" href="index.html"><img
			src="template/images/logo.svg" alt="logo"></a> <a
			class="navbar-brand brand-logo-mini" href="index.html"><img
			src="template/images/logo.jpg" alt="logo"></a>
		<button
			class="navbar-toggler navbar-toggler align-self-center d-none d-lg-flex"
			type="button" data-toggle="minimize">
			<span class="typcn typcn-th-menu"></span>
		</button>
	</div>
	<div
		class="navbar-menu-wrapper d-flex align-items-center justify-content-end">
		<ul class="navbar-nav mr-lg-2">
			<li class="nav-item  d-none d-lg-flex "><a class="nav-link active"
				href="reservation.jsp"> Planning </a></li>
			<li class="nav-item  d-none d-lg-flex"><a class="nav-link active"
				href="charts.jsp"> Statistic </a></li>
			
		</ul>
		<ul class="navbar-nav navbar-nav-right">
			
			
			
			<li class="nav-item nav-profile dropdown"><a
				class="nav-link dropdown-toggle  pl-0 pr-0" href="#"
				data-toggle="dropdown" id="profileDropdown"> <i
					class="typcn typcn-user-outline mr-0"></i> <span
					class="nav-profile-name"> <%=session.getAttribute("login")%></span>
			</a>
				<div class="dropdown-menu dropdown-menu-right navbar-dropdown"
					aria-labelledby="profileDropdown">
					<a class="dropdown-item"> <i
						class="typcn typcn-cog text-primary"></i> Settings
					</a>
					<button class="dropdown-item deco" id="deco" onclick="deco()">
						<i class="typcn typcn-power text-primary"></i> Logout
					</button>
				</div></li>
		</ul>
		<button
			class="navbar-toggler navbar-toggler-right d-lg-none align-self-center"
			type="button" data-toggle="offcanvas">
			<span class="typcn typcn-th-menu"></span>
		</button>
	</div>

<script src="script/client.js" type="text/javascript"></script>
</body>
</html>