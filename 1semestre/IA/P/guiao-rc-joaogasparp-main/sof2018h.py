from bayes_net import *
import itertools

bn = BayesNet()

variables = ['sc', 'pt', 'cp', 'fr', 'pa', 'cnl']

bn.add('sc', [], 0.6)

bn.add('pt', [], 0.05)

bn.add('cp', [('sc', True), ('pt', True)], 0.02)
bn.add('cp', [('sc', True), ('pt', False)], 0.011)
bn.add('cp', [('sc', False), ('pt', True)], 0.01)
bn.add('cp', [('sc', False), ('pt', False)], 0.001)

bn.add('pa', [('pt', True)], 0.25)
bn.add('pa', [('pt', False)], 0.004)

bn.add('cnl', [('st', True)], 0.9)
bn.add('cnl', [('st', False)], 0.001)

bn.add('fr', [('pt', True), ('pa', True)], 0.9)
bn.add('fr', [('pt', True), ('pa', False)], 0.9)
bn.add('fr', [('pt', False), ('pa', True)], 0.1)
bn.add('fr', [('pt', False), ('pa', False)], 0.01)

x = itertools.product([True, False], repeat=3)
print(list(x))

y = zip(('r','t','a','j','m'),[True,False,True,False,True])
print(list(y))

# se quero calcular a probabilidade individual de alarme não preciso da marira uso apenas as variaveis ascendetes ou seja, as que o alarme depende
