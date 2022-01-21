$(document).ready(function() {
	let calendrier = null;
	let eventss = [];

	$("#shownew").click(function(){
			$.ajax({
		url: "GReservationController",
		data: { op: "loadDem" },
		type: 'POST',
		success: function(data, textStatus, jqXHR) {
			//alert("load");
			remplirDem(data);

		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR + "  " + errorThrown + "  " + textStatus);
			alert("No");
		}
	});
	});
	$("#shownew").click(function(){
			$.ajax({
		url: "GReservationController",
		data: { op: "loadNDemC" },
		type: 'POST',
		success: function(data, textStatus, jqXHR) {
			//alert("load");
			remplirNDem(data);

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
		data: { op: "loadDem" },
		type: 'POST',
		success: function(data, textStatus, jqXHR) {
			//alert("load");
			remplirDem(data);

		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR + "  " + errorThrown + "  " + textStatus);
			alert("No");
		}
	});
	});

	function remplirDem(data) {
		var ligne = "";
		for (var i = 0; i < data.length; i++) {
			var debut1 = new Date(data[i].crenn.debut);
			var h = debut1.getHours();
			var h1;
			var m1;
			if (h < 10) {
				h1 = "0" + h;
			} else {
				h1 = h;
			}
			var m = debut1.getMinutes();
			if (m < 10) {
				m1 = "0" + m;
			} else {
				m1 = m;
			}
			var debut2 = new Date(data[i].crenn.fin);
			var h2 = debut2.getHours();
			var h2;
			var m2;
			if (h2 < 10) {
				h2 = "0" + h2;
			} else {
				h2 = h2;
			}
			var m2 = debut2.getMinutes();
			if (m2 < 10) {
				m2 = "0" + m2;
			} else {
				m2 = m2;
			}
			ligne += "<tr><td>" + data[i].id + "</td><td>" + data[i].date + "</td><td>" + data[i].salle.code + "</td><td> De " + h1 + ":" + m1 + " a " + h2 + ":" + m2 + "</td><td>" + data[i].titre + "</td><td>" + data[i].client.login + "</td><td>" + data[i].etat + "</td>";
		}
		$("#contentDem").html(ligne);
	}
	
	function remplirNDem(data) {
		var ligne = "";
		for (var i = 0; i < data.length; i++) {
			var debut1 = new Date(data[i].crenn.debut);
			var h = debut1.getHours();
			var h1;
			var m1;
			if (h < 10) {
				h1 = "0" + h;
			} else {
				h1 = h;
			}
			var m = debut1.getMinutes();
			if (m < 10) {
				m1 = "0" + m;
			} else {
				m1 = m;
			}
			var debut2 = new Date(data[i].crenn.fin);
			var h2 = debut2.getHours();
			var h2;
			var m2;
			if (h2 < 10) {
				h2 = "0" + h2;
			} else {
				h2 = h2;
			}
			var m2 = debut2.getMinutes();
			if (m2 < 10) {
				m2 = "0" + m2;
			} else {
				m2 = m2;
			}
			ligne += "<tr><td>" + data[i].id + "</td><td>" + data[i].date + "</td><td>" + data[i].salle.code + "</td><td> De " + h1 + ":" + m1 + " a " + h2 + ":" + m2 + "</td><td>" + data[i].titre + "</td><td>" + data[i].client.login + "</td><td>" + data[i].etat + "</td><td><button class='btn btn-danger delete' val='" + data[i].id + "'>Supprimer</button></td>";
		}
		$("#contentDem").html(ligne);
	}
	
	$("#contentDem").on("click", ".delete", function() {
		//alert($(this).attr('val'));
		var id = $(this).closest('tr').find('td').eq(0).text();
		$.ajax({
			url: "GReservationController",
			data: { op: "delete", id: id },
			type: 'POST',
			success: function(data, textStatus, jqXHR) {
				remplirNDem(data);

			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(textStatus);
			}
		});

	});

	$.ajax({
		url: "GReservationController",
		data: { op: "load1" },
		type: 'POST',
		success: function(data, textStatus, jqXHR) {
			remplir1(data);
		},
		error: function(jqXHR, textStatus, errorThrown) {
			console.log(jqXHR + "  " + errorThrown + "  " + textStatus);
		}
	});



	$("#addReservation").click(function() {
		// alert("clickedd");
		var select = document.getElementById('salles2');
		var salle = select.options[select.selectedIndex].value;

		var select2 = document.getElementById('creneaux');
		var creneau = select2.options[select2.selectedIndex].value;

		var titre = $("#titre").val();
		var user = $("#user").val();
		var date = $("#date").val();


		//	alert(user);

		if (titre !== "" && date !== "" && creneau !== "" && salle !== "") {
			$.ajax({
				url: "GReservationController",
				data: { user: user, titre: titre, date: date, creneau: creneau, salle: salle },
				type: 'POST',
				success: function(data, textStatus, jqXHR) {
					//remplir(data);
					$("#date").val("");
					$("#titre").val("");

					alert("Demande de reservation bien placee");


				},
				error: function(jqXHR, textStatus, errorThrown) {
					console.log(textStatus);
					alert("No");
				}
			});
		}
		else {
			alert("Remplir tous les champs")
		}


	});


	function remplir1(data) {
		var ligne = "<option value=''>--Please choose an option--</option>"
		for (var i = 0; i < data.length; i++) {
			ligne += "<option value='" + data[i].id + "'>" + data[i].code + "</option>";
		}
		$("#salles").html(ligne);
		$("#salles2").html(ligne);
	}
	function remplirC(data) {
		var ligne = "<option value=''>--Please choose an option--</option>";
		for (var i = 0; i < data.length; i++) {
			var debut1 = new Date(data[i].debut);
			var h = debut1.getHours();
			var h1;
			var m1;
			if (h < 10) {
				h1 = "0" + h;
			} else {
				h1 = h;
			}
			var m = debut1.getMinutes();
			if (m < 10) {
				m1 = "0" + m;
			} else {
				m1 = m;
			}
			var debut2 = new Date(data[i].fin);
			var h2 = debut2.getHours();
			var h2;
			var m2;
			if (h2 < 10) {
				h2 = "0" + h2;
			} else {
				h2 = h2;
			}
			var m2 = debut2.getMinutes();
			if (m2 < 10) {
				m2 = "0" + m2;
			} else {
				m2 = m2;
			}
			ligne += "<option value='" + data[i].id + "'>" + "De " + h1 + ":" + m1 + " a " + h2 + ":" + m2 + "</option>";
		}
		$("#creneaux").html(ligne);

	}
	document.querySelector('.close-btn').addEventListener("click", function() {
		document.querySelector('#popup-form').classList.remove("active");
	});
	$("#salles").on('change', function() {
		eventss = [];
		var select = document.getElementById('salles');
		var value = select.options[select.selectedIndex].value;
		document.getElementById('salles2').value = value;
		$.ajax({
			url: "GReservationController",
			data: { op: "load3", id: value },
			type: 'POST',
			success: function(data, textStatus, jqXHR) {
				$('#sallef').val(data.code);
				$.ajax({
					url: "GReservationController",
					data: { op: "load4", salle: value },
					type: 'POST',
					success: function(data, textStatus, jqXHR) {
						//alert("load4")

						for (var i = 0; i < data.length; i++) {

							eventss.push({
								id: data[i].id,
								title: data[i].titre,
								start: '' + data[i].date + 'T' + data[i].debut,
								end: '' + data[i].date + 'T' + data[i].fin

							});

						}
						$("calendrier").empty();

						document.getElementById('calendrier').innerHTML = "";
						calendrier = null;
						elementCalendrier = document.getElementById("calendrier")

						calendrier = new FullCalendar.Calendar(elementCalendrier, {
							plugins: ['dayGrid', 'timeGrid', 'list', 'interaction'],
							defaultView: 'dayGridMonth',
							locale: 'fr',
							minTime: "08:00:00",
							maxTime: "18:45:00",
							selectable: true,
							nowIndicator: true,
							header: {
								left: 'prev , next today',
								center: 'title',
								right: 'dayGridMonth , timeGridWeek , list'
							},
							events: eventss,
							dateClick: function(info) {
								var q = new Date();
								var m = q.getMonth() ;
								var d = q.getDay();
								var y = q.getYear();

								var date = new Date(Date.now());
//alert(info.dateStr);
//alert(date);

								mydate = new Date(info.dateStr);
								console.log(date);
								console.log(mydate)

								if (date < mydate ) {
									console.log(info.dateStr);
									$('#date').val(info.dateStr);
									//alert('selected ' + info.dateStr);
									$.ajax({
										url: "GReservationController",
										data: { op: "loadDispoC", date: info.dateStr, salle: value },
										type: 'POST',
										success: function(data, textStatus, jqXHR) {
											//alert("remplirC");
											for (var i = 0; i < data.length; i++) {
												console.log("11");
											}
											remplirC(data);

										},
										error: function(jqXHR, textStatus, errorThrown) {
											console.log(jqXHR + "  " + errorThrown + "  " + textStatus);
										}
									});
									document.querySelector('#popup-form').classList.add("active");
								}else{
									alert("Veuiller choisir une date de futur")
								}
							},
							select: function(info) {

							}
						});


						calendrier.render()
					},
					error: function(jqXHR, textStatus, errorThrown) {
						console.log(jqXHR + "  " + errorThrown + "  " + textStatus);
					}
				});

			},
			error: function(jqXHR, textStatus, errorThrown) {
				console.log(jqXHR + "  " + errorThrown + "  " + textStatus);
			}
		});






	});
});

