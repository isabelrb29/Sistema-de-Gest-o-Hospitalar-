package br.com.uninter.sghss.services;

import br.com.uninter.sghss.entities.Suprimento;
import br.com.uninter.sghss.repositories.SuprimentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SuprimentoService {

    @Autowired
    private SuprimentoRepository suprimentoRepository;

    public Suprimento salvar(Suprimento suprimento) {
        return suprimentoRepository.save(suprimento);
    }

    public List<Suprimento> listarTodos() {
        return suprimentoRepository.findAll();
    }

    public Optional<Suprimento> buscarPorId(Long id) {
        return suprimentoRepository.findById(id);
    }

    public Suprimento atualizar(Long id, Suprimento suprimentoAtualizado) {
        Optional<Suprimento> suprimentoOptional = suprimentoRepository.findById(id);

        if (suprimentoOptional.isPresent()) {
            Suprimento suprimento = suprimentoOptional.get();
            suprimento.setNome(suprimentoAtualizado.getNome());
            suprimento.setQuantidade(suprimentoAtualizado.getQuantidade());
            suprimento.setUnidadeMedida(suprimentoAtualizado.getUnidadeMedida());
            return suprimentoRepository.save(suprimento);
        } else {
            throw new RuntimeException("Suprimento não encontrado.");
        }
    }

    public void deletar(Long id) {
        if (suprimentoRepository.existsById(id)) {
            suprimentoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Suprimento não encontrado.");
        }
    }
}

