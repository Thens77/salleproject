
<%
response.setHeader("Expires", "0");
response.setHeader("Pragma", "no-cache");
if (session.getAttribute("login") != null) {
	
	if (session.getAttribute("statut").equals("Admin")) {
		response.sendRedirect("index.jsp");
	}
	else if (session.getAttribute("statut").equals("Client")) {
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

<title>Gestion de reservation des salle</title>
<!-- base:css -->
<link rel="stylesheet"
	href="template/vendors/typicons.font/font/typicons.css">
<link rel="stylesheet"
	href="template/vendors/css/vendor.bundle.base.css">
<!-- endinject -->
<!-- plugin css for this page -->
<!-- End plugin css for this page -->
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
</head>

<body>
	<div class="container-scroller">
		<div class="container-fluid page-body-wrapper full-page-wrapper">
			<div class="content-wrapper d-flex align-items-center auth px-0">
				<div class="row w-100 mx-0">
					<div class="col-lg-4 mx-auto">
						<div class="auth-form-light text-left py-5 px-4 px-sm-5">
							<div class="brand-logo">
								<img src="template/images/logo.svg" alt="logo">
							</div>
							<h4>Bienvenue sur la page d'authentification</h4>
							<h6 class="font-weight-light">Veuillez s'authentifier pour y acceder.</h6>
							<form class="pt-3">
								<div class="form-group">
									<input type="text" class="form-control form-control-lg"
										id="login" name="login" placeholder="Username">
								</div>
								<div class="form-group">
									<input type="password" class="form-control form-control-lg"
										id="pass" name="pass" placeholder="Password">
								</div>
								<div class="mt-3">
									<button type="submit" id="signin"
										class="btn btn-block btn-primary btn-lg font-weight-medium auth-form-btn">SIGN
										IN</button>
								</div>
								<div
									class="my-2 d-flex justify-content-between align-items-center">
									<div class="form-check">
										<label class="form-check-label text-muted"> <input
											type="checkbox" class="form-check-input"> rester connecter
										</label>
									</div>
									<a href="#" class="auth-link text-black">Mot de passe oublie?</a>
								</div>

								<div class="text-center mt-4 font-weight-light">
									Don't have an account? <a href="register.html"
										class="text-primary">Create</a>
								</div>
							</form>
						</div>
					</div>
				</div>
			</div>
			<!-- content-wrapper ends -->
		</div>
		<!-- page-body-wrapper ends -->
	</div>
	<!-- container-scroller -->
	<!-- base:js -->
	<script src="templatevendors/js/vendor.bundle.base.js"></script>
	<!-- endinject -->
	<!-- inject:js -->
	<script src="template/js/off-canvas.js"></script>
	<script src="template/js/hoverable-collapse.js"></script>
	<script src="template/js/template.js"></script>
	<!--  <script src="template/js/settings.js"></script>-->
	<script src="template/js/todolist.js"></script>
	<script src="script/client.js" type="text/javascript"></script>
	<!-- endinject -->
</body>

</html>
