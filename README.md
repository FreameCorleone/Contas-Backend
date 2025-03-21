create database if not exists projeto_contas;
use projeto_contas;

create table if not exists endereco (
    idendereco INT AUTO_INCREMENT PRIMARY KEY,
    estado varchar(45) not null,
    cidade varchar(45) not null,
	bairro varchar(45) not null,
    rua varchar(45) not null,
    numero varchar(45) not null,
    cep varchar(9) not null

);

create table if not exists telefone (
    idtelefone INT AUTO_INCREMENT PRIMARY KEY,
    numero varchar(45) not null,
    tipo_numero varchar(45) not null

);

create table if not exists usuario (
    idusuario INT AUTO_INCREMENT PRIMARY KEY,
    nome varchar(45) not null,
    cpf varchar(45) not null,
	email varchar(45) not null,
    login varchar(45) not null,
    senha varchar(45) not null,
    idendereco int not null,
    constraint fk_idendereco_endereco foreign key (idendereco)
	references endereco(idendereco),
    idtelefone int not null,
    constraint fk_idtelefone_telefone foreign key (idtelefone)
	references telefone(idtelefone)

);


create table if not exists categoria (
    idcategoria INT AUTO_INCREMENT PRIMARY KEY,
    descricao varchar(45) not null,
    tipo varchar(45) not null

);

create table if not exists contas (
    idcontas INT AUTO_INCREMENT PRIMARY KEY,
    descricao varchar(45) not null,
    valor float not null,
	data_vencimento date not null,
    data_pagamento date,
    tipo_conta varchar(45) not null,
	status_contas boolean not null,
    idusuario int not null,
    constraint fk_idusuario_usuario foreign key (idusuario)
	references usuario(idusuario),
    idcategoria int not null,
    constraint fk_idcategoria_categoria foreign key (idcategoria)
	references categoria(idcategoria)

);

create table if not exists parcela (
    idparcela INT AUTO_INCREMENT PRIMARY KEY,
    data_vencimento date not null,
    numero_parcela int not null,
	valor_parcela double not null,
    idcontas int not null,
    constraint fk_idcontas_contas foreign key (idcontas)
	references contas(idcontas)

);



INSERT INTO categoria (descricao, tipo) VALUES 
('Alimentação', 'Despesa'),
('Transporte', 'Despesa'),
('Saúde', 'Despesa'),
('Educação', 'Despesa'),
('Lazer', 'Despesa'),
('Moradia', 'Despesa'),
('Salário', 'Receita'),
('Investimentos', 'Receita'),
('Renda Extra', 'Receita'),
('Impostos', 'Despesa');

