grammar Calculator;

program: stat* EOF;

stat: NEWLINE                       #StatEmpty
    | expr? NEWLINE                 #StatExpr
    | assignment? NEWLINE           #StatAssignment
    ;

assignment: ID '=' expr;

expr: op=('+'|'-') expr             #ExprUnary
    | expr op=('*'|'/'|'%') expr    #ExprMulDivMod
    | expr op=('+'|'-') expr        #ExprAddSub
    | Integer                       #ExprInteger
    | ID                            #ExprID
    | '(' expr ')'                  #ExprParent
    ;

Integer: [0-9]+;

ID: [a-zA-Z]+;

NEWLINE: '\r'? '\n';

WS: [ \t]+ -> skip;

COMMENT: '#' .*? '\n' -> skip;
