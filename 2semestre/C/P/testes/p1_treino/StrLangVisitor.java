// Generated from StrLang.g4 by ANTLR 4.13.1
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link StrLangParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface StrLangVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link StrLangParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(StrLangParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by the {@code statPrint}
	 * labeled alternative in {@link StrLangParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatPrint(StrLangParser.StatPrintContext ctx);
	/**
	 * Visit a parse tree produced by the {@code statAssign}
	 * labeled alternative in {@link StrLangParser#stat}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStatAssign(StrLangParser.StatAssignContext ctx);
	/**
	 * Visit a parse tree produced by the {@code printableString}
	 * labeled alternative in {@link StrLangParser#printable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintableString(StrLangParser.PrintableStringContext ctx);
	/**
	 * Visit a parse tree produced by the {@code printableSubstitution}
	 * labeled alternative in {@link StrLangParser#printable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintableSubstitution(StrLangParser.PrintableSubstitutionContext ctx);
	/**
	 * Visit a parse tree produced by the {@code printableVariable}
	 * labeled alternative in {@link StrLangParser#printable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintableVariable(StrLangParser.PrintableVariableContext ctx);
	/**
	 * Visit a parse tree produced by the {@code printableTrim}
	 * labeled alternative in {@link StrLangParser#printable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintableTrim(StrLangParser.PrintableTrimContext ctx);
	/**
	 * Visit a parse tree produced by the {@code printableParenthesis}
	 * labeled alternative in {@link StrLangParser#printable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintableParenthesis(StrLangParser.PrintableParenthesisContext ctx);
	/**
	 * Visit a parse tree produced by the {@code printableRemove}
	 * labeled alternative in {@link StrLangParser#printable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintableRemove(StrLangParser.PrintableRemoveContext ctx);
	/**
	 * Visit a parse tree produced by the {@code printableInput}
	 * labeled alternative in {@link StrLangParser#printable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintableInput(StrLangParser.PrintableInputContext ctx);
	/**
	 * Visit a parse tree produced by the {@code printableConcat}
	 * labeled alternative in {@link StrLangParser#printable}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrintableConcat(StrLangParser.PrintableConcatContext ctx);
}