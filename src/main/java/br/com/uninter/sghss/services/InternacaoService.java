package br.com.uninter.sghss.services;

import br.com.uninter.sghss.entities.Internacao;
import br.com.uninter.sghss.repositories.InternacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InternacaoService {

    @Autowired
    private InternacaoRepository internacaoRepository;

    public Internacao salvar(Internacao internacao) {
        return internacaoRepository.save(internacao);
    }

    public List<Internacao> listarTodas() {
        return internacaoRepository.findAll();
    }

    public Optional<Internacao> buscarPorId(Long id) {
        return internacaoRepository.findById(id);
    }

    public Internacao atualizar(Long id, Internacao internacaoAtualizada) {
        Optional<Internacao> internacaoOptional = internacaoRepository.findById(id);

        if (internacaoOptional.isPresent()) {
            Internacao internacao = internacaoOptional.get();
            internacao.setPaciente(internacaoAtualizada.getPaciente());
            internacao.setLeito(internacaoAtualizada.getLeito());
            internacao.setDataEntrada(internacaoAtualizada.getDataEntrada());
            internacao.setDataSaida(internacaoAtualizada.getDataSaida());
            return internacaoRepository.save(internacao);
        } else {
            throw new RuntimeException("Internação não encontrada.");
        }
    }

    public void deletar(Long id) {
        if (internacaoRepository.existsById(id)) {
            internacaoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Internação não encontrada.");
        }
    }
}
