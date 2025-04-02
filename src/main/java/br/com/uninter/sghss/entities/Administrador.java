package br.com.uninter.sghss.entities;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
@DiscriminatorValue("Administrador")
public class Administrador extends Usuario{

    @Override
    public String getTipoUsuario() {
        return "Administrador";
    }
}
