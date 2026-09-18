package com.ppooii.demo.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ppooii.demo.Entities.Vehiculo;

@Repository("IVehiculoRepo")
public interface VehiculoRepository extends JpaRepository<Vehiculo, Long> {
    Optional<Vehiculo> findById(Long id);
    Vehiculo findByPlaca(String placa);
    List<Vehiculo> findByTipoVehiculo(String tipoVehiculo);
}