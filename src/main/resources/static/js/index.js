/**
 * Autor: Jorge Alexander ortiz
 * Fecha: 2020-11-23
 * Prueba: Crear carro y ubicarlo dentro de un mapa.
 */
var flageditar = false;
var currentId = 0;
var vehicles = [];
$(document).ready(function () {


	listarVehiculos();

	$("#agregar").click(function () {
		var matricula = $("#matricula").val();
		var marca = $("#marca").val();
		var propietario = $("#propietario").val();
		var lon = $("#longitud").val();
		var lat = $("#latitud").val();

		if (matricula != "" && marca != "" && propietario != "" && lon != "" && lat != "") {
			let longitud = parseFloat(lon);
			let latitud = parseFloat(lat);
			if (!flageditar) {
				$.ajax({
					url: "/vehiculo/",
					method: "POST",
					data: { matricula, marca, propietario, longitud: longitud, latitud: latitud },
					dataType: "json",
					success: function (res) {

						limpiar();
						alert("Se registro correctamente");
						listarVehiculos();
						console.log(res);

					}
				});
			} else {
				$.ajax({
					url: "/vehiculo/" + currentId,
					method: "PUT",
					data: { matricula, marca, propietario, longitud: longitud, latitud: latitud },
					dataType: "json",
					success: function (res) {

						limpiar();
						alert("Se registro correctamente");
						listarVehiculos();
						console.log(res);

					}
				});
			}
		} else {
			alert("Debe completar todos los campos");
		}

	});

	$("#limpiar").click(function () {
		limpiar();
	});


});

function listarVehiculos() {
	$.ajax({
		url: "/vehiculo/",
		method: "GET",
		dataType: "json",
		success: function (res) {
			console.log(res);
			var features = [];
			$("#tablaview").html('');
			for (var i in res) {
				let buttonEdit = "<button id='editar' onclick='editar(" + res[i]["idVehiculo"] + ")' type='button' >Editar</button>";
				$("#tablaview").append($(`<tr><td>${res[i]["idVehiculo"]}</td>
											  <td>${res[i]["matricula"]}</td>
									          <td>${res[i]["marca"]}</td>
											  <td>${res[i]["propietario"]}</td>
											  <td>${res[i]["longitud"]}</td>
											  <td>${res[i]["latitud"]}</td>` +
					'<td>' + buttonEdit + '<button id="eliminar" type="button" >Eliminar</button></td></tr>'
				));

				var item = res[i];
				var longitude = item.longitud;
				var latitude = item.latitud;
	
				var iconPath = "https://mapavehiculoing.herokuapp.com/img/vehicle.png";

				
				var iconFeature = new ol.Feature({
					geometry: new ol.geom.Point(ol.proj.transform([longitude, latitude], 'EPSG:4326',
						'EPSG:3857'))
				});

				
				var iconStyle = new ol.style.Style({
					image: new ol.style.Icon(/** @type {olx.style.IconOptions} */({
						anchor: [0.5, 46],
						anchorXUnits: 'fraction',
						anchorYUnits: 'pixels',
						opacity: 0.75,
						src: iconPath
					}))
				});

				iconFeature.setStyle(iconStyle);
				features.push(iconFeature);
			


			}

	
			var vectorSource = new ol.source.Vector({
				features: features
			});

			var vectorLayer = new ol.layer.Vector({
				source: vectorSource
			});
			map.addLayer(vectorLayer);
		}
	});
}

function editar(idVehiculo) {
	$.ajax({
		url: "/vehiculo/" + idVehiculo,
		method: "GET",
		dataType: "json",
		success: function (res) {
			$("#matricula").val(res.matricula);
			$("#marca").val(res.marca);
			$("#propietario").val(res.propietario);
			$("#longitud").val(res.longitud);
			$("#latitud").val(res.latitud);
			flageditar = true;
			currentId = idVehiculo;
		}
	});

}

function limpiar() {
	flageditar = false;
	$("#matricula").val('');
	$("#marca").val('');
	$("#propietario").val('');
	$("#longitud").val('');
	$("#latitud").val('');
}