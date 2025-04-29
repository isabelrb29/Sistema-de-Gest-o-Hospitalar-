package br.com.uninter.sghss.services;

import br.com.uninter.sghss.entities.Administrador;
import br.com.uninter.sghss.entities.Paciente;
import br.com.uninter.sghss.entities.Profissional;
import br.com.uninter.sghss.entities.Usuario;
import br.com.uninter.sghss.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Usuario salvar(Usuario usuario) {
        usuario.setSenha(passwordEncoder.encode(usuario.getSenha()));
        return usuarioRepository.save(usuario);
    }

    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    public Optional<Usuario> buscarPorId(Long id) {
        return usuarioRepository.findById(id);
    }

    public List<Usuario> buscarPorTipo(Class<?> tipoClasse) {
        return usuarioRepository.findAll()
                .stream()
                .filter(tipoClasse::isInstance)
                .toList();
    }

    public List<Administrador> listarAdministradores() {
        return buscarPorTipo(Administrador.class).stream()
                .map(Administrador.class::cast)
                .toList();
    }

    public List<Paciente> listarPacientes() {
        return buscarPorTipo(Paciente.class).stream()
                .map(Paciente.class::cast)
                .toList();
    }

    public List<Profissional> listarProfissionais() {
        return buscarPorTipo(Profissional.class).stream()
                .map(Profissional.class::cast)
                .toList();
    }

    public Usuario atualizar(Long id, Usuario usuarioAtualizado) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(id);

        if (usuarioOptional.isPresent()) {
            Usuario usuario = usuarioOptional.get();
            usuario.setNome(usuarioAtualizado.getNome());
            usuario.setDataNascimento(usuarioAtualizado.getDataNascimento());
            usuario.setEndereco(usuarioAtualizado.getEndereco());
            usuario.setTelefone(usuarioAtualizado.getTelefone());
            usuario.setEmail(usuarioAtualizado.getEmail());
            usuario.setSenha(usuarioAtualizado.getSenha());
            usuario.setPerfil(usuarioAtualizado.getPerfil());
            usuario.setAtivo(usuarioAtualizado.isAtivo());
            return usuarioRepository.save(usuario);
        } else {
            throw new RuntimeException("Usuário não encontrado.");
        }
    }

    public void deletar(Long id) {
        if (usuarioRepository.existsById(id)) {
            usuarioRepository.deleteById(id);
        } else {
            throw new RuntimeException("Usuário não encontrado.");
        }
    }
}

