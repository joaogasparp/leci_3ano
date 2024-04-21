# BD: Guião 7


## ​7.2 
 
### *a)*

<!-- ... Write here your answer ...
It is possible to <u>underline</u> -> {A, B,.. }
-> R1 (_A_, B, C)
-> R2 (B,F) -->
```
A relação está na 1FN (Primeira Forma Normal) uma vez que os seus atributos contêm apenas valores atômicos e não existem relações dentro de relações. Ou seja, cada valor num atributo deve ser indivisível e não pode haver tabelas dentro de tabelas ou conjuntos de valores num único atributo.
```


### *b)*

1FN:
![ex_7_2_1fn!](ex_7_2_1fn.png "1FN")

2FN (Segunda Forma Normal):
![ex_7_2_2fn!](ex_7_2_2fn.png "2FN")

3FN (Terceira Forma Normal):
![ex_7_2_3fn!](ex_7_2_3fn.png "3FN")


## ​7.3
 
### *a)*

```
Primeiramente temos o conjunto de todos os atributos, R = {A, B, C, D, E, F, G, H, I, J}.
Depois, removemos os atributos que aparecem ao lado direito das dependências funcionais visto que são determinados por outros atributos, ou seja, removemos {C, D, E, F, G, H, I, J}.
Ficamos com {A, B} como possíveis chaves.
Verificamos se este conjunto determina todos os outros atributos e como determina, {A, B} é a chave.
```
1FN:
![ex_7_3_1fn!](ex_7_3_1fn.png "1FN")

### *b)* 

2FN:
![ex_7_3_2fn!](ex_7_3_2fn.png "2FN")


### *c)* 

3FN:
![ex_7_3_3fn!](ex_7_3_3fn.png "3FN")


## ​7.4
 
### *a)* 

```
A chave de R é {A, B}.
```


### *b)* 

1FN e 2FN:
![ex_7_4_b_1fn_2fn!](ex_7_4_b_1fn_2fn.png "1FN e 2FN")

3FN:
![ex_7_4_b_3fn!](ex_7_4_b_3fn.png "3FN")

### *c)* 

BCNF (Boyce-Codd Normal Form):

1FN:
![ex_7_4_c_1fn!](ex_7_4_c_1fn.png "1FN")

2FN:
![ex_7_4_c_2fn!](ex_7_4_c_2fn.png "2FN")

3FN:
![ex_7_4_c_3fn!](ex_7_4_c_3fn.png "3FN")


## ​7.5
 
### *a)*

```
A chave de R é {A, B}.
```

### *b)* 

1FN:
![ex_7_5_1fn!](ex_7_5_1fn.png "1FN")

2FN:
![ex_7_5_2fn!](ex_7_5_2fn.png "2FN")


### *c)* 


3FN:
![ex_7_5_3fn_bcnf!](ex_7_5_3fn_bcnf.png "3FN")


### *d)* 

BCNF:
![ex_7_5_3fn_bcnf!](ex_7_5_3fn_bcnf.png "BCNF")
