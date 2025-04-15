package br.com.uninter.sghss.services;

import br.com.uninter.sghss.entities.Perfil;
import br.com.uninter.sghss.entities.Permissao;
import br.com.uninter.sghss.repositories.PerfilRepository;
import br.com.uninter.sghss.repositories.PermissaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerfilService {

    @Autowired
    private PerfilRepository perfilRepository;

    @Autowired
    private PermissaoRepository permissaoRepository;

    public Perfil salvar(Perfil perfil) {
        return perfilRepository.save(perfil);
    }

    public List<Perfil> listarTodos() {
        return perfilRepository.findAll();
    }

    public Optional<Perfil> buscarPorId(Long id) {
        return perfilRepository.findById(id);
    }

    public Perfil atualizar(Long id, Perfil perfilAtualizado) {
        Optional<Perfil> perfilOptional = perfilRepository.findById(id);

        if (perfilOptional.isPresent()) {
            Perfil perfil = perfilOptional.get();
            perfil.setDescricao(perfilAtualizado.getDescricao());
            return perfilRepository.save(perfil);
        } else {
            throw new RuntimeException("Perfil não encontrado.");
        }
    }

    public void deletar(Long id) {
        if (perfilRepository.existsById(id)) {
            perfilRepository.deleteById(id);
        } else {
            throw new RuntimeException("Perfil não encontrado.");
        }
    }

    public Perfil adicionarPermissoes(Long idPerfil, List<Long> idsPermissoes) {
        Perfil perfil = perfilRepository.findById(idPerfil)
                .orElseThrow(() -> new RuntimeException("Perfil não encontrado"));

        List<Permissao> permissoes = permissaoRepository.findAllById(idsPermissoes);
        perfil.setPermissoes(permissoes);

        return perfilRepository.save(perfil);
    }

}
