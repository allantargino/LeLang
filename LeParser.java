// $ANTLR 2.7.6 (2005-12-22): "grammar.g" -> "LeParser.java"$

import antlr.TokenBuffer;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.ANTLRException;
import antlr.LLkParser;
import antlr.Token;
import antlr.TokenStream;
import antlr.RecognitionException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.ParserSharedInputState;
import antlr.collections.impl.BitSet;

	import java.util.*;

public class LeParser extends antlr.LLkParser       implements LeParserTokenTypes
 {

	// Variable Fields
	private HashMap<String, Variable> _symbolTable; 
	private int _varType;
	private Boolean _endOfAssignment;

	// Variable Assignment
	private int _varFrom;
	private Variable _varTo;
	private String _mathExpression;
	private String _logicalExpression;
	
	// Error Fields
	private ArrayList<Error> _errorList;


	// Code Writing
	private Command _cmd;
	private ProgramStructure _programStructure;
	private Stack<Command> _cmdStack;

	public void Init(){
		_symbolTable = new HashMap<String, Variable>(); 
		_varType=0;
		_endOfAssignment=false;

		_varFrom=0;
		_varTo=null;

		_errorList = new ArrayList<Error>();

		_programStructure = new ProgramStructure();
		_cmdStack = new Stack<Command>();
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

	private Boolean CheckVariableIsBoolean(Variable v){
		if (v.GetType()==Variable.BOOLEAN){
			return true;
		}else{
			CreateError(6, "Variable " + _varTo.GetId() +  " is not bool.");
			return false;
		}
	}

	//Code Writing

	private void AddGlobalCommand(){
		if(_cmdStack.empty())
			_programStructure.AddCommand(_cmd);
		else
			((CommandReceiver)_cmdStack.peek()).AddCommand(_cmd);
	}

protected LeParser(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
}

public LeParser(TokenBuffer tokenBuf) {
  this(tokenBuf,2);
}

protected LeParser(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
}

public LeParser(TokenStream lexer) {
  this(lexer,2);
}

public LeParser(ParserSharedInputState state) {
  super(state,2);
  tokenNames = _tokenNames;
}

	public final void program() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_program);
			match(ID);
			match(6);
			declare();
			
								_endOfAssignment = true;
								for (Variable v : _symbolTable.values()) {
									_programStructure.AddVariable(v);
								}
							
			block();
			match(7);
			
							System.out.println(_programStructure.WriteCode());
						
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
	}
	
	public final void declare() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			_loop4:
			do {
				switch ( LA(1)) {
				case LITERAL_int:
				case LITERAL_decimal:
				case LITERAL_str:
				case LITERAL_bool:
				{
					var();
					break;
				}
				case LITERAL_cte:
				{
					cte();
					break;
				}
				default:
				{
					break _loop4;
				}
				}
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_1);
		}
	}
	
	public final void block() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			_loop15:
			do {
				if ((_tokenSet_2.member(LA(1)))) {
					cmd();
				}
				else {
					break _loop15;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_3);
		}
	}
	
	public final void var() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			type();
			_varType = Variable.GetTypeNumber(LT(0).getText());
			match(ID);
			CheckAndDeclareVariable(LT(0).getText(), false);
			{
			_loop7:
			do {
				if ((LA(1)==VG)) {
					match(VG);
					match(ID);
					CheckAndDeclareVariable(LT(0).getText(), false);
				}
				else {
					break _loop7;
				}
				
			} while (true);
			}
			match(PV);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_4);
		}
	}
	
	public final void cte() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_cte);
			type();
			_varType = Variable.GetTypeNumber(LT(0).getText());
			match(ID);
			
								if(CheckAndDeclareVariable(LT(0).getText(), true)){
									_varTo = GetVariable(LT(0).getText());
								}
							
			attr();
			_varTo.SetExpression(_mathExpression);
			{
			_loop10:
			do {
				if ((LA(1)==VG)) {
					match(VG);
					match(ID);
					
											if(CheckAndDeclareVariable(LT(0).getText(), true)){
												_varTo = GetVariable(LT(0).getText());
											}
										
					attr();
					_varTo.SetExpression(_mathExpression);
				}
				else {
					break _loop10;
				}
				
			} while (true);
			}
			match(PV);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_4);
		}
	}
	
	public final void type() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			switch ( LA(1)) {
			case LITERAL_int:
			{
				match(LITERAL_int);
				break;
			}
			case LITERAL_decimal:
			{
				match(LITERAL_decimal);
				break;
			}
			case LITERAL_str:
			{
				match(LITERAL_str);
				break;
			}
			case LITERAL_bool:
			{
				match(LITERAL_bool);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_5);
		}
	}
	
	public final void attr() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(IG);
			
								_varFrom = 0;
							
			cmdExpr();
			
								if(CheckVariableAssignment() && !CheckVariableIsConst())
								{
									((CommandAssign) _cmd).SetToVariable(_varTo);
									((CommandAssign) _cmd).SetExpression(_mathExpression);
									AddGlobalCommand();
								}
							
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_6);
		}
	}
	
	public final void cmd() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case ID:
			{
				cmdAttr();
				break;
			}
			case LITERAL_Read:
			{
				cmdRead();
				break;
			}
			case LITERAL_Write:
			{
				cmdWrite();
				break;
			}
			case LITERAL_if:
			{
				cmdIf();
				break;
			}
			case LITERAL_while:
			{
				cmdWhile();
				break;
			}
			case LITERAL_for:
			{
				cmdFor();
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_7);
		}
	}
	
	public final void cmdAttr() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(ID);
			
								if(CheckVariableCanBeUsed(LT(0).getText())){
									_varTo = GetVariable(LT(0).getText());
									_cmd = new CommandAssign();					
								}
							
			attr();
			match(PV);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_7);
		}
	}
	
	public final void cmdRead() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_Read);
			match(17);
			_cmd = new CommandRead();
			match(ID);
			
								String varName = LT(0).getText();
								if(CheckVariableCanBeUsed(varName)){
									Variable v = _symbolTable.get(varName);
									if(!CheckVariableIsConst(v)){
										((CommandRead) _cmd).SetVariable(v);
									}
								}
							
			match(18);
			match(PV);
			
							AddGlobalCommand();
						
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_7);
		}
	}
	
	public final void cmdWrite() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_Write);
			match(17);
			_cmd = new CommandWrite();
			{
			switch ( LA(1)) {
			case TEXTO:
			{
				match(TEXTO);
				((CommandWrite) _cmd).SetContent(LT(0).getText());
				break;
			}
			case ID:
			{
				match(ID);
				
										String varName = LT(0).getText();
										if(CheckVariableCanBeUsed(varName)){
											Variable v = _symbolTable.get(varName);
											((CommandWrite) _cmd).SetType(CommandWrite.TYPE_ID);
											((CommandWrite) _cmd).SetVariable(v);
										}
									
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(18);
			match(PV);
			
							AddGlobalCommand();
						
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_7);
		}
	}
	
	public final void cmdIf() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_if);
			
								_cmd = new CommandIf();
								AddGlobalCommand();
								_cmdStack.push(_cmd);
							
			match(17);
			boolExpr();
			((CommandIf)_cmd).SetLogicalExpression(_logicalExpression);
			match(18);
			block();
			{
			switch ( LA(1)) {
			case LITERAL_else:
			{
				match(LITERAL_else);
				((CommandIf) _cmdStack.peek()).SetElseFlag(true);
				block();
				break;
			}
			case LITERAL_endif:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(LITERAL_endif);
			_cmdStack.pop();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_7);
		}
	}
	
	public final void cmdWhile() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_while);
			
								_cmd = new CommandWhile();
								AddGlobalCommand();
								_cmdStack.push(_cmd);
							
			match(17);
			boolExpr();
			((CommandWhile)_cmd).SetLogicalExpression(_logicalExpression);
			match(18);
			block();
			match(LITERAL_next);
			_cmdStack.pop();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_7);
		}
	}
	
	public final void cmdFor() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_for);
			match(AP);
			match(NUM);
			match(29);
			match(NUM);
			match(FP);
			match(LITERAL_next);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_7);
		}
	}
	
	public final void cmdExpr() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			_mathExpression = "";
			termo();
			
								_mathExpression += " " + LT(0).getText();
							
			exprl();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_8);
		}
	}
	
	public final void boolExpr() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			_logicalExpression = "";
			boolCond();
			{
			_loop28:
			do {
				if ((LA(1)==OPLOG)) {
					match(OPLOG);
					_logicalExpression += " " + LT(0).getText();
					boolCond();
				}
				else {
					break _loop28;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_9);
		}
	}
	
	public final void boolCond() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			if ((_tokenSet_10.member(LA(1))) && (LA(2)==OPREL||LA(2)==OP)) {
				cmdExpr();
				_logicalExpression+=_mathExpression;
				match(OPREL);
				_logicalExpression+= " " + LT(0).getText();
				cmdExpr();
				_logicalExpression+=_mathExpression + " ";
			}
			else if ((LA(1)==ID) && (LA(2)==18||LA(2)==OPLOG)) {
				match(ID);
				
										if(CheckVariableCanBeUsed(LT(0).getText())){
											Variable v = GetVariable(LT(0).getText());
											if(CheckVariableIsBoolean(v)){
												_logicalExpression+=v.GetId();
											}
										}
									
			}
			else if ((LA(1)==LITERAL_true||LA(1)==LITERAL_false) && (LA(2)==18||LA(2)==OPLOG)) {
				boolVal();
				_logicalExpression+=LT(0).getText();
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_11);
		}
	}
	
	public final void boolVal() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case LITERAL_true:
			{
				match(LITERAL_true);
				break;
			}
			case LITERAL_false:
			{
				match(LITERAL_false);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_12);
		}
	}
	
	public final void termo() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case ID:
			{
				match(ID);
				
									if(CheckVariableCanBeUsed(LT(0).getText())){
										Variable v = GetVariable(LT(0).getText());
										SetMaxType(v.GetType());
									}
								
				break;
			}
			case NUM:
			{
				match(NUM);
				
									if(NumberIsDecimal(LT(0).getText()))
										SetMaxType(Variable.DECIMAL);
									else
										SetMaxType(Variable.INTEGER);
								
				break;
			}
			case TEXTO:
			{
				match(TEXTO);
				SetMaxType(Variable.STRING);
				break;
			}
			case LITERAL_true:
			case LITERAL_false:
			{
				boolVal();
				SetMaxType(Variable.BOOLEAN);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_12);
		}
	}
	
	public final void exprl() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			_loop34:
			do {
				if ((LA(1)==OP)) {
					match(OP);
					
										_mathExpression += " " + LT(0).getText();
									
					termo();
					
										_mathExpression += " " + LT(0).getText();
									
				}
				else {
					break _loop34;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_8);
		}
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"\"program\"",
		"ID",
		"\"{\"",
		"\"}\"",
		"VG",
		"PV",
		"\"cte\"",
		"\"int\"",
		"\"decimal\"",
		"\"str\"",
		"\"bool\"",
		"IG",
		"\"Read\"",
		"\"(\"",
		"\")\"",
		"\"Write\"",
		"TEXTO",
		"\"if\"",
		"\"else\"",
		"\"endif\"",
		"\"while\"",
		"\"next\"",
		"\"for\"",
		"AP",
		"NUM",
		"\":\"",
		"FP",
		"OPLOG",
		"OPREL",
		"OP",
		"\"true\"",
		"\"false\"",
		"BLANK",
		"COMMENT",
		"AC",
		"FC"
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 2L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 86573216L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 86573088L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 46137472L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 86604960L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { 32L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6() {
		long[] data = { 768L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	private static final long[] mk_tokenSet_7() {
		long[] data = { 132710560L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
	private static final long[] mk_tokenSet_8() {
		long[] data = { 6442713856L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_8 = new BitSet(mk_tokenSet_8());
	private static final long[] mk_tokenSet_9() {
		long[] data = { 262144L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_9 = new BitSet(mk_tokenSet_9());
	private static final long[] mk_tokenSet_10() {
		long[] data = { 51809091616L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_10 = new BitSet(mk_tokenSet_10());
	private static final long[] mk_tokenSet_11() {
		long[] data = { 2147745792L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_11 = new BitSet(mk_tokenSet_11());
	private static final long[] mk_tokenSet_12() {
		long[] data = { 15032648448L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_12 = new BitSet(mk_tokenSet_12());
	
	}
