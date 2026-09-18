package com.ppooii.demo.Services;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppooii.demo.Entities.Persona;
import com.ppooii.demo.Entities.Usuario;
import com.ppooii.demo.Entities.UsuarioPK;
import com.ppooii.demo.Repository.PersonaRepository;
import com.ppooii.demo.Repository.UsuarioRepository;
import com.ppooii.demo.Services.Interfaces.IPersonaService;

@Service
public class PersonaServiceImpl implements IPersonaService {

    @Autowired
    private PersonaRepository personaRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public Persona guardarPersona(Persona persona) {
        Persona nuevaPersona = personaRepository.save(persona);

        if ("A".equalsIgnoreCase(nuevaPersona.getTipoPersona())) {
            String primerNombre = nuevaPersona.getNombres().trim().substring(0, 1).toLowerCase();
            String primerApellido = nuevaPersona.getApellidos().trim().substring(0, 1).toLowerCase();
            String loginMnemotecnico = primerNombre + primerApellido + nuevaPersona.getIdentificacion();

            String autoPassword = UUID.randomUUID().toString().substring(0, 8);
            String autoApiKey = UUID.randomUUID().toString();

            UsuarioPK pk = new UsuarioPK();
            pk.setLogin(loginMnemotecnico);
            pk.setIdpersona(nuevaPersona.getId());

            Usuario usuario = new Usuario();
            usuario.setId(pk);
            usuario.setPersona(nuevaPersona);
            usuario.setPassword(autoPassword);
            usuario.setApikey(autoApiKey);

            usuarioRepository.save(usuario);
        }

        return nuevaPersona;
    }

    @Override
    public Persona obtenerPorId(Long id) {
        return personaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Persona no encontrada con ID: " + id));
    }

    @Override
    public List<Persona> obtenerTodas() {
        return personaRepository.findAll();
    }

    @Override
    public List<Object[]> obtenerTotalPorTipo() {
        return personaRepository.contarPersonasPorTipo();
    }
}