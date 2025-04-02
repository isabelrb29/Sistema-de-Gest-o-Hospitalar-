package br.com.uninter.sghss.entities;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@DiscriminatorValue("Profissional")
public class Profissional extends Usuario{

    @Column(nullable = false, length = 255)
    private String especialidade;

    @Column(nullable = false, length = 50)
    private String registroProfissional;

    @Override
    public String getTipoUsuario() {
        return "Profissional";
    }
}
