package com.ppooii.demo.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ppooii.demo.Entities.Usuario;
import com.ppooii.demo.Services.Interfaces.IUsuarioService;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private IUsuarioService usuarioService;

    @PutMapping("/{login}/password")
    public ResponseEntity<Usuario> cambiarPassword(
            @PathVariable String login,
            @RequestBody String nuevaPassword) {
        return ResponseEntity.ok(usuarioService.cambiarPassword(login, nuevaPassword));
    }

    @GetMapping("/{login}/apikey")
    public ResponseEntity<String> regenerarApiKey(@PathVariable String login) {
        return ResponseEntity.ok(usuarioService.regenerarApiKey(login));
    }
}