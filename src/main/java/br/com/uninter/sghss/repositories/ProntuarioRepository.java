package br.com.uninter.sghss.repositories;

import br.com.uninter.sghss.entities.Prontuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProntuarioRepository extends JpaRepository<Prontuario, Long> {
}
