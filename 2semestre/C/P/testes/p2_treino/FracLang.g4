grammar FracLang;

program: (stat ';')* EOF;

stat: 
    'display' expr          #statDisplay
    | VARIABLE '<=' expr    #statAssigment
    ;

expr: 
    sign= ('+' | '-') expr              #exprUnary
    | e1=expr op= ('*' | ':') e2=expr   #exprMulDiv
    | e1=expr op= ('+' | '-') e2=expr   #exprSumSub
    | '(' expr ')'                      #exprParenthesis
    | 'read' STR                        #exprRead
    | 'reduce' expr                     #exprReduce
    | NUM '/' NUM                       #exprFraction
    | NUM                               #exprNum
    | VARIABLE                          #exprVariable
    ;

STR: '"' .*? '"';
NUM: [0-9]+;
VARIABLE: [a-z]+;

WS: [ \t\n\r]+ -> skip;
COMMENT: '--' .*? '\n' -> skip;
