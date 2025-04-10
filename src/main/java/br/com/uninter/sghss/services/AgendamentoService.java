package br.com.uninter.sghss.services;

import br.com.uninter.sghss.entities.Agendamento;
import br.com.uninter.sghss.repositories.AgendamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendamentoService {

    @Autowired
    private AgendamentoRepository agendamentoRepository;

    public Agendamento salvar(Agendamento agendamento) {
        return agendamentoRepository.save(agendamento);
    }

    public List<Agendamento> listarTodos() {
        return agendamentoRepository.findAll();
    }

    public Optional<Agendamento> buscarPorId(Long id) {
        return agendamentoRepository.findById(id);
    }

    public Agendamento atualizar(Long id, Agendamento agendamentoAtualizado) {
        Optional<Agendamento> agendamentoOptional = agendamentoRepository.findById(id);

        if (agendamentoOptional.isPresent()) {
            Agendamento agendamento = agendamentoOptional.get();
            agendamento.setPaciente(agendamentoAtualizado.getPaciente());
            agendamento.setProfissional(agendamentoAtualizado.getProfissional());
            agendamento.setDataHora(agendamentoAtualizado.getDataHora());
            agendamento.setTipoAgendamento(agendamentoAtualizado.getTipoAgendamento());
            agendamento.setRealizado(agendamentoAtualizado.getRealizado());
            return agendamentoRepository.save(agendamento);
        } else {
            throw new RuntimeException("Agendamento não encontrado.");
        }
    }

    public void deletar(Long id) {
        if (agendamentoRepository.existsById(id)) {
            agendamentoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Agendamento não encontrado.");
        }
    }
}
