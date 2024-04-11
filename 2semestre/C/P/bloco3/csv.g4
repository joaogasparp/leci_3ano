grammar csv;

file: header row* EOF;

header: hvalue (','hvalue)* NEWLINE;
row: value (','value)* NEWLINE;

hvalue: String;
value: (Number | String);

Number: [0-9]+('.'[0-9]+)?;
String: [a-zA-Z0-9];
NEWLINE: '\r'? '\n';
WS: [ \t]+ -> skip;
