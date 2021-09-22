CREATE TABLE clientes(
    nome varchar (25), 
    cpf varchar (25),
    id serial,
    PRIMARY KEY (id)
);

CREATE TABLE produtos (
    id serial,
    descricao VARCHAR(50),
    valor DECIMAL,
    PRIMARY KEY (id)
);