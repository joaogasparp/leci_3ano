--CREATE SCHEMA Gestao_Area_Servico
--go

-- Remover tabelas existentes
if object_id('GAS_Item', 'u') is not null
	drop table dbo.GAS_Item;
if object_id('GAS_Encomenda', 'u') is not null
	drop table dbo.GAS_Encomenda;
if object_id('GAS_Funcionario', 'u') is not null
	drop table dbo.GAS_Funcionario;
if object_id('GAS_Gerente', 'u') is not null
	drop table dbo.GAS_Gerente;
if object_id('GAS_Cliente', 'u') is not null
	drop table dbo.GAS_Cliente;
if object_id('GAS_Fornecedor', 'u') is not null
	drop table dbo.GAS_Fornecedor;
if object_id('GAS_Pessoa', 'u') is not null
	drop table dbo.GAS_Pessoa;
if object_id('GAS_BombaCombustivel', 'u') is not null
	drop table dbo.GAS_BombaCombustivel;
if object_id('GAS_Produto', 'u') is not null
	drop table dbo.GAS_Produto;
if object_id('GAS_Estoque', 'u') is not null
	drop table dbo.GAS_Estoque;
if object_id('GAS_Loja', 'u') is not null
	drop table dbo.GAS_Loja;
if object_id('GAS_EstacionamentoON', 'u') is not null
	drop table dbo.GAS_EstacionamentoON;
if object_id('GAS_Area_Servico', 'u') is not null
	drop table dbo.GAS_Area_Servico;

-- Criação das tabelas
CREATE TABLE GAS_Area_Servico(
	Num_Area int NOT NULL,
	Localizacao varchar(128) NOT NULL,
	PRIMARY KEY ( Num_Area )
)

CREATE TABLE GAS_EstacionamentoON(
	Num_Estacionamento int NOT NULL,
	Capacidade int NOT NULL,
	Taxa int NOT NULL,
	AS_Num_Area int NOT NULL,
	PRIMARY KEY ( Num_Estacionamento ),
	FOREIGN KEY ( AS_Num_Area ) REFERENCES GAS_Area_Servico ( Num_Area )
)

CREATE TABLE GAS_Loja(
	ID int NOT NULL,
	Nome varchar(128) NOT NULL,
	AS_Num_Area int NOT NULL,
	PRIMARY KEY ( ID ),
	FOREIGN KEY ( AS_Num_Area ) REFERENCES GAS_Area_Servico ( Num_Area )
)

CREATE TABLE GAS_Estoque(
	Num_Estoque int NOT NULL,
	Quantidade int NOT NULL,
	L_ID int NOT NULL,
	PRIMARY KEY ( Num_Estoque ),
	FOREIGN KEY ( L_ID ) REFERENCES GAS_Loja ( ID )
)

CREATE TABLE GAS_Produto(
	Codigo int NOT NULL,
	TaxaIva real NOT NULL,
	Quantidade int NOT NULL,
	Preco real NOT NULL,
	E_Num_Estoque int NOT NULL,
	PRIMARY KEY ( Codigo ),
	FOREIGN KEY ( E_Num_Estoque ) REFERENCES GAS_Estoque ( Num_Estoque )
)

CREATE TABLE GAS_BombaCombustivel(
	Num_Bomba int NOT NULL,
	Marca varchar(128) NOT NULL,
	Preco real NOT NULL,
	P_Codigo int NOT NULL,
	PRIMARY KEY ( Num_Bomba ),
	FOREIGN KEY ( P_Codigo ) REFERENCES GAS_Produto ( Codigo )
)

CREATE TABLE GAS_Pessoa(
	NIF int NOT NULL,
	Nome varchar(128) NOT NULL,
	Contacto int NOT NULL,
	AS_Num_Area int NOT NULL,
	PRIMARY KEY ( NIF ),
	FOREIGN KEY ( AS_Num_Area ) REFERENCES GAS_Area_servico ( Num_Area )
)

CREATE TABLE GAS_Fornecedor(
	NIF int NOT NULL,
	Marca varchar(128) NOT NULL,
	PRIMARY KEY ( NIF )
)

CREATE TABLE GAS_Cliente(
	NIF int NOT NULL,
	Classificacao_Credito varchar(128) NOT NULL,
	PRIMARY KEY ( NIF )
)

CREATE TABLE GAS_Gerente(
	NIF int NOT NULL,
	Gabinete int NOT NULL,
	PRIMARY KEY ( NIF )
)

CREATE TABLE GAS_Funcionario(
	NIF int NOT NULL,
	Cargo varchar(128) NOT NULL,
	G_NIF int NOT NULL,
	PRIMARY KEY ( NIF ),
	FOREIGN KEY ( G_NIF ) REFERENCES GAS_Gerente ( NIF )
)

CREATE TABLE GAS_Encomenda(
	Num_Encomenda int NOT NULL,
	Data_entrega date NOT NULL,
	F_NIF int NOT NULL,
	G_NIF int NOT NULL,
	PRIMARY KEY ( Num_Encomenda ),
	FOREIGN KEY ( F_NIF ) REFERENCES GAS_Fornecedor ( NIF ),
	FOREIGN KEY ( G_NIF ) REFERENCES GAS_Gerente ( NIF )
)

CREATE TABLE GAS_Item(
	ItemID int NOT NULL,
	Quantidade int NOT NULL,
	P_Codigo int NOT NULL,
	Enc_Num_Encomenda int NOT NULL,
	PRIMARY KEY ( ItemID ),
	FOREIGN KEY ( P_Codigo ) REFERENCES GAS_Produto ( Codigo ),
	FOREIGN KEY ( Enc_Num_Encomenda ) REFERENCES GAS_Encomenda ( Num_Encomenda )
)

-- Criação de índices
