
CREATE TABLE usuario (
    id bigserial PRIMARY KEY,
    nome varchar(50) NOT NULL,
    email varchar(50) NOT NULL,
    senha varchar(150) NOT NULL
);
CREATE TABLE permissao (
    id bigserial PRIMARY KEY,
    descricao varchar(50) NOT NULL
);
CREATE TABLE usuario_permissao (
    id_usuario int REFERENCES usuario(id) ON UPDATE CASCADE ON DELETE CASCADE,
    id_permissao int REFERENCES permissao(id) ON UPDATE CASCADE ON DELETE CASCADE,
    PRIMARY KEY(id_usuario, id_permissao)
);

INSERT INTO usuario (nome, email, senha) values ('Administrador', 'admin@algamoney.com', '$2a$10$X607ZPhQ4EgGNaYKt3n4SONjIv9zc.VMWdEuhCuba7oLAL5IvcL5.');
INSERT INTO usuario (nome, email, senha) values ('Maria Silva', 'maria@algamoney.com', '$2a$10$Zc3w6HyuPOPXamaMhh.PQOXvDnEsadztbfi6/RyZWJDzimE8WQjaq');

INSERT INTO permissao (descricao) values ('ROLE_CADASTRAR_CATEGORIA');
INSERT INTO permissao (descricao) values ('ROLE_PESQUISAR_CATEGORIA');

INSERT INTO permissao (descricao) values ('ROLE_CADASTRAR_PESSOA');
INSERT INTO permissao (descricao) values ('ROLE_REMOVER_PESSOA');
INSERT INTO permissao (descricao) values ('ROLE_PESQUISAR_PESSOA');

INSERT INTO permissao (descricao) values ('ROLE_CADASTRAR_LANCAMENTO');
INSERT INTO permissao (descricao) values ('ROLE_REMOVER_LANCAMENTO');
INSERT INTO permissao (descricao) values ('ROLE_PESQUISAR_LANCAMENTO');

-- admin
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 1);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 2);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 3);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 4);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 5);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 6);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 7);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (1, 8);

-- maria
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (2, 2);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (2, 5);
INSERT INTO usuario_permissao (id_usuario, id_permissao) values (2, 8);