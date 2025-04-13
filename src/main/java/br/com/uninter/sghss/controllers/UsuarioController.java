package br.com.uninter.sghss.controllers;

import br.com.uninter.sghss.entities.Usuario;
import br.com.uninter.sghss.entities.Perfil;
import br.com.uninter.sghss.entities.Permissao;
import br.com.uninter.sghss.services.UsuarioService;
import br.com.uninter.sghss.services.PerfilService;
import br.com.uninter.sghss.services.PermissaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private PerfilService perfilService;

    @Autowired
    private PermissaoService permissaoService;

    // -------------------- USUÁRIOS --------------------

    @PostMapping
    public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
        Usuario salvo = usuarioService.salvar(usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable Long id) {
        return usuarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizarUsuario(@PathVariable Long id, @RequestBody Usuario usuarioAtualizado) {
        try {
            Usuario atualizado = usuarioService.atualizar(id, usuarioAtualizado);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        try {
            usuarioService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // -------------------- PERFIS --------------------

    @PostMapping("/perfis")
    public ResponseEntity<Perfil> criarPerfil(@RequestBody Perfil perfil) {
        Perfil salvo = perfilService.salvar(perfil);
        return ResponseEntity.status(HttpStatus.CREATED).body(salvo);
    }

    @GetMapping("/perfis")
    public ResponseEntity<List<Perfil>> listarPerfis() {
        return ResponseEntity.ok(perfilService.listarTodos());
    }

    @PutMapping("/perfis/{id}")
    public ResponseEntity<Perfil> atualizarPerfil(@PathVariable Long id, @RequestBody Perfil perfilAtualizado) {
        try {
            Perfil atualizado = perfilService.atualizar(id, perfilAtualizado);
            return ResponseEntity.ok(atualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // -------------------- PERMISSÕES --------------------

    @PostMapping("/permissoes")
    public ResponseEntity<Permissao> criarPermissao(@RequestBody Permissao permissao) {
        Permissao salva = permissaoService.salvar(permissao);
        return ResponseEntity.status(HttpStatus.CREATED).body(salva);
    }

    @GetMapping("/permissoes")
    public ResponseEntity<List<Permissao>> listarPermissoes() {
        return ResponseEntity.ok(permissaoService.listarTodas());
    }

    @PutMapping("/permissoes/{id}")
    public ResponseEntity<Permissao> atualizarPermissao(@PathVariable Long id, @RequestBody Permissao permissaoAtualizada) {
        try {
            Permissao atualizada = permissaoService.atualizar(id, permissaoAtualizada);
            return ResponseEntity.ok(atualizada);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
