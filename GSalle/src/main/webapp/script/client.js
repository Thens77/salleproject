$(document).ready(function() {
	$.ajax({
		url: "GClientController",
		data: { op: "load" },
		type: 'POST',
		success: function(data, textStatus, jqXHR) {
			remplir(data);
 


		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR + "  " + errorThrown + "  " + textStatus);
		}
	});
	$("#deco").click(function() {

		//alert("deco");
		$.ajax({
			url: "GClientController",
			data: { op: "deco" },
			type: 'POST',
			success: function(data, textStatus, jqXHR) {

				//location.assign("auth.jsp");
				window.location.replace("auth.jsp");
				//   history.forward();
				//location.reload();

			},
			error: function(jqXHR, textStatus, errorThrown) {

			}



		});
	});

	$("#signin").click(function() {
		//alert("clickeddddddddd1");
		var login = $("#login").val();
		var pass = $("#pass").val();


		if (login !== "" && pass !== "") {

			$.ajax({
				url: "GClientController",
				data: { op: "signin", login: login, pass: pass },
				type: 'POST',
				success: function(data, textStatus, jqXHR) {
					var parsed_data = JSON.parse(data);
					var loginn = parsed_data.login;
					var statut = parsed_data.statut;

					//alert(login + " " + pass)
                    if (statut.localeCompare("Admin")) {
	                     
						window.location.replace("index.jsp");
					}
					if (statut.localeCompare("Client")) {
						
						window.location.replace("home.jsp");
					}
s
					//location.reload();




				},
				error: function(jqXHR, textStatus, errorThrown) {
					console.log(textStatus);
					alert("no")
				}
			});
		}

		else {
			alert("Remplir tous les champs")
		}


	});

	$("#add").click(function() {
		var nom = $("#nom").val();
		var prenom = $("#prenom").val();
		var cin = $("#cin").val();
		var login = $("#login").val();
		var pass = $("#pass").val();
		var repass = $("#repass").val();

		if (nom !== "" && prenom !== "" && login !== "" && cin !== "" && pass !== "" && repass !== "") {
			if (pass.localeCompare(repass) == 0) {
				$.ajax({
					url: "GClientController",
					data: { nom: nom, prenom: prenom, cin: cin, login: login, pass: pass },
					type: 'POST',
					success: function(data, textStatus, jqXHR) {
						remplir(data);
						$("#nom").val("");
						$("#prenom").val("");
						$("#cin").val("");
						$("#login").val("");
						$("#pass").val("");
						$("#reppas").val("");
						alert("added");
					},
					error: function(jqXHR, textStatus, errorThrown) {
						console.log(textStatus);
						alert("no")
					}
				});
			} else {
				alert("les mot de passes ne sont pas identiques")
			}
		}
		else {
			alert("Remplir tous les champs")
		}


	});
	$("#content").on("click", ".delete", function() {
		//alert($(this).attr('val'));
		var id = $(this).closest('tr').find('td').eq(0).text();
		$.ajax({
			url: "GClientController",
			data: { op: "delete", id: id },
			type: 'POST',
			success: function(data, textStatus, jqXHR) {
				remplir(data);

			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});

	});
	$("#content").on("click", ".update", function() {
       

		var id = $(this).closest('tr').find('td').eq(0).text();
		var nom = $("#nom").val();
		var prenom = $("#prenom").val();
		var cin = $("#cin").val();
		var login = $("#login").val();
		var pass = $("#pass").val();
		var repass = $("#repass").val();

if (nom !== "" && prenom !== "" && login !== "" && cin !== "" && pass !== "" && repass !== "") {
			if (pass.localeCompare(repass) == 0) {
		$.ajax({
			url: "GClientController",
			data: { op: "update", id: id, nom: nom, prenom: prenom, cin: cin, login: login, pass: pass },
			type: 'POST',
			success: function(data, textStatus, jqXHR) {
				alert("bien modifiee");
				remplir(data);
				$("#nom").val("");
				$("#prenom").val("");
				$("#cin").val("");
				$("#login").val("");
				$("#pass").val("");
				$("#repass").val("");

			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(textStatus);
			}
			
		});
		} else {
				alert("les mot de passes ne sont pas identiques")
			}
		}
		else {
			alert("Remplir tous les champs")
		}

	});
	$("#table").on("click", "tbody tr", function() {
		var nom = $(this).find('td:eq(1)').text();

		var prenom = $(this).find('td:eq(2)').text();
		var cin = $(this).find('td:eq(3)').text();
		var login = $(this).find('td:eq(4)').text();
		var pass = $(this).find('td:eq(5)').text();

		$("#nom").val(nom);
		$("#prenom").val(prenom);
		$("#cin").val(cin);
		$("#login").val(login);
		//$("#pass").val(pass);

	});
	function remplir(data) {
		var ligne = "";
		for (var i = 0; i < data.length; i++) {
			//alert(data[i].dateAchat.ToString("dd/MM/yyyy"));
			ligne += "<tr><td>" + data[i].id + "</td><td>" + data[i].nom + "</td><td>" + data[i].prenom + "</td><td>" + data[i].cin + "</td><td>" + data[i].login + "</td><td><button class='btn btn-danger delete' val='" + data[i].id + "'>Supprimer</button></td><td><button class='btn btn-warning update' val='" + data[i].id + "'>Modifier</button></td></tr>";
		}
		$("#content").html(ligne);
	}
});