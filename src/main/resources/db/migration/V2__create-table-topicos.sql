CREATE TABLE public."topicos"
(
    id serial PRIMARY KEY,
    titulo character varying(150) NOT NULL,
    mensaje character varying(1500) NOT NULL,
    fecha_creacion timestamp NOT NULL,
    status boolean NOT NULL,
    usuario_id integer NOT NULL,
    CONSTRAINT fk_topicos_usuarios FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);