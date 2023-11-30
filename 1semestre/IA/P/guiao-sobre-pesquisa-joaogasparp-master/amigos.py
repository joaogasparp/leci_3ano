from constraintsearch import *

amigos = ["Andre", "Bernardo", "Claudio"]

dominios = { 'Andre': [ ('Bernando', 'Claudio'), ('Claudio', 'Bernardo') ],
             'Bernardo': [ ('Andre', 'Claudio'), ('Claudio', 'Andre') ],
             'Claudio': [ ('Andre', 'Bernardo'), ('Bernardo', 'Andre') ] }

arestas = [ (a1, a2) for a1 in amigos for a2 in amigos if a1 != a2 ]

restricoes = { a: lambda v1,x1,v2,x2: x1[1]}

cs = ConstraintSearch(dominios, None)

print(cs.search())
