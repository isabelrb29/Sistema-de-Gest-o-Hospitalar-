package br.com.uninter.sghss.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "prescricao")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Prescricao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPrescricao;

    @ManyToOne
    @JoinColumn(name = "paciente_id", nullable = false)
    private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "profissional_id", nullable = false)
    private Profissional profissional;

    @Column(name = "medicamento", length = 255)
    private String medicamento;

    @Column(name = "dosagem", length = 20)
    private String dosagem;

    @Column(name = "instrucoes", columnDefinition = "TEXT")
    private String instrucoes;

    @Column(name = "data_hora", nullable = false)
    private LocalDate dataHora;
}
