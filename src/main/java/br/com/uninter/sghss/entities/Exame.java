package br.com.uninter.sghss.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "exame")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Exame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", nullable = false)
    private Long codigo;

    @Column(name = "resultado", nullable = false, columnDefinition = "TEXT")
    private String resultado;

    @OneToOne
    @JoinColumn(name = "id_agendamento", referencedColumnName = "id")
    private Agendamento agendamento;
}
