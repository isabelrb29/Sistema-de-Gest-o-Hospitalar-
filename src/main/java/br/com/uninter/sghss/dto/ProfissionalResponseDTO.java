package br.com.uninter.sghss.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class ProfissionalResponseDTO extends UsuarioResponseDTO {
    private String especialidade;
    private String registroProfissional;

    public ProfissionalResponseDTO(Long id, String nome, String email, String telefone,
                                   boolean ativo, String tipoUsuario,
                                   String especialidade, String registroProfissional) {
        super(id, nome, email, telefone, ativo, tipoUsuario);
        this.especialidade = especialidade;
        this.registroProfissional = registroProfissional;
    }

}
