# BD: Guião 9

## ​9.1

### _a)_

```
IF EXISTS (SELECT * FROM sys.objects WHERE type = 'P' AND name = 'stored_procedure_a')
BEGIN
    DROP PROCEDURE stored_procedure_a
END
GO
CREATE PROC stored_procesure_a @Ssn INT AS
    BEGIN
        UPDATE dbo.C_department SET Mgr_ssn=NULL WHERE Mgr_ssn=@Ssn;
        UPDATE dbo.C_employee SET Super_ssn=NULL WHERE Super_ssn=@Ssn;
        DELETE FROM dbo.C_dependent WHERE Essn=@Ssn;
        DELETE FROM dbo.C_works_on WHERE Essn=@Ssn;
        DELETE FROM dbo.C_employee WHERE Ssn=@Ssn;
    END

Uma vez que damos delete ao essn e ao ssn, temos de definir o mgr_ssn do department e da employee que queriamos apagar como NULL.
```

### _b)_

```
IF EXISTS (SELECT * FROM sys.objects WHERE type = 'P' AND name = 'stored_procedure_b')
BEGIN
    DROP PROCEDURE stored_procedure_b
END
GO
CREATE PROC stored_procedure_b (@Mgr_ssn INT OUTPUT, @years INT OUTPUT) AS
    BEGIN
        SELECT dbo.C_employee.* FROM dbo.C_employee INNER JOIN dbo.C_department ON dbo.C_employee.Ssn = dbo.C_department.Mgr_ssn;
        SELECT @Mgr_ssn = dbo.C_department.Mgr_ssn, @years = DATEDIFF(year, dbo.C_department.Mgr_start_date, GETDATE())
            FROM dbo.C_department
            WHERE Mgr_start_date = (SELECT MIN(Mgr_start_date) from dbo.C_department);
    END
```

### _c)_

```
GO
CREATE TRIGGER trigger_c ON dbo.C_department INSTEAD OF INSERT, UPDATE AS
    BEGIN
        IF (SELECT count(*) FROM inserted) > 0
            BEGIN
                DECLARE @Ssn AS INT;
                SELECT @Ssn = Mgr_ssn FROM inserted;
                IF (@Ssn) IS NULL OR ((SELECT count(*) FROM dbo.C_employee WHERE Ssn = @Ssn) = 0)
                    RAISERROR('Employee does not exist!', 16, 1);
                ELSE
                    BEGIN
                        IF (SELECT COUNT(Dnumber) FROM dbo.C_department WHERE Mgr_ssn = @Ssn) >=1
                            RAISERROR('Employee already manages one department!', 16, 1);
                        ELSE
                            INSERT INTO dbo.C_department SELECT * FROM inserted;
                    END
            END
    END
```

### _d)_

```
GO
CREATE TRIGGER trigger_d ON dbo.C_employee AFTER INSERT, UPDATE AS
    BEGIN
        DECLARE @Ssn AS INT;
        DECLARE @Salary AS INT;
        DECLARE @Dno AS INT;
        DECLARE @Mgr_salary AS INT;

        SELECT @Ssn = inserted.Ssn, @Salary = inserted.Salary, @Dno = inserted.Dno FROM inserted;
        SELECT @Mgr_salary = dbo.C_employee.Salary
            FROM dbo.C_department INNER JOIN dbo.C_employee ON dbo.C_department.Mgr_Ssn=dbo.C_employee.Ssn
            WHERE @Dno = dbo.C_department.Dnumber;

        IF @Salary > @Mgr_salary
        BEGIN
            UPDATE dbo.C_employee SET dbo.C_employee.Salary = (@Mgr_salary - 1) WHERE dbo.C_employee.Ssn = @Ssn
        END
    END
```

### _e)_

```
GO
CREATE FUNCTION Company.UDF_e (@Ssn INT) RETURNS @Table TABLE([name] VARCHAR(45), [location] VARCHAR(15)) AS
	BEGIN
		INSERT @Table
			SELECT Company.Project.Pname, Company.Project.Plocation FROM Company.Project
				INNER JOIN Company.Works_on ON Company.Works_on.Pno=Company.Project.Pnumber
				WHERE Works_on.Essn = @Ssn
		RETURN;
	END
```

### _f)_

```
GO
CREATE FUNCTION dbo.UDF_f (@Dno INT) RETURNS @Table TABLE ([ssn] INT, [fname] VARCHAR(15), [lname] VARCHAR(15)) AS
    BEGIN
        INSERT @Table
            SELECT dbo.C_employee.Ssn, dbo.C_employee.Fname, dbo.C_employee.Lname
            FROM dbo.C_employee JOIN (SELECT Dno, AVG(Salary) as avg_sal
                                        FROM dbo.C_department, dbo.C_employee
                                        WHERE Dno=Dnumber
                                        GROUP BY Dno) AS dep_avg_sal
            ON dep_avg_sal.Dno=dbo.C_employee.Dno AND Salary > avg_sal AND dbo.C_employee.Dno = @Dno;
        RETURN;
    END
```

### _g)_

