grammar LangComplex;

program: (stat ';')* EOF;

stat: 
    'display' expr          #statDisplay
    | VARIABLE '<=' expr    #statAssigment
    ;

expr: e1=expr op=('+' | '-') e2=expr    #exprSumSub
    | sign=('+' | '-') expr             #exprUnary
    | e1=expr op=('*' | ':') e2=expr    #exprMulDiv
    | '(' expr ')'                      #exprParenthesis
    | complex                           #exprComplex
    | float                             #exprFloat
    | NUM                               #exprNum
    | 'i'                               #exprI
    | VARIABLE                          #exprVariable
    ;

complex: NUM '+' NUM 'i';
float: NUM '.' NUM;

NUM: [0-9]+;
VARIABLE: [A-Za-z][A-Za-z0-9]*;

WS: [ \r\t\n]+ -> skip;
COMMENT: '*COM*' .*? '\n' -> skip;
