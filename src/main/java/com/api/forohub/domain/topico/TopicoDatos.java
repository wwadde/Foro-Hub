package com.api.forohub.domain.topico;

import com.api.forohub.repository.TopicoEntity;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDateTime;

public record TopicoDatos(

        @NotBlank(message = "Titulo no debe estar vacío")
        String titulo,

        @NotBlank(message = "Mensaje no debe estar vacío")
        String mensaje,

        @FutureOrPresent(message = "La fecha debe ser válida, del presente o futuro")
//        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        LocalDateTime fechaCreacion,

        @NotNull(message = "Status no debe estar vacío")
        Boolean status,

        @NotNull(message = "Se debe hacer referencia al usuario")
        Long usuarioId
) {


        public TopicoDatos(TopicoEntity topicoEntity) {
        this(topicoEntity.getTitulo(),
                topicoEntity.getMensaje(),
                topicoEntity.getFechaCreacion(),
                topicoEntity.getStatus(),
                topicoEntity.getUsuario().getId());
        }
}
