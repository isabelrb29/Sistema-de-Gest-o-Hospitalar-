package br.com.uninter.sghss.services;

import br.com.uninter.sghss.entities.Consulta;
import br.com.uninter.sghss.repositories.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ConsultaService {

    @Autowired
    private ConsultaRepository consultaRepository;

    public Consulta salvar(Consulta consulta) {
        return consultaRepository.save(consulta);
    }

    public List<Consulta> listarTodas() {
        return consultaRepository.findAll();
    }

    public Optional<Consulta> buscarPorId(Long id) {
        return consultaRepository.findById(id);
    }

    public Consulta atualizar(Long id, Consulta consultaAtualizada) {
        Optional<Consulta> consultaOptional = consultaRepository.findById(id);

        if (consultaOptional.isPresent()) {
            Consulta consulta = consultaOptional.get();
            consulta.setTipoConsulta(consultaAtualizada.getTipoConsulta());
            return consultaRepository.save(consulta);
        } else {
            throw new RuntimeException("Consulta não encontrada.");
        }
    }

    public void deletar(Long id) {
        if (consultaRepository.existsById(id)) {
            consultaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Consulta não encontrada.");
        }
    }
}
