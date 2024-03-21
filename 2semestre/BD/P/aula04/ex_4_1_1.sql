--CREATE SCHEMA Rent_A_Car;
--go

if object_id('RAC_aluguer', 'u') is not null
	drop table dbo.RAC_aluguer;
if object_id('RAC_balcao', 'u') is not null
	drop table dbo.RAC_balcao;
if object_id('RAC_cliente', 'u') is not null
	drop table dbo.RAC_cliente;
if object_id('RAC_similaridade', 'u') is not null
	drop table dbo.RAC_similaridade;
if object_id('RAC_pesado', 'u') is not null
	drop table dbo.RAC_pesado;
if object_id('RAC_ligeiro', 'u') is not null
	drop table dbo.RAC_ligeiro;
if object_id('RAC_veiculo', 'u') is not null
	drop table dbo.RAC_veiculo;
if object_id('RAC_tipoveiculo', 'u') is not null
	drop table dbo.RAC_tipoveiculo;

CREATE TABLE RAC_tipoveiculo(
	codigo int primary key(codigo),
	arcondicionado bit,
	designacao varchar(512),
)

CREATE TABLE RAC_veiculo(
	matricula varchar(8) primary key(matricula),
	marca varchar(128),
	ano int,
	tvcodigo int references RAC_tipoveiculo(codigo)
)

CREATE TABLE RAC_ligeiro(
	tvcodigo int primary key(tvcodigo) references RAC_tipoveiculo(codigo),
	numLugares int,
	portas int,
	combustivel varchar(128)
)

CREATE TABLE RAC_pesado(
	tvcodigo int primary key(tvcodigo) references RAC_tipoveiculo(codigo),
	peso real,
	passageiros int	
)

CREATE TABLE RAC_similaridade(
	tvcodigo1 int references RAC_tipoveiculo(codigo),
	tvcodigo2 int references RAC_tipoveiculo(codigo),
	primary key(tvcodigo1, tvcodigo2)
)

CREATE TABLE RAC_cliente(
	nif int primary key(nif) CHECK(nif>10000000),
	nome varchar (128),
	endereco varchar (512),
	numcarta int UNIQUE(numcarta),
)

CREATE TABLE RAC_balcao(
	numero int primary key(numero),
	nome varchar (128),
	endereco varchar(512)	
)

CREATE TABLE RAC_aluguer(
	numero int,
	duracao time,
	[data] date,
	clientenif int references RAC_cliente(nif),
	balcaonumero int references RAC_balcao(numero),
	veiculomatricula varchar(8) references RAC_veiculo(matricula),
	primary key(numero)
)