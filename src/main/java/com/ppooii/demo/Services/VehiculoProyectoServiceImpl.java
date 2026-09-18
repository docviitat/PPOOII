package com.ppooii.demo.Services;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppooii.demo.Entities.Documento;
import com.ppooii.demo.Entities.Vehiculo;
import com.ppooii.demo.Entities.VehiculoDocumento;
import com.ppooii.demo.Repository.DocumentoRepository;
import com.ppooii.demo.Repository.VehiculoDocumentoRepository;
import com.ppooii.demo.Repository.VehiculoRepository;
import com.ppooii.demo.Services.Interfaces.IVehiculoProyectoService;
import com.ppooii.demo.dto.VehiculoConDocumentosDTO;

@Service("VehiculoProyectoService")
public class VehiculoProyectoServiceImpl implements IVehiculoProyectoService {

    private static final Logger logger = LogManager.getLogger(VehiculoProyectoServiceImpl.class);

    @Autowired
    @Qualifier("IVehiculoRepo")
    private VehiculoRepository vehiculoRepo;

    @Autowired
    @Qualifier("IDocumentoRepo")
    private DocumentoRepository documentoRepo;

    @Autowired
    @Qualifier("IVehiculoDocumentoRepo")
    private VehiculoDocumentoRepository vehiculoDocumentoRepo;

    @Override
    public boolean guardarDocumento(Documento doc) {
        try {
            if (doc.getCodigoDocumento() == null || doc.getCodigoDocumento().trim().isEmpty()) {
                logger.error("El código del documento es obligatorio.");
                return false;
            }
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
            if (dto.getDocumentos() == null || dto.getDocumentos().isEmpty()) {
                logger.error("No se puede crear un vehículo sin al menos un documento asociado.");
                return false;
            }

            Vehiculo vehiculoGuardado = vehiculoRepo.save(dto.getVehiculo());

            for (VehiculoConDocumentosDTO.DocumentoAsociarDTO docDto : dto.getDocumentos()) {
                VehiculoDocumento vd = new VehiculoDocumento();
                vd.setIdVehiculo(vehiculoGuardado.getId());
                vd.setIdDocumento(docDto.getIdDocumento());
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

    @Override
    public boolean asociarDocumento(VehiculoDocumento vd) {
        try {
            if (vd.getEstadoDocumento() == null || vd.getEstadoDocumento().trim().isEmpty()) {
                vd.setEstadoDocumento("En Verificacion");
            }
            vehiculoDocumentoRepo.save(vd);
            return true;
        } catch (Exception e) {
            logger.error("Error al asociar documento: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Vehiculo buscarPorPlaca(String placa) {
        return vehiculoRepo.findByPlaca(placa);
    }

    @Override
    public List<Vehiculo> buscarPorTipoVehiculo(String tipo) {
        return vehiculoRepo.findByTipoVehiculo(tipo);
    }

    @Override
    public List<Vehiculo> buscarPorTipoDocumento(int idDocumento) {
        List<VehiculoDocumento> relaciones = vehiculoDocumentoRepo.findByIdDocumento(idDocumento);
        List<Vehiculo> resultado = new ArrayList<>();
        for (VehiculoDocumento rel : relaciones) {
            Vehiculo v = vehiculoRepo.findById((long) rel.getIdVehiculo()).orElse(null);
            if (v != null && !resultado.contains(v)) {
                resultado.add(v);
            }
        }
        return resultado;
    }

    @Override
    public List<Vehiculo> buscarPorEstadoDocumento(String estado) {
        List<VehiculoDocumento> relaciones = vehiculoDocumentoRepo.findByEstadoDocumento(estado);
        List<Vehiculo> resultado = new ArrayList<>();
        for (VehiculoDocumento rel : relaciones) {
            Vehiculo v = vehiculoRepo.findById((long) rel.getIdVehiculo()).orElse(null);
            if (v != null && !resultado.contains(v)) {
                resultado.add(v);
            }
        }
        return resultado;
    }
}