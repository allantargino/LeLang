class LeParser extends Parser;
options{
    k = 2;
}

{
	private java.util.HashMap<String, Variable> _symbolTable; 
	private java.util.ArrayList<Variable> _tempVarList;

	private java.util.ArrayList<Error> _errorList;

	public void Init(){
		//programa = new Programa();
		//stack    = new StackCommand();

		_symbolTable = new java.util.HashMap<String, Variable>(); 

		_errorList = new java.util.ArrayList<Error>();
	}

	public void ErrorHandling(){

	}



	private void CheckVariable(){
		if (_symbolTable.get(LT(0).getText()) == null){
				Variable v = new Variable();
				v.SetId(LT(0).getText());
				_symbolTable.put(v.GetId(), v);
				_tempVarList.add(v);
		}   
	}
}


program : 	"program" ID "{"
				declare
				block
			"}"
		;

declare	:	(var | cte)+
		;
		
var		:	type { _tempVarList = new java.util.ArrayList<Variable>(); }
			ID {CheckVariable();}
			(
				VG
				ID {CheckVariable();}
			)*
			PV
		;
		
cte		:	"cte" type ID attr (VG ID attr)*  PV
		;

type	:	("int" | "str"| "decimal"| "bool")
		;
		
block	:	(cmd)*
		;

cmd		:	cmdAttr | cmdRead | cmdWrite | cmdIf | cmdFor | cmdWhile | cmdStr | cmdExpr
		;

cmdAttr	:	ID attr PV
		;
	   
attr	:	IG (NUM | TEXTO | boolVal)
		;
		
cmdRead	:	"Read" "(" ID ")" PV
		;

cmdWrite:	"Write" "(" (TEXT | ID) ")" PV
		;
		
cmdIf	:	"if" "(" boolExpr ")"
				block
			"endif"
		;
		
cmdFor	:	"for" AP NUM ":" NUM FP
			"nextfor"
		;
		
cmdWhile:	"while" AP boolExpr FP
			"nextfor"
		;
		
boolExpr:	boolCond (OPLOG boolCond)*
		;
		
boolCond:	(cmdExpr OPREL cmdExpr | ID)
		;

boolVal	:	("true" | "false")
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

AP	:		'('
	;

FP	:		')'
	;

PV	: 		';'
	;

VG	:		','
	;

IG	:		":="
	;