package com.ppooii.demo.Services.Interfaces;

import com.ppooii.demo.Entities.Persona;
import java.util.List;

public interface IPersonaService {
    Persona guardarPersona(Persona persona);
    Persona actualizarPersona(Long id, Persona persona); // FIX: was missing (requirement asks for POST-GET-PUT)
    Persona obtenerPorId(Long id);
    List<Persona> obtenerTodas();
    List<Object[]> obtenerTotalPorTipo();
}
