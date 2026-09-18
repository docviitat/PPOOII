package com.ppooii.demo.Services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppooii.demo.Entities.Documento;
import com.ppooii.demo.Entities.Persona;
import com.ppooii.demo.Entities.Vehiculo;
import com.ppooii.demo.Entities.VehiculoConductor;
import com.ppooii.demo.Entities.VehiculoDocumento;
import com.ppooii.demo.Repository.DocumentoRepository;
import com.ppooii.demo.Repository.PersonaRepository;
import com.ppooii.demo.Repository.VehiculoConductorRepository;
import com.ppooii.demo.Repository.VehiculoDocumentoRepository;
import com.ppooii.demo.Repository.VehiculoRepository;
import com.ppooii.demo.Services.Interfaces.IVehiculoProyectoService;
import com.ppooii.demo.dto.VehiculoConDocumentosDTO;
import com.ppooii.demo.dto.VehiculoDetalleDTO;

@Service("VehiculoProyectoService")
public class VehiculoProyectoServiceImpl implements IVehiculoProyectoService {

    private static final Logger logger = LogManager.getLogger(VehiculoProyectoServiceImpl.class);

    @Autowired private VehiculoRepository vehiculoRepo;
    @Autowired private DocumentoRepository documentoRepo;
    @Autowired private VehiculoDocumentoRepository vehiculoDocumentoRepo;
    @Autowired private VehiculoConductorRepository vehiculoConductorRepo;
    @Autowired private PersonaRepository personaRepo;

