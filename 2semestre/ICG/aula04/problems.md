# 1.

## a)
```
A matriz de transformação em coordenadas homogêneas que realiza a rotação é dada por:
R = [ cos(θ) -sin(θ) 0
      sin(θ)  cos(θ) 0
      0       0      1 ]
R = [ 0 -1 0
      1 0  0
      0 0  1]
Onde θ é o ângulo de rotação. No nosso caso, θ é 90 graus.

A matriz de translação que move o quadrado para a origem é:
T1 = [ 1 0 -x        
       0 1 -y
       0 0 1 ]
T1 = [ 1 0 -2.5
       0 1 -2.5
       0 0 1    ]

E a matriz que move o quadrado de volta para sua posição original é:
T2 = [ 1 0 x
       0 1 y
       0 0 1 ]
T2 = [ 1 0 2.5
       0 1 2.5
       0 0 1   ]
Onde (x, y) é o centro do quadrado. No nosso caso, (x, y) é (2.5, 2.5).

A matriz de transformação final é dada pela multiplicação dessas matrizes:
M = T2 * R * T1
M = [ 1 0 2.5   * [ 0 -1 0   * [ 1 0 -2.5  
      0 1 2.5       1  0 0       0 1 -2.5  
      0 0 1   ]     0  0 1 ]     0 0 1    ]
Após realizar a multiplicação das matrizes, obtemos a matriz de transformação final:
M = [ 0 -1 5  
      1 0  0  
      0 0  1 ]
Esta é a matriz de transformação que realiza uma rotação de 90 graus em torno do centro do quadrado.
```

## b)
```
Para calcular as coordenadas dos vértices transformados, multiplicamos as coordenadas originais de cada vértice pela matriz de transformação M. As coordenadas originais dos vértices do quadrado são (2, 2), (3, 2), (3, 3) e (2, 3). Em coordenadas homogêneas, essas coordenadas se tornam (2, 2, 1), (3, 2, 1), (3, 3, 1) e (2, 3, 1).

A multiplicação de um ponto P = (x, y, 1) pela matriz M é dada por:
P' = M * P
Onde P' são as novas coordenadas do ponto após a transformação.

```