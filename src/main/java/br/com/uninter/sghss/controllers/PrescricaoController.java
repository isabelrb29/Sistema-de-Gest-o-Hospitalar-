package br.com.uninter.sghss.controllers;

import br.com.uninter.sghss.entities.Prescricao;
import br.com.uninter.sghss.services.PrescricaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prescricoes")
public class PrescricaoController {

    @Autowired
    private PrescricaoService prescricaoService;

    @PostMapping
    public ResponseEntity<Prescricao> criar(@RequestBody Prescricao prescricao) {
        Prescricao salva = prescricaoService.salvar(prescricao);
        return ResponseEntity.status(HttpStatus.CREATED).body(salva);
    }

    @GetMapping
    public ResponseEntity<List<Prescricao>> listarTodas() {
        return ResponseEntity.ok(prescricaoService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prescricao> buscarPorId(@PathVariable Long id) {
        return prescricaoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prescricao> atualizar(@PathVariable Long id, @RequestBody Prescricao atualizada) {
        try {
            Prescricao prescricao = prescricaoService.atualizar(id, atualizada);
            return ResponseEntity.ok(prescricao);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            prescricaoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
