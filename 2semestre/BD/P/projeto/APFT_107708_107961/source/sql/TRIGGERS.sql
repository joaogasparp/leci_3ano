-- Criação do trigger que verifica se a quantidade de um produto é negativa
CREATE TRIGGER CheckQuantidadeProduto
ON GAS_Produto
FOR INSERT, UPDATE
AS
BEGIN
    IF EXISTS (SELECT 1 FROM inserted WHERE Quantidade < 0)
    BEGIN
        RAISERROR ('A quantidade de um produto não pode ser negativa.', 16, 1);
        ROLLBACK TRANSACTION;
    END
END;
GO

-- Criação do trigger que verifica se a capacidade de um estacionamento é negativa
CREATE TRIGGER CheckCapacidadeEstacionamento
ON GAS_EstacionamentoON
FOR INSERT, UPDATE
AS
BEGIN
    IF EXISTS (SELECT 1 FROM inserted WHERE Capacidade < 0)
    BEGIN
        RAISERROR ('A capacidade do estacionamento não pode ser negativa.', 16, 1);
        ROLLBACK TRANSACTION;
    END
END;
GO


-- Criação do trigger que verifica se a quantidade de um produto é negativa
CREATE TRIGGER CheckQuantidadeItem
ON GAS_Item
FOR INSERT, UPDATE
AS
BEGIN
    IF EXISTS (SELECT 1 FROM inserted WHERE Quantidade < 0)
    BEGIN
        RAISERROR ('A quantidade de um item na encomenda não pode ser negativa.', 16, 1);
        ROLLBACK TRANSACTION;
    END
END;
GO
