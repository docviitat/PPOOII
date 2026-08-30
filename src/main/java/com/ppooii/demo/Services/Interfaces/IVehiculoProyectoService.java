package com.ppooii.demo.Services.Interfaces;

import java.util.List;

import com.ppooii.demo.Entities.Documento;
import com.ppooii.demo.Entities.Vehiculo;
import com.ppooii.demo.Entities.VehiculoDocumento;
import com.ppooii.demo.dto.VehiculoConDocumentosDTO;

public interface IVehiculoProyectoService {

    boolean guardarDocumento(Documento doc);
    boolean actualizarDocumento(Documento doc);
    boolean eliminarDocumento(int id);
    List<Documento> listarDocumentos();

    boolean guardarVehiculoConDocumentos(VehiculoConDocumentosDTO dto);
    boolean actualizarVehiculo(Vehiculo vehiculo);
    boolean eliminarVehiculo(int id);
    List<Vehiculo> listarVehiculos();

    boolean asociarDocumento(VehiculoDocumento vd);

    Vehiculo buscarPorPlaca(String placa);
    List<Vehiculo> buscarPorTipoVehiculo(String tipo);
    List<Vehiculo> buscarPorTipoDocumento(int idDocumento);
    List<Vehiculo> buscarPorEstadoDocumento(String estado);
}