package br.com.uninter.sghss.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "permissao")
@Getter @Setter
@NoArgsConstructor
public class Permissao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPermissao;

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(length = 255)
    private String descricao;
}
