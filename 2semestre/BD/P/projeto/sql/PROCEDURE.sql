-- Inserir um nova área de serviço
CREATE PROCEDURE dbo.InserirAreaServico
    @Num_Area int,
    @Localizacao varchar(128)
AS
BEGIN
    IF NOT EXISTS (SELECT 1 FROM GAS_Area_Servico WHERE Num_Area = @Num_Area)
    BEGIN
        INSERT INTO GAS_Area_Servico(Num_Area, Localizacao)
        VALUES (@Num_Area, @Localizacao);
    END
    ELSE
    BEGIN
        PRINT 'A área de serviço com o número especificado já existe.';
    END
END;
GO

-- Inserir um novo estacionamento
CREATE PROCEDURE dbo.InserirEstacionamentoON
    @Num_Estacionamento int,
    @Capacidade int,
    @Taxa int,
    @AS_Num_Area int
AS
BEGIN
    IF NOT EXISTS (SELECT 1 FROM GAS_EstacionamentoON WHERE Num_Estacionamento = @Num_Estacionamento)
    BEGIN
        INSERT INTO GAS_EstacionamentoON(Num_Estacionamento, Capacidade, Taxa, AS_Num_Area)
        VALUES (@Num_Estacionamento, @Capacidade, @Taxa, @AS_Num_Area);
    END
    ELSE
    BEGIN
        PRINT 'O estacionamento com o número especificado já existe.';
    END
END;
GO

-- Inserir uma nova loja
CREATE PROCEDURE dbo.InserirLoja
    @ID int,
    @Nome varchar(128),
    @AS_Num_Area int
AS
BEGIN
    IF NOT EXISTS (SELECT 1 FROM GAS_Loja WHERE ID = @ID)
    BEGIN
        INSERT INTO GAS_Loja(ID, Nome, AS_Num_Area)
        VALUES (@ID, @Nome, @AS_Num_Area);
    END
    ELSE
    BEGIN
        PRINT 'A loja com o ID especificado já existe.';
    END
END;
GO
