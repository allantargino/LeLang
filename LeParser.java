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

public class LeParser extends antlr.LLkParser       implements LeParserTokenTypes
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
			block();
			match(7);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_0);
		}
	}
	
	public final void declare() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			int _cnt4=0;
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
					if ( _cnt4>=1 ) { break _loop4; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				}
				_cnt4++;
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
			CheckVariableCanBeDeclared(LT(0).getText());
			{
			_loop7:
			do {
				if ((LA(1)==VG)) {
					match(VG);
					match(ID);
					CheckVariableCanBeDeclared(LT(0).getText());
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
			
								if(CheckVariableCanBeDeclared(LT(0).getText())){
									_varTo = GetVariable(LT(0).getText());
								}
							
			attr();
			{
			_loop10:
			do {
				if ((LA(1)==VG)) {
					match(VG);
					match(ID);
					
											if(CheckVariableCanBeDeclared(LT(0).getText())){
												_varTo = GetVariable(LT(0).getText());
											}
										
					attr();
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
			{
			switch ( LA(1)) {
			case ID:
			case NUM:
			case TEXTO:
			case LITERAL_true:
			case LITERAL_false:
			{
				cmdExpr();
				break;
			}
			case VG:
			case PV:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			CheckVariableAssignment();
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
			case LITERAL_for:
			{
				cmdFor();
				break;
			}
			case LITERAL_while:
			{
				cmdWhile();
				break;
			}
			case 32:
			{
				cmdStr();
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
			match(ID);
			CheckVariableCanBeUsed(LT(0).getText());
			match(18);
			match(PV);
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
			{
			switch ( LA(1)) {
			case TEXT:
			{
				match(TEXT);
				break;
			}
			case ID:
			{
				match(ID);
				CheckVariableCanBeUsed(LT(0).getText());
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
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_7);
		}
	}
	
	public final void cmdIf() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_if);
			match(17);
			boolExpr();
			match(18);
			block();
			match(LITERAL_endif);
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
			match(26);
			match(NUM);
			match(FP);
			match(LITERAL_nextfor);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_7);
		}
	}
	
	public final void cmdWhile() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_while);
			match(AP);
			boolExpr();
			match(FP);
			match(LITERAL_nextfor);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_7);
		}
	}
	
	public final void cmdStr() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(32);
			match(17);
			match(TEXT);
			{
			int _cnt33=0;
			_loop33:
			do {
				if ((LA(1)==VG)) {
					match(VG);
					match(TEXT);
				}
				else {
					if ( _cnt33>=1 ) { break _loop33; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt33++;
			} while (true);
			}
			match(18);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_7);
		}
	}
	
	public final void cmdExpr() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			termo();
			exprl();
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_8);
		}
	}
	
	public final void boolExpr() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			boolCond();
			{
			_loop28:
			do {
				if ((LA(1)==OPLOG)) {
					match(OPLOG);
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
				match(OPREL);
				cmdExpr();
			}
			else if ((LA(1)==ID) && (LA(2)==18||LA(2)==FP||LA(2)==OPLOG)) {
				match(ID);
				CheckVariableCanBeUsed(LT(0).getText());
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
				
									System.out.println(LT(0).getText());
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
			_loop37:
			do {
				if ((LA(1)==OP)) {
					match(OP);
					termo();
				}
				else {
					break _loop37;
				}
				
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_8);
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
		"TEXT",
		"\"if\"",
		"\"endif\"",
		"\"for\"",
		"AP",
		"NUM",
		"\":\"",
		"FP",
		"\"nextfor\"",
		"\"while\"",
		"OPLOG",
		"OPREL",
		"\"str.Concat\"",
		"OP",
		"TEXTO",
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
		long[] data = { 4842913952L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 4842913824L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 4194432L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 4842945696L, 0L};
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
		long[] data = { 4847108256L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
	private static final long[] mk_tokenSet_8() {
		long[] data = { 3355706112L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_8 = new BitSet(mk_tokenSet_8());
	private static final long[] mk_tokenSet_9() {
		long[] data = { 134479872L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_9 = new BitSet(mk_tokenSet_9());
	private static final long[] mk_tokenSet_10() {
		long[] data = { 120292638752L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_10 = new BitSet(mk_tokenSet_10());
	private static final long[] mk_tokenSet_11() {
		long[] data = { 1208221696L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_11 = new BitSet(mk_tokenSet_11());
	private static final long[] mk_tokenSet_12() {
		long[] data = { 11945640704L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_12 = new BitSet(mk_tokenSet_12());
	
	}
