
<%
response.setHeader("Expires", "0");
response.setHeader("Pragma", "no-cache");
if (session.getAttribute("login") == null) {
	response.sendRedirect("auth.jsp");
} else if (session.getAttribute("login") != null) {
	if (session.getAttribute("statut").equals("Admin")) {
		response.sendRedirect("index.jsp");
	}
}
%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Mes demandes</title>
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
<script src="script/reservation.js" type="text/javascript"></script>
<script
	src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.3/dist/jquery.validate.js"
	type="text/javascript"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js"
	integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13"
	crossorigin="anonymous"></script>
	
	<script>
function myFunction() {
  // Declare variables
  var input, filter, table, tr, td, i, txtValue;
  input = document.getElementById("search");
  filter = input.value.toUpperCase();
  table = document.getElementById("table");
  tr = table.getElementsByTagName("tr");

  // Loop through all table rows, and hide those who don't match the search query
  for (i = 0; i < tr.length; i++) {
    td = tr[i].getElementsByTagName("td")[2];
    if (td) {
      txtValue = td.textContent || td.innerText;
      if (txtValue.toUpperCase().indexOf(filter) > -1) {
        tr[i].style.display = "";
      } else {
        tr[i].style.display = "none";
      }
    }
  }
}
</script>

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

				<%@include file="partial/sidebar2.jsp"%>
			</nav>
			<div class="main-panel">
				<div class="content-wrapper">
				<div class="row">
						<div class="col-1">
							<button class="btn btn-primary" type="button" id="shownew"> Nouveaux </button>
						</div>
						<div class="col-1">
							<button class="btn btn-primary" type="button" id="showall"> Tous </button>
						</div>
						<div class="col-8 text-align-right">
							
						</div>
						<div class="col-2 text-align-right">
							<div class="form-group">
							<i class="fi fi-rr-zoom-out"></i>
							
						 <input
							type="text" name="search" id="search" class="form-control" 
							placeholder="recherche" size="100" onkeyup="myFunction()"
							>
					</div>
						</div>
					</div>
					<div class="row">
					
							<fieldset>
								<legend>Liste des nouveaux demandes de reservation</legend>

								<table border="1" class="table  table-hover" id="table">
									<thead class="bg-dark" style="color: white">
										<tr class="color-white">
											<th scope="col" class="color-white">Id</th>
											<th>Date<br>
											<th>Salle</th>
											<th>Creneau</th>
											<th>Titre</th>
											<th>Client</th>
											<th>Etat</th>
											<th>Supprimer</th>
											
										</tr>
									</thead>
									<tbody id="contentDem" class="bg-white">

									</tbody>
								</table>

							</fieldset>
						</div>
					
					
					
					</div>
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
	<script src="script/reservation.js"></script>

</body>
</html>