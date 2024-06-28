grammar TupleGrammar;

program: (stat ';')* EOF;

stat:
    'print'                 #statPrintLine
    | 'print' expr          #statPrint
    | VARIABLE '<=' expr    #statAssign
    | 'head' expr           #statHead 
    | 'tail' expr           #statTail
    | 'zip' e1=expr e2=expr #statZip
    ;

expr:
    '[' elements? ']'               #exprElements
    | '(' expr ')'                  #exprParenthesis
    | e1=expr op=('*'|'/') e2=expr  #exprMulDiv
    | e1=expr op=('+'|'-') e2=expr  #exprAddSub
    | op=('+'|'-') expr             #exprUnary
    | VARIABLE                      #exprVariable
    | NUMBER                        #exprNumber
    ;

elements:
    expr (',' expr)*        #elementsList
    ;

NUMBER: [0-9]+;
VARIABLE: [a-zA-Z][a-zA-Z0-9]*;

WS: [ \t\r\n]+ -> skip;
COMMENT: '--' .*? '\n' -> skip;
