create table Pessoa(
    id serial,
    nome VARCHAR(30) NOT NULL,
    cpf VARCHAR(20),
    email VARCHAR(50),
    endereco VARCHAR(50),
    primary key(id)
)