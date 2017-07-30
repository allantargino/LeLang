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
			CheckVariableIsDeclared(LT(0).getText());
			{
			_loop7:
			do {
				if ((LA(1)==VG)) {
					match(VG);
					match(ID);
					CheckVariableIsDeclared(LT(0).getText());
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
			CheckVariableIsDeclared(LT(0).getText());
			attr();
			{
			_loop10:
			do {
				if ((LA(1)==VG)) {
					match(VG);
					match(ID);
					CheckVariableIsDeclared(LT(0).getText());
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
			{
			switch ( LA(1)) {
			case ID:
			case NUM:
			{
				cmdExpr();
				break;
			}
			case TEXTO:
			{
				match(TEXTO);
				break;
			}
			case LITERAL_true:
			case LITERAL_false:
			{
				boolVal();
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
			case 35:
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
			match(18);
			match(ID);
			match(19);
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
			match(18);
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
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			match(19);
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
			match(18);
			boolExpr();
			match(19);
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
			match(27);
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
			match(35);
			match(18);
			match(TEXT);
			{
			int _cnt35=0;
			_loop35:
			do {
				if ((LA(1)==VG)) {
					match(VG);
					match(TEXT);
				}
				else {
					if ( _cnt35>=1 ) { break _loop35; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt35++;
			} while (true);
			}
			match(19);
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
	
	public final void boolVal() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
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
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_6);
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
			if ((LA(1)==ID||LA(1)==NUM) && (LA(2)==OPREL||LA(2)==OP)) {
				cmdExpr();
				match(OPREL);
				cmdExpr();
			}
			else if ((LA(1)==ID) && (LA(2)==19||LA(2)==FP||LA(2)==OPLOG)) {
				match(ID);
			}
			else {
				throw new NoViableAltException(LT(1), getFilename());
			}
			
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_10);
		}
	}
	
	public final void termo() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case ID:
			{
				match(ID);
				break;
			}
			case NUM:
			{
				match(NUM);
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
			recover(ex,_tokenSet_11);
		}
	}
	
	public final void exprl() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			{
			_loop39:
			do {
				if ((LA(1)==OP)) {
					match(OP);
					termo();
				}
				else {
					break _loop39;
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
		"TEXTO",
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
		"\"true\"",
		"\"false\"",
		"\"str.Concat\"",
		"OP",
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
		long[] data = { 35455631520L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 35455631392L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 8388736L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 35455663264L, 0L};
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
		long[] data = { 35464020128L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
	private static final long[] mk_tokenSet_8() {
		long[] data = { 6711411456L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_8 = new BitSet(mk_tokenSet_8());
	private static final long[] mk_tokenSet_9() {
		long[] data = { 268959744L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_9 = new BitSet(mk_tokenSet_9());
	private static final long[] mk_tokenSet_10() {
		long[] data = { 2416443392L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_10 = new BitSet(mk_tokenSet_10());
	private static final long[] mk_tokenSet_11() {
		long[] data = { 75430888192L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_11 = new BitSet(mk_tokenSet_11());
	
	}
