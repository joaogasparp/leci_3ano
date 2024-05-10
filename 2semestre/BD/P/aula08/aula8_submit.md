# BD: Guião 8


## ​8.1. Complete a seguinte tabela.
Complete the following table.

| #    | Query                                                                                                      | Rows  | Cost  | Pag. Reads | Time (ms) | Index used | Index Op.            | Discussion |
| :--- | :--------------------------------------------------------------------------------------------------------- | :---- | :---- | :--------- | :-------- | :--------- | :------------------- | :--------- |
| 1    | SELECT * from Production.WorkOrder                                                                         | 72591 | 0.473 | 552 | 1020 | PK_WorkOrder_WorkOrderID | Clustered Index Scan |  |
| 2    | SELECT * from Production.WorkOrder where WorkOrderID=1234                                                  | 1 | 0.003 | 54 | 102 | PK_WorkOrder_WorkOrderID | Clustered Index Seek |  |
| 3.1  | SELECT * FROM Production.WorkOrder WHERE WorkOrderID between 10000 and 10010                               | 11 | 0.003 | 26 | 165 | PK_WorkOrder_WorkOrderID | Clustered Index Seek |  |
| 3.2  | SELECT * FROM Production.WorkOrder WHERE WorkOrderID between 1 and 72591                                   | 72591 | 0.473 | 554 | 955 | PK_WorkOrder_WorkOrderID | Clustered Index Seek |  |
| 4    | SELECT * FROM Production.WorkOrder WHERE StartDate = '2007-06-25'                                          | 0 | 0.473 | 554 | 141 | PK_WorkOrder_WorkOrderID | Clustered Index Scan |  |
| 5    | SELECT * FROM Production.WorkOrder WHERE ProductID = 757                                                   | 9 | 0.034 | 44 | 161 | IX_WorkOrder_ProductID and PK_WorkOrder_WorkOrderID | Index Seek and Key Lookup | There are 2 indexes. |
| 6.1  | SELECT WorkOrderID, StartDate FROM Production.WorkOrder WHERE ProductID = 757                              | 9 | 0.003 | 56 | 50 | IX_WorkOrder_ProductID and PK_WorkOrder_WorkOrderID | Index Seek |  |
| 6.2  | SELECT WorkOrderID, StartDate FROM Production.WorkOrder WHERE ProductID = 945                              | 1105 | 0.005 | 30 | 185 | IX_WorkOrder_ProductID | Index Seek |  |
| 6.3  | SELECT WorkOrderID FROM Production.WorkOrder WHERE ProductID = 945 AND StartDate = '2006-01-04'            | 0 | 0.005 | 32 | 36 | IX_WorkOrder_ProductID | Index Seek |  |
| 7    | SELECT WorkOrderID, StartDate FROM Production.WorkOrder WHERE ProductID = 945 AND StartDate = '2006-01-04' | 0 | 0.003 | 28 | 37 | IX_WorkOrder_ProductID | Index Seek |  |
| 8    | SELECT WorkOrderID, StartDate FROM Production.WorkOrder WHERE ProductID = 945 AND StartDate = '2006-01-04' | 0 | 0.003 | 28 | 41 | IX_WorkOrder_ProductID | Index Seek |  |

## ​8.2.

### a)

```
... Write here your answer ...
```

### b)

```
... Write here your answer ...
```

### c)

```
... Write here your answer ...
```

### d)

```
... Write here your answer ...
```

### e)

```
... Write here your answer ...
```

## ​8.3.

```
... Write here your answer ...
```
