create table TIPO_MATERIA(
    id serial,
    nome VARCHAR(45) NOT NULL,
    primary key(id)
);
create table Materia(
    id serial,
    nome VARCHAR(200) NOT NULL,
    descricao VARCHAR(255),
    tipo_materia_id int,
    CONSTRAINT fk_tipo_materia FOREIGN KEY (tipo_materia_id) references TIPO_MATERIA(id),
    primary key(id)
);