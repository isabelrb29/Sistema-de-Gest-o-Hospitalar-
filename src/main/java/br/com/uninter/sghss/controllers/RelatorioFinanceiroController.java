package br.com.uninter.sghss.controllers;

import br.com.uninter.sghss.entities.RelatorioFinanceiro;
import br.com.uninter.sghss.services.RelatorioFinanceiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/financeiro")
public class RelatorioFinanceiroController {

    @Autowired
    private RelatorioFinanceiroService relatorioService;

    @PostMapping("/relatorios")
    public ResponseEntity<RelatorioFinanceiro> criar(@RequestBody RelatorioFinanceiro relatorio) {
        RelatorioFinanceiro salvo = relatorioService.salvar(relatorio);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping("/relatorios")
    public ResponseEntity<List<RelatorioFinanceiro>> listarTodos() {
        return ResponseEntity.ok(relatorioService.listarTodos());
    }

    @GetMapping("/relatorios/{id}")
    public ResponseEntity<RelatorioFinanceiro> buscarPorId(@PathVariable Long id) {
        return relatorioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/relatorios/{id}")
    public ResponseEntity<RelatorioFinanceiro> atualizar(@PathVariable Long id, @RequestBody RelatorioFinanceiro atualizado) {
        try {
            RelatorioFinanceiro relatorio = relatorioService.atualizar(id, atualizado);
            return ResponseEntity.ok(relatorio);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/relatorios/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            relatorioService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
