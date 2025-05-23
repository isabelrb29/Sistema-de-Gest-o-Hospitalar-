package br.com.uninter.sghss.controllers;

import br.com.uninter.sghss.entities.Prontuario;
import br.com.uninter.sghss.services.ProntuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/prontuarios")
public class ProntuarioController {

    @Autowired
    private ProntuarioService prontuarioService;

    @PostMapping
    public ResponseEntity<Prontuario> criar(@RequestBody Prontuario prontuario) {
        Prontuario salvo = prontuarioService.salvar(prontuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping
    public ResponseEntity<List<Prontuario>> listarTodos() {
        return ResponseEntity.ok(prontuarioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prontuario> buscarPorId(@PathVariable Long id) {
        return prontuarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Prontuario> atualizar(@PathVariable Long id, @RequestBody Prontuario atualizado) {
        try {
            Prontuario prontuario = prontuarioService.atualizar(id, atualizado);
            return ResponseEntity.ok(prontuario);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            prontuarioService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
