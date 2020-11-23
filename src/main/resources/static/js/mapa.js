/**
 * Autor: Jorge Alexander ortiz
 * Fecha: 2020-11-23
 * Prueba: Crear carro y ubicarlo dentro de un mapa.
 */

var map = new ol.Map({
	target: 'map',
	layers: [
		new ol.layer.Tile({
			source: new ol.source.OSM()
		})
	],
	view: new ol.View({
		center: ol.proj.fromLonLat([-74.297333, 4.570868]),
		zoom: 7
	})
});

