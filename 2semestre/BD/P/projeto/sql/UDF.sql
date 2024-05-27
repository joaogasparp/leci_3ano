-- Obter a quantidade total de um produto
CREATE FUNCTION dbo.GetQuantidadeTotalProduto(@Codigo int)
RETURNS int
AS
BEGIN
    DECLARE @QuantidadeTotal int;

    SELECT @QuantidadeTotal = SUM(Quantidade)
    FROM GAS_Produto
    WHERE Codigo = @Codigo;

    RETURN ISNULL(@QuantidadeTotal, 0);
END;
GO

-- Obter a capacidade total de um estacionamento
CREATE FUNCTION dbo.GetCapacidadeTotalEstacionamento(@AS_Num_Area int)
RETURNS int
AS
BEGIN
    DECLARE @CapacidadeTotal int;

    SELECT @CapacidadeTotal = SUM(Capacidade)
    FROM GAS_EstacionamentoON
    WHERE AS_Num_Area = @AS_Num_Area;

    RETURN ISNULL(@CapacidadeTotal, 0);
END;
GO

-- Obter o total de funcionários por gerente
CREATE FUNCTION dbo.GetTotalFuncionariosPorGerente(@G_NIF int)
RETURNS int
AS
BEGIN
    DECLARE @TotalFuncionarios int;

    SELECT @TotalFuncionarios = COUNT(*)
    FROM GAS_Funcionario
    WHERE G_NIF = @G_NIF;

    RETURN ISNULL(@TotalFuncionarios, 0);
END;
GO