    @Override
    public boolean guardarDocumento(Documento doc) {
        try {
            if (doc.getCodigoDocumento() == null || doc.getCodigoDocumento().trim().isEmpty()) return false;
            documentoRepo.save(doc);
            return true;
        } catch (Exception e) {
            logger.error("Error al guardar documento: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean actualizarDocumento(Documento doc) {
        try {
            if (doc.getId() <= 0) return false;
            documentoRepo.save(doc);
            return true;
        } catch (Exception e) {
            logger.error("Error al actualizar documento: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminarDocumento(int id) {
        try {
            Documento d = documentoRepo.findById((long) id).orElse(null);
            if (d == null) return false;
            documentoRepo.delete(d);
            return true;
        } catch (Exception e) {
            logger.error("Error al eliminar documento: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Documento> listarDocumentos() {
        return documentoRepo.findAll();
    }

    @Override
    @Transactional
    public boolean guardarVehiculoConDocumentos(VehiculoConDocumentosDTO dto) {
        try {
            if (dto.getDocumentos() == null || dto.getDocumentos().isEmpty()) return false;

            Vehiculo vehiculoGuardado = vehiculoRepo.save(dto.getVehiculo());

            for (VehiculoConDocumentosDTO.DocumentoAsociarDTO docDto : dto.getDocumentos()) {
                VehiculoDocumento vd = new VehiculoDocumento();
                vd.setIdVehiculo((long) vehiculoGuardado.getId());
                vd.setIdDocumento((long) docDto.getIdDocumento());
                vd.setFechaExpedicion(docDto.getFechaExpedicion());
                vd.setFechaVencimiento(docDto.getFechaVencimiento());
                vd.setEstadoDocumento("En Verificacion");
                vehiculoDocumentoRepo.save(vd);
            }
            return true;
        } catch (Exception e) {
            logger.error("Error al registrar vehículo con documentos: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean actualizarVehiculo(Vehiculo vehiculo) {
        try {
            if (vehiculo.getId() <= 0) return false;
            vehiculoRepo.save(vehiculo);
            return true;
        } catch (Exception e) {
            logger.error("Error al actualizar vehículo: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean eliminarVehiculo(int id) {
        try {
            Vehiculo v = vehiculoRepo.findById((long) id).orElse(null);
            if (v == null) return false;
            vehiculoRepo.delete(v);
            return true;
        } catch (Exception e) {
            logger.error("Error al eliminar vehículo: " + e.getMessage());
            return false;
        }
    }

    @Override
    public List<Vehiculo> listarVehiculos() {
        return vehiculoRepo.findAll();
    }

    // FIX: now takes a list so several documents can be uploaded/associated in a single call,
    // as required ("pueda realizar el cargue de uno o varios documentos a la vez").
    @Override
    @Transactional
    public boolean asociarDocumentos(List<VehiculoDocumento> documentos) {
        try {
            if (documentos == null || documentos.isEmpty()) return false;
            for (VehiculoDocumento vd : documentos) {
                if (vd.getEstadoDocumento() == null || vd.getEstadoDocumento().trim().isEmpty()) {
                    vd.setEstadoDocumento("En Verificacion");
                }
                vehiculoDocumentoRepo.save(vd);
            }
            return true;
        } catch (Exception e) {
            logger.error("Error al asociar documentos: " + e.getMessage());
            return false;
        }
    }

    // FIX: now returns the vehicle together with its associated drivers and documents.
    @Override
    public VehiculoDetalleDTO buscarPorPlaca(String placa) {
        Vehiculo vehiculo = vehiculoRepo.findByPlaca(placa);
        if (vehiculo == null) return null;

        List<VehiculoConductor> conductores = vehiculoConductorRepo.findByVehiculoId((long) vehiculo.getId());
        List<VehiculoDocumento> documentos = vehiculoDocumentoRepo.findByIdVehiculo((long) vehiculo.getId());

        return new VehiculoDetalleDTO(vehiculo, conductores, documentos);
    }

    @Override
    public List<Vehiculo> buscarPorTipoVehiculo(String tipo) {
        return vehiculoRepo.findByTipoVehiculo(tipo);
    }

    @Override
    public List<Vehiculo> buscarPorTipoDocumento(int idDocumento) {
        List<VehiculoDocumento> relaciones = vehiculoDocumentoRepo.findByIdDocumento((long) idDocumento);
        List<Vehiculo> resultado = new ArrayList<>();
        for (VehiculoDocumento rel : relaciones) {
            Vehiculo v = vehiculoRepo.findById(rel.getIdVehiculo()).orElse(null);
            if (v != null && !resultado.contains(v)) resultado.add(v);
        }
        return resultado;
    }

    @Override
    public List<Vehiculo> buscarPorEstadoDocumento(String estado) {
        List<VehiculoDocumento> relaciones = vehiculoDocumentoRepo.findByEstadoDocumento(estado);
        List<Vehiculo> resultado = new ArrayList<>();
        for (VehiculoDocumento rel : relaciones) {
            Vehiculo v = vehiculoRepo.findById(rel.getIdVehiculo()).orElse(null);
            if (v != null && !resultado.contains(v)) resultado.add(v);
        }
        return resultado;
    }

    @Override
    @Transactional
    public VehiculoConductor asociarConductorAVehiculo(Long vehiculoId, Long personaId, String estado) {
        Persona persona = personaRepo.findById(personaId)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada."));

        if (!"C".equalsIgnoreCase(persona.getTipoPersona())) {
            throw new IllegalArgumentException("Solo personas de tipo CONDUCTOR ('C') pueden asociarse a un vehículo.");
        }

        Vehiculo vehiculo = vehiculoRepo.findById(vehiculoId)
                .orElseThrow(() -> new RuntimeException("Vehículo no encontrado."));

        VehiculoConductor vc = new VehiculoConductor();
        vc.setVehiculo(vehiculo);
        vc.setConductor(persona);
        vc.setFechaAsociacion(LocalDate.now());
        vc.setEstado(estado);

        return vehiculoConductorRepo.save(vc);
    }

    @Override
    @Transactional
    public VehiculoConductor actualizarEstadoConductor(Long vehiculoConductorId, String nuevoEstado) {
        if (!List.of("PO", "EA", "RO").contains(nuevoEstado)) {
            throw new IllegalArgumentException("Estado inválido. Valores permitidos: PO, EA, RO");
        }
        VehiculoConductor vc = vehiculoConductorRepo.findById(vehiculoConductorId)
                .orElseThrow(() -> new RuntimeException("Relación Vehículo-Conductor no encontrada."));
        vc.setEstado(nuevoEstado);
        return vehiculoConductorRepo.save(vc);
    }

    @Override
    public List<VehiculoConductor> buscarPorEstadoConductor(String estado) {
        return vehiculoConductorRepo.findByEstado(estado);
    }

    @Override
    public List<Vehiculo> buscarVehiculosConDocumentosVencidos() {
        List<VehiculoDocumento> vencidos = vehiculoDocumentoRepo.findByFechaVencimientoBefore(LocalDate.now());
        List<Vehiculo> resultado = new ArrayList<>();
        for (VehiculoDocumento vd : vencidos) {
            Vehiculo v = vehiculoRepo.findById(vd.getIdVehiculo()).orElse(null);
            if (v != null && !resultado.contains(v)) resultado.add(v);
        }
        return resultado;
    }

    @Override
    public List<Vehiculo> buscarVehiculosPorVencer(int dias) {
        LocalDate limite = LocalDate.now().plusDays(dias);
        List<VehiculoDocumento> porVencer = vehiculoDocumentoRepo.findByFechaVencimientoBetween(LocalDate.now(), limite);
        List<Vehiculo> resultado = new ArrayList<>();
        for (VehiculoDocumento vd : porVencer) {
            Vehiculo v = vehiculoRepo.findById(vd.getIdVehiculo()).orElse(null);
            if (v != null && !resultado.contains(v)) resultado.add(v);
        }
        return resultado;
    }
}
