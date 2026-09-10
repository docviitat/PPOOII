package com.ppooii.demo.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ppooii.demo.Entities.Documento;
import com.ppooii.demo.Entities.Vehiculo;
import com.ppooii.demo.Entities.VehiculoDocumento;
import com.ppooii.demo.Services.Interfaces.IVehiculoProyectoService;
import com.ppooii.demo.dto.VehiculoConDocumentosDTO;

@RestController
@RequestMapping("/v1")
public class ProyectoController {

    @Autowired
    @Qualifier("VehiculoProyectoService")
    private IVehiculoProyectoService service;

    @PostMapping("/documento")
    public boolean agregarDocumento(@RequestBody Documento doc) {
        return service.guardarDocumento(doc);
    }

    @PutMapping("/documento")
    public boolean editarDocumento(@RequestBody Documento doc) {
        return service.actualizarDocumento(doc);
    }

    @DeleteMapping("/documento/{id}")
    public boolean eliminarDocumento(@PathVariable("id") int id) {
        return service.eliminarDocumento(id);
    }

    @GetMapping("/documentos")
    public List<Documento> listarDocumentos() {
        return service.listarDocumentos();
    }

    @PostMapping("/vehiculo")
    public boolean crearVehiculoConDocumentos(@RequestBody VehiculoConDocumentosDTO dto) {
        return service.guardarVehiculoConDocumentos(dto);
    }

    @PutMapping("/vehiculo")
    public boolean actualizarVehiculo(@RequestBody Vehiculo vehiculo) {
        return service.actualizarVehiculo(vehiculo);
    }

    @DeleteMapping("/vehiculo/{id}")
    public boolean eliminarVehiculo(@PathVariable("id") int id) {
        return service.eliminarVehiculo(id);
    }

    @GetMapping("/vehiculos")
    public List<Vehiculo> listarVehiculos() {
        return service.listarVehiculos();
    }

    @PostMapping("/vehiculo/documento")
    public boolean asociarDocumento(@RequestBody VehiculoDocumento vd) {
        return service.asociarDocumento(vd);
    }

    @GetMapping("/vehiculo/placa/{placa}")
    public Vehiculo getPorPlaca(@PathVariable("placa") String placa) {
        return service.buscarPorPlaca(placa);
    }

    @GetMapping("/vehiculo/tipo/{tipo}")
    public List<Vehiculo> getPorTipoVehiculo(@PathVariable("tipo") String tipo) {
        return service.buscarPorTipoVehiculo(tipo);
    }

    @GetMapping("/vehiculo/documento/{idDocumento}")
    public List<Vehiculo> getPorTipoDocumento(@PathVariable("idDocumento") int idDocumento) {
        return service.buscarPorTipoDocumento(idDocumento);
    }

    @GetMapping("/vehiculo/estado/{estado}")
    public List<Vehiculo> getPorEstadoDocumento(@PathVariable("estado") String estado) {
        return service.buscarPorEstadoDocumento(estado);
    }
}



// HOLA 
