package br.com.uninter.sghss.entities;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "relatorio_financeiro")
@Data
public class RelatorioFinanceiro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String descricao;
}
