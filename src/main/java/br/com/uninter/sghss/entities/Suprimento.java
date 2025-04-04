package br.com.uninter.sghss.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "suprimento")
@Data
public class Suprimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSuprimento;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private Long quantidade;

    @Column(nullable = false, length = 10)
    private String unidadeMedida;
}
