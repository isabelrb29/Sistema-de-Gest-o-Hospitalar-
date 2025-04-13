package br.com.uninter.sghss.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "leito")
@Data
public class Leito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_leito", nullable = false, unique = true, length = 10)
    private String numeroLeito;

    @Column(name = "tipo_leito", nullable = false, length = 255)
    private String tipoLeito;

    @Column(name = "disponibilidade", nullable = false)
    private Boolean disponibilidade;
}
