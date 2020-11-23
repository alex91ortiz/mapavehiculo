package com.jcsoluciones.mapavehiculoing.service;

import java.util.List;
import java.util.Optional;

import com.jcsoluciones.mapavehiculoing.model.Vehiculo;

public interface VehiculoService {
	Optional<Vehiculo> findById(int id);

	List<Vehiculo> findByMatricula(String matricula);

	Vehiculo save(Vehiculo Vehiculo);

	boolean update(Vehiculo Vehiculo);

	boolean deleteById(int id);

	List<Vehiculo> findAll();
}