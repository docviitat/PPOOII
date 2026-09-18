package com.ppooii.demo.Services.Interfaces;

import com.ppooii.demo.Entities.Usuario;

public interface IUsuarioService {
    Usuario cambiarPassword(String login, String nuevaPassword);
    String regenerarApiKey(String login);
}