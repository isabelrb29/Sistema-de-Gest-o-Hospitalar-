package br.com.uninter.sghss.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "internacao_suprimento")
@Data
public class InternacaoSuprimento {

    @EmbeddedId
    private InternacaoSuprimentoId id;

    @ManyToOne
    @MapsId("internacaoId")
    @JoinColumn(name = "internacao_id", nullable = false)
    private Internacao internacao;

    @ManyToOne
    @MapsId("suprimentoId")
    @JoinColumn(name = "suprimento_id", nullable = false)
    private Suprimento suprimento;

    @Column(nullable = false)
    private Integer quantidadeUtilizada;

    @Column(nullable = false, length = 10)
    private String unidadeMedida;

    @Column(nullable = false)
    private LocalDateTime dataUtilizacao;

}
