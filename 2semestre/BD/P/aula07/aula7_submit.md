# BD: Guião 7


## ​7.2 
 
### *a)*

<!-- ... Write here your answer ...
It is possible to <u>underline</u> -> {A, B,.. }
-> R1 (_A_, B, C)
-> R2 (B,F) -->
```
A relação está na 1FN (Primeira Forma Normal).
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

2FN:
![ex_7_4!](ex_7_4.png "2FN")


### *c)* 

BCNF (Boyce-Codd Normal Form):
![ex_7_4!](ex_7_4.png "BCNF")


## ​7.5
 
### *a)*

```
A chave de R é {A, B}.
```

### *b)* 

2FN:
![ex_7_5!](ex_7_5.png "2FN")


### *c)* 

```
Como não existem dependências transitivas, R já está na 3FN.
```

### *d)* 

BCNF:
![ex_7_5!](ex_7_5.png "BCNF")
