# BD: Guião 8


## ​8.1. Complete a seguinte tabela.
Complete the following table.

| #    | Query                                                                                                      | Rows  | Cost  | Pag. Reads | Time (ms) | Index used | Index Op.            | Discussion |
| :--- | :--------------------------------------------------------------------------------------------------------- | :---- | :---- | :--------- | :-------- | :--------- | :------------------- | :--------- |
| 1    | SELECT * from Production.WorkOrder                                                                         | 72591 | 0.473 | 552 | 1020 | WorkOrderID (PK) | Clustered Index Scan |  |
| 2    | SELECT * from Production.WorkOrder where WorkOrderID=1234                                                  | 1 | 0.003 | 54 | 102 | WorkOrderID (PK) | Clustered Index Seek |  |
| 3.1  | SELECT * FROM Production.WorkOrder WHERE WorkOrderID between 10000 and 10010                               | 11 | 0.003 | 26 | 165 | WorkOrderID (PK) | Clustered Index Seek |  |
| 3.2  | SELECT * FROM Production.WorkOrder WHERE WorkOrderID between 1 and 72591                                   | 72591 | 0.473 | 554 | 955 | WorkOrderID (PK) | Clustered Index Seek |  |
| 4    | SELECT * FROM Production.WorkOrder WHERE StartDate = '2007-06-25'                                          | 55 | 0.473 | 554 | 141 | WorkOrderID (PK) | Clustered Index Scan |  |
| 5    | SELECT * FROM Production.WorkOrder WHERE ProductID = 757                                                   | 9 | 0.034 | 44 | 161 | ProductID | Non Clustered Index Seek |  |
| 6.1  | SELECT WorkOrderID, StartDate FROM Production.WorkOrder WHERE ProductID = 757                              | 9 | 0.003 | 56 | 50 | ProductID Covered (StartDate) | Non Clustered Index Seek |  |
| 6.2  | SELECT WorkOrderID, StartDate FROM Production.WorkOrder WHERE ProductID = 945                              | 1005 | 0.005 | 30 | 185 | ProductID Covered (StartDate) | Non Clustered Index Seek |  |
| 6.3  | SELECT WorkOrderID FROM Production.WorkOrder WHERE ProductID = 945 AND StartDate = '2006-01-04'            | 1 | 0.005 | 32 | 36 | ProductID Covered (StartDate) | Non Clustered Index Seek |  |
| 7    | SELECT WorkOrderID, StartDate FROM Production.WorkOrder WHERE ProductID = 945 AND StartDate = '2006-01-04' | 1 | 0.003 | 28 | 37 | ProductID and StartDate | Non Clustered Index Seek 2x |  |
| 8    | SELECT WorkOrderID, StartDate FROM Production.WorkOrder WHERE ProductID = 945 AND StartDate = '2006-01-04' | 1 | 0.003 | 28 | 41 | Composite (ProductID, StartDate) | Non Clustered Index Seek |  |

## ​8.2.

### a)

```
ALTER TABLE mytemp
ADD PRIMARY KEY CLUSTERED (rid);
```

### b)

```
Fragmentação: 98.8%
Ocupação: 69.9%
```

### c)

```
Fillfactor: 65%
62303 ms
Fillfactor: 80%
62827 ms
Fillfactor: 90%
66938 ms
```

### d)

```
Fillfactor: 65%
55264 ms
Fillfactor: 80%
55789 ms
Fillfactor: 90%
55357 ms
```

### e)

```
76876 ms
Visto que existem mais indices que antes, a performance de inserção tende a piorar tal como expectado.
```

## ​8.3.

```
i) Primary Index em Employee(Ssn)
ii) Secondary Index em Employee(Fname, Lname)
iii) Clustered Index em Employee(Dno)
iv) Clustered Index em Works_On(Pno)
v) Clustered Index em Dependent(Essn)
vi) Clustered Index em Project(Dnum)
```
