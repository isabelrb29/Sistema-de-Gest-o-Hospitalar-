package br.com.uninter.sghss.services;

import br.com.uninter.sghss.entities.HistoricoTrocaLeito;
import br.com.uninter.sghss.repositories.HistoricoTrocaLeitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HistoricoTrocaLeitoService {

    @Autowired
    private HistoricoTrocaLeitoRepository historicoRepository;

    public HistoricoTrocaLeito registrarTroca(HistoricoTrocaLeito troca) {
        return historicoRepository.save(troca);
    }

    public List<HistoricoTrocaLeito> listarTodos() {
        return historicoRepository.findAll();
    }

    public Optional<HistoricoTrocaLeito> buscarPorId(Long id) {
        return historicoRepository.findById(id);
    }

    public List<HistoricoTrocaLeito> buscarPorInternacao(Long internacaoId) {
        return historicoRepository.findByInternacaoId(internacaoId);
    }
}

