
<%
response.setHeader("Expires", "0");
response.setHeader("Pragma", "no-cache");
if (session.getAttribute("login") == null) {
	response.sendRedirect("auth.jsp");
} else if (session.getAttribute("login") != null) {
	if (session.getAttribute("statut").equals("Client")) {
		response.sendRedirect("home.jsp");
	}
}
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Statistiques</title>
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>CelestialUI Admin</title>
<!-- base:css -->
<link rel="stylesheet"
	href="template/vendors/typicons.font/font/typicons.css">
<link rel="stylesheet"
	href="template/vendors/css/vendor.bundle.base.css">
<!-- endinject -->
<!-- inject:css -->
<link rel="stylesheet"
	href="template/css/vertical-layout-light/style.css">
<!-- endinject -->
<link rel="shortcut icon" href="template/images/favicon.png" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3"
	crossorigin="anonymous">
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
	integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
	crossorigin="anonymous"></script>
<script src="script/jquery-3.3.1.min.js" type="text/javascript"></script>
<script src="script/charts.js" type="text/javascript"></script>
<script
	src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.js"
	type="text/javascript"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
	integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
	crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/3.5.1/chart.min.js" integrity="sha512-Wt1bJGtlnMtGP0dqNFH1xlkLBNpEodaiQ8ZN5JLA5wpc1sUlk/O5uuOMNgvzddzkpvZ9GLyYNa8w2s7rqiTk5Q==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>

</head>

<body>

	<div class="container-scroller d-flex p-0">
		<nav class="navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
			<%@include file="partial/navbar.jsp"%>
		</nav>
		<div class="container-fluid px-0 page-body-wrapper">

			<div class="theme-setting-wrapper">
				<%@include file="partial/themeconfig.jsp"%>
			</div>

			<nav class="sidebar sidebar-offcanvas" id="sidebar">

				<%@include file="partial/sidebar.jsp"%>
			</nav>
			<div class="main-panel">
				<div class="content-wrapper">
					<div class="row">
						
							<div class="card col-sm-6">
								<div class="card-body">
									<h4 class="card-title">Statistiques sur le nombre des reservations effectuees par annee</h4>
									<p class="card-description">Veuillez hoisir l'annee</p>
									<form class="forms-sample" name="salleform">
										<div class="form-group">
											<label for="years"> Annee </label> <select id="years"
												name="years" class="form-control">
												<option>year</option>
												
												<option value="2010">2010</option>
												<option value="2011">2011</option>
												<option value="2012">2012</option>
												<option value="2013">2013</option>
												<option value="2014">2014</option>
												<option value="2015">2015</option>
												<option value="2016">2016</option>
												<option value="2017">2017</option>
												<option value="2018">2018</option>
												<option value="2019">2019</option>
												<option value="2020">2020</option>
												<option value="2021">2021</option>
												<option value="2022">2022</option>
											</select>

										</div>
									</form>
									
									<canvas id="myChart" width="400" height="200"></canvas>
								</div>
								
								
								
								
								
						</div>
					
					<div class="card col-sm-6">
								<div class="card-body">
									<h4 class="card-title">Statistiques sur le nombre des reservations effectuees pour une salle par annee</h4>
									<p class="card-description">Veuillez choisir la salle et l'annee</p>
									<form class="forms-sample" name="salleform">
									<div class="row"> 
									<div class="form-group col-5">
									 
											<label for="salles"> Salle </label> <select name="salles"
												id="salles" class="form-control">

											</select>

										</div>
										<div class="form-group col-5">
										
											<label for="years2"> Annee </label> <select id="years2"
												name="years2" class="form-control">
												<option>year</option>
												
												<option value="2010">2010</option>
												<option value="2011">2011</option>
												<option value="2012">2012</option>
												<option value="2013">2013</option>
												<option value="2014">2014</option>
												<option value="2015">2015</option>
												<option value="2016">2016</option>
												<option value="2017">2017</option>
												<option value="2018">2018</option>
												<option value="2019">2019</option>
												<option value="2020">2020</option>
												<option value="2021">2021</option>
												<option value="2022">2022</option>
											</select>

										</div>
										
										<div class="form-group col-2"><label for="years2">  </label><button class="btn btn-warning" id="afficher" type="button">Afficher</button></div>
										</div>
										
										
									</form>
									
									<canvas id="myChart2" width="400" height="200"></canvas>
								</div>
							</div>
					<div class="row">
						<div class="col-sm-8"></div>
					</div>
				</div>
			</div>
		</div>

	</div>
	<script src="template/vendors/js/vendor.bundle.base.js"></script>
	<!-- endinject -->
	<!-- Plugin js for this page-->
	<!-- End plugin js for this page-->
	<!-- inject:js -->
	<script src="template/js/off-canvas.js"></script>
	<script src="template/js/hoverable-collapse.js"></script>
	<script src="template/js/template.js"></script>
	<script src="template/js/settings.js"></script>
	<script src="template/js/todolist.js"></script>
	<!-- endinject -->
	<!-- plugin js for this page -->
	<script src="template/vendors/progressbar.js/progressbar.min.js"></script>
	<script src="template/vendors/chart.js/Chart.min.js"></script>
	<!-- End plugin js for this page -->
	<!-- Custom js for this page-->
	<script src="template/js/dashboard.js"></script>

</body>
</html>