package br.com.uninter.sghss.entities;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class InternacaoSuprimentoId implements Serializable {
    private Long internacaoId;
    private Long suprimentoId;
}
