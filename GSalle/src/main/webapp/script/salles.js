$(document).ready(function() {
	$.ajax({
		url: "GSalleController",
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
		var code = $("#code").val();
		var capacite = $("#capacite").val();
		var type = $("#type").val();
		if (code !== "" && capacite !== "" && type !== "") {
			$.ajax({
				url: "GSalleController",
				data: { code: code, capacite: capacite, type: type },
				type: 'POST',
				success: function(data, textStatus, jqXHR) {
					remplir(data);
					$("#code").val("");
					$("#capacite").val("");
					$("#type").val("");
					alert("added");


				},
				error: function(jqXHR, textStatus, errorThrown) {
					console.log(textStatus);
				}
			});
		}
		else {
			alert("Remplir tous les champs")
		}


	});
	$("#content").on("click", ".delete", function() {
		//alert($(this).attr('val'));
		var id = $(this).closest('tr').find('td').eq(0).text();
		$.ajax({
			url: "GSalleController",
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
	$("#contentS").on("click", ".update", function() {


		var id = $(this).closest('tr').find('td').eq(0).text();
		var code = $("#code").val();
		var capacite = $("#capacite").val();
		var type = $("#type").val();


		$.ajax({
			url: "GSalleController",
			data: { op: "update", id: id, code: code, capacite: capacite, type: type },
			type: 'POST',
			success: function(data, textStatus, jqXHR) {
				alert("has been modified");
				remplir(data);
				$("#code").val("");
				$("#capacite").val("");
				$("#type").val("");

			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});

	});
	$("#table").on("click", "tbody tr", function() {
		var code = $(this).find('td:eq(1)').text();

		var capacite = $(this).find('td:eq(2)').text();
		var type = $(this).find('td:eq(3)').text();
		$("#code").val(code);
		$("#capacite").val(capacite);
		$("#type").val(type);

	});
	function remplir(data) {
		var ligne = "";
		for (var i = 0; i < data.length; i++) {
			//alert(data[i].dateAchat.ToString("dd/MM/yyyy"));
			ligne += "<tr><td>" + data[i].id + "</td><td>" + data[i].code + "</td><td>" + data[i].capacite + "</td><td>" + data[i].type + "</td><td><button class='btn btn-danger delete' val='" + data[i].id + "'>Supprimer</button></td><td><button class='btn btn-warning update' val='" + data[i].id + "'>Modifier</button></td></tr>";
		}
		$("#contentS").html(ligne);
	}
});