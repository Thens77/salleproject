
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
<title>Reservation</title>
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
<link rel="stylesheet" href="fullcalendar/core/main.css">
<link rel="stylesheet" href="fullcalendar/daygrid/main.css">
<link rel="stylesheet" href="fullcalendar/timegrid/main.css">
<link rel="stylesheet" href="fullcalendar/list/main.css">


<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js"
	integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB"
	crossorigin="anonymous"></script>
<script src="script/jquery-3.3.1.min.js" type="text/javascript"></script>

<script
	src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.js"
	type="text/javascript"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
	integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
	crossorigin="anonymous"></script>
<style>
#popup-form {
	position: absolute;
	background-color:;
	opacity: 0;
	top: -150%;
	left: 50%;
	transform: translate(-50%, -50%);
	box-shadow: 2px 2px 5px 5px rgba(0, 0, 0, 0.15);
	transition: top 0ms ease-int-out 200ms, opacity 200ms ease-int-out 0ms,
		transform 200ms ease-int-out 0ms;
}

#popup-form.active {
	z-index: 1000;
	position: fixed;
	opacity: 1;
	top: 50%;
	transition: top 0ms ease-int-out 200ms, opacity 200ms ease-int-out 0ms,
		transform 200ms ease-int-out 0ms;
}

.close-btn {
	position: absolute;
	top: 25px;
	right: 25px;
	width: 25px;
	height: 25px;
	background: #E0002D;
	color: white;
	text-align: center;
	line-height: 25px;
	cursor: pointer;
	border-radius: 5px;
}
}
</style>
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
						<div class="col-sm-12">
							
							<div class="card col-sm-8">
								<div class="card-body">
									<h4 class="card-title">Choisir</h4>
									<p class="card-description">Choisir la salle</p>
									<form class="forms-sample" name="salleform">
										<div class="form-group">
											<label for="salles"> Salle </label> <select name="salles"
												id="salles" class="form-control">

											</select>

										</div>
									</form>
								</div>
							</div>
							<div class="card col-sm-8">
								<div class="card-body">
									<h4 class="card-title">Planning des reservations </h4>
									<p class="card-description">Selectionner une date pour
										reserver</p>
									<div id="calendrier">
									Choisir la salle pour visualiser le plannig
									</div>
								</div>
							</div>

						</div>

					</div>

				</div>
			</div>
		</div>
		<div class="card col-5" id="popup-form">
			<div class="card-body">
				<div class="close-btn">X</div>
				<h4 class="card-title">Formulaire de reservation</h4>
				<p class="card-description">Veuillez remplir les informations :</p>
				<form class="forms-sample" name="salleform">
					<div class="form-group">
						<label for="exampleInputUsername1">Utilisateur</label> <input
							type="text" name="user" id="user" class="form-control" disabled
							placeholder="user" value="<%=session.getAttribute("login")%>"
							required>
					</div>
					<div class="form-group">

						<label for="exampleInputUsername1">Titre</label> <input
							type="text" name="titre" id="titre" class="form-control"
							placeholder="titre" required>
					</div>
					<div class="form-group">
						<label for="exampleInputUsername1">Date</label> <input type="date"
							name="date" id="date" class="form-control" placeholder="Date"
							required disabled>
					</div>
					<div class="form-group">
						<label for="salles2"> Salle </label> <select name="salles2"
							id="salles2" class="form-control" required disabled>

						</select>

					</div>
					<div class="form-group">
						<label for="creneaux"> Creneaux </label> <select name="creneaux"
							id="creneaux" class="form-control">

						</select>

					</div>

					<div class="text-center">
						<button type="submit" id="addReservation"
							class="btn btn-info mr-2 align-center">Reserver</button>
					</div>


				</form>
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
	<script src="fullcalendar/core/main.js"></script>
	<script src="fullcalendar/daygrid/main.js"></script>
	<script src="fullcalendar/timegrid/main.js"></script>
	<script src="fullcalendar/list/main.js"></script>
	<script src='fullcalendar/interaction/main.js'></script>
	<script src="script/reservation.js"></script>

</body>
</html>