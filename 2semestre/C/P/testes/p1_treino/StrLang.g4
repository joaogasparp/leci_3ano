grammar StrLang;

program: stat* EOF;

stat: 
    'print' printable           #statPrint
    | VARIABLE ':' printable    #statAssign
    ;

printable:
    printable '+' printable                                     #printableConcat
    | printable '-' printable                                   #printableRemove
    | 'trim' printable                                          #printableTrim
    | '(' printable ')'                                         #printableParenthesis
    | p1 = printable '/' p2 = printable '/' p3 = printable      #printableSubstitution
    | 'input' '(' printable ')'                                 #printableInput
    | STR                                                       #printableString
    | VARIABLE                                                  #printableVariable
    ;

STR: '"' .*? '"';
VARIABLE: [A-Za-z][A-Za-z0-9]*;
WS: [ \t\n\r]+ -> skip;
COMMENT: '//' .*? '\n' -> skip;
