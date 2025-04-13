package br.com.uninter.sghss.controllers;

import br.com.uninter.sghss.entities.Agendamento;
import br.com.uninter.sghss.entities.Consulta;
import br.com.uninter.sghss.entities.Exame;
import br.com.uninter.sghss.services.AgendamentoService;
import br.com.uninter.sghss.services.ConsultaService;
import br.com.uninter.sghss.services.ExameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agendamentos")
public class AgendamentoController {

    @Autowired
    private AgendamentoService agendamentoService;

    @Autowired
    private ConsultaService consultaService;

    @Autowired
    private ExameService exameService;

    // -------------------- AGENDAMENTO --------------------

    @PostMapping
    public ResponseEntity<Agendamento> criar(@RequestBody Agendamento agendamento) {
        Agendamento salvo = agendamentoService.salvar(agendamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping
    public ResponseEntity<List<Agendamento>> listarTodos() {
        return ResponseEntity.ok(agendamentoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Agendamento> buscarPorId(@PathVariable Long id) {
        return agendamentoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Agendamento> atualizar(@PathVariable Long id, @RequestBody Agendamento atualizado) {
        try {
            Agendamento agendamento = agendamentoService.atualizar(id, atualizado);
            return ResponseEntity.ok(agendamento);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        try {
            agendamentoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // -------------------- CONSULTA --------------------

    @PostMapping("/{id}/consulta")
    public ResponseEntity<Consulta> registrarConsulta(@PathVariable Long id, @RequestBody Consulta consulta) {
        consulta.setId(id); // ID do agendamento é usado aqui
        Consulta salva = consultaService.salvar(consulta);
        return ResponseEntity.status(HttpStatus.CREATED).body(salva);
    }

    @GetMapping("/{id}/consulta")
    public ResponseEntity<Consulta> buscarConsulta(@PathVariable Long id) {
        return consultaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // -------------------- EXAME --------------------

    @PostMapping("/{id}/exame")
    public ResponseEntity<Exame> registrarExame(@PathVariable Long id, @RequestBody Exame exame) {
        exame.setId(id); // ID do agendamento é usado aqui
        Exame salvo = exameService.salvar(exame);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping("/{id}/exame")
    public ResponseEntity<Exame> buscarExame(@PathVariable Long id) {
        return exameService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
