$(document).ready(function() {
	$.ajax({
		url: "GCreneauController",
		data: { op: "load" },
		type: 'POST',
		success: function(data, textStatus, jqXHR) {
			remplir(data);

		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR + "  " + errorThrown + "  " + textStatus);
		}
	});
	

	$("#add").click(function() {
		var debut = $("#debut").val();
		var fin = $("#fin").val();
		console.log();
		if (debut !== "" && fin !== "") {
			if(debut<fin){
				$.ajax({
				url: "GCreneauController",
				data: { debut: debut, fin: fin },
				type: 'POST',
				success: function(data, textStatus, jqXHR) {
					remplir(data);
					$("#debut").val("");
					$("#fin").val("");

					alert("added" + debut);


				},
				error: function(jqXHR, textStatus, errorThrown) {
					console.log(textStatus);
				}
			});
			}else{
				alert("heure debut doit etre avant heure fin");
			}
			
		}
		else {
			alert("Remplir tous les champs")
		}


	});
	$("#contentC").on("click", ".delete", function() {
		//alert($(this).attr('val'));
		var id = $(this).closest('tr').find('td').eq(0).text();
		$.ajax({
			url: "GCreneauController",
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
	$("#contentC").on("click", ".update", function() {


		var id = $(this).closest('tr').find('td').eq(0).text();
		var debut = $("#debut").val();
		var fin = $("#fin").val();


		$.ajax({
			url: "GCreneauController",
			data: { op: "update", id: id, debut: debut, fin: fin },
			type: 'POST',
			success: function(data, textStatus, jqXHR) {
				alert("has been modified");
				remplir(data);
				$("#debut").val("");
				$("#fin").val("");


			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});

	});
	$("#table").on("click", "tbody tr", function() {
		var debut = $(this).find('td:eq(1)').text();

		var fin = $(this).find('td:eq(2)').text();

		$("#debut").val(debut);
		$("#fin").val(fin);


	});
	function remplir(data) {
		var ligne = "";
		for (var i = 0; i < data.length; i++) {
			//alert(data[i].dateAchat.ToString("dd/MM/yyyy"));
			var debut1 = new Date(data[i].debut);
			var h = debut1.getHours();
			var h1;
			var m1;
			if (h < 10) {
				h1 = "0" + h ;
			} else {
				h1 = h;
			}
			var m = debut1.getMinutes();
			if (m < 10) {
				m1 = "0" + m ;
			} else {
				m1 = m;
			}
			var debut2 = new Date(data[i].fin);
			var h2 = debut2.getHours();
			var h2;
			var m2;
			if (h2 < 10) {
				h2 = "0" + h2 ;
			} else {
				h2 = h2;
			}
			var m2 = debut2.getMinutes();
			if (m2 < 10) {
				m2 = "0" + m2 ;
			} else {
				m2 = m2;
			}
			ligne += "<tr><td>" + data[i].id + "</td><td>" + h1 + ":" + m1 + "</td><td>" + h2 + ":" + m2 + "</td><td><button class='btn btn-danger delete' val='" + data[i].id + "'>Supprimer</button></td><td><button class='btn btn-warning update' val='" + data[i].id + "'>Modifier</button></td></tr>";
		}
		$("#contentC").html(ligne);
	}
});