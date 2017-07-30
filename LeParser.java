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

   public void init(){
       //programa = new Programa();
       //stack    = new StackCommand();
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
			match(AC);
			declare();
			block();
			match(FC);
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
				case LITERAL_str:
				case LITERAL_decimal:
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
			int _cnt15=0;
			_loop15:
			do {
				if ((_tokenSet_1.member(LA(1)))) {
					cmd();
				}
				else {
					if ( _cnt15>=1 ) { break _loop15; } else {throw new NoViableAltException(LT(1), getFilename());}
				}
				
				_cnt15++;
			} while (true);
			}
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_2);
		}
	}
	
	public final void var() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			type();
			match(ID);
			{
			_loop7:
			do {
				if ((LA(1)==VG)) {
					match(VG);
					match(ID);
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
			recover(ex,_tokenSet_3);
		}
	}
	
	public final void cte() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_cte);
			type();
			match(ID);
			attr();
			{
			_loop10:
			do {
				if ((LA(1)==VG)) {
					match(VG);
					match(ID);
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
			recover(ex,_tokenSet_3);
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
			case LITERAL_str:
			{
				match(LITERAL_str);
				break;
			}
			case LITERAL_decimal:
			{
				match(LITERAL_decimal);
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
			recover(ex,_tokenSet_4);
		}
	}
	
	public final void attr() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(IG);
			{
			switch ( LA(1)) {
			case NUM:
			{
				match(NUM);
				break;
			}
			case TEXTO:
			{
				match(TEXTO);
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
	
	public final void cmd() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			switch ( LA(1)) {
			case 18:
			{
				cmdRead();
				break;
			}
			case 20:
			{
				cmdWrite();
				break;
			}
			case LITERAL_if:
			{
				cmdIf();
				break;
			}
			case 25:
			{
				cmdFor();
				break;
			}
			case 28:
			{
				cmdWhile();
				break;
			}
			case 31:
			{
				cmdStr();
				break;
			}
			default:
				if ((LA(1)==ID) && (LA(2)==IG)) {
					cmdAttr();
				}
				else if ((LA(1)==ID||LA(1)==NUM||LA(1)==TEXTO) && (_tokenSet_6.member(LA(2)))) {
					cmdExpr();
				}
			else {
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
			match(18);
			match(ID);
			match(19);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_7);
		}
	}
	
	public final void cmdWrite() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(20);
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
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_7);
		}
	}
	
	public final void cmdIf() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(LITERAL_if);
			match(23);
			boolExpr();
			match(19);
			match(LITERAL_endif);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_7);
		}
	}
	
	public final void cmdFor() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(25);
			match(NUM);
			match(26);
			match(NUM);
			match(19);
			match(LITERAL_nextfor);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_7);
		}
	}
	
	public final void cmdWhile() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(28);
			boolExpr();
			match(19);
			match(LITERAL_nextfor);
		}
		catch (RecognitionException ex) {
			reportError(ex);
			recover(ex,_tokenSet_7);
		}
	}
	
	public final void cmdStr() throws RecognitionException, TokenStreamException {
		
		
		try {      // for error handling
			match(31);
			match(23);
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
			if ((LA(1)==ID||LA(1)==NUM||LA(1)==TEXTO) && (LA(2)==OPREL||LA(2)==OP)) {
				cmdExpr();
				match(OPREL);
				cmdExpr();
			}
			else if ((LA(1)==ID) && (LA(2)==19||LA(2)==OPLOG)) {
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
			case TEXTO:
			{
				match(TEXTO);
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
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"\"program\"",
		"ID",
		"AC",
		"FC",
		"VG",
		"PV",
		"\"cte\"",
		"\"int\"",
		"\"str\"",
		"\"decimal\"",
		"\"bool\"",
		"IG",
		"NUM",
		"TEXTO",
		"\"Read(\"",
		"\")\"",
		"\"Write(\"",
		"TEXT",
		"\"if\"",
		"\"(\"",
		"\"endif\"",
		"\"for(\"",
		"\":\"",
		"\"nextfor\"",
		"\"while(\"",
		"OPLOG",
		"OPREL",
		"\"str.Concat\"",
		"OP",
		"BLANK",
		"COMMENT"
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 2L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 2455175200L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 128L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { 2455206944L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 32L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = { 768L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6() {
		long[] data = { 6750142624L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	private static final long[] mk_tokenSet_7() {
		long[] data = { 2455175328L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
	private static final long[] mk_tokenSet_8() {
		long[] data = { 4066312352L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_8 = new BitSet(mk_tokenSet_8());
	private static final long[] mk_tokenSet_9() {
		long[] data = { 524288L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_9 = new BitSet(mk_tokenSet_9());
	private static final long[] mk_tokenSet_10() {
		long[] data = { 537395200L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_10 = new BitSet(mk_tokenSet_10());
	private static final long[] mk_tokenSet_11() {
		long[] data = { 8361279648L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_11 = new BitSet(mk_tokenSet_11());
	
	}
