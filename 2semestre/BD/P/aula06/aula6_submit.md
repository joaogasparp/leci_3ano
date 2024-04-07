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
... Write here your answer ...
```

### *o)* Obter uma lista que incluía o número de vendas de um título (ytd_sales), o seu nome, a faturação total, o valor da faturação relativa aos autores e o valor da faturação relativa à editora;

```
... Write here your answer ...
```

### *p)* Obter uma lista que incluía o número de vendas de um título (ytd_sales), o seu nome, o nome de cada autor, o valor da faturação de cada autor e o valor da faturação relativa à editora;

```
... Write here your answer ...
```

### *q)* Lista de lojas que venderam pelo menos um exemplar de todos os livros;

```
... Write here your answer ...
```

### *r)* Lista de lojas que venderam mais livros do que a média de todas as lojas;

```
... Write here your answer ...
```

### *s)* Nome dos títulos que nunca foram vendidos na loja “Bookbeat”;

```
... Write here your answer ...
```

### *t)* Para cada editora, a lista de todas as lojas que nunca venderam títulos dessa editora; 

```
... Write here your answer ...
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
... Write here your answer ...
```

##### *b)* 

```
... Write here your answer ...
```

##### *c)* 

```
... Write here your answer ...
```

##### *d)* 

```
... Write here your answer ...
```

##### *e)* 

```
... Write here your answer ...
```

##### *f)* 

```
... Write here your answer ...
```

##### *g)* 

```
... Write here your answer ...
```

##### *h)* 

```
... Write here your answer ...
```

##### *i)* 

```
... Write here your answer ...
```

### 5.2

#### a) SQL DDL Script
 
[a) SQL DDL File](ex_6_2_2_ddl.sql "SQLFileQuestion")

#### b) Data Insertion Script

[b) SQL Data Insertion File](ex_6_2_2_data.sql "SQLFileQuestion")

#### c) Queries

##### *a)*

```
... Write here your answer ...
```

##### *b)* 

```
... Write here your answer ...
```


##### *c)* 

```
... Write here your answer ...
```


##### *d)* 

```
... Write here your answer ...
```

### 5.3

#### a) SQL DDL Script
 
[a) SQL DDL File](ex_6_2_3_ddl.sql "SQLFileQuestion")

#### b) Data Insertion Script

[b) SQL Data Insertion File](ex_6_2_3_data.sql "SQLFileQuestion")

#### c) Queries

##### *a)*

```
... Write here your answer ...
```

##### *b)* 

```
... Write here your answer ...
```


##### *c)* 

```
... Write here your answer ...
```


##### *d)* 

```
... Write here your answer ...
```

##### *e)* 

```
... Write here your answer ...
```

##### *f)* 

```
... Write here your answer ...
```
