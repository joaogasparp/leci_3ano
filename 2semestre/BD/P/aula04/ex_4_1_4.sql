--CREATE SCHEMA Sistema_Prescricao_Eletronica_Medicamentos;
--go


if object_id('SPEM_Vende', 'u') is not null
	drop table dbo.SPEM_Vende;
if object_id('SPEM_Tem', 'u') is not null
	drop table dbo.SPEM_Tem;
if object_id('SPEM_Farmaco', 'u') is not null
	drop table dbo.SPEM_Farmaco;
if object_id('SPEM_Farmaceutica', 'u') is not null
	drop table dbo.SPEM_Farmaceutica;
if object_id('SPEM_Farmacia', 'u') is not null
	drop table dbo.SPEM_Farmacia;
if object_id('SPEM_Prescricao', 'u') is not null
	drop table dbo.SPEM_Prescricao;
if object_id('SPEM_Paciente', 'u') is not null
	drop table dbo.SPEM_Paciente;
if object_id('SPEM_Medico', 'u') is not null
	drop table dbo.SPEM_Medico;
if object_id('SPEM_Especializacao', 'u') is not null
	drop table dbo.SPEM_Especializacao;


CREATE TABLE SPEM_Especializacao(
	Codigo int NOT NULL,
	Designacao varchar(128) NOT NULL,
	PRIMARY KEY (Codigo)
)

CREATE TABLE SPEM_Medico(
	SNS int NOT NULL,
	Nome varchar(128) NOT NULL,
	E_Codigo int NOT NULL,
	PRIMARY KEY (SNS),
	FOREIGN KEY (E_Codigo) REFERENCES SPEM_Especializacao (Codigo)
)

CREATE TABLE SPEM_Paciente(
	Num_Utente int NOT NULL,
	Nome varchar(128) NOT NULL,
	Endereco varchar(128) NOT NULL,
	Data_Nascimento date NOT NULL,
	PRIMARY KEY (Num_Utente)
)

CREATE TABLE SPEM_Prescricao(
	NUP int NOT NULL,
	Datas Date NOT NULL,
	Medico_SNS int NOT NULL,
	Paciente_NU int NOT NULL,
	PRIMARY KEY (NUP),
	FOREIGN KEY (Medico_SNS) REFERENCES SPEM_Medico (SNS),
	FOREIGN KEY (Paciente_NU) REFERENCES SPEM_Paciente (Num_Utente),
)

CREATE TABLE SPEM_Farmacia(
	NIF int NOT NULL,
	Nome varchar(128) NOT NULL,
	Telefone int NOT NULL,
	Morada varchar(128) NOT NULL,
	Data_Processo date NOT NULL,
	Prescricao_NUP int NOT NULL,
	PRIMARY KEY (NIF),
	FOREIGN KEY (Prescricao_NUP) REFERENCES SPEM_Prescricao (NUP)
)

CREATE TABLE SPEM_Farmaceutica(
	NRN int NOT NULL,
	Nome varchar(128) NOT NULL,
	Endereco varchar(128) NOT NULL,
	Telefone int NOT NULL,
	PRIMARY KEY (NRN)
)

CREATE TABLE SPEM_Farmaco(
	NUF int NOT NULL,
	Nome_Comercial varchar(128) NOT NULL,
	Formula varchar(128) NOT NULL,
	Farmaceutica_NRN int NOT NULL,
	PRIMARY KEY (NUF),
	FOREIGN KEY (Farmaceutica_NRN) REFERENCES SPEM_Farmaceutica (NRN)
)

CREATE TABLE SPEM_Tem(
	Farmaco_NUF int NOT NULL,
	Prescricao_NUP int NOT NULL,
	PRIMARY KEY (Farmaco_NUF, Prescricao_NUP),
	FOREIGN KEY (Farmaco_NUF) REFERENCES SPEM_Farmaco (NUF),
	FOREIGN KEY (Prescricao_NUP) REFERENCES SPEM_Prescricao (NUP)
)

CREATE TABLE SPEM_Vende(
	Farmaco_NUF int NOT NULL,
	Farmacia_NIF int NOT NULL,
	PRIMARY KEY (Farmaco_NUF, Farmacia_NIF),
	FOREIGN KEY (Farmaco_NUF) REFERENCES SPEM_Farmaco (NUF),
	FOREIGN KEY (Farmacia_NIF) REFERENCES SPEM_Farmacia (NIF)
)

