package br.com.uninter.sghss.security;

import br.com.uninter.sghss.entities.Usuario;
import br.com.uninter.sghss.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado com o email: " + email));

        // Pega a role do tipo do usuário (ex: ROLE_ADMINISTRADOR, ROLE_PROFISSIONAL)
        String role = usuario.getTipoUsuario();

        return User.builder()
                .username(usuario.getEmail())
                .password(usuario.getSenha())
                .roles(role)
                .build();
    }
}

