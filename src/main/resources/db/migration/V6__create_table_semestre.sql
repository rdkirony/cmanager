create table SEMESTRE(
    id serial,
    data_inicial TIMESTAMP,
    data_final TIMESTAMP,
    primary key(id)
);
create table SEMESTRE_MATERIA(
     id serial,
     materia_id int,
     semestre_id int,
     CONSTRAINT fk_materia FOREIGN KEY (materia_id) references MATERIA(id),
     CONSTRAINT fk_semestre FOREIGN KEY (semestre_id) references SEMESTRE(id),
     primary key(id)
);



create table ALOCACAO_SEMESTRE(
    id serial,
    data_inicial TIMESTAMP,
    data_final TIMESTAMP,
    semestre_id int,
    CONSTRAINT fk_semestre_alocacao FOREIGN KEY (semestre_id) references SEMESTRE(id),
    primary key(id)
);