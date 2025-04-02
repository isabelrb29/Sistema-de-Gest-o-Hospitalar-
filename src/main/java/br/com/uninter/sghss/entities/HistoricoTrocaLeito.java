package br.com.uninter.sghss.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "historico_troca_leito")
@Data
public class HistoricoTrocaLeito {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idHistoricoTrocaLeito;

    @ManyToOne
    @JoinColumn(name = "internacao_id", nullable = false)
    private Internacao internacao;

    @ManyToOne
    @JoinColumn(name = "leito_id", nullable = false)
    private Leito leito;

    @Column(nullable = false)
    private LocalDateTime dataTroca;
}
