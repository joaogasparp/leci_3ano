// Generated from TupleGrammar.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link TupleGrammarParser}.
 */
public interface TupleGrammarListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link TupleGrammarParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(TupleGrammarParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link TupleGrammarParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(TupleGrammarParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by the {@code statPrintLine}
	 * labeled alternative in {@link TupleGrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStatPrintLine(TupleGrammarParser.StatPrintLineContext ctx);
	/**
	 * Exit a parse tree produced by the {@code statPrintLine}
	 * labeled alternative in {@link TupleGrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStatPrintLine(TupleGrammarParser.StatPrintLineContext ctx);
	/**
	 * Enter a parse tree produced by the {@code statPrint}
	 * labeled alternative in {@link TupleGrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStatPrint(TupleGrammarParser.StatPrintContext ctx);
	/**
	 * Exit a parse tree produced by the {@code statPrint}
	 * labeled alternative in {@link TupleGrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStatPrint(TupleGrammarParser.StatPrintContext ctx);
	/**
	 * Enter a parse tree produced by the {@code statAssign}
	 * labeled alternative in {@link TupleGrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStatAssign(TupleGrammarParser.StatAssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code statAssign}
	 * labeled alternative in {@link TupleGrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStatAssign(TupleGrammarParser.StatAssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code statHead}
	 * labeled alternative in {@link TupleGrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStatHead(TupleGrammarParser.StatHeadContext ctx);
	/**
	 * Exit a parse tree produced by the {@code statHead}
	 * labeled alternative in {@link TupleGrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStatHead(TupleGrammarParser.StatHeadContext ctx);
	/**
	 * Enter a parse tree produced by the {@code statTail}
	 * labeled alternative in {@link TupleGrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStatTail(TupleGrammarParser.StatTailContext ctx);
	/**
	 * Exit a parse tree produced by the {@code statTail}
	 * labeled alternative in {@link TupleGrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStatTail(TupleGrammarParser.StatTailContext ctx);
	/**
	 * Enter a parse tree produced by the {@code statZip}
	 * labeled alternative in {@link TupleGrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void enterStatZip(TupleGrammarParser.StatZipContext ctx);
	/**
	 * Exit a parse tree produced by the {@code statZip}
	 * labeled alternative in {@link TupleGrammarParser#stat}.
	 * @param ctx the parse tree
	 */
	void exitStatZip(TupleGrammarParser.StatZipContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprVariable}
	 * labeled alternative in {@link TupleGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprVariable(TupleGrammarParser.ExprVariableContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprVariable}
	 * labeled alternative in {@link TupleGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprVariable(TupleGrammarParser.ExprVariableContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprAddSub}
	 * labeled alternative in {@link TupleGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprAddSub(TupleGrammarParser.ExprAddSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprAddSub}
	 * labeled alternative in {@link TupleGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprAddSub(TupleGrammarParser.ExprAddSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprUnary}
	 * labeled alternative in {@link TupleGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprUnary(TupleGrammarParser.ExprUnaryContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprUnary}
	 * labeled alternative in {@link TupleGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprUnary(TupleGrammarParser.ExprUnaryContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprParenthesis}
	 * labeled alternative in {@link TupleGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprParenthesis(TupleGrammarParser.ExprParenthesisContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprParenthesis}
	 * labeled alternative in {@link TupleGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprParenthesis(TupleGrammarParser.ExprParenthesisContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprNumber}
	 * labeled alternative in {@link TupleGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprNumber(TupleGrammarParser.ExprNumberContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprNumber}
	 * labeled alternative in {@link TupleGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprNumber(TupleGrammarParser.ExprNumberContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprElements}
	 * labeled alternative in {@link TupleGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprElements(TupleGrammarParser.ExprElementsContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprElements}
	 * labeled alternative in {@link TupleGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprElements(TupleGrammarParser.ExprElementsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code exprMulDiv}
	 * labeled alternative in {@link TupleGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExprMulDiv(TupleGrammarParser.ExprMulDivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code exprMulDiv}
	 * labeled alternative in {@link TupleGrammarParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExprMulDiv(TupleGrammarParser.ExprMulDivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code elementsList}
	 * labeled alternative in {@link TupleGrammarParser#elements}.
	 * @param ctx the parse tree
	 */
	void enterElementsList(TupleGrammarParser.ElementsListContext ctx);
	/**
	 * Exit a parse tree produced by the {@code elementsList}
	 * labeled alternative in {@link TupleGrammarParser#elements}.
	 * @param ctx the parse tree
	 */
	void exitElementsList(TupleGrammarParser.ElementsListContext ctx);
}