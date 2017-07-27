class MeuParser extends Parser;
options{
    k = 2;
}

prog   	: 	"program" ID "{"
				declare
				block
			"}"
		;
		
declare	:	type ":" ID ("," ID)*  ";"
		;
		
const	:	"const" type ":" ID attr ("," ID attr)*  ";"
		;

type	:	("int" | "str"| "decimal"| "bool")
		;
		
block	:	(cmd)+
		;

cmd		:	cmdAttr | cmdRead | cmdWrite | cmdIf | cmdFor | cmdWhile | cmdStr | cmdExpr
		;

cmdAttr	:	ID attr ";"
		;
	   
attr	:	"=" (NUM | TEXTO)
		;
		
cmdRead	:	"Read(" ID ")"
		;

cmdWrite:	"Write(" (TEXT | ID) ")"
		;
		
cmdIf	:	"if" "(" boolExpr ")"
			"endif"
		;
		
cmdFor	:	"for(" NUM ":" NUM ")"
			"nextfor"
		;
		
cmdWhile:	"while(" boolExpr")"
			"nextfor"
		;
		
boolExpr:	boolCond (OPLOG boolCond)*
		;
		
boolCond:	(cmdExpr OPREL cmdExpr | ID)
		;

cmdStr	:	"str.Concat" "(" TEXT ("," TEXT)+ ")"
		;

cmdExpr	: 	termo
			exprl
		;
       
exprl  	:  	(OP termo)*
		;
       
termo  	: 	ID | NUM | TEXTO
		;



class Lexer extends Lexer;
options{
   caseSensitive = true;
}

BLANK       : (' ' | '\n' | '\r' | '\t') {_ttype=Token.SKIP;}
            ;
        
ID          : ('a'..'z' | 'A'..'Z') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
            ;
        
NUM         : ('0'..'9')+ ('.' ('0'..'9')+)?
            ;
        
OPREL       : '>' | '<' | "=="
            ;
			
OPLOG		: '&' | '|'
			;
        
TEXTO       : '"' ('a'..'z' | 'A'..'Z' | ' ' | '0'..'9')* '"'
            ;