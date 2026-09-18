package com.ppooii.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.ppooii.demo.Entities.Usuario;
import com.ppooii.demo.Entities.UsuarioPK;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, UsuarioPK> {
    Optional<Usuario> findByIdLogin(String login);
    Optional<Usuario> findByApikey(String apikey);
}