from constraintsearch import *

region = ['A', 'B', 'C', 'D', 'E']
colors = ['red', 'blue', 'green', 'yellow', 'white']

dominios = { r:colors for r in region }

arestas = [ ('A','B'), ('B','C'), ('C','D'), ('D','A') ]
arestas += [ (r, 'E') for r in region if r != 'E' ]
arestas += [ (v2,v1) for (v1,v2) in arestas ]

restrições = { (v1,v2): lambda r1,c1,r2,c2: c1!=c2 for (v1,v2) in arestas }

cs = ConstraintSearch(dominios,restrições)

print(cs.search())
