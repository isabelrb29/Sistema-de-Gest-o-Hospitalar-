package br.com.uninter.sghss.controllers;

import br.com.uninter.sghss.dto.PermissoesRequest;
import br.com.uninter.sghss.dto.ProfissionalResponseDTO;
import br.com.uninter.sghss.dto.UsuarioResponseDTO;
import br.com.uninter.sghss.entities.Profissional;
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
import java.util.Optional;

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
    public ResponseEntity<?> buscarUsuario(@PathVariable Long id) {
        Optional<Usuario> optionalUsuario = usuarioService.buscarPorId(id);

        if (optionalUsuario.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário não encontrado");
        }

        Usuario usuario = optionalUsuario.get();

        if (usuario instanceof Profissional profissional) {
            ProfissionalResponseDTO dto = new ProfissionalResponseDTO();
            dto.setId(profissional.getId());
            dto.setNome(profissional.getNome());
            dto.setEmail(profissional.getEmail());
            dto.setTelefone(profissional.getTelefone());
            dto.setAtivo(profissional.isAtivo());
            dto.setTipoUsuario(profissional.getTipoUsuario());
            dto.setEspecialidade(profissional.getEspecialidade());
            dto.setRegistroProfissional(profissional.getRegistroProfissional());
            return ResponseEntity.ok(dto);
        }

        UsuarioResponseDTO dto = new UsuarioResponseDTO();
        dto.setId(usuario.getId());
        dto.setNome(usuario.getNome());
        dto.setEmail(usuario.getEmail());
        dto.setTelefone(usuario.getTelefone());
        dto.setAtivo(usuario.isAtivo());
        dto.setTipoUsuario(usuario.getTipoUsuario());

        return ResponseEntity.ok(dto);
    }

    @GetMapping("/pacientes")
    public ResponseEntity<List<UsuarioResponseDTO>> listarPacientes() {
        var lista = usuarioService.listarPacientes()
                .stream()
                .map(paciente -> new UsuarioResponseDTO(
                        paciente.getId(),
                        paciente.getNome(),
                        paciente.getEmail(),
                        paciente.getTelefone(),
                        paciente.isAtivo(),
                        paciente.getTipoUsuario()))
                .toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/profissionais")
    public ResponseEntity<List<ProfissionalResponseDTO>> listarProfissionais() {
        var lista = usuarioService.listarProfissionais()
                .stream()
                .map(p -> new ProfissionalResponseDTO(
                        p.getId(),
                        p.getNome(),
                        p.getEmail(),
                        p.getTelefone(),
                        p.isAtivo(),
                        p.getTipoUsuario(),
                        p.getEspecialidade(),
                        p.getRegistroProfissional()))
                .toList();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/administradores")
    public ResponseEntity<List<UsuarioResponseDTO>> listarAdministradores() {
        var lista = usuarioService.listarAdministradores()
                .stream()
                .map(admin -> new UsuarioResponseDTO(
                        admin.getId(),
                        admin.getNome(),
                        admin.getEmail(),
                        admin.getTelefone(),
                        admin.isAtivo(),
                        admin.getTipoUsuario()))
                .toList();
        return ResponseEntity.ok(lista);
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

    @PostMapping("/perfis/{id}/permissoes")
    public ResponseEntity<Perfil> adicionarPermissoes(
            @PathVariable Long id,
            @RequestBody PermissoesRequest request
    ) {
        Perfil perfilAtualizado = perfilService.adicionarPermissoes(id, request.getIdsPermissoes());
        return ResponseEntity.ok(perfilAtualizado);
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

    @GetMapping("/permissoes/{id}")
    public ResponseEntity<?> buscarPermissao(@PathVariable Long id) {
        Optional<Permissao> permissao = permissaoService.buscarPorId(id);

        if (permissao.isPresent()) {
            return ResponseEntity.ok(permissao.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Permissão não encontrada");
        }
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
