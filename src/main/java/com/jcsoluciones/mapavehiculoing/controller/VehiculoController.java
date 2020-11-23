package com.jcsoluciones.mapavehiculoing.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.jcsoluciones.mapavehiculoing.model.Vehiculo;
import com.jcsoluciones.mapavehiculoing.service.VehiculoService;

@RestController
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.PUT,RequestMethod.DELETE})
public class VehiculoController {
	@Autowired
	VehiculoService VehiculoServiceImpl;

	@RequestMapping(value = "/vehiculo/", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<List<Vehiculo>> listAllVehiculos() {
		List<Vehiculo> vehiculos = VehiculoServiceImpl.findAll();
		if (vehiculos.isEmpty()) {
			return new ResponseEntity<List<Vehiculo>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Vehiculo>>(vehiculos, HttpStatus.OK);
	}

	@RequestMapping(value = "/vehiculo/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Vehiculo> getVehiculo(@PathVariable("id") int id) {
		System.out.println("Fetching Vehiculo with id " + id);
		Vehiculo vehiculo = VehiculoServiceImpl.findById(id).get();
		if (vehiculo == null) {
			System.out.println("Vehiculo with id " + id + " not found");
			return new ResponseEntity<Vehiculo>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Vehiculo>(vehiculo, HttpStatus.OK);
	}

	@RequestMapping(value = "/vehiculo/", method = RequestMethod.POST, produces = "application/json")
	public  ResponseEntity<Vehiculo> createVehiculo(Vehiculo vehiculo, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating  ");
		List<Vehiculo> vehiculoOld = VehiculoServiceImpl.findByMatricula(vehiculo.getMatricula());
		if (!vehiculoOld.isEmpty()) {
			System.out.println("A Companie with matricula " + vehiculo.getMatricula() + " already exist");
			return new ResponseEntity<Vehiculo>(HttpStatus.CONFLICT);
		}
		VehiculoServiceImpl.save(vehiculo);
		return new ResponseEntity<Vehiculo>(vehiculo,HttpStatus.CREATED);
	}

	@RequestMapping(value = "/vehiculo/{id}", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Vehiculo> updateVehiculo(@PathVariable("id") int id, Vehiculo vehiculo) {
		System.out.println("Updating Vehiculo " + id);
		Vehiculo currentVehiculo = VehiculoServiceImpl.findById(id).get();
		if (currentVehiculo == null) {
			System.out.println("Vehiculo with id " + id + " not found");
			return new ResponseEntity<Vehiculo>(HttpStatus.NOT_FOUND);
		}
		currentVehiculo.setMatricula(vehiculo.getMatricula());
		currentVehiculo.setPropietario(vehiculo.getPropietario());
		currentVehiculo.setMarca(vehiculo.getMarca());
		currentVehiculo.setLongitud(vehiculo.getLongitud());
		currentVehiculo.setLatitud(vehiculo.getLatitud());
		VehiculoServiceImpl.update(currentVehiculo);
		return new ResponseEntity<Vehiculo>(currentVehiculo, HttpStatus.OK);
	}

	@RequestMapping(value = "/vehiculo/{id}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Vehiculo> deleteVehiculo(@PathVariable("id") int id) {
		System.out.println("Fetching & Deleting Vehiculo with id " + id);
		Vehiculo vehiculo = VehiculoServiceImpl.findById(id).get();
		if (vehiculo == null) {
			System.out.println("Unable to delete. Vehiculo with id " + id + " not found");
			return new ResponseEntity<Vehiculo>(HttpStatus.NOT_FOUND);
		}
		VehiculoServiceImpl.deleteById(id);
		return new ResponseEntity<Vehiculo>(HttpStatus.NO_CONTENT);
	}
}