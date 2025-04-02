package br.com.uninter.sghss.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@DiscriminatorValue("Paciente")
public class Paciente extends Usuario{

    @Override
    public String getTipoUsuario() {
        return "Paciente";
    }
}
