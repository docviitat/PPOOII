package com.ppooii.demo.Repository;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ppooii.demo.Entities.VehiculoDocumento;

@Repository("IVehiculoDocumentoRepo")
public interface VehiculoDocumentoRepository extends JpaRepository<VehiculoDocumento, Serializable> {
    
    List<VehiculoDocumento> findByIdVehiculo(int idVehiculo);
    
    List<VehiculoDocumento> findByIdDocumento(int idDocumento);
    
    List<VehiculoDocumento> findByEstadoDocumento(String estadoDocumento);
}