package br.com.uninter.sghss.services;

import br.com.uninter.sghss.entities.Prescricao;
import br.com.uninter.sghss.repositories.PrescricaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrescricaoService {

    @Autowired
    private PrescricaoRepository prescricaoRepository;

    public Prescricao salvar(Prescricao prescricao) {
        return prescricaoRepository.save(prescricao);
    }

    public List<Prescricao> listarTodas() {
        return prescricaoRepository.findAll();
    }

    public Optional<Prescricao> buscarPorId(Long id) {
        return prescricaoRepository.findById(id);
    }

    public Prescricao atualizar(Long id, Prescricao prescricaoAtualizada) {
        Optional<Prescricao> prescricaoOptional = prescricaoRepository.findById(id);

        if (prescricaoOptional.isPresent()) {
            Prescricao prescricao = prescricaoOptional.get();
            prescricao.setPaciente(prescricaoAtualizada.getPaciente());
            prescricao.setProfissional(prescricaoAtualizada.getProfissional());
            prescricao.setMedicamento(prescricaoAtualizada.getMedicamento());
            prescricao.setDosagem(prescricaoAtualizada.getDosagem());
            prescricao.setInstrucoes(prescricaoAtualizada.getInstrucoes());
            prescricao.setDataHora(prescricaoAtualizada.getDataHora());
            return prescricaoRepository.save(prescricao);
        } else {
            throw new RuntimeException("Prescrição não encontrada.");
        }
    }

    public void deletar(Long id) {
        if (prescricaoRepository.existsById(id)) {
            prescricaoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Prescrição não encontrada.");
        }
    }
}
