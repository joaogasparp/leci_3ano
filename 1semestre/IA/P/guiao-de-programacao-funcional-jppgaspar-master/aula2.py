#Exercicio 4.1
impar = lambda x: True if x % 2 == 1 else False

#Exercicio 4.2
positivo = lambda x: True if x>0 else False

#Exercicio 4.3
comparar_modulo = lambda x,y: True if abs(x)<abs(y) else False

#Exercicio 4.4
import math
cart2pol = lambda x,y: (math.sqrt(x*x + y*y), math.atan2(y, x))

#Exercicio 4.5
ex5 = lambda f,g,h: lambda x,y,z: h(f(x,y),g(y,z))

#Exercicio 4.6
def quantificador_universal(lista, f):
    if lista == []:
        return True
    return f(lista[0]) and quantificador_universal(lista[1:], f)

#Exercicio 4.8
def subconjunto(lista1, lista2):
    if lista1 == []:
        return True
    if lista1[0] in lista2:
        return subconjunto(lista1[1:], lista2)
    else:
        return False

#Exercicio 4.9
def menor_ordem(lista, f):
    if len(lista) == 1:
        return lista[0]
    min = menor_ordem(lista[1:], f)
    if f(lista[0],min):
        return lista[0]
    else:
        return min

#Exercicio 4.10
def menor_e_resto_ordem(lista, f):
    min = menor_ordem(lista,f)
    resto = lista.remove(min)
    return min,resto

#Exercicio 5.2
def ordenar_seleccao(lista, ordem):
    pass
