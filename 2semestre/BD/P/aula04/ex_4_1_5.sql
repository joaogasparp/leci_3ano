--CREATE SCHEMA Sistema_Gestao_Conferencias;
--go


if object_id('SGC_Tem', 'u') is not null
	drop table dbo.SGC_Tem;
if object_id('SGC_Estudante', 'u') is not null
	drop table dbo.SGC_Estudante;
if object_id('SGC_NaoEstudante', 'u') is not null
	drop table dbo.SGC_NaoEstudante;
if object_id('SGC_Comprovativo', 'u') is not null
	drop table dbo.SGC_Comprovativo;
if object_id('SGC_ReferenciaMultibanca', 'u') is not null
	drop table dbo.SGC_ReferenciaMultibanca;
if object_id('SGC_Participante', 'u') is not null
	drop table dbo.SGC_Participante;
if object_id('SGC_Autor', 'u') is not null
	drop table dbo.SGC_Autor;
if object_id('SGC_Instituicao', 'u') is not null
	drop table dbo.SGC_Instituicao;
if object_id('SGC_ArtigoCientifico', 'u') is not null
	drop table dbo.SGC_ArtigoCientifico;


CREATE TABLE SGC_ArtigoCientifico(
	Num_Registro int NOT NULL,
	Titulo varchar(128) NOT NULL,
	PRIMARY KEY (Num_Registro)
)

CREATE TABLE SGC_Instituicao(
	Endereco varchar(128) NOT NULL,
	Nome varchar(128) NOT NULL,
	PRIMARY KEY (Endereco)
)

CREATE TABLE SGC_Autor(
	Email varchar(128) NOT NULL,
	Nome varchar(128) NOT NULL,
	Inst_Endereco varchar(128) NOT NULL,
	PRIMARY KEY (Email),
	FOREIGN KEY (Inst_Endereco) REFERENCES SGC_Instituicao (Endereco)
)

CREATE TABLE SGC_Participante(
	Email varchar(128) NOT NULL,
	Nome varchar(128) NOT NULL,
	Morada varchar(128) NOT NULL,
	Data_Inscricao date NOT NULL,
	Inst_Endereco varchar(128) NOT NULL,
	PRIMARY KEY (Email),
	FOREIGN KEY (Inst_Endereco) REFERENCES SGC_Instituicao (Endereco)
)

CREATE TABLE SGC_ReferenciaMultibanca(
	ValorInscricao int NOT NULL,
	PRIMARY KEY (ValorInscricao)
)

CREATE TABLE SGC_Comprovativo(
	LocalizacaoEletronica varchar(128) NOT NULL,
	CustoInscricao int NOT NULL,
	PRIMARY KEY (LocalizacaoEletronica)
)

CREATE TABLE SGC_NaoEstudante(
	Email varchar(128) NOT NULL,
	RTM_Valor int NOT NULL,
	PRIMARY KEY (Email),
	FOREIGN KEY (RTM_Valor) REFERENCES SGC_ReferenciaMultibanca (ValorInscricao),
	FOREIGN KEY (Email) REFERENCES SGC_Participante (Email)
)

CREATE TABLE SGC_Estudante(
	Email varchar(128) NOT NULL,
	Comprovativo_LE varchar(128) NOT NULL,
	PRIMARY KEY (Email),
	FOREIGN KEY (Comprovativo_LE) REFERENCES SGC_Comprovativo (LocalizacaoEletronica),
	FOREIGN KEY (Email) REFERENCES SGC_Participante (Email)
)

CREATE TABLE SGC_Tem(
	Autor_Email varchar(128) NOT NULL,
	AC_NumRegisto int NOT NULL,
	PRIMARY KEY (Autor_Email, AC_NumRegisto),
	FOREIGN KEY (Autor_Email) REFERENCES SGC_Autor (Email),
	FOREIGN KEY (AC_NumRegisto) REFERENCES SGC_ArtigoCientifico (Num_Registro),
)