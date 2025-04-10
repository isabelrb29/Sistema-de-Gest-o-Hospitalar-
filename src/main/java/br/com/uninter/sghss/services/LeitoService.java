package br.com.uninter.sghss.services;

import br.com.uninter.sghss.entities.Leito;
import br.com.uninter.sghss.repositories.LeitoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LeitoService {

    @Autowired
    private LeitoRepository leitoRepository;

    public Leito salvar(Leito leito) {
        return leitoRepository.save(leito);
    }

    public List<Leito> listarTodos() {
        return leitoRepository.findAll();
    }

    public Optional<Leito> buscarPorId(Long id) {
        return leitoRepository.findById(id);
    }

    public Leito atualizar(Long id, Leito leitoAtualizado) {
        Optional<Leito> leitoOptional = leitoRepository.findById(id);

        if (leitoOptional.isPresent()) {
            Leito leito = leitoOptional.get();
            leito.setNumeroLeito(leitoAtualizado.getNumeroLeito());
            leito.setTipoLeito(leitoAtualizado.getTipoLeito());
            leito.setDisponibilidade(leitoAtualizado.getDisponibilidade());
            return leitoRepository.save(leito);
        } else {
            throw new RuntimeException("Leito não encontrado.");
        }
    }

    public void deletar(Long id) {
        if (leitoRepository.existsById(id)) {
            leitoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Leito não encontrado.");
        }
    }
}
