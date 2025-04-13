package br.com.uninter.sghss.controllers;

import br.com.uninter.sghss.entities.Suprimento;
import br.com.uninter.sghss.services.SuprimentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/suprimentos")
public class SuprimentoController {

    @Autowired
    private SuprimentoService suprimentoService;

    @PostMapping
    public ResponseEntity<Suprimento> criar(@RequestBody Suprimento suprimento) {
        Suprimento salvo = suprimentoService.salvar(suprimento);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping
    public ResponseEntity<List<Suprimento>> listarTodos() {
        return ResponseEntity.ok(suprimentoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Suprimento> buscarPorId(@PathVariable Long id) {
        return suprimentoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Suprimento> atualizar(@PathVariable Long id, @RequestBody Suprimento atualizado) {
        try {
            Suprimento suprimento = suprimentoService.atualizar(id, atualizado);
            return ResponseEntity.ok(suprimento);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            suprimentoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
