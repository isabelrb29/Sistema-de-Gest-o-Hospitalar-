package br.com.uninter.sghss.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "tipoUsuario"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = Administrador.class, name = "Administrador"),
        @JsonSubTypes.Type(value = Paciente.class, name = "Paciente"),
        @JsonSubTypes.Type(value = Profissional.class, name = "Profissional")
})

@Data
@Entity
@Table(name = "usuario")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_usuario", discriminatorType = DiscriminatorType.STRING)
public abstract class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(nullable = false)
    private LocalDate dataNascimento;

    @Column(unique = true, nullable = false, length = 14)
    private String cpf;

    @Column(nullable = false, length = 255)
    private String endereco;

    @Column(unique = true, nullable = false, length = 255)
    private String email;

    @Column(nullable = false, length = 20)
    private String telefone;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(nullable = false, length = 255)
    private String senha;

    @Column(nullable = false)
    private boolean ativo = true;

    @ManyToOne
    @JoinColumn(name = "id_perfil", nullable = false)
    private Perfil perfil;

    @JsonIgnore
    public abstract String getTipoUsuario();
}
