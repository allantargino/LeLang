class LeParser extends Parser;
options{
    k = 2;
}

{
	// Variable Fields
	private java.util.HashMap<String, Variable> _symbolTable; 
	private int _varType;

	private java.util.ArrayList<Error> _errorList;

	public void Init(){
		//programa = new Programa();
		//stack    = new StackCommand();

		_symbolTable = new java.util.HashMap<String, Variable>(); 

		_errorList = new java.util.ArrayList<Error>();
	}


	//Error Handling Methods

	private void CreateError(int code, String message){
		Error e = new Error(code, message);
		_errorList.add(e);
	}

	public void ErrorHandling(){
		for (Error e: _errorList) System.out.println(e.toString());
		//TODO: Save in a output file
	}

	//Variable Handling Methods

	private void CheckVariableIsDeclared(String varName){
		if (_symbolTable.get(varName) == null){
				Variable v = new Variable(varName, _varType);
				_symbolTable.put(v.GetId(), v);
		}else{
			CreateError(1, "Variable " + varName +  " already declared");
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
		
var		:	type { _varType = Variable.GetTypeNumber(LT(0).getText()); }
			ID {CheckVariableIsDeclared(LT(0).getText());}
			(
				VG
				ID {CheckVariableIsDeclared(LT(0).getText());}
			)*
			PV
		;
		
cte		:	"cte" type { _varType = Variable.GetTypeNumber(LT(0).getText()); }
			ID {CheckVariableIsDeclared(LT(0).getText());}
			attr
			(
				VG
				ID {CheckVariableIsDeclared(LT(0).getText());}
				attr
			)*
			PV
		;

type	:	("int" | "decimal"| "str"| "bool")
		;
		
block	:	(cmd)*
		;

cmd		:	cmdAttr | cmdRead | cmdWrite | cmdIf | cmdFor | cmdWhile | cmdStr
		;

cmdAttr	:	ID attr PV
		;
	   
attr	:	IG (cmdExpr | TEXTO | boolVal)
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
       
termo  	: 	ID | NUM 
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

OP			: '+' | '-'
        
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