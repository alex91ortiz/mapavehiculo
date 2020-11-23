package com.jcsoluciones.mapavehiculoing.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.jcsoluciones.mapavehiculoing.model.Vehiculo;

public interface VehiculoRepository extends CrudRepository<Vehiculo, Integer> {
  @Query("from Vehiculo l  where l.matricula=:matricula")
  public List<Vehiculo> findByMatricula(@Param("matricula") String matricula); 
}