package br.com.uninter.sghss.controllers;

import br.com.uninter.sghss.entities.Leito;
import br.com.uninter.sghss.services.LeitoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/leitos")
public class LeitoController {

    @Autowired
    private LeitoService leitoService;

    @PostMapping
    public ResponseEntity<Leito> criar(@RequestBody Leito leito) {
        Leito salvo = leitoService.salvar(leito);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping
    public ResponseEntity<List<Leito>> listarTodos() {
        return ResponseEntity.ok(leitoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Leito> buscarPorId(@PathVariable Long id) {
        return leitoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Leito> atualizar(@PathVariable Long id, @RequestBody Leito atualizado) {
        try {
            Leito leito = leitoService.atualizar(id, atualizado);
            return ResponseEntity.ok(leito);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            leitoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
