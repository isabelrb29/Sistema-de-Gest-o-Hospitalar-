package br.com.uninter.sghss.repositories;

import br.com.uninter.sghss.entities.Prescricao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PrescricaoRepository extends JpaRepository<Prescricao, Long> {
}
