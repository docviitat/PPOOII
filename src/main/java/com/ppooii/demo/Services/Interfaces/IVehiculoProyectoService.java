package com.ppooii.demo.Services.Interfaces;

import java.util.List;

import com.ppooii.demo.Entities.Documento;
import com.ppooii.demo.Entities.Vehiculo;
import com.ppooii.demo.Entities.VehiculoConductor;
import com.ppooii.demo.Entities.VehiculoDocumento;
import com.ppooii.demo.dto.VehiculoConDocumentosDTO;
import com.ppooii.demo.dto.VehiculoDetalleDTO;

public interface IVehiculoProyectoService {

    boolean guardarDocumento(Documento doc);
    boolean actualizarDocumento(Documento doc);
    boolean eliminarDocumento(int id);
    List<Documento> listarDocumentos();

    boolean guardarVehiculoConDocumentos(VehiculoConDocumentosDTO dto);
    boolean actualizarVehiculo(Vehiculo vehiculo);
    boolean eliminarVehiculo(int id);
    List<Vehiculo> listarVehiculos();

    // FIX: now accepts a list so "uno o varios documentos a la vez" is actually possible here too,
    // not just in the initial vehicle-creation flow.
    boolean asociarDocumentos(List<VehiculoDocumento> documentos);

    // FIX: returns vehicle + drivers + documents instead of a bare Vehiculo.
    VehiculoDetalleDTO buscarPorPlaca(String placa);

    List<Vehiculo> buscarPorTipoVehiculo(String tipo);
    List<Vehiculo> buscarPorTipoDocumento(int idDocumento);
    List<Vehiculo> buscarPorEstadoDocumento(String estado);

    VehiculoConductor asociarConductorAVehiculo(Long vehiculoId, Long personaId, String estado);
    VehiculoConductor actualizarEstadoConductor(Long vehiculoConductorId, String nuevoEstado);
    List<VehiculoConductor> buscarPorEstadoConductor(String estado);
    List<Vehiculo> buscarVehiculosConDocumentosVencidos();
    List<Vehiculo> buscarVehiculosPorVencer(int dias);
}
