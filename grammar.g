class LeParser extends Parser;
options{
    k = 2;
}

{
   public void init(){
       //programa = new Programa();
       //stack    = new StackCommand();
   }
}

program : 	"program" ID AC
				declare
				block
			FC
		;

declare	:	(var | cte)+
		;
		
var		:	type ID (VG ID)*  PV
		;
		
cte		:	"cte" type ID attr (VG ID attr)*  PV
		;

type	:	("int" | "str"| "decimal"| "bool")
		;
		
block	:	(cmd)+
		;

cmd		:	cmdAttr | cmdRead | cmdWrite | cmdIf | cmdFor | cmdWhile | cmdStr | cmdExpr
		;

cmdAttr	:	ID attr PV
		;
	   
attr	:	IG (NUM | TEXTO)
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

cmdStr	:	"str.Concat" "(" TEXT (VG TEXT)+ ")"
		;

cmdExpr	: 	termo
			exprl
		;
       
exprl  	:  	(OP termo)*
		;
       
termo  	: 	ID | NUM | TEXTO
		;


class LeLexer extends Lexer;
options{
   caseSensitive = true;
}

BLANK       : (' ' | '\n' | '\r' | '\t') {_ttype=Token.SKIP;}
            ;

COMMENT		:	'#' ('a'..'z' | 'A'..'Z' | ' ' | '0'..'9')* '\r' '\n'  {_ttype=Token.SKIP;}
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

AC	: 		'{'
	;

FC	:		'}'
	;

PV	: 		';'
	;

VG	:		','
	;

IG	:		":="
	;

