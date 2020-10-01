#Exercicio 1.1
def comprimento(lista):
	pass

#Exercicio 1.1
def soma(lista):
	pass

#Exercicio 1.1
def existe(lista, elem):
	pass

#Exercicio 1.1
def concat(l1, l2):
	pass

#Exercicio 1.1
def inverte(lista):
	pass

#Exercicio 1.1
def capicua(lista):
	pass

#Exercicio 2.1
def separar(lista):
	pass


def remove_e_conta(lista, elem):
	pass

#Exercicio 3.1
def cabeca(lista):
	pass

#Exercicio 3.2
def cauda(lista):
	pass

#Exercicio 3.3
def juntar(l1, l2):
	if len(l1) != len(l2):
		return None

	if l1 == []:
		return []

	return [(l1[0], l2[0])] + juntar(l1[1:], l2[1:])

#Exercicio 3.4
def menor(lista):
	pass

#Exercicio 3.6
def max_min(lista):
	pass
