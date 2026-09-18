package com.ppooii.demo.Services;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ppooii.demo.Entities.Usuario;
import com.ppooii.demo.Repository.UsuarioRepository;
import com.ppooii.demo.Services.Interfaces.IUsuarioService;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public Usuario cambiarPassword(String login, String nuevaPassword) {
        Usuario usuario = usuarioRepository.findByIdLogin(login)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con login: " + login));
        
        usuario.setPassword(nuevaPassword);
        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public String regenerarApiKey(String login) {
        Usuario usuario = usuarioRepository.findByIdLogin(login)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con login: " + login));

        String nuevaApiKey = UUID.randomUUID().toString();
        usuario.setApikey(nuevaApiKey);
        usuarioRepository.save(usuario);

        return nuevaApiKey;
    }
}