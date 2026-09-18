package com.ppooii.demo.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ppooii.demo.Entities.Persona;
import com.ppooii.demo.Services.Interfaces.IPersonaService;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {

    @Autowired
    private IPersonaService personaService;

    @PostMapping
    public ResponseEntity<Persona> crearPersona(@RequestBody Persona persona) {
        return ResponseEntity.ok(personaService.guardarPersona(persona));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Persona> obtenerPersona(@PathVariable Long id) {
        return ResponseEntity.ok(personaService.obtenerPorId(id));
    }

    @GetMapping
    public ResponseEntity<List<Persona>> obtenerTodas() {
        return ResponseEntity.ok(personaService.obtenerTodas());
    }

    // Servicio publico: Consultar total de personas agrupadas por tipo
    @GetMapping("/public/conteo-por-tipo")
    public ResponseEntity<List<Object[]>> consultarTotalPorTipo() {
        return ResponseEntity.ok(personaService.obtenerTotalPorTipo());
    }
}