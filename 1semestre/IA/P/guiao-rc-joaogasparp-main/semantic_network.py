

# Guiao de representacao do conhecimento
# -- Redes semanticas
# 
# Inteligencia Artificial & Introducao a Inteligencia Artificial
# DETI / UA
#
# (c) Luis Seabra Lopes, 2012-2020
# v1.9 - 2019/10/20
#


# Classe Relation, com as seguintes classes derivadas:
#     - Association - uma associacao generica entre duas entidades
#     - Subtype     - uma relacao de subtipo entre dois tipos
#     - Member      - uma relacao de pertenca de uma instancia a um tipo
#

class Relation:
    def __init__(self,e1,rel,e2):
        self.entity1 = e1
#       self.relation = rel  # obsoleto
        self.name = rel
        self.entity2 = e2
        
    def __str__(self):
        return self.name + "(" + str(self.entity1) + "," + \
               str(self.entity2) + ")"
               
    def __repr__(self):
        return str(self)


# Subclasse Association
class Association(Relation):
    def __init__(self,e1,assoc,e2):
        Relation.__init__(self,e1,assoc,e2)

#   Exemplo:
#   a = Association('socrates','professor','filosofia')

# Subclasse Subtype
class Subtype(Relation):
    def __init__(self,sub,super):
        Relation.__init__(self,sub,"subtype",super)


#   Exemplo:
#   s = Subtype('homem','mamifero')

# Subclasse Member
class Member(Relation):
    def __init__(self,obj,type):
        Relation.__init__(self,obj,"member",type)

#   Exemplo:
#   m = Member('socrates','homem')

# classe Declaration
# -- associa um utilizador a uma relacao por si inserida
#    na rede semantica
#
class Declaration:
    def __init__(self,user,rel):
        self.user = user
        self.relation = rel
        
    def __str__(self):
        return "decl("+str(self.user)+","+str(self.relation)+")"
    
    def __repr__(self):
        return str(self)

#   Exemplos:
#   da = Declaration('descartes',a)
#   ds = Declaration('darwin',s)
#   dm = Declaration('descartes',m)

# classe SemanticNetwork
# -- composta por um conjunto de declaracoes
#    armazenado na forma de uma lista
#
class SemanticNetwork:
    def __init__(self,ldecl=None):
        self.declarations = [] if ldecl==None else ldecl
        
    def __str__(self):
        return str(self.declarations)
    
    def insert(self,decl):
        self.declarations.append(decl)
        
    def query_local(self,user=None,e1=None,rel=None,e2=None):
        self.query_result = \
            [ d for d in self.declarations
                if  (user == None or d.user==user)
                and (e1 == None or d.relation.entity1 == e1)
                and (rel == None or d.relation.name == rel)
                and (e2 == None or d.relation.entity2 == e2) ]
        return self.query_result
    
    def show_query_result(self):
        for d in self.query_result:
            print(str(d))
    
    def list_associations(self):
        lassoc = [d.relation.name for d in self.declarations if isinstance(d.relation, Association)]
        return list(set(lassoc))
    
    def list_objects(self):
        lobj = [d.relation.entity1 for d in self.declarations if isinstance(d.relation, Member)]
        return list(set(lobj))
        
    def list_users(self):
        lusers = [d.user for d in self.declarations]
        return list(set(lusers))
    
    def list_types(self):
        ltype1 = [d.relation.entity1 for d in self.declarations if isinstance(d.relation, Subtype)]
        ltype2 = [d.relation.entity2 for d in self.declarations if not isinstance(d.relation, Association)]
        return list(set(ltype1 + ltype2))

    def list_local_associations(self, entity):
        llassoc = [d.relation.name for d in self.declarations if isinstance(d.relation, Association) and d.relation.entity1 == entity]
        return list(set(llassoc))
    
    def list_relations_by_user(self, user):
        lrel = [d.relation.name for d in self.declarations if d.user == user]
        return list(set(lrel))

    def associations_by_user(self, user):
        lrel = [d.relation.name for d in self.declarations if d.user == user and isinstance(d.relation, Association)]
        return len(set(lrel))
        
    def list_local_associations_by_entity(self, entidade):
        llabe =[(d.relation.name, d.user) for d in self.declarations if isinstance(d.relation,Association) and (d.relation.entity1 == entidade)]
        return list(set(llabe))
    
    def predecessor(self, entity1, entity2):
        ldecl= self.query_local(e2=entity1, rel="subtype")
        ldecl += self.query_local(e2=entity1, rel="member")
        lchild = [d.relation.entity1 for d in ldecl]

        for c in lchild:
            if c == entity2 or self.predecessor(c, entity2):
                return True
        return False
    
    def predecessor_path(self, entity1, entity2):
        ldecl = self.query_local(e2=entity1, rel="subtype")
        ldecl += self.query_local(e2=entity1, rel="member")
        lchild = [d.relation.entity1 for d in ldecl]

        for c in lchild:
            if c==entity2:
                return [entity1,entity2]
            elif self.predecessor(c, entity2):
                return [entity1] + self.predecessor_path(c, entity2)
        return []
    
    def query(self, entity, assoc=None):
        parents = [d.relation.entity2 for d in self.declarations if isinstance(d.relation, (Member, Subtype)) and d.relation.entity1 == entity]
        
        ldecl = [d for d in self.query_local(e1 = entity, rel = assoc) if isinstance(d.relation, Association)]
        for p in parents:
            ldecl += self.query(p, assoc)
        return ldecl
    
    def query2(self, entity, rel=None):
        ldecl = [d for d in self.query_local(e1 = entity, rel = rel)]        
        return ldecl + self.query(entity, rel)
    
    def query_cancel(self, entity, assoc=None):
        ldecl = [d for d in self.query_local(e1 = entity, rel = assoc)]

        if ldecl == []:
            parents = [d.relation.entity2 for d in self.declarations if isinstance(d.relation, (Member, Subtype)) and d.relation.entity1 == entity]
            for p in parents:
                ldecl += self.query_cancel(p, assoc)
        return ldecl