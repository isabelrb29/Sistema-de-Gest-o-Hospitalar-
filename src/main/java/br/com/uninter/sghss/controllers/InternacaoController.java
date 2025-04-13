package br.com.uninter.sghss.controllers;

import br.com.uninter.sghss.dto.TrocaLeitoRequest;
import br.com.uninter.sghss.entities.HistoricoTrocaLeito;
import br.com.uninter.sghss.entities.Internacao;
import br.com.uninter.sghss.services.HistoricoTrocaLeitoService;
import br.com.uninter.sghss.services.InternacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/internacoes")
public class InternacaoController {

    @Autowired
    private InternacaoService internacaoService;

    @Autowired
    private HistoricoTrocaLeitoService trocaLeitoService;

    @PostMapping
    public ResponseEntity<Internacao> criar(@RequestBody Internacao internacao) {
        Internacao salva = internacaoService.salvar(internacao);
        return ResponseEntity.status(HttpStatus.CREATED).body(salva);
    }

    @GetMapping
    public ResponseEntity<List<Internacao>> listarTodas() {
        return ResponseEntity.ok(internacaoService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Internacao> buscarPorId(@PathVariable Long id) {
        return internacaoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Internacao> atualizar(@PathVariable Long id, @RequestBody Internacao atualizada) {
        try {
            Internacao internacao = internacaoService.atualizar(id, atualizada);
            return ResponseEntity.ok(internacao);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            internacaoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/troca-leito")
    public ResponseEntity<?> registrarTrocaLeito(@PathVariable Long id, @RequestBody TrocaLeitoRequest request) {
        try {
            HistoricoTrocaLeito troca = trocaLeitoService.registrarTroca(id, request.getLeitoId());
            return ResponseEntity.status(HttpStatus.CREATED).body(troca);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
