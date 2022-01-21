$(document).ready(function() {
	
	
	$("#shownew").click(function(){
			$.ajax({
		url: "GReservationController",
		data: { op: "loadN" },
		type: 'POST',
		success: function(data, textStatus, jqXHR) {
			//alert(data);
			remplirN(data);
			
		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR + "  " + errorThrown + "  " + textStatus);
			alert("No");
		}
	});
	});
	
	$("#showall").click(function(){
			$.ajax({
		url: "GReservationController",
		data: { op: "loadAll" },
		type: 'POST',
		success: function(data, textStatus, jqXHR) {
			//alert(data);
			remplirN(data);
			
		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR + "  " + errorThrown + "  " + textStatus);
			alert("No");
		}
	});
	});
	
	
	function remplirN(data) {
		var ligne = "";
		for (var i = 0; i < data.length; i++) {
			var debut1 = new Date(data[i].crenn.debut);
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
			var debut2 = new Date(data[i].crenn.fin);
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
			ligne += "<tr><td>" + data[i].id + "</td><td>" + data[i].date + "</td><td>" + data[i].salle.code + "</td><td> De " + h1 + ":" + m1 + " a " + h2 + ":" + m2 +"</td><td>"+ data[i].titre + "</td><td>" + data[i].client.login + "</td><td><button class='btn btn-danger accept' val='" + data[i].id + "'>Accepter</button></td><td><button class='btn btn-warning refus' val='" + data[i].id + "'>Refuser</button></td></tr>";
		}
		$("#contentN").html(ligne);
	}
	
	
	$("#contentN").on("click", ".accept", function() {
		//alert($(this).attr('val'));
		var id = $(this).closest('tr').find('td').eq(0).text();
		$.ajax({
			url: "GReservationController",
			data: { op: "accept", id: id },
			type: 'POST',
			success: function(data, textStatus, jqXHR) {
				alert("accept");
				remplirN(data);

			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(textStatus);
					alert("No");
			}
		});

	});
	$("#contentN").on("click", ".refus", function() {
		//alert($(this).attr('val'));
		var id = $(this).closest('tr').find('td').eq(0).text();
		$.ajax({
			url: "GReservationController",
			data: { op: "refus", id: id },
			type: 'POST',
			success: function(data, textStatus, jqXHR) {
				alert("refus");
				remplirN(data);

			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(textStatus);
					alert("No");
			}
		});

	});
});