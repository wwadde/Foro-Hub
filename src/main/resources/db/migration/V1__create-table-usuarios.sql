CREATE TABLE public."usuarios"
(
    id serial PRIMARY KEY,
    nombre character varying(150) NOT NULL,
    correo_electronico character varying(150) NOT NULL,
    contrasena character varying(100) NOT NULL
);