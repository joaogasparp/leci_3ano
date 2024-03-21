# BD: Guião 5


## ​Problema 5.1
 
### *a)*

```
π Fname, Lname, Ssn, Pname (employee ⨝ Ssn = Essn works_on ⨝ Pno = Pnumber project)
```


### *b)* 

```
π employee.Fname, employee.Minit, employee.Lname (employee ⨝ employee.Super_ssn = Chefe.Ssn ρ Chefe σ Fname = 'Carlos' and Minit = 'D' and Lname = 'Gomes' employee)
```


### *c)* 

```
γ Pname; SUM(Hours)→totalHours (works_on ⨝ Pno = Pnumber project)
```


### *d)*

```
π employee.Fname, employee.Minit, employee.Lname ( σ employee.Dno = 3 and works_on.Hours > 20 and project.Pname = 'Aveiro Digital' (employee ⨝ employee.Ssn = works_on.Essn works_on ⨝ works_on.Pno = project.Pnumber project))
```


### *e)* 

```
π employee.Fname, employee.Minit, employee.Lname σ Essn = null (employee ⟕ Ssn = Essn works_on)
```


### *f)* 

```
γ department.Dname; AVG(employee.Salary)→mediaSalary ( σ employee.Sex = 'F' (employee ⨝ employee.Dno = department.Dnumber department))
```


### *g)* 

```
... Write here your answer ...
```


### *h)* 

```
... Write here your answer ...
```


### *i)* 

```
... Write here your answer ...
```


## ​Problema 5.2

### *a)*

```
... Write here your answer ...
```

### *b)* 

```
... Write here your answer ...
```


### *c)* 

```
... Write here your answer ...
```


### *d)* 

```
... Write here your answer ...
```


## ​Problema 5.3

### *a)*

```
... Write here your answer ...
```

### *b)* 

```
... Write here your answer ...
```


### *c)* 

```
... Write here your answer ...
```


### *d)* 

```
... Write here your answer ...
```

### *e)* 

```
... Write here your answer ...
```

### *f)* 

```
... Write here your answer ...
```
