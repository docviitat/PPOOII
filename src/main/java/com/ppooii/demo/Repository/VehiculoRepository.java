package com.ppooii.demo.Repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ppooii.demo.Entities.Vehiculo;

@Repository("IVehiculoRepo")
public interface VehiculoRepository extends JpaRepository<Vehiculo, Serializable> {
    Vehiculo findById(int id);
    Vehiculo findByPlaca(String placa);
    List<Vehiculo> findByTipoVehiculo(String tipoVehiculo);
}