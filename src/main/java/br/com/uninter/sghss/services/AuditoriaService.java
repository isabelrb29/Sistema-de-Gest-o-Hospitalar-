package br.com.uninter.sghss.services;

import br.com.uninter.sghss.entities.Auditoria;
import br.com.uninter.sghss.repositories.AuditoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class AuditoriaService {

    @Autowired
    private AuditoriaRepository auditoriaRepository;

    public Auditoria registrar(Auditoria auditoria) {
        return auditoriaRepository.save(auditoria);
    }

    public List<Auditoria> listarTodas() {
        return auditoriaRepository.findAll();
    }

    public Optional<Auditoria> buscarPorId(Long id) {
        return auditoriaRepository.findById(id);
    }

    public List<Auditoria> buscarPorUsuario(Long usuarioId) {
        return auditoriaRepository.findByUsuarioId(usuarioId);
    }

    public List<Auditoria> buscarPorTipoOperacao(String tipoOperacao) {
        return auditoriaRepository.findByTipoOperacaoIgnoreCase(tipoOperacao);
    }

    public List<Auditoria> buscarPorTabela(String tabelaAfetada) {
        return auditoriaRepository.findByTabelaAfetadaIgnoreCase(tabelaAfetada);
    }

    public List<Auditoria> buscarPorPeriodo(LocalDateTime inicio, LocalDateTime fim) {
        return auditoriaRepository.findByDataHoraBetween(inicio, fim);
    }
}

