package br.com.uninter.sghss.controllers;

import br.com.uninter.sghss.entities.Prontuario;
import br.com.uninter.sghss.services.ProntuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/prontuarios")
public class ProntuarioController {
    private final ProntuarioService prontuarioService;

    public ProntuarioController(ProntuarioService prontuarioService) {
        this.prontuarioService = prontuarioService;
    }


    @GetMapping
    public List<Prontuario> listarTodos() {
        return prontuarioService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prontuario> buscarPorId(@PathVariable Long id) {
        Optional<Prontuario> prontuario = prontuarioService.buscarPorId(id);
        return prontuario.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public Prontuario salvar(@RequestBody Prontuario prontuario) {
        return prontuarioService.salvar(prontuario);
    }

 /*   @PutMapping("/{id}")
    public ResponseEntity<Prontuario> editar(@PathVariable Long id, @RequestBody Prontuario prontuario) {
        Optional<Prontuario> prontuarioAtual = prontuarioService.buscarPorId(id);
        if (prontuarioAtual.isPresent()) {
            return ResponseEntity.ok(prontuarioAtual.get());
        }
        return ResponseEntity.notFound().build();
    } */

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        prontuarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
