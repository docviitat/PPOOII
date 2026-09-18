package com.ppooii.demo.Config;

import java.util.Date;
import java.util.List;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ppooii.demo.Entities.Usuario;
import com.ppooii.demo.Repository.UsuarioRepository;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import static com.ppooii.demo.Config.Model.JwtConstants.*;

@Service
public class JWTAuthenticationConfig {

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Validates login+password against the usuario table and, if correct,
     * returns a signed "Bearer <token>" string. Returns null on bad credentials
     * so the controller can respond 401 instead of leaking which part failed.
     */
    public String authenticateAndGetToken(String login, String password) {
        Usuario usuario = usuarioRepository.findByIdLogin(login).orElse(null);

        if (usuario == null || !usuario.getPassword().equals(password)) {
            return null;
        }

        // Determine role from the linked Persona's tipo_persona (A = admin, C = conductor)
        String tipoPersona = usuario.getPersona() != null ? usuario.getPersona().getTipoPersona() : "C";
        String role = "A".equalsIgnoreCase(tipoPersona) ? "ROLE_ADMIN" : "ROLE_CONDUCTOR";

        String token = Jwts.builder()
                .setId("PPOOII_JWT")
                .setSubject(login)
                .claim("authorities", List.of(role))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_EXPIRATION_TIME))
                .signWith(getSigningKey(SUPER_SECRET_KEY))
                .compact();

        return TOKEN_BEARER_PREFIX + token;
    }

    private SecretKey getSigningKey(String secret) {
        return Keys.hmacShaKeyFor(secret.getBytes());
    }
}
