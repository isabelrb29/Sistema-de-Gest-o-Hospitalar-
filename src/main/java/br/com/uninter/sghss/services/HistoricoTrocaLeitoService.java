package br.com.uninter.sghss.services;

import br.com.uninter.sghss.entities.HistoricoTrocaLeito;
import br.com.uninter.sghss.entities.Internacao;
import br.com.uninter.sghss.entities.Leito;
import br.com.uninter.sghss.repositories.HistoricoTrocaLeitoRepository;
import br.com.uninter.sghss.repositories.InternacaoRepository;
import br.com.uninter.sghss.repositories.LeitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class HistoricoTrocaLeitoService {

    @Autowired
    private HistoricoTrocaLeitoRepository historicoRepository;

    @Autowired
    private InternacaoRepository internacaoRepository;

    @Autowired
    private LeitoRepository leitoRepository;

    public HistoricoTrocaLeito registrarTroca(Long internacaoId, Long novoLeitoId) {
        Internacao internacao = internacaoRepository.findById(internacaoId)
                .orElseThrow(() -> new RuntimeException("Internação não encontrada"));

        Leito novoLeito = leitoRepository.findById(novoLeitoId)
                .orElseThrow(() -> new RuntimeException("Leito não encontrado"));

        // Atualiza o leito atual da internação
        internacao.setLeito(novoLeito);
        internacaoRepository.save(internacao);

        // Registra a troca
        HistoricoTrocaLeito troca = new HistoricoTrocaLeito();
        troca.setInternacao(internacao);
        troca.setLeito(novoLeito);
        troca.setDataTroca(LocalDateTime.now());

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

