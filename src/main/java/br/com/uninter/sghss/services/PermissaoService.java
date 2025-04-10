package br.com.uninter.sghss.services;

import br.com.uninter.sghss.entities.Permissao;
import br.com.uninter.sghss.repositories.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PermissaoService {

    @Autowired
    private PermissaoRepository permissaoRepository;

    public Permissao salvar(Permissao permissao) {
        return permissaoRepository.save(permissao);
    }

    public List<Permissao> listarTodas() {
        return permissaoRepository.findAll();
    }

    public Optional<Permissao> buscarPorId(Long id) {
        return permissaoRepository.findById(id);
    }

    public Permissao atualizar(Long id, Permissao permissaoAtualizada) {
        Optional<Permissao> permissaoOptional = permissaoRepository.findById(id);

        if (permissaoOptional.isPresent()) {
            Permissao permissao = permissaoOptional.get();
            permissao.setNome(permissaoAtualizada.getNome());
            permissao.setDescricao(permissaoAtualizada.getDescricao());
            return permissaoRepository.save(permissao);
        } else {
            throw new RuntimeException("Permissão não encontrada.");
        }
    }

    public void deletar(Long id) {
        if (permissaoRepository.existsById(id)) {
            permissaoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Permissão não encontrada.");
        }
    }
}
