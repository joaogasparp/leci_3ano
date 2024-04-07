--CREATE SCHEMA Prescricao
--go

if object_id('P_presc_farmaco', 'u') is not null
	drop table dbo.P_presc_farmaco;
if object_id('P_prescricao', 'u') is not null
	drop table dbo.P_prescricao;
if object_id('P_farmaco', 'u') is not null
	drop table dbo.P_farmaco;
if object_id('P_farmaceutica', 'u') is not null
	drop table dbo.P_farmaceutica;
if object_id('P_farmacia', 'u') is not null
	drop table dbo.P_farmacia;
if object_id('P_paciente', 'u') is not null
	drop table dbo.P_paciente;
if object_id('P_medico', 'u') is not null
	drop table dbo.P_medico;

CREATE TABLE P_medico (
    numSNS INT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    especialidade VARCHAR(255) NOT NULL
);

CREATE TABLE P_paciente (
    numUtente INT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    dataNasc DATE NOT NULL,
    endereco VARCHAR(255) NOT NULL
);

CREATE TABLE P_farmacia (
    nome VARCHAR(255) PRIMARY KEY,
    telefone INT NOT NULL,
    endereco VARCHAR(255) NOT NULL
);

CREATE TABLE P_farmaceutica (
    numReg INT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    endereco VARCHAR(255) NOT NULL
);

CREATE TABLE P_farmaco (
    numRegFarm INT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    formula VARCHAR(255) NOT NULL
);

CREATE TABLE P_prescricao (
    numPresc INT PRIMARY KEY,
    numUtente INT,
    numMedico INT,
    farmacia VARCHAR(255),
    dataProc DATE,
    FOREIGN KEY (numUtente) REFERENCES P_paciente(numUtente),
    FOREIGN KEY (numMedico) REFERENCES P_medico(numSNS),
    FOREIGN KEY (farmacia) REFERENCES P_farmacia(nome)
);

CREATE TABLE P_presc_farmaco (
    numPresc INT,
    numRegFarm INT,
    nomeFarmaco VARCHAR(255),
    FOREIGN KEY (numPresc) REFERENCES P_prescricao(numPresc),
    FOREIGN KEY (numRegFarm) REFERENCES P_farmaco(numRegFarm)
);
