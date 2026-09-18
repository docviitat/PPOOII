package com.ppooii.demo.Repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ppooii.demo.Entities.VehiculoDocumento;

@Repository
public interface VehiculoDocumentoRepository extends JpaRepository<VehiculoDocumento, Long> {

    List<VehiculoDocumento> findByIdDocumento(Long idDocumento);

    // FIX: was missing - needed to look up all documents for a given vehicle (used by the
    // "consultar por placa" public service to embed related documents).
    List<VehiculoDocumento> findByIdVehiculo(Long idVehiculo);

    List<VehiculoDocumento> findByEstadoDocumento(String estadoDocumento);

    List<VehiculoDocumento> findByFechaVencimientoBefore(LocalDate fecha);

    List<VehiculoDocumento> findByFechaVencimientoBetween(LocalDate inicio, LocalDate fin);
}
