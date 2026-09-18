package com.ppooii.demo.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ppooii.demo.Entities.Vehiculo;
import com.ppooii.demo.Services.Interfaces.IPersonaService;
import com.ppooii.demo.Services.Interfaces.IVehiculoProyectoService;

@RestController
@RequestMapping("/api/public")
public class PublicController {

    @Autowired
    private IPersonaService personaService;

    @Autowired
    private IVehiculoProyectoService vehiculoService;

    // 1. Consult drivers with 'PO' (Can Operate) status
    @GetMapping("/conductores/operar")
    public ResponseEntity<?> consultarConductoresQuePuedenOperar() {
        return ResponseEntity.ok(vehiculoService.buscarPorEstadoConductor("PO"));
    }

    // 2. Consult vehicle by plate including related drivers and documents
    @GetMapping("/vehiculos/placa/{placa}")
    public ResponseEntity<Vehiculo> consultarVehiculoPorPlaca(@PathVariable String placa) {
        Vehiculo vehiculo = vehiculoService.buscarPorPlaca(placa);
        if (vehiculo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(vehiculo);
    }

    // 3. Consult vehicles with expired documents
    @GetMapping("/vehiculos/documentos-vencidos")
    public ResponseEntity<?> consultarVehiculosConDocumentosVencidos() {
        return ResponseEntity.ok(vehiculoService.buscarVehiculosConDocumentosVencidos());
    }

    // 4. Consult vehicles with documents expiring within N days
    @GetMapping("/vehiculos/por-vencer")
    public ResponseEntity<?> consultarVehiculosPorVencer(@RequestParam(name = "dias", defaultValue = "30") int dias) {
        return ResponseEntity.ok(vehiculoService.buscarVehiculosPorVencer(dias));
    }

    // 5. Consult total count of people grouped by type
    @GetMapping("/personas/total-por-tipo")
    public ResponseEntity<List<Object[]>> consultarTotalPersonasPorTipo() {
        return ResponseEntity.ok(personaService.obtenerTotalPorTipo());
    }
}