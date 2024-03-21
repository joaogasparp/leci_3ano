--CREATE SCHEMA ATL;
--go

if object_id('ATL_tem', 'u') is not null
	drop table dbo.ATL_tem;
if object_id('ATL_frequenta', 'u') is not null
	drop table dbo.ATL_frequenta;
if object_id('ATL_encarregadodeeducacao', 'u') is not null
	drop table dbo.ATL_encarregadodeeducacao;
if object_id('ATL_apenasautorizada', 'u') is not null
	drop table dbo.ATL_apenasautorizada;
if object_id('ATL_pessoaautorizada', 'u') is not null
	drop table dbo.ATL_pessoaautorizada;
if object_id('ATL_aluno', 'u') is not null
	drop table dbo.ATL_aluno;
if object_id('ATL_atividade', 'u') is not null
	drop table dbo.ATL_atividade;
if object_id('ATL_turma', 'u') is not null
	drop table dbo.ATL_turma;
if object_id('ATL_professor', 'u') is not null
	drop table dbo.ATL_professor;

CREATE TABLE ATL_professor(
	ncc varchar(9) primary key (ncc),
	nome varchar(128),
	num_funcionario int,
	data_nascimeto date,
	morada varchar(512),
	email varchar(128),
	contacto_telefonico int,
)

CREATE TABLE ATL_turma(
	id int primary key(id),
	designacao varchar(512),
	escolaridade int,
	ano_letivo int,
	n_max_alunos int,
	professor_ncc varchar(9) references ATL_professor(ncc),
)

CREATE TABLE ATL_atividade(
	id int primary key(id),
	designacao varchar(512),
	custo money,
)

CREATE TABLE ATL_aluno(
	ncc varchar(9) primary key(ncc),
	nome varchar(128),
	morada varchar(512),
	data_nascimento date,
	turma_id int references ATL_turma(id),
)

CREATE TABLE ATL_pessoaautorizada(
	ncc varchar(9) primary key(ncc),
	nome varchar(128),
	morada varchar(512),
	data_nascimento date,
	email varchar(128),
	relacao varchar(128),
	contacto_telefonico int,
	aluno_ncc varchar(9) references ATL_aluno(ncc),
)

CREATE TABLE ATL_apenasautorizada(
	ncc varchar(9) references ATL_pessoaautorizada(ncc),
)

CREATE TABLE ATL_encarregadodeeducacao(
	ncc varchar(9) references ATL_pessoaautorizada(ncc),
)

CREATE TABLE ATL_frequenta(
	atividade_id int references ATL_atividade(id),
	aluno_ncc varchar(9) references ATL_aluno(ncc),
	primary key(atividade_id, aluno_ncc),
)

CREATE TABLE ATL_tem(
	atividade_id int references ATL_atividade(id),
	turma_id int references ATL_turma(id),
	primary key(atividade_id, turma_id),
)