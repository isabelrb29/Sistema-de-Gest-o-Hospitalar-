package br.com.uninter.sghss.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "consulta")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tipo_consulta", nullable = false, length = 15)
    private String tipoConsulta;

    @OneToOne
    @JoinColumn(name = "id_agendamento", referencedColumnName = "id")
    private Agendamento agendamento;

}
