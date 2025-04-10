package br.com.uninter.sghss.repositories;

import br.com.uninter.sghss.entities.Auditoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface AuditoriaRepository extends JpaRepository<Auditoria, Long> {
    List<Auditoria> findByUsuarioId(Long usuarioId);
    List<Auditoria> findByTipoOperacaoIgnoreCase(String tipoOperacao);
    List<Auditoria> findByTabelaAfetadaIgnoreCase(String tabelaAfetada);
    List<Auditoria> findByDataHoraBetween(LocalDateTime inicio, LocalDateTime fim);
}
