package br.com.uninter.sghss.services;

import br.com.uninter.sghss.entities.RelatorioFinanceiro;
import br.com.uninter.sghss.repositories.RelatorioFinanceiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RelatorioFinanceiroService {

    @Autowired
    private RelatorioFinanceiroRepository relatorioRepository;

    public RelatorioFinanceiro salvar(RelatorioFinanceiro relatorio) {
        return relatorioRepository.save(relatorio);
    }

    public List<RelatorioFinanceiro> listarTodos() {
        return relatorioRepository.findAll();
    }

    public Optional<RelatorioFinanceiro> buscarPorId(Long id) {
        return relatorioRepository.findById(id);
    }

    public RelatorioFinanceiro atualizar(Long id, RelatorioFinanceiro relatorioAtualizado) {
        Optional<RelatorioFinanceiro> relatorioOptional = relatorioRepository.findById(id);

        if (relatorioOptional.isPresent()) {
            RelatorioFinanceiro relatorio = relatorioOptional.get();
            relatorio.setDescricao(relatorioAtualizado.getDescricao());
            return relatorioRepository.save(relatorio);
        } else {
            throw new RuntimeException("Relatório financeiro não encontrado.");
        }
    }

    public void deletar(Long id) {
        if (relatorioRepository.existsById(id)) {
            relatorioRepository.deleteById(id);
        } else {
            throw new RuntimeException("Relatório financeiro não encontrado.");
        }
    }
}
