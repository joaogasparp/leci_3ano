# BD: Guião 6

## Problema 6.1

### *a)* Todos os tuplos da tabela autores (authors);

```
SELECT *
	FROM authors;
```

### *b)* O primeiro nome, o último nome e o telefone dos autores;

```
SELECT au_lname, au_fname, phone
	FROM authors;
```

### *c)* Consulta definida em b) mas ordenada pelo primeiro nome (ascendente) e depois o último nome (ascendente); 

```
SELECT au_lname , au_fname, phone
	FROM authors
	ORDER BY au_lname ASC, au_fname ASC;
```

### *d)* Consulta definida em c) mas renomeando os atributos para (first_name, last_name, telephone); 

```
SELECT au_lname AS first_name, au_fname AS last_name, phone AS telephone
	FROM authors
	ORDER BY au_lname ASC, au_fname ASC;
```

### *e)* Consulta definida em d) mas só os autores da Califórnia (CA) cujo último nome é diferente de ‘Ringer’; 

```
SELECT au_lname AS first_name, au_fname AS last_name, phone AS telephone
	FROM authors
	WHERE state='CA' AND au_fname != 'Ringer'
	ORDER BY au_lname ASC, au_fname ASC;
```

### *f)* Todas as editoras (publishers) que tenham ‘Bo’ em qualquer parte do nome; 

```
SELECT pub_name
	FROM publishers
	WHERE pub_name LIKE '%Bo%';
```

### *g)* Nome das editoras que têm pelo menos uma publicação do tipo ‘Business’; 

```
SELECT DISTINCT pub.pub_name, titles.type
	FROM publishers AS pub JOIN titles ON pub.pub_id = titles.pub_id
	WHERE titles.type = 'business';
```

### *h)* Número total de vendas de cada editora; 

```
SELECT pub.pub_name, SUM(qty) AS quantity
	FROM publishers AS pub JOIN titles ON pub.pub_id = titles.pub_id 
	JOIN sales ON titles.title_id = sales.title_id
	GROUP BY pub.pub_name;
```

### *i)* Número total de vendas de cada editora agrupado por título; 

```
SELECT pub.pub_name, titles.title_id, SUM(qty) AS quantity
	FROM publishers AS pub JOIN titles ON pub.pub_id = titles.pub_id 
	JOIN sales ON titles.title_id = sales.title_id
	GROUP BY pub.pub_name, titles.title_id;
```

### *j)* Nome dos títulos vendidos pela loja ‘Bookbeat’; 

```
SELECT titles.title
	FROM titles JOIN sales ON titles.title_id = sales.title_id
	JOIN stores ON sales.stor_id = stores.stor_id
	WHERE stores.stor_name = 'Bookbeat';
```

### *k)* Nome de autores que tenham publicações de tipos diferentes; 

```
SELECT authors.au_id, authors.au_lname, authors.au_fname
	FROM authors JOIN titleauthor ON authors.au_id = titleauthor.au_id
	JOIN titles ON titleauthor.title_id = titles.title_id
	GROUP BY authors.au_id, authors.au_lname, authors.au_fname
	HAVING COUNT(titles.type)  > 1;
```

### *l)* Para os títulos, obter o preço médio e o número total de vendas agrupado por tipo (type) e editora (pub_id);

```
SELECT titles.type, pub.pub_id, AVG(price) AS meanPrice, SUM(qty) AS quantity
	FROM publishers AS pub JOIN titles ON pub.pub_id = titles.pub_id 
	JOIN sales ON titles.title_id = sales.title_id
	GROUP BY titles.type, pub.pub_id;
```

### *m)* Obter o(s) tipo(s) de título(s) para o(s) qual(is) o máximo de dinheiro “à cabeça” (advance) é uma vez e meia superior à média do grupo (tipo);

```
SELECT titles.type, AVG(advance) AS meanAdvance, MAX(advance) as maxAdvance
	FROM publishers AS pub JOIN titles ON pub.pub_id = titles.pub_id 
	JOIN sales ON titles.title_id = sales.title_id
	GROUP BY titles.type
	HAVING AVG(advance) * 1.5 > MAX(advance);
```

### *n)* Obter, para cada título, nome dos autores e valor arrecadado por estes com a sua venda;

```
SELECT titles.title_id, titles.title, authors.au_fname, authors.au_lname, (sales.qty * titles.price) AS valorTotal 
	FROM authors JOIN titleauthor ON authors.au_id = titleauthor.au_id
	JOIN titles ON titleauthor.title_id = titles.title_id
	JOIN sales ON titles.title_id = sales.title_id;
```

### *o)* Obter uma lista que incluía o número de vendas de um título (ytd_sales), o seu nome, a faturação total, o valor da faturação relativa aos autores e o valor da faturação relativa à editora;

```
SELECT title, ytd_sales, (price * ytd_sales) AS facturacao, 
(price * ytd_sales * titles.royalty / 100) AS auths_revenue, 
((price * ytd_sales)-(price * ytd_sales * titles.royalty / 100)) AS publisher_revenue 
    FROM titles
    ORDER BY title;
```

### *p)* Obter uma lista que incluía o número de vendas de um título (ytd_sales), o seu nome, o nome de cada autor, o valor da faturação de cada autor e o valor da faturação relativa à editora;

```
SELECT title, ytd_sales, (au_fname + ' ' + au_lname) AS author,
(price * ytd_sales * titles.royalty / 100) * royaltyper/100 AS auths_revenue, 
((price * ytd_sales)-(price * ytd_sales * titles.royalty / 100)) AS publisher_revenue 
    FROM titles JOIN titleauthor ON titles.title_id = titleauthor.title_id
	JOIN authors ON titleauthor.au_id = authors.au_id
    ORDER BY title, author;
```

### *q)* Lista de lojas que venderam pelo menos um exemplar de todos os livros;

```
SELECT s.stor_name, COUNT(DISTINCT t.title_id) AS numTitles
	FROM titles AS t JOIN sales ON t.title_id = sales.title_id
	JOIN stores AS s ON sales.stor_id = s.stor_id
	GROUP BY s.stor_name
	HAVING COUNT(DISTINCT t.title_id) = (SELECT COUNT(DISTINCT title_id) FROM titles);

```

### *r)* Lista de lojas que venderam mais livros do que a média de todas as lojas;

```
SELECT s.stor_name, SUM(sal.qty) AS numSales
	FROM sales AS sal 
	JOIN stores AS s ON sal.stor_id = s.stor_id
	GROUP BY s.stor_name
	HAVING SUM(sal.qty) > (
		SELECT AVG(sub.qty)
		FROM (
			SELECT SUM(qty) AS qty
			FROM sales
			GROUP BY stor_id
		) AS sub
	);
```

### *s)* Nome dos títulos que nunca foram vendidos na loja “Bookbeat”;

```
SELECT title, stor_name
	FROM titles JOIN sales ON titles.title_id = sales.title_id
	JOIN stores ON sales.stor_id = stores.stor_id 
	WHERE title NOT IN ( SELECT title
							FROM titles JOIN sales ON titles.title_id = sales.title_id
							JOIN stores ON sales.stor_id = stores.stor_id 
							WHERE stor_name = 'Bookbeat');
```

### *t)* Para cada editora, a lista de todas as lojas que nunca venderam títulos dessa editora; 

```
SELECT pub_name, stor_name
FROM publishers CROSS JOIN stores
WHERE NOT EXISTS (
    SELECT pub_name, stor_name
    FROM publishers AS p
    JOIN titles AS t ON p.pub_id = t.pub_id
    JOIN sales AS s ON t.title_id = s.title_id
    JOIN stores AS st ON s.stor_id = st.stor_id
    WHERE p.pub_name = publishers.pub_name AND st.stor_name = stores.stor_name
)
ORDER BY pub_name;
```

## Problema 6.2

### ​5.1

#### a) SQL DDL Script
 
[a) SQL DDL File](ex_6_2_1_ddl.sql "SQLFileQuestion")

#### b) Data Insertion Script

[b) SQL Data Insertion File](ex_6_2_1_data.sql "SQLFileQuestion")

#### c) Queries

##### *a)*

```
SELECT employee.Fname, employee.Lname, employee.Ssn, project.Pname
FROM employee
JOIN works_on ON employee.Ssn = works_on.Essn
JOIN project ON works_on.Pno = project.Pnumber;
```

##### *b)* 

```
SELECT employee.Fname, employee.Minit, employee.Lname
FROM employee
JOIN (SELECT Ssn FROM employee WHERE Fname = 'Carlos' AND Minit = 'D' AND Lname = 'Gomes') AS Chefe ON employee.Super_ssn = Chefe.Ssn;
```

##### *c)* 

```
SELECT project.Pname, SUM(works_on.Hours) AS totalHours
FROM works_on
JOIN project ON works_on.Pno = project.Pnumber
GROUP BY project.Pname;
```

##### *d)* 

```
SELECT employee.Fname, employee.Minit, employee.Lname
FROM employee
JOIN works_on ON employee.Ssn = works_on.Essn
JOIN project ON works_on.Pno = project.Pnumber
WHERE employee.Dno = 3 AND works_on.Hours > 20 AND project.Pname = 'Aveiro Digital';
```

##### *e)* 

```
SELECT employee.Fname, employee.Minit, employee.Lname
FROM employee
LEFT JOIN works_on ON employee.Ssn = works_on.Essn
WHERE works_on.Essn IS NULL;
```

##### *f)* 

```
SELECT department.Dname, AVG(employee.Salary) AS mediaSalary
FROM employee
JOIN department ON employee.Dno = department.Dnumber
WHERE employee.Sex = 'F'
GROUP BY department.Dname;
```

##### *g)* 

```
SELECT employee.Fname, employee.Minit, employee.Lname
FROM dependent
JOIN employee ON dependent.Essn = employee.Ssn
GROUP BY employee.Fname, employee.Minit, employee.Lname
HAVING COUNT(dependent.Essn) > 2;
```

##### *h)* 

```
SELECT employee.Fname, employee.Minit, employee.Lname
FROM dependent
RIGHT JOIN employee ON dependent.Essn = employee.Ssn
JOIN department ON employee.Ssn = department.Mgr_ssn
WHERE dependent.Essn IS NULL;
```

##### *i)* 

```
SELECT employee.Fname, employee.Minit, employee.Lname, employee.Address
FROM employee
JOIN works_on ON employee.Ssn = works_on.Essn
JOIN project ON works_on.Pno = project.Pnumber
JOIN dept_location ON employee.Dno = dept_location.Dnumber
WHERE project.Plocation = 'Aveiro' AND dept_location.Dlocation <> 'Aveiro';
```

### 5.2

#### a) SQL DDL Script
 
[a) SQL DDL File](ex_6_2_2_ddl.sql "SQLFileQuestion")

#### b) Data Insertion Script

[b) SQL Data Insertion File](ex_6_2_2_data.sql "SQLFileQuestion")

#### c) Queries

##### *a)*

```
SELECT fornecedor.nif, fornecedor.nome
FROM fornecedor
LEFT JOIN encomenda ON encomenda.fornecedor = fornecedor.nif
WHERE encomenda.numero IS NULL;
```

##### *b)* 

```
SELECT produto.codigo, produto.nome, AVG(item.unidades) AS media_unidades
FROM produto
INNER JOIN item ON produto.codigo = item.codProd
GROUP BY produto.codigo, produto.nome;
```


##### *c)* 

```
SELECT AVG(unidadesPorEncomenda) AS mediaProdutosPorEncomenda
FROM (
    SELECT encomenda.numero, COUNT(item.codProd) AS unidadesPorEncomenda
    FROM encomenda
    INNER JOIN item ON encomenda.numero = item.numEnc
    GROUP BY encomenda.numero
) AS subquery;
```


##### *d)* 

```
SELECT fornecedor.nome, produto.nome, SUM(item.unidades) AS quantidade
FROM fornecedor
INNER JOIN encomenda ON fornecedor.nif = encomenda.fornecedor
INNER JOIN item ON encomenda.numero = item.numEnc
INNER JOIN produto ON item.codProd = produto.codigo
GROUP BY fornecedor.nome, produto.nome;
```

### 5.3

#### a) SQL DDL Script
 
[a) SQL DDL File](ex_6_2_3_ddl.sql "SQLFileQuestion")

#### b) Data Insertion Script

[b) SQL Data Insertion File](ex_6_2_3_data.sql "SQLFileQuestion")

#### c) Queries

##### *a)*

```
SELECT paciente.numUtente, paciente.nome 
FROM paciente 
LEFT JOIN prescricao ON prescricao.numUtente = paciente.numUtente 
WHERE prescricao.numPresc = IS NULL;
```

##### *b)* 

```
SELECT medico.especialidade, COUNT(prescricao.numPresc) AS num_prescricoes 
FROM medico 
INNER JOIN prescricao ON medico.numSNS = prescricao.numMedico 
GROUP BY medico.especialidade;
```


##### *c)* 

```
SELECT farmacia.nome, COUNT(prescricao.numPresc) AS num_prescricoes 
FROM farmacia 
INNER JOIN prescricao ON farmacia.nome = prescricao.farmacia 
GROUP BY farmacia.nome;
```


##### *d)* 

```
SELECT presc_farmaco.nomeFarmaco 
FROM prescricao 
INNER JOIN presc_farmaco ON prescricao.numPresc = presc_farmaco.numPresc 
WHERE presc_farmaco.numRegFarm = 906 AND prescricao.farmacia IS NULL;
```

##### *e)* 

```
SELECT farmacia.nome, farmaceutica.nome, COUNT(farmaco.nome) AS num_farmaco 
FROM farmaco 
INNER JOIN farmaceutica ON farmaco.numRegFarm = farmaceutica.numReg 
INNER JOIN presc_farmaco ON farmaceutica.numReg = presc_farmaco.numRegFarm 
INNER JOIN prescricao ON presc_farmaco.numPresc = prescricao.numPresc 
INNER JOIN farmacia ON prescricao.farmacia = farmacia.nome 
GROUP BY farmacia.nome, farmaceutica.nome;
```

##### *f)* 

```
SELECT paciente.numUtente, paciente.nome 
FROM paciente 
INNER JOIN prescricao ON prescricao.numUtente = paciente.numUtente 
INNER JOIN medico ON medico.numSNS = prescricao.numMedico 
GROUP BY paciente.numUtente, paciente.nome 
HAVING COUNT(DISTINCT medico.numSNS) > 1;
```
