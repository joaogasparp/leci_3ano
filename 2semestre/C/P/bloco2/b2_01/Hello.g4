grammar Hello;
program : greetings* EOF ;
greetings : hello | bye ;
hello : 'hello' ID+ ;
bye : 'bye' ID+ ;
ID : [a-zA-Z]+ ;
WS : [ \t\r\n]+ -> skip;