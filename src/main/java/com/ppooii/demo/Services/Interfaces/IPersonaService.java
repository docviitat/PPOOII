package com.ppooii.demo.Services.Interfaces;

import com.ppooii.demo.Entities.Persona;
import java.util.List;

public interface IPersonaService {
    Persona guardarPersona(Persona persona);
    Persona obtenerPorId(Long id);
    List<Persona> obtenerTodas();
    List<Object[]> obtenerTotalPorTipo();
}