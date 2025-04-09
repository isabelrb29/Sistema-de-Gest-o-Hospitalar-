package br.com.uninter.sghss.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UsuarioLogadoResponse {
    private String email;
    private String role;
}
