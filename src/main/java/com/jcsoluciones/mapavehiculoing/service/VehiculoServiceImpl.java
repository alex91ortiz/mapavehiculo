package com.jcsoluciones.mapavehiculoing.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jcsoluciones.mapavehiculoing.model.Vehiculo;
import com.jcsoluciones.mapavehiculoing.repository.VehiculoRepository;


@Transactional
@Service("vehiculoService")
public class VehiculoServiceImpl implements VehiculoService {
	@Autowired
	VehiculoRepository vehiculoRepository;

	@Override
	public Optional<Vehiculo> findById(int id) {
		Optional<Vehiculo> vehiculo = vehiculoRepository.findById(id);
		return vehiculo;
	}

	@Override
	public List<Vehiculo> findByMatricula(String matricula) {
		List<Vehiculo> vehiculo = vehiculoRepository.findByMatricula(matricula);

         return vehiculo;
	}

	@Override
	public Vehiculo save(Vehiculo vehiculo) {
		if (vehiculo != null) {
			return vehiculoRepository.save(vehiculo);
		}
		return new Vehiculo();
	}

	@Override
	public boolean update(Vehiculo vehiculo) {
		vehiculoRepository.save(vehiculo);
		return true;
	}

	@Override
	public boolean deleteById(int id) {
		vehiculoRepository.deleteById(id);
		return true;
	}

	@Override
	public List<Vehiculo> findAll() {
		return (List<Vehiculo>) vehiculoRepository.findAll();
	}
}