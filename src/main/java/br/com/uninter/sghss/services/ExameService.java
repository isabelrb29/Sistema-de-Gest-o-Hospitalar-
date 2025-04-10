package br.com.uninter.sghss.services;

import br.com.uninter.sghss.entities.Exame;
import br.com.uninter.sghss.repositories.ExameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExameService {

    @Autowired
    private ExameRepository exameRepository;

    public Exame salvar(Exame exame) {
        return exameRepository.save(exame);
    }

    public List<Exame> listarTodos() {
        return exameRepository.findAll();
    }

    public Optional<Exame> buscarPorId(Long id) {
        return exameRepository.findById(id);
    }

    public Exame atualizar(Long id, Exame exameAtualizado) {
        Optional<Exame> exameOptional = exameRepository.findById(id);

        if (exameOptional.isPresent()) {
            Exame exame = exameOptional.get();
            exame.setCodigo(exameAtualizado.getCodigo());
            exame.setResultado(exameAtualizado.getResultado());
            return exameRepository.save(exame);
        } else {
            throw new RuntimeException("Exame não encontrado.");
        }
    }

    public void deletar(Long id) {
        if (exameRepository.existsById(id)) {
            exameRepository.deleteById(id);
        } else {
            throw new RuntimeException("Exame não encontrado.");
        }
    }
}
