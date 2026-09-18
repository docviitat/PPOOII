package com.ppooii.demo.dto;

import java.util.List;

import com.ppooii.demo.Entities.Vehiculo;
import com.ppooii.demo.Entities.VehiculoConductor;
import com.ppooii.demo.Entities.VehiculoDocumento;

// FIX: buscarPorPlaca used to return a bare Vehiculo with no related data, even though the
// requirement explicitly asks for the associated drivers and documents to be included.
public class VehiculoDetalleDTO {

    private Vehiculo vehiculo;
    private List<VehiculoConductor> conductores;
    private List<VehiculoDocumento> documentos;

    public VehiculoDetalleDTO(Vehiculo vehiculo, List<VehiculoConductor> conductores, List<VehiculoDocumento> documentos) {
        this.vehiculo = vehiculo;
        this.conductores = conductores;
        this.documentos = documentos;
    }

    public Vehiculo getVehiculo() { return vehiculo; }
    public void setVehiculo(Vehiculo vehiculo) { this.vehiculo = vehiculo; }

    public List<VehiculoConductor> getConductores() { return conductores; }
    public void setConductores(List<VehiculoConductor> conductores) { this.conductores = conductores; }

    public List<VehiculoDocumento> getDocumentos() { return documentos; }
    public void setDocumentos(List<VehiculoDocumento> documentos) { this.documentos = documentos; }
}
