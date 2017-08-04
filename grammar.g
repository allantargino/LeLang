class LeParser extends Parser;
options{
    k = 2;
}

{
	// Variable Fields
	private java.util.HashMap<String, Variable> _symbolTable; 
	private int _varType;
	private Boolean _endOfAssignment;

	// Variable Assignment
	private int _varFrom;
	private Variable _varTo;
	private String _expression;
	
	// Error Fields
	private java.util.ArrayList<Error> _errorList;


	// Code Writing
	private Command cmd;
	private ProgramStructure programStructure;

	public void Init(){
		_symbolTable = new java.util.HashMap<String, Variable>(); 
		_varType=0;
		_endOfAssignment=false;

		_varFrom=0;
		_varTo=null;

		_errorList = new java.util.ArrayList<Error>();

		programStructure = new ProgramStructure();
	}


	//Error Handling Methods

	private void CreateError(int code, String message){
		Error e = new Error(code, 0, message);
		_errorList.add(e);
	}

	public void ErrorHandling(){
		int size = _errorList.size();
		if(size > 0)
		{
			System.out.println(size + " ERRORS DURING ANALYSIS:");
			for (Error e: _errorList) System.out.println(e.toString());
		}
		//TODO: Save in a output file
	}


	//Variable Handling Methods

	private Boolean CheckAndDeclareVariable(String varName, Boolean constant){
		if (_symbolTable.get(varName) == null){
			Variable v = new Variable(varName, _varType, constant);
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
	
	private Boolean CheckVariableAssignment(){
		if(_varFrom > _varTo.GetType()){
			CreateError(3, "Variable " + _varTo.GetId() +  " cannot received this operation with the current invalid types.");
			return false;
		}else{
			return true;
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

	private Boolean CheckVariableIsConst(){
		if (_varTo.IsConst()){
			if(_endOfAssignment)
				CreateError(4, "Variable " + _varTo.GetId() +  " is a constant and cannot be assign.");
			return true;
		}else{
			return false;
		}
	}

	private Boolean CheckVariableIsConst(Variable v){
		if (v.IsConst()){
			CreateError(5, "Variable " + _varTo.GetId() +  " is a constant and cannot be used in Read command.");
			return true;
		}else{
			return false;
		}
	}
}


program : 	"program" ID "{"
				declare
				{
					_endOfAssignment = true;
					for (Variable v : _symbolTable.values()) {
						programStructure.AddVariable(v);
					}
				}
				block
			"}"
			{
				System.out.println(programStructure.WriteCode());
			}
		;

declare	:	(var | cte) *
		;
		
var		:	type { _varType = Variable.GetTypeNumber(LT(0).getText()); }
			ID {CheckAndDeclareVariable(LT(0).getText(), false);}
			(
				VG
				ID {CheckAndDeclareVariable(LT(0).getText(), false);}
			)*
			PV
		;
		
cte		:	"cte" type { _varType = Variable.GetTypeNumber(LT(0).getText()); }
			ID 	{
					if(CheckAndDeclareVariable(LT(0).getText(), true)){
						_varTo = GetVariable(LT(0).getText());
					}
				}
			attr { _varTo.SetExpression(_expression); }
			(
				VG
				ID	{
						if(CheckAndDeclareVariable(LT(0).getText(), true)){
							_varTo = GetVariable(LT(0).getText());
						}
					}
				attr  { _varTo.SetExpression(_expression); }
			)*
			PV
		;

type	:	("int" | "decimal" | "str"| "bool")
		;
		
block	:	(cmd)*
		;

cmd		:	cmdAttr | cmdRead | cmdWrite | cmdIf | cmdFor | cmdWhile
		;

cmdAttr	:	ID
				{
					if(CheckVariableCanBeUsed(LT(0).getText())){
						_varTo = GetVariable(LT(0).getText());
						cmd = new CommandAssign();					
					}
				}
			attr
			PV
		;
	   
attr	:	IG
				{
					_varFrom = 0;
					_expression = "";
				}
			cmdExpr
				{
					if(CheckVariableAssignment() && !CheckVariableIsConst())
					{
						((CommandAssign) cmd).SetToVariable(_varTo);
						((CommandAssign) cmd).SetExpression(_expression);
						programStructure.AddCommand(cmd);
					}
				}
		;
		
cmdRead	:	"Read" "(" { cmd = new CommandRead(); }
				ID
				{
					String varName = LT(0).getText();
					if(CheckVariableCanBeUsed(varName)){
						Variable v = _symbolTable.get(varName);
						if(!CheckVariableIsConst(v)){
							((CommandRead) cmd).SetVariable(v);
						}
					}
				}
			")"
			PV
			{
				programStructure.AddCommand(cmd);
			}
		;

cmdWrite:	"Write" "(" { cmd = new CommandWrite(); }
			(
				TEXTO {((CommandWrite) cmd).SetContent(LT(0).getText());}
				|
				ID {
						String varName = LT(0).getText();
						if(CheckVariableCanBeUsed(varName)){
							Variable v = _symbolTable.get(varName);
							((CommandWrite) cmd).SetType(CommandWrite.TYPE_ID);
							((CommandWrite) cmd).SetVariable(v);
						}
					}
			) ")"
			PV
			{
				programStructure.AddCommand(cmd);
			}
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

cmdExpr	: 	termo
				{
					_expression += " " + LT(0).getText();
				}
			exprl
		;
       
exprl  	:  	(
				OP
				{
					_expression += " " + LT(0).getText();
				}
				termo
				{
					_expression += " " + LT(0).getText();
				}
			)*
		;
       
termo  	: 	ID 	{
					if(CheckVariableCanBeUsed(LT(0).getText())){
						Variable v = GetVariable(LT(0).getText());
						SetMaxType(v.GetType());
					}
				}
			|
			NUM { 
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
{
	public int _errorLine = 0;
}

BLANK       :	( 	  ' '
					| '\n'
						{
							_errorLine++;
						}
					| '\r'
					| '\t'
				)
				{ _ttype=Token.SKIP; }
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