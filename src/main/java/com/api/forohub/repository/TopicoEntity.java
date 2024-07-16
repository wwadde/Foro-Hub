package com.api.forohub.repository;

import com.api.forohub.domain.topico.TopicoDatos;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "topicos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

//@EqualsAndHashCode(of = "id")
public class TopicoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String  titulo;

    private String mensaje;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    private Boolean status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private UsuarioEntity usuario;

    public TopicoEntity(TopicoDatos dto, UsuarioEntity usuario) {
        this.titulo = dto.titulo();
        this.mensaje = dto.mensaje();
        this.fechaCreacion = dto.fechaCreacion();
        this.status = dto.status();
        this.usuario = usuario;

    }
}
