create table Usuario(
    id serial,
    login varchar(30) NOT NULL ,
    senha varchar(50) NOT NULL,
    pessoa_id int,
    CONSTRAINT fk_pessoa FOREIGN KEY (pessoa_id) references pessoa(id),
    primary key(id)
);
create table Usuario_Perfil(
    id serial,
    id_perfil int,
    id_usuario int,
    constraint fk_perfil_usuario foreign key (id_perfil) references Perfil(id),
    constraint fk_usuario_perfil foreign key (id_usuario) references Usuario(id),
    primary key(id)
);