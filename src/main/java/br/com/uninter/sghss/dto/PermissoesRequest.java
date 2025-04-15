package br.com.uninter.sghss.dto;

import lombok.Data;

import java.util.List;

@Data
public class PermissoesRequest {
    private List<Long> idsPermissoes;
}
