package br.com.uninter.sghss.services;

import br.com.uninter.sghss.entities.Prontuario;
import br.com.uninter.sghss.repositories.ProntuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProntuarioService {

    @Autowired
    private ProntuarioRepository prontuarioRepository;

    public Prontuario salvar(Prontuario prontuario) {
        return prontuarioRepository.save(prontuario);
    }

    public List<Prontuario> listarTodos() {
        return prontuarioRepository.findAll();
    }

    public Optional<Prontuario> buscarPorId(Long id) {
        return prontuarioRepository.findById(id);
    }

    public Prontuario atualizar(Long id, Prontuario prontuarioAtualizado) {
        Optional<Prontuario> prontuarioOptional = prontuarioRepository.findById(id);

        if (prontuarioOptional.isPresent()) {
            Prontuario prontuario = prontuarioOptional.get();
            prontuario.setDescricao(prontuarioAtualizado.getDescricao());
            prontuario.setDataHora(prontuarioAtualizado.getDataHora());
            prontuario.setPaciente(prontuarioAtualizado.getPaciente());
            return prontuarioRepository.save(prontuario);
        } else {
            throw new RuntimeException("Prontuário não encontrado.");
        }
    }

    public void deletar(Long id) {
        if (prontuarioRepository.existsById(id)) {
            prontuarioRepository.deleteById(id);
        } else {
            throw new RuntimeException("Prontuário não encontrado.");
        }
    }
}
