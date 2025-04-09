package br.com.uninter.sghss.dto;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String senha;
}