$(document).ready(function() {
	$.ajax({
		url: "GChartsController",
		data: { op: "load1" },
		type: 'POST',
		success: function(data, textStatus, jqXHR) {
			remplir1(data);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR + "  " + errorThrown + "  " + textStatus);
		}
	});
	
	
	

	function remplir1(data) {
		var ligne = "<option value=''>--Please choose an option--</option>"
		for (var i = 0; i < data.length; i++) {
			ligne += "<option value='" + data[i].id + "'>" + data[i].code + "</option>";
		}
		$("#salles").html(ligne);
		//$("#salles2").html(ligne);
	}
	
	
	
	
	$("#years").on('change', function() {
		eventss = [];
		var select = document.getElementById('years');
		var value = select.options[select.selectedIndex].value;
		//document.getElementById('years').value = value;


		$.ajax({
			url: "GChartsController",
			data: { op: "loadByYear", year: value },
			type: 'POST',
			success: function(data, textStatus, jqXHR) {

				//  var json = $.parseJSON(data);
				//alert(json) ;
				const myChart = document.getElementById("myChart");
				var ctx = myChart.getContext('2d');

				let gradient = ctx.createLinearGradient(0, 0, 0, 400);
				gradient.addColorStop(0, "rgba(58,123,213,1)");
				gradient.addColorStop(1, "rgba(32,143,43,0.3)")
				const labels = [
					"Jan",
					"Fev",
					"Mar",
					"Avr",
					"Mai",
					"Ju",
					"Jui",
					"Aut",
					"Sep",
					"Oct",
					"Nov",
					"Dec",
				];
				const data1 = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
				for (var i = 0; i < data.length; i++) {
					var y = parseInt(data[i].mois);
					//alert(y);
					data1[y - 1] = parseInt(data[i].nombre);
				}
				for (var i = 0; i < 12; i++) {
					//alert("mois " +(i+1)  + "nombre: "+data1[i]);
				}


				const data2 = {
					labels,
					datasets: [
						{
							data: data1,
							label: "nombre de resrvation par mois pour l'annee " + value,
							fill: true,
							backgroundColor: gradient,
							borderColor: "#DF9797",
							pointBackgroundColor: "red",
						},
					],
				};

				const config = {
					type: 'line',
					data: data2,
					options: {
						responsive: true,
					},

				};
				const Chart1 = new Chart(myChart, config);

			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR + "  " + errorThrown + "  " + textStatus);
			}
		});
	});






	$("#afficher").click(function() {
		eventss = [];
		var select = document.getElementById('years2');
		var year = select.options[select.selectedIndex].value;
		
		var select = document.getElementById('salles');
		var salle = select.options[select.selectedIndex].value;
		
		
		//document.getElementById('years').value = value;


		$.ajax({
			url: "GChartsController",
			data: { op: "loadBySalleYear", salle: salle, year: year },
			type: 'POST',
			success: function(data, textStatus, jqXHR) {

				//  var json = $.parseJSON(data);
				//alert(json) ;
				const myChart2 = document.getElementById("myChart2");
				var ctx = myChart.getContext('2d');

				let gradient = ctx.createLinearGradient(0, 0, 0, 400);
				gradient.addColorStop(0, "rgba(173,23,213,1)");
				gradient.addColorStop(1, "rgba(32,13,43,0.7)")
				const labels = [
					"Jan",
					"Fev",
					"Mar",
					"Avr",
					"Mai",
					"Ju",
					"Jui",
					"Aut",
					"Sep",
					"Oct",
					"Nov",
					"Dec",
				];
				const data1 = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0];
				for (var i = 0; i < data.length; i++) {
					var y = parseInt(data[i].mois);
					//alert(y);
					data1[y - 1] = parseInt(data[i].nombre);
				}
				for (var i = 0; i < 12; i++) {
					//alert("mois " +(i+1)  + "nombre: "+data1[i]);
				}


				const data2 = {
					labels,
					datasets: [
						{
							data: data1,
							label: "nombre de resrvation par mois pour l'annee " + year ,
							fill: true,
							backgroundColor: gradient,
							borderColor: "#DF9797",
							pointBackgroundColor: "red",
						},
					],
				};

				const config = {
					type: 'line',
					data: data2,
					options: {
						responsive: true,
					},

				};
				const Chart2 = new Chart(myChart2, config);

			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR + "  " + errorThrown + "  " + textStatus);
			}
		});




	});





});