```
CREATE FUNCTION employeeDeptHighAverage (@Dnum INT)
RETURNS @ProjectsBudget TABLE
(
    pname NVARCHAR(50),
    pnumber INT,
    plocation NVARCHAR(50),
    dnum INT,
    budget DECIMAL(18, 2),
    totalbudget DECIMAL(18, 2)
)
AS
BEGIN
    DECLARE @pname NVARCHAR(50), @pnumber INT, @plocation NVARCHAR(50), @EmployeeCount INT, @budget DECIMAL(18, 2), @totalbudget DECIMAL(18, 2)

    SET @totalbudget = 0

    DECLARE project_cursor CURSOR FOR
    SELECT pname, pnumber, plocation
    FROM dbo.C_project
    WHERE dnum = @Dnum

    OPEN project_cursor

    FETCH NEXT FROM project_cursor INTO @pname, @pnumber, @plocation

    WHILE @@FETCH_STATUS = 0
    BEGIN
        SET @EmployeeCount = (SELECT COUNT(*) FROM dbo.C_works_on WHERE pno = @pnumber)

        SET @budget = @EmployeeCount * (SELECT AVG(Salary) FROM dbo.C_employee WHERE Dno = @Dnum)
        SET @totalbudget = @totalbudget + @budget

        INSERT INTO @ProjectsBudget
        VALUES (@pname, @pnumber, @plocation, @Dnum, @budget, @totalbudget)

        FETCH NEXT FROM project_cursor INTO @pname, @pnumber, @plocation
    END

    CLOSE project_cursor
    DEALLOCATE project_cursor

    RETURN
END
```

### _h)_

```
CREATE TRIGGER trg_after_delete_on_department
AFTER DELETE ON dbo.Department
FOR EACH ROW
BEGIN
    IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'dbo' AND TABLE_NAME = 'department_deleted')
    BEGIN
        CREATE TABLE dbo.department_deleted (Dname NVARCHAR(50), Dnumber INT, Mgr_ssn INT, Mgr_start_date DATE);
    END

    INSERT INTO dbo.department_deleted
    SELECT * FROM deleted;
END;

CREATE TRIGGER trg_instead_of_delete_on_department
INSTEAD OF DELETE ON dbo.Department
FOR EACH ROW
BEGIN
    IF NOT EXISTS (SELECT * FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'dbo' AND TABLE_NAME = 'department_deleted')
    BEGIN
        CREATE TABLE dbo.department_deleted (Dname NVARCHAR(50), Dnumber INT, Mgr_ssn INT, Mgr_start_date DATE);
    END

    INSERT INTO dbo.department_deleted
    SELECT * FROM deleted;

    DELETE FROM dbo.Department WHERE Dnumber IN (SELECT Dnumber FROM deleted);
END;

Vantagens e Desvantagens:
AFTER DELETE: A vantagem é que ele garante que a operação de DELETE foi bem-sucedida antes de mover os dados para a tabela department_deleted. A desvantagem é que, se houver um erro ao mover os dados para a tabela department_deleted, o departamento já terá sido eliminado da tabela Department.

INSTEAD OF DELETE: A vantagem é que ele permite controlar a operação de DELETE e só a executar se a movimentação dos dados para a tabela department_deleted for bem-sucedida. A desvantagem é que ele pode ser mais complexo de implementar, pois precisamos de gerenciar a operação de DELETE manualmente.
```

### _i)_

```
Stored Procedures – Mais Valias
Extensibilidade
Performance
Usabilidade
Integridade de Dados:
Segurança

UDFs – Mais Valias
Os mesmos benefícios dos Stored procedures.
São igualmente compilados e otimizados.
Podem ser utilizadas para incorporar lógica complexa dentro de uma consulta.
Oferecem os mesmo benefícios das vistas pois podem ser utilizados como fonte de dados nas consultas e nas cláusulas WHERE/HAVING.
Acresce o fato de aceitar parâmetros, algo impossível em views.
Criação de novas funções contendo expressões complexas.

SP versus UDF
Stored Procedures: retornam zero, um ou múltiplos valores; possuem parâmetros de entrada/saída; não podem usar as instruções SELECT/WHERE/HAVING; podem chamar outras Stored Procedures; possuem tratamento de exceções; suportam transações.
UDFs: retornam um único valor (escalar ou tabela); possuem parâmetros de entrada; podem usar as instruções SELECT/WHERE/HAVING; não podem chamar Stored Procedures; não possuem tratamento de exceções; não suportam transações.

Exemplo: CREATE Storage Procedure devolver um conjunto de registos (record-set)
go
CREATE PROCEDURE dbo.CategoryList
AS
SELECT ProductCategoryName, ProductCategoryDescription
FROM dbo.ProductCategory;
go

Exemplo: Criação e Utilização de UDF Escalar
CREATE FUNCTION dbo.Revenue_Day (@Date datetime) RETURNS money
AS
BEGIN
DECLARE @total money
SELECT @total = sum(sali_Quantity * sali_price)
FROM Sales_Orders s, Sales_Orders_Items si
WHERE s.sal_number = si.sal_number and year(sal_date) = year(@Date)
and month(sal_date) = month(@Date) and day(sal_date)= day(@Date)
RETURN @total
END
GO
SELECT dbo.Revenue_Day(GETDATE())
```
