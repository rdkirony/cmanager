create table Usuario(
    id serial,
    login varchar(30) NOT NULL ,
    senha varchar(50) NOT NULL,
    pessoa_id int,
    perfil_id int,
    CONSTRAINT fk_pessoa FOREIGN KEY (pessoa_id) references pessoa(id),
    CONSTRAINT fk_perfil FOREIGN KEY (perfil_id) references perfil(id),
    primary key(id)
);
