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
σ dependent > 2 ( γ employee.Fname, employee.Minit, employee.Lname; COUNT(dependent.Essn)→dependent (dependent ⨝ dependent.Essn = employee.Ssn employee))
```


### *h)* 

```
π employee.Fname, employee.Minit, employee.Lname σ dependent.Essn = null (dependent ⟗ dependent.Essn = employee.Ssn employee ⨝ employee.Ssn = department.Mgr_ssn department)
```


### *i)* 

```
π employee.Fname, employee.Minit, employee.Lname, employee.Address σ project.Plocation = 'Aveiro' and dept_location.Dlocation ≠ 'Aveiro' (employee ⨝ employee.Ssn = works_on.Essn works_on ⨝ works_on.Pno = project.Pnumber project ⨝ employee.Dno = dept_location.Dnumber dept_location)
```


## ​Problema 5.2

### *a)*

```
π fornecedor.nif, fornecedor.nome σ encomenda.numero = null (encomenda ⟗ encomenda.fornecedor = fornecedor.nif fornecedor)
```

### *b)* 

```
γ produto.codigo, produto.nome; AVG(item.unidades)→media_unidades (produto ⨝ produto.codigo = item.codProd item)
```


### *c)* 

```
γ ; AVG(unidadesPorEncomenda)→mediaProdutosPorEncomenda ( γ encomenda.numero; COUNT(item.codProd)→unidadesPorEncomenda (encomenda ⨝ encomenda.numero = item.numEnc item))
```


### *d)* 

```
γ fornecedor.nome, produto.nome; SUM(item.unidades)→quantidade (fornecedor ⨝ fornecedor.nif = encomenda.fornecedor encomenda ⨝ encomenda.numero = item.numEnc item ⨝ item.codProd = produto.codigo produto)
```


## ​Problema 5.3

### *a)*

```
π paciente.numUtente, paciente.nome σ prescricao.numPresc = null (prescricao ⟗ prescricao.numUtente = paciente.numUtente paciente)
```

### *b)* 

```
γ medico.especialidade; COUNT(prescricao.numPresc)→num_prescricoes (medico ⨝ medico.numSNS = prescricao.numMedico prescricao)
```


### *c)* 

```
γ farmacia.nome; COUNT(prescricao.numPresc)→num_prescricoes (farmacia ⨝ farmacia.nome = prescricao.farmacia prescricao)
```


### *d)* 

```
π presc_farmaco.nomeFarmaco σ presc_farmaco.numRegFarm = 906 and prescricao.farmacia = null (prescricao ⨝ prescricao.numPresc = presc_farmaco.numPresc presc_farmaco)
```

### *e)* 

```
γ farmacia.nome, farmaceutica.nome; COUNT(farmaco.nome)→num_farmaco (farmaco ⨝ farmaco.numRegFarm = farmaceutica.numReg farmaceutica ⨝ farmaceutica.numReg = presc_farmaco.numRegFarm presc_farmaco ⨝ presc_farmaco.numPresc = prescricao.numPresc prescricao ⨝ prescricao.farmacia = farmacia.nome farmacia)
```

### *f)* 

```
σ countMedicos > 1 ( γ paciente.numUtente, paciente.nome; COUNT(medico.numSNS)→countMedicos ( γ paciente.numUtente, paciente.nome, medico.numSNS; COUNT(prescricao.numMedico)→countMedicos (medico ⨝ medico.numSNS = prescricao.numMedico prescricao ⨝ prescricao.numUtente = paciente.numUtente paciente)))
```
