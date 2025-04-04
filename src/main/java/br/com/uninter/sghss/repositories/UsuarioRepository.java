package br.com.uninter.sghss.repositories;

import br.com.uninter.sghss.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
}
