package br.com.uninter.sghss.repositories;

import br.com.uninter.sghss.entities.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {
}
