// Generated from /home/joao/Desktop/leci_3ano/2semestre/C/P/testes/p2/FracLang.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue"})
public class FracLangParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, LITERAL_STRING=13, ID=14, NUM=15, WS=16, 
		COMMENT=17;
	public static final int
		RULE_program = 0, RULE_stat = 1, RULE_fraction = 2;
	private static String[] makeRuleNames() {
		return new String[] {
			"program", "stat", "fraction"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'display'", "'<='", "'+'", "'-'", "'('", "')'", "'*'", 
			"':'", "'read'", "'reduce'", "'/'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, "LITERAL_STRING", "ID", "NUM", "WS", "COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "FracLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public FracLangParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@SuppressWarnings("CheckReturnValue")
	public static class ProgramContext extends ParserRuleContext {
		public TerminalNode EOF() { return getToken(FracLangParser.EOF, 0); }
		public List<StatContext> stat() {
			return getRuleContexts(StatContext.class);
		}
		public StatContext stat(int i) {
			return getRuleContext(StatContext.class,i);
		}
		public ProgramContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_program; }
	}

	public final ProgramContext program() throws RecognitionException {
		ProgramContext _localctx = new ProgramContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_program);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(11);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__1 || _la==ID) {
				{
				{
				setState(6);
				stat();
				setState(7);
				match(T__0);
				}
				}
				setState(13);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(14);
			match(EOF);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class StatContext extends ParserRuleContext {
		public StatContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stat; }
	 
		public StatContext() { }
		public void copyFrom(StatContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StatAssigmentContext extends StatContext {
		public TerminalNode ID() { return getToken(FracLangParser.ID, 0); }
		public FractionContext fraction() {
			return getRuleContext(FractionContext.class,0);
		}
		public StatAssigmentContext(StatContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class StatDisplayContext extends StatContext {
		public FractionContext fraction() {
			return getRuleContext(FractionContext.class,0);
		}
		public StatDisplayContext(StatContext ctx) { copyFrom(ctx); }
	}

	public final StatContext stat() throws RecognitionException {
		StatContext _localctx = new StatContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_stat);
		try {
			setState(21);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
				_localctx = new StatDisplayContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(16);
				match(T__1);
				setState(17);
				fraction(0);
				}
				break;
			case ID:
				_localctx = new StatAssigmentContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(18);
				match(ID);
				setState(19);
				match(T__2);
				setState(20);
				fraction(0);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	@SuppressWarnings("CheckReturnValue")
	public static class FractionContext extends ParserRuleContext {
		public FractionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_fraction; }
	 
		public FractionContext() { }
		public void copyFrom(FractionContext ctx) {
			super.copyFrom(ctx);
		}
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FractionNumContext extends FractionContext {
		public TerminalNode NUM() { return getToken(FracLangParser.NUM, 0); }
		public FractionNumContext(FractionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FractionSumSubContext extends FractionContext {
		public FractionContext f1;
		public Token op;
		public FractionContext f2;
		public List<FractionContext> fraction() {
			return getRuleContexts(FractionContext.class);
		}
		public FractionContext fraction(int i) {
			return getRuleContext(FractionContext.class,i);
		}
		public FractionSumSubContext(FractionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FractionReduceContext extends FractionContext {
		public FractionContext fraction() {
			return getRuleContext(FractionContext.class,0);
		}
		public FractionReduceContext(FractionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FractionReadContext extends FractionContext {
		public TerminalNode LITERAL_STRING() { return getToken(FracLangParser.LITERAL_STRING, 0); }
		public FractionReadContext(FractionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FractionParenthesisContext extends FractionContext {
		public FractionContext fraction() {
			return getRuleContext(FractionContext.class,0);
		}
		public FractionParenthesisContext(FractionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FractionIdContext extends FractionContext {
		public TerminalNode ID() { return getToken(FracLangParser.ID, 0); }
		public FractionIdContext(FractionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FractionFractionContext extends FractionContext {
		public List<TerminalNode> NUM() { return getTokens(FracLangParser.NUM); }
		public TerminalNode NUM(int i) {
			return getToken(FracLangParser.NUM, i);
		}
		public FractionFractionContext(FractionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FractionMultDivContext extends FractionContext {
		public FractionContext f1;
		public Token op;
		public FractionContext f2;
		public List<FractionContext> fraction() {
			return getRuleContexts(FractionContext.class);
		}
		public FractionContext fraction(int i) {
			return getRuleContext(FractionContext.class,i);
		}
		public FractionMultDivContext(FractionContext ctx) { copyFrom(ctx); }
	}
	@SuppressWarnings("CheckReturnValue")
	public static class FractionSignContext extends FractionContext {
		public Token sign;
		public FractionContext fraction() {
			return getRuleContext(FractionContext.class,0);
		}
		public FractionSignContext(FractionContext ctx) { copyFrom(ctx); }
	}

	public final FractionContext fraction() throws RecognitionException {
		return fraction(0);
	}

	private FractionContext fraction(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		FractionContext _localctx = new FractionContext(_ctx, _parentState);
		FractionContext _prevctx = _localctx;
		int _startState = 4;
		enterRecursionRule(_localctx, 4, RULE_fraction, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				{
				_localctx = new FractionSignContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;

				setState(24);
				((FractionSignContext)_localctx).sign = _input.LT(1);
				_la = _input.LA(1);
				if ( !(_la==T__3 || _la==T__4) ) {
					((FractionSignContext)_localctx).sign = (Token)_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(25);
				fraction(9);
				}
				break;
			case 2:
				{
				_localctx = new FractionParenthesisContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(26);
				match(T__5);
				setState(27);
				fraction(0);
				setState(28);
				match(T__6);
				}
				break;
			case 3:
				{
				_localctx = new FractionReadContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(30);
				match(T__9);
				setState(31);
				match(LITERAL_STRING);
				}
				break;
			case 4:
				{
				_localctx = new FractionReduceContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(32);
				match(T__10);
				setState(33);
				fraction(4);
				}
				break;
			case 5:
				{
				_localctx = new FractionIdContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(34);
				match(ID);
				}
				break;
			case 6:
				{
				_localctx = new FractionNumContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(35);
				match(NUM);
				}
				break;
			case 7:
				{
				_localctx = new FractionFractionContext(_localctx);
				_ctx = _localctx;
				_prevctx = _localctx;
				setState(36);
				match(NUM);
				setState(37);
				match(T__11);
				setState(38);
				match(NUM);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(49);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(47);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
					case 1:
						{
						_localctx = new FractionMultDivContext(new FractionContext(_parentctx, _parentState));
						((FractionMultDivContext)_localctx).f1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_fraction);
						setState(41);
						if (!(precpred(_ctx, 7))) throw new FailedPredicateException(this, "precpred(_ctx, 7)");
						setState(42);
						((FractionMultDivContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__7 || _la==T__8) ) {
							((FractionMultDivContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(43);
						((FractionMultDivContext)_localctx).f2 = fraction(8);
						}
						break;
					case 2:
						{
						_localctx = new FractionSumSubContext(new FractionContext(_parentctx, _parentState));
						((FractionSumSubContext)_localctx).f1 = _prevctx;
						pushNewRecursionContext(_localctx, _startState, RULE_fraction);
						setState(44);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(45);
						((FractionSumSubContext)_localctx).op = _input.LT(1);
						_la = _input.LA(1);
						if ( !(_la==T__3 || _la==T__4) ) {
							((FractionSumSubContext)_localctx).op = (Token)_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(46);
						((FractionSumSubContext)_localctx).f2 = fraction(7);
						}
						break;
					}
					} 
				}
				setState(51);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 2:
			return fraction_sempred((FractionContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean fraction_sempred(FractionContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 7);
		case 1:
			return precpred(_ctx, 6);
		}
		return true;
	}

	public static final String _serializedATN =
		"\u0004\u0001\u00115\u0002\u0000\u0007\u0000\u0002\u0001\u0007\u0001\u0002"+
		"\u0002\u0007\u0002\u0001\u0000\u0001\u0000\u0001\u0000\u0005\u0000\n\b"+
		"\u0000\n\u0000\f\u0000\r\t\u0000\u0001\u0000\u0001\u0000\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0003\u0001\u0016\b\u0001\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0003\u0002(\b\u0002\u0001"+
		"\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0002\u0005"+
		"\u00020\b\u0002\n\u0002\f\u00023\t\u0002\u0001\u0002\u0000\u0001\u0004"+
		"\u0003\u0000\u0002\u0004\u0000\u0002\u0001\u0000\u0004\u0005\u0001\u0000"+
		"\b\t;\u0000\u000b\u0001\u0000\u0000\u0000\u0002\u0015\u0001\u0000\u0000"+
		"\u0000\u0004\'\u0001\u0000\u0000\u0000\u0006\u0007\u0003\u0002\u0001\u0000"+
		"\u0007\b\u0005\u0001\u0000\u0000\b\n\u0001\u0000\u0000\u0000\t\u0006\u0001"+
		"\u0000\u0000\u0000\n\r\u0001\u0000\u0000\u0000\u000b\t\u0001\u0000\u0000"+
		"\u0000\u000b\f\u0001\u0000\u0000\u0000\f\u000e\u0001\u0000\u0000\u0000"+
		"\r\u000b\u0001\u0000\u0000\u0000\u000e\u000f\u0005\u0000\u0000\u0001\u000f"+
		"\u0001\u0001\u0000\u0000\u0000\u0010\u0011\u0005\u0002\u0000\u0000\u0011"+
		"\u0016\u0003\u0004\u0002\u0000\u0012\u0013\u0005\u000e\u0000\u0000\u0013"+
		"\u0014\u0005\u0003\u0000\u0000\u0014\u0016\u0003\u0004\u0002\u0000\u0015"+
		"\u0010\u0001\u0000\u0000\u0000\u0015\u0012\u0001\u0000\u0000\u0000\u0016"+
		"\u0003\u0001\u0000\u0000\u0000\u0017\u0018\u0006\u0002\uffff\uffff\u0000"+
		"\u0018\u0019\u0007\u0000\u0000\u0000\u0019(\u0003\u0004\u0002\t\u001a"+
		"\u001b\u0005\u0006\u0000\u0000\u001b\u001c\u0003\u0004\u0002\u0000\u001c"+
		"\u001d\u0005\u0007\u0000\u0000\u001d(\u0001\u0000\u0000\u0000\u001e\u001f"+
		"\u0005\n\u0000\u0000\u001f(\u0005\r\u0000\u0000 !\u0005\u000b\u0000\u0000"+
		"!(\u0003\u0004\u0002\u0004\"(\u0005\u000e\u0000\u0000#(\u0005\u000f\u0000"+
		"\u0000$%\u0005\u000f\u0000\u0000%&\u0005\f\u0000\u0000&(\u0005\u000f\u0000"+
		"\u0000\'\u0017\u0001\u0000\u0000\u0000\'\u001a\u0001\u0000\u0000\u0000"+
		"\'\u001e\u0001\u0000\u0000\u0000\' \u0001\u0000\u0000\u0000\'\"\u0001"+
		"\u0000\u0000\u0000\'#\u0001\u0000\u0000\u0000\'$\u0001\u0000\u0000\u0000"+
		"(1\u0001\u0000\u0000\u0000)*\n\u0007\u0000\u0000*+\u0007\u0001\u0000\u0000"+
		"+0\u0003\u0004\u0002\b,-\n\u0006\u0000\u0000-.\u0007\u0000\u0000\u0000"+
		".0\u0003\u0004\u0002\u0007/)\u0001\u0000\u0000\u0000/,\u0001\u0000\u0000"+
		"\u000003\u0001\u0000\u0000\u00001/\u0001\u0000\u0000\u000012\u0001\u0000"+
		"\u0000\u00002\u0005\u0001\u0000\u0000\u000031\u0001\u0000\u0000\u0000"+
		"\u0005\u000b\u0015\'/1";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}