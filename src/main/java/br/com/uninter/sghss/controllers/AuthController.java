package br.com.uninter.sghss.controllers;

import br.com.uninter.sghss.dto.LoginRequest;
import br.com.uninter.sghss.dto.LoginResponse;
import br.com.uninter.sghss.dto.UsuarioLogadoResponse;
import br.com.uninter.sghss.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserDetailsService userDetailsService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        try {
            // Autentica o usuário usando o AuthenticationManager
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getSenha()
                    )
            );

            // Pega o UserDetails autenticado
            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // Gera o token com as roles
            String token = jwtUtil.generateToken(userDetails);

            return ResponseEntity.ok(new LoginResponse(token));

        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


    @GetMapping("/usuario-logado")
    public ResponseEntity<UsuarioLogadoResponse> getUsuarioLogado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        Object principal = authentication.getPrincipal();

        if (!(principal instanceof UserDetails userDetails)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }

        String email = userDetails.getUsername();
        String role = userDetails.getAuthorities().stream()
                .findFirst()
                .map(auth -> auth.getAuthority().replace("ROLE_", ""))
                .orElse("SEM_PERMISSAO");

        UsuarioLogadoResponse response = new UsuarioLogadoResponse(email, role);
        return ResponseEntity.ok(response);
    }


}
