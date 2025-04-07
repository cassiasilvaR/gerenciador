use sis_gerenciador;

create table solicitacao (
	id_solicitacao int primary key auto_increment,
    tipo_sol bool,
    data timestamp default current_timestamp,
    status varchar(50), 
    conteudo varchar(250) not null,
    resposta varchar(100),
    id_consumidor int, 
    id_atendente int,
    constraint fk_consumidor foreign key (id_usuario)references usuario
);

create table usuario (
	id_usuario int primary key auto_increment,
    email varchar(50) not null,
    nome varchar(50) not null,
    senha varchar(50) not null
);

create table funcionario (
	id_funcionario int primary key auto_increment,
    cargo varchar(50) not null,
    departamento varchar(50) not null
);

create table parecer (
	id_parecer int primary key auto_increment,
    id_funcionario int,
    id_solicitacao int,
    conteudo varchar(250),
    data timestamp default current_timestamp
);
