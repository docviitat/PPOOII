package com.ppooii.demo.Repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ppooii.demo.Entities.VehiculoDocumento;

@Repository
public interface VehiculoDocumentoRepository extends JpaRepository<VehiculoDocumento, Long> {

    // Fix: Parameter updated to int to match primitive field type
    List<VehiculoDocumento> findByIdDocumento(int idDocumento);

    // Alternative overload if idDocumento uses Long in entity
    List<VehiculoDocumento> findByIdDocumento(Long idDocumento);

    List<VehiculoDocumento> findByEstadoDocumento(String estadoDocumento);

    // Fix: Added missing date range query methods for expiration logic
    List<VehiculoDocumento> findByFechaVencimientoBefore(LocalDate fecha);

    List<VehiculoDocumento> findByFechaVencimientoBetween(LocalDate inicio, LocalDate fin);
}