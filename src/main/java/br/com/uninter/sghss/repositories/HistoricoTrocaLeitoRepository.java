package br.com.uninter.sghss.repositories;

import br.com.uninter.sghss.entities.HistoricoTrocaLeito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoricoTrocaLeitoRepository extends JpaRepository<HistoricoTrocaLeito, Long> {
    List<HistoricoTrocaLeito> findByInternacaoId(Long internacaoId);
}
