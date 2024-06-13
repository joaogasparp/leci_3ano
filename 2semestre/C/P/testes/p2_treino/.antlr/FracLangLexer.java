// Generated from /home/joao/Desktop/leci_3ano/2semestre/C/P/testes/p2_treino/FracLang.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast", "CheckReturnValue", "this-escape"})
public class FracLangLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.13.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, STR=13, NUM=14, VARIABLE=15, WS=16, COMMENT=17;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"T__9", "T__10", "T__11", "STR", "NUM", "VARIABLE", "WS", "COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'display'", "'<='", "'+'", "'-'", "'*'", "':'", "'('", 
			"')'", "'read'", "'reduce'", "'/'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, "STR", "NUM", "VARIABLE", "WS", "COMMENT"
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


	public FracLangLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "FracLang.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\u0004\u0000\u0011q\u0006\uffff\uffff\u0002\u0000\u0007\u0000\u0002\u0001"+
		"\u0007\u0001\u0002\u0002\u0007\u0002\u0002\u0003\u0007\u0003\u0002\u0004"+
		"\u0007\u0004\u0002\u0005\u0007\u0005\u0002\u0006\u0007\u0006\u0002\u0007"+
		"\u0007\u0007\u0002\b\u0007\b\u0002\t\u0007\t\u0002\n\u0007\n\u0002\u000b"+
		"\u0007\u000b\u0002\f\u0007\f\u0002\r\u0007\r\u0002\u000e\u0007\u000e\u0002"+
		"\u000f\u0007\u000f\u0002\u0010\u0007\u0010\u0001\u0000\u0001\u0000\u0001"+
		"\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001\u0001"+
		"\u0001\u0001\u0001\u0001\u0002\u0001\u0002\u0001\u0002\u0001\u0003\u0001"+
		"\u0003\u0001\u0004\u0001\u0004\u0001\u0005\u0001\u0005\u0001\u0006\u0001"+
		"\u0006\u0001\u0007\u0001\u0007\u0001\b\u0001\b\u0001\t\u0001\t\u0001\t"+
		"\u0001\t\u0001\t\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001\n\u0001"+
		"\n\u0001\u000b\u0001\u000b\u0001\f\u0001\f\u0005\fM\b\f\n\f\f\fP\t\f\u0001"+
		"\f\u0001\f\u0001\r\u0004\rU\b\r\u000b\r\f\rV\u0001\u000e\u0004\u000eZ"+
		"\b\u000e\u000b\u000e\f\u000e[\u0001\u000f\u0004\u000f_\b\u000f\u000b\u000f"+
		"\f\u000f`\u0001\u000f\u0001\u000f\u0001\u0010\u0001\u0010\u0001\u0010"+
		"\u0001\u0010\u0005\u0010i\b\u0010\n\u0010\f\u0010l\t\u0010\u0001\u0010"+
		"\u0001\u0010\u0001\u0010\u0001\u0010\u0002Nj\u0000\u0011\u0001\u0001\u0003"+
		"\u0002\u0005\u0003\u0007\u0004\t\u0005\u000b\u0006\r\u0007\u000f\b\u0011"+
		"\t\u0013\n\u0015\u000b\u0017\f\u0019\r\u001b\u000e\u001d\u000f\u001f\u0010"+
		"!\u0011\u0001\u0000\u0003\u0001\u000009\u0001\u0000az\u0003\u0000\t\n"+
		"\r\r  u\u0000\u0001\u0001\u0000\u0000\u0000\u0000\u0003\u0001\u0000\u0000"+
		"\u0000\u0000\u0005\u0001\u0000\u0000\u0000\u0000\u0007\u0001\u0000\u0000"+
		"\u0000\u0000\t\u0001\u0000\u0000\u0000\u0000\u000b\u0001\u0000\u0000\u0000"+
		"\u0000\r\u0001\u0000\u0000\u0000\u0000\u000f\u0001\u0000\u0000\u0000\u0000"+
		"\u0011\u0001\u0000\u0000\u0000\u0000\u0013\u0001\u0000\u0000\u0000\u0000"+
		"\u0015\u0001\u0000\u0000\u0000\u0000\u0017\u0001\u0000\u0000\u0000\u0000"+
		"\u0019\u0001\u0000\u0000\u0000\u0000\u001b\u0001\u0000\u0000\u0000\u0000"+
		"\u001d\u0001\u0000\u0000\u0000\u0000\u001f\u0001\u0000\u0000\u0000\u0000"+
		"!\u0001\u0000\u0000\u0000\u0001#\u0001\u0000\u0000\u0000\u0003%\u0001"+
		"\u0000\u0000\u0000\u0005-\u0001\u0000\u0000\u0000\u00070\u0001\u0000\u0000"+
		"\u0000\t2\u0001\u0000\u0000\u0000\u000b4\u0001\u0000\u0000\u0000\r6\u0001"+
		"\u0000\u0000\u0000\u000f8\u0001\u0000\u0000\u0000\u0011:\u0001\u0000\u0000"+
		"\u0000\u0013<\u0001\u0000\u0000\u0000\u0015A\u0001\u0000\u0000\u0000\u0017"+
		"H\u0001\u0000\u0000\u0000\u0019J\u0001\u0000\u0000\u0000\u001bT\u0001"+
		"\u0000\u0000\u0000\u001dY\u0001\u0000\u0000\u0000\u001f^\u0001\u0000\u0000"+
		"\u0000!d\u0001\u0000\u0000\u0000#$\u0005;\u0000\u0000$\u0002\u0001\u0000"+
		"\u0000\u0000%&\u0005d\u0000\u0000&\'\u0005i\u0000\u0000\'(\u0005s\u0000"+
		"\u0000()\u0005p\u0000\u0000)*\u0005l\u0000\u0000*+\u0005a\u0000\u0000"+
		"+,\u0005y\u0000\u0000,\u0004\u0001\u0000\u0000\u0000-.\u0005<\u0000\u0000"+
		"./\u0005=\u0000\u0000/\u0006\u0001\u0000\u0000\u000001\u0005+\u0000\u0000"+
		"1\b\u0001\u0000\u0000\u000023\u0005-\u0000\u00003\n\u0001\u0000\u0000"+
		"\u000045\u0005*\u0000\u00005\f\u0001\u0000\u0000\u000067\u0005:\u0000"+
		"\u00007\u000e\u0001\u0000\u0000\u000089\u0005(\u0000\u00009\u0010\u0001"+
		"\u0000\u0000\u0000:;\u0005)\u0000\u0000;\u0012\u0001\u0000\u0000\u0000"+
		"<=\u0005r\u0000\u0000=>\u0005e\u0000\u0000>?\u0005a\u0000\u0000?@\u0005"+
		"d\u0000\u0000@\u0014\u0001\u0000\u0000\u0000AB\u0005r\u0000\u0000BC\u0005"+
		"e\u0000\u0000CD\u0005d\u0000\u0000DE\u0005u\u0000\u0000EF\u0005c\u0000"+
		"\u0000FG\u0005e\u0000\u0000G\u0016\u0001\u0000\u0000\u0000HI\u0005/\u0000"+
		"\u0000I\u0018\u0001\u0000\u0000\u0000JN\u0005\"\u0000\u0000KM\t\u0000"+
		"\u0000\u0000LK\u0001\u0000\u0000\u0000MP\u0001\u0000\u0000\u0000NO\u0001"+
		"\u0000\u0000\u0000NL\u0001\u0000\u0000\u0000OQ\u0001\u0000\u0000\u0000"+
		"PN\u0001\u0000\u0000\u0000QR\u0005\"\u0000\u0000R\u001a\u0001\u0000\u0000"+
		"\u0000SU\u0007\u0000\u0000\u0000TS\u0001\u0000\u0000\u0000UV\u0001\u0000"+
		"\u0000\u0000VT\u0001\u0000\u0000\u0000VW\u0001\u0000\u0000\u0000W\u001c"+
		"\u0001\u0000\u0000\u0000XZ\u0007\u0001\u0000\u0000YX\u0001\u0000\u0000"+
		"\u0000Z[\u0001\u0000\u0000\u0000[Y\u0001\u0000\u0000\u0000[\\\u0001\u0000"+
		"\u0000\u0000\\\u001e\u0001\u0000\u0000\u0000]_\u0007\u0002\u0000\u0000"+
		"^]\u0001\u0000\u0000\u0000_`\u0001\u0000\u0000\u0000`^\u0001\u0000\u0000"+
		"\u0000`a\u0001\u0000\u0000\u0000ab\u0001\u0000\u0000\u0000bc\u0006\u000f"+
		"\u0000\u0000c \u0001\u0000\u0000\u0000de\u0005-\u0000\u0000ef\u0005-\u0000"+
		"\u0000fj\u0001\u0000\u0000\u0000gi\t\u0000\u0000\u0000hg\u0001\u0000\u0000"+
		"\u0000il\u0001\u0000\u0000\u0000jk\u0001\u0000\u0000\u0000jh\u0001\u0000"+
		"\u0000\u0000km\u0001\u0000\u0000\u0000lj\u0001\u0000\u0000\u0000mn\u0005"+
		"\n\u0000\u0000no\u0001\u0000\u0000\u0000op\u0006\u0010\u0000\u0000p\""+
		"\u0001\u0000\u0000\u0000\u0006\u0000NV[`j\u0001\u0006\u0000\u0000";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}