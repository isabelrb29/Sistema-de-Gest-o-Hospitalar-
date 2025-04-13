package br.com.uninter.sghss.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "auditoria")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Auditoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(name = "tabela_afetada", nullable = false, length = 255)
    private String tabelaAfetada;

    @Column(name = "registro_afetado_id", nullable = false)
    private Long registroAfetadoId;

    @Column(name = "tipo_operacao", nullable = false, length = 255)
    private String tipoOperacao;

    @Column(name = "data_hora", nullable = false)
    private LocalDate dataHora;

    @Column(name = "valores_antigos", length = 255)
    private String valoresAntigos;

    @Column(name = "valores_novos", length = 255)
    private String valoresNovos;

    @Column(name = "endereco_ip", nullable = false, length = 50)
    private String enderecoIp;

    @Column(name = "dispositivo", nullable = false, length = 150)
    private String dispositivo;

    @Column(name = "detalhes", columnDefinition = "TEXT")
    private String detalhes;
}
