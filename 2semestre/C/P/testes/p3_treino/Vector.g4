grammar Vector;

program: (stat ';')* EOF;

stat:
    'show' expr                 #statShow
    | expr '->' VARIABLE        #statAssigment
    ;

expr: op = ( '+' | '-' ) expr               #exprUnary
    | e1 = expr '*' e2 = expr               #exprMultiply
    | e1 = expr '.' expr                    #exprInternalProduct
    | e1 = expr op = ('+' | '-') e2 = expr  #exprSumSub
    | '(' expr ')'                          #exprParenthesis
    | NUMBER                                #exprNumber
    | VARIABLE                              #exprVariable
    | VECTOR                                #exprVector
    ;

NUMBER: 
    INT
    | FLOAT
    ;
INT: [0-9]+;
FLOAT: [0-9]+ '.' [0-9]+;
VECTOR: '[' NUMBER (',' NUMBER)* ']';
VARIABLE: [a-z][a-z0-9]*;

WS: [ \t\r\n]+ -> skip;
COMMENT: '#' .*? '\r'? '\n' -> skip;
