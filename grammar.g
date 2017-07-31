class LeParser extends Parser;
options{
    k = 2;
}

{
	// Variable Fields
	private java.util.HashMap<String, Variable> _symbolTable; 
	private int _varType;

	// Variable Assignment
	private int _varFrom;
	private Variable _varTo;
	
	// Error Fields
	private java.util.ArrayList<Error> _errorList;

	public void Init(){
		_symbolTable = new java.util.HashMap<String, Variable>(); 
		_varType=0;

		_varFrom=0;
		_varTo=null;

		_errorList = new java.util.ArrayList<Error>();
	}


	//Error Handling Methods

	private void CreateError(int code, String message){
		Error e = new Error(code, message);
		_errorList.add(e);
	}

	public void ErrorHandling(){
		if(_errorList.size() > 0)
			for (Error e: _errorList) System.out.println(e.toString());
		else
			System.out.println("ANALYSIS WITHOUT ERRORS");
		//TODO: Save in a output file
	}


	//Variable Handling Methods

	private Boolean CheckVariableCanBeDeclared(String varName){
		if (_symbolTable.get(varName) == null){
			Variable v = new Variable(varName, _varType);
			_symbolTable.put(v.GetId(), v);
			return true;
		}else{
			CreateError(1, "Variable " + varName +  " already declared");
			return false;
		}
	}
	
	private Boolean CheckVariableCanBeUsed(String varName){
		if (_symbolTable.get(varName) == null){
			CreateError(2, "Variable " + varName +  " was not declared");
			return false;
		}else{
			return true;
		}
	}

	private Boolean NumberIsDecimal(String tokenNumber){
		return tokenNumber.contains("f");
	}


	//Variable Assignment Methods
	
	private void CheckVariableAssignment(){
		if(_varFrom > _varTo.GetType()){
			CreateError(3, "Variable " + _varTo.GetId() +  " cannot received this operation with the current invalid types.");
		}
	}

	private Variable GetVariable(String varName){
		Variable v = _symbolTable.get(varName);
		if (v != null){
			return v;
		}else{
			throw new RuntimeException("Variable not declared.");
		}
	}

	private void SetMaxType(int varType){
		if(varType > _varFrom)
			_varFrom = varType;
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
			ID {CheckVariableCanBeDeclared(LT(0).getText());}
			(
				VG
				ID {CheckVariableCanBeDeclared(LT(0).getText());}
			)*
			PV
		;
		
cte		:	"cte" type { _varType = Variable.GetTypeNumber(LT(0).getText()); }
			ID 	{
					if(CheckVariableCanBeDeclared(LT(0).getText())){
						_varTo = GetVariable(LT(0).getText());
					}
				}
			attr
			(
				VG
				ID	{
						if(CheckVariableCanBeDeclared(LT(0).getText())){
							_varTo = GetVariable(LT(0).getText());
						}
					}
				attr
			)*
			PV
		;

type	:	("int" | "decimal" | "str"| "bool")
		;
		
block	:	(cmd)*
		;

cmd		:	cmdAttr | cmdRead | cmdWrite | cmdIf | cmdFor | cmdWhile | cmdStr
		;

cmdAttr	:	ID
				{
					if(CheckVariableCanBeUsed(LT(0).getText())){
						_varTo = GetVariable(LT(0).getText());
					}
				}
			attr
			PV
		;
	   
attr	:	IG
				{ _varFrom = 0;}
			(
				cmdExpr
				|

			)
			{ CheckVariableAssignment();}
		;
		
cmdRead	:	"Read" "("
				ID { CheckVariableCanBeUsed(LT(0).getText());}
			")"
			PV
		;

cmdWrite:	"Write" "("
			(
				TEXT
				|
				ID { CheckVariableCanBeUsed(LT(0).getText());}
			) ")"
			PV
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
		
boolCond:	(
				cmdExpr OPREL cmdExpr
				|
				ID { CheckVariableCanBeUsed(LT(0).getText());}
			)
		;


cmdStr	:	"str.Concat" "(" TEXT (VG TEXT)+ ")"
		;

cmdExpr	: 	termo
			exprl
		;
       
exprl  	:  	(OP termo)*
		;
       
termo  	: 	ID 	{
					if(CheckVariableCanBeUsed(LT(0).getText())){
						Variable v = GetVariable(LT(0).getText());
						SetMaxType(v.GetType());
					}
				}
			|
			NUM { 
					System.out.println(LT(0).getText());
					if(NumberIsDecimal(LT(0).getText()))
						SetMaxType(Variable.DECIMAL);
					else
						SetMaxType(Variable.INTEGER);
				}
			|
			TEXTO { SetMaxType(Variable.STRING);}
			|
			boolVal { SetMaxType(Variable.BOOLEAN);}
		;


boolVal:	"true" | "false"
			;


class LeLexer extends Lexer;
options{
   caseSensitive = true;
}

BLANK       :	(' ' | '\n' | '\r' | '\t') {_ttype=Token.SKIP;}
            ;

COMMENT		:	'#' ('a'..'z' | 'A'..'Z' | ' ' | '0'..'9')* '\r' '\n'  {_ttype=Token.SKIP;}
			;

ID          :	('a'..'z' |'A'..'Z') ('a'..'z'|'A'..'Z'|'0'..'9'|'_')*
            ;

NUM			:	('0'..'9')+ ('.' ('0'..'9')+ 'f')?
			;
        
TEXTO       :	'"' ('a'..'z' | 'A'..'Z' | ' ' | '0'..'9')* '"'
            ;

OPREL       :	'>' | '<' | "=="
            ;
			
OPLOG		:	'&' | '|'
			;

OP			:	'+' | '-'
			;

AC			: 	'{'
			;
		
FC			:	'}'
			;
		
AP			:	'('
			;
		
FP			:	')'
			;
		
PV			: 	';'
			;
		
VG			:	','
			;
		
IG			:	":="
			;