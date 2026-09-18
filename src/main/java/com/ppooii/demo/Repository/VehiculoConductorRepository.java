package com.ppooii.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ppooii.demo.Entities.VehiculoConductor;
import java.util.List;

@Repository
public interface VehiculoConductorRepository extends JpaRepository<VehiculoConductor, Long> {
    List<VehiculoConductor> findByEstado(String estado);
    List<VehiculoConductor> findByVehiculoId(Long vehiculoId);
    List<VehiculoConductor> findByConductorId(Long conductorId);
}