--CREATE SCHEMA Get_Stock
--go

if object_id('GS_item', 'u') is not null
	drop table dbo.GS_item;
if object_id('GS_encomenda', 'u') is not null
	drop table dbo.GS_encomenda;
if object_id('GS_produto', 'u') is not null
	drop table dbo.GS_produto;
if object_id('GS_fornecedor', 'u') is not null
	drop table dbo.GS_fornecedor;
if object_id('GS_tipo_fornecedor', 'u') is not null
	drop table dbo.GS_tipo_fornecedor;

CREATE TABLE GS_tipo_fornecedor (
    codigo INT PRIMARY KEY,
    designacao VARCHAR(255) NOT NULL
);

CREATE TABLE GS_fornecedor (
    nif INT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    fax INT,
    endereco VARCHAR(255),
    condpag INT,
    tipo INT,
    FOREIGN KEY (tipo) REFERENCES GS_tipo_fornecedor(codigo)
);

CREATE TABLE GS_produto (
    codigo INT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    preco DECIMAL(10, 2),
    iva DECIMAL(5, 2),
    unidades INT
);

CREATE TABLE GS_encomenda (
    numero INT PRIMARY KEY,
    [data] DATE,
    fornecedor INT,
    FOREIGN KEY (fornecedor) REFERENCES GS_fornecedor(nif)
);

CREATE TABLE GS_item (
    numEnc INT,
    codProd INT,
    unidades INT,
    PRIMARY KEY (numEnc, codProd),
    FOREIGN KEY (numEnc) REFERENCES GS_encomenda(numero),
    FOREIGN KEY (codProd) REFERENCES GS_produto(codigo)
);