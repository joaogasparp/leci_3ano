--CREATE SCHEMA Stock_Management_System;
--go

if object_id('SMS_Itens', 'u') is not null
	drop table dbo.SMS_Itens;
if object_id('SMS_Encomenda', 'u') is not null
	drop table dbo.SMS_Encomenda;
if object_id('SMS_Fornecedor', 'u') is not null
	drop table dbo.SMS_Fornecedor;
if object_id('SMS_Tipo_Pagamento', 'u') is not null
	drop table dbo.SMS_Tipo_Pagamento;
if object_id('SMS_Tipo_Fornecedor', 'u') is not null
	drop table dbo.SMS_Tipo_Fornecedor;
if object_id('SMS_Produto', 'u') is not null
	drop table dbo.SMS_Produto;

CREATE TABLE SMS_Produto(
	Codigo int NOT NULL,
	Nome varchar(128) NOT NULL,
	Preco real NOT NULL,
	TaxaIVA real NOT NULL,
	PRIMARY KEY ( Codigo )
)


CREATE TABLE SMS_Tipo_Fornecedor(
	Codigo int NOT NULL,
	Nome varchar(128) NOT NULL,
	PRIMARY KEY ( Codigo )
)


CREATE TABLE SMS_Tipo_Pagamento(
	Codigo int NOT NULL,
	Nome varchar(128) NOT NULL,
	PRIMARY KEY ( Codigo )
)


CREATE TABLE SMS_Fornecedor(
	NIF int NOT NULL,
	Nome varchar(128) NOT NULL,
	Morada varchar(128) NOT NULL,
	TF_Codigo int NOT NULL,
	TP_Codigo int NOT NULL,
	PRIMARY KEY ( NIF ),
	FOREIGN KEY ( TF_Codigo ) REFERENCES SMS_Tipo_Fornecedor ( Codigo ),
	FOREIGN KEY ( TP_Codigo ) REFERENCES SMS_Tipo_Pagamento ( Codigo )
)


CREATE TABLE SMS_Encomenda(
	Numero int NOT NULL,
	Datas Date NOT NULL,
	F_NIF int NOT NULL,
	PRIMARY KEY ( Numero ),
	FOREIGN KEY ( F_NIF ) REFERENCES SMS_Fornecedor( NIF )
)


CREATE TABLE SMS_Itens(
	Id int NOT NULL,
	Quantidade int NOT NULL,
	P_Codigo int NOT NULL,
	E_Numero int NOT NULL,
	PRIMARY KEY ( Id ),
	FOREIGN KEY ( P_Codigo ) REFERENCES SMS_Produto( Codigo ),
	FOREIGN KEY ( E_Numero ) REFERENCES SMS_Encomenda( Numero )
)