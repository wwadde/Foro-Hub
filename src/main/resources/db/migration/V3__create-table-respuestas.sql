CREATE TABLE public."respuestas"
(
    id serial PRIMARY KEY,
    mensaje character varying(1000) NOT NULL,
    fecha_creacion timestamp NOT NULL,
    solucion character varying(50),
    usuario_id integer NOT NULL,
    topico_id integer NOT NULL,
    CONSTRAINT fk_respuestas_usuario FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    CONSTRAINT fk_respuestas_topico FOREIGN KEY (topico_id) REFERENCES topicos(id)
);