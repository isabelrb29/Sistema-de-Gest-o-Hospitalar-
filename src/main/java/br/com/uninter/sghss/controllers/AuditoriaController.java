package br.com.uninter.sghss.controllers;

import br.com.uninter.sghss.entities.Auditoria;
import br.com.uninter.sghss.services.AuditoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/auditoria")
public class AuditoriaController {

    @Autowired
    private AuditoriaService auditoriaService;

    @PostMapping
    public ResponseEntity<Auditoria> registrar(@RequestBody Auditoria auditoria) {
        Auditoria salva = auditoriaService.registrar(auditoria);
        return ResponseEntity.status(HttpStatus.CREATED).body(salva);
    }

    @GetMapping
    public ResponseEntity<List<Auditoria>> buscarRegistros(
            @RequestParam(required = false) Long usuarioId,
            @RequestParam(required = false) String tipoOperacao,
            @RequestParam(required = false) String tabelaAfetada,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime inicio,
            @RequestParam(required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime fim
    ) {
        if (usuarioId != null) {
            return ResponseEntity.ok(auditoriaService.buscarPorUsuario(usuarioId));
        }

        if (tipoOperacao != null) {
            return ResponseEntity.ok(auditoriaService.buscarPorTipoOperacao(tipoOperacao));
        }

        if (tabelaAfetada != null) {
            return ResponseEntity.ok(auditoriaService.buscarPorTabela(tabelaAfetada));
        }

        if (inicio != null && fim != null) {
            return ResponseEntity.ok(auditoriaService.buscarPorPeriodo(inicio, fim));
        }

        return ResponseEntity.ok(auditoriaService.listarTodas());
    }
}
