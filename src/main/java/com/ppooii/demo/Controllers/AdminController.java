package com.ppooii.demo.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ppooii.demo.Entities.Documento;
import com.ppooii.demo.Entities.Persona;
import com.ppooii.demo.Entities.Vehiculo;
import com.ppooii.demo.Entities.VehiculoConductor;
import com.ppooii.demo.Entities.VehiculoDocumento;
import com.ppooii.demo.Services.Interfaces.IPersonaService;
import com.ppooii.demo.Services.Interfaces.IVehiculoProyectoService;
import com.ppooii.demo.dto.VehiculoConDocumentosDTO;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private IPersonaService personaService;

    @Autowired
    private IVehiculoProyectoService vehiculoService;

    // --- PERSON ENDPOINTS ---

    @PostMapping("/personas")
    public ResponseEntity<Persona> crearPersona(@RequestBody Persona persona) {
        return ResponseEntity.status(HttpStatus.CREATED).body(personaService.guardarPersona(persona));
    }

    @GetMapping("/personas/{id}")
    public ResponseEntity<Persona> obtenerPersonaPorId(@PathVariable Long id) {
        return ResponseEntity.ok(personaService.obtenerPorId(id));
    }

    @GetMapping("/personas")
    public ResponseEntity<List<Persona>> obtenerTodasLasPersonas() {
        return ResponseEntity.ok(personaService.obtenerTodas());
    }

    // --- VEHICLE & DOCUMENT ENDPOINTS ---

    @PostMapping("/vehiculos")
    public ResponseEntity<?> crearVehiculoConDocumentos(@RequestBody VehiculoConDocumentosDTO dto) {
        boolean guardado = vehiculoService.guardarVehiculoConDocumentos(dto);
        if (!guardado) {
            return ResponseEntity.badRequest().body("Error al registrar el vehiculo o documentos.");
        }
        return ResponseEntity.status(HttpStatus.CREATED).body("Vehiculo registrado exitosamente.");
    }

    @PutMapping("/vehiculos")
    public ResponseEntity<?> actualizarVehiculo(@RequestBody Vehiculo vehiculo) {
        return ResponseEntity.ok(vehiculoService.actualizarVehiculo(vehiculo));
    }

    @DeleteMapping("/vehiculos/{id}")
    public ResponseEntity<?> eliminarVehiculo(@PathVariable int id) {
        return ResponseEntity.ok(vehiculoService.eliminarVehiculo(id));
    }

    @GetMapping("/vehiculos")
    public ResponseEntity<List<Vehiculo>> listarVehiculos() {
        return ResponseEntity.ok(vehiculoService.listarVehiculos());
    }

    @PostMapping("/vehiculos/documentos")
    public ResponseEntity<?> asociarDocumentoPdfBase64(@RequestBody VehiculoDocumento vd) {
        boolean asociado = vehiculoService.asociarDocumento(vd);
        if (!asociado) {
            return ResponseEntity.badRequest().body("Error al asociar el documento.");
        }
        return ResponseEntity.ok("Documento asociado correctamente.");
    }

    // --- DRIVER-VEHICLE RELATIONSHIP ENDPOINTS ---

    @PostMapping("/vehiculos/asociar-conductor")
    public ResponseEntity<?> asociarConductor(
            @RequestParam Long vehiculoId,
            @RequestParam Long conductorId,
            @RequestParam String estado) {
        try {
            VehiculoConductor relacion = vehiculoService.asociarConductorAVehiculo(vehiculoId, conductorId, estado);
            return ResponseEntity.status(HttpStatus.CREATED).body(relacion);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/vehiculos/estado-conductor")
    public ResponseEntity<?> cambiarEstadoConductor(
            @RequestParam Long vehiculoConductorId,
            @RequestParam String nuevoEstado) {
        try {
            VehiculoConductor actualizado = vehiculoService.actualizarEstadoConductor(vehiculoConductorId, nuevoEstado);
            return ResponseEntity.ok(actualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // --- BASE DOCUMENT CRUD ---

    @PostMapping("/documentos")
    public ResponseEntity<?> agregarDocumento(@RequestBody Documento doc) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vehiculoService.guardarDocumento(doc));
    }

    @GetMapping("/documentos")
    public ResponseEntity<List<Documento>> listarDocumentos() {
        return ResponseEntity.ok(vehiculoService.listarDocumentos());
    }
}