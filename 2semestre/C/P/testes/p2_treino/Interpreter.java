import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@SuppressWarnings("CheckReturnValue")
public class Interpreter extends FracLangBaseVisitor<Fraction> {
   protected Map<String, Fraction> variables = new HashMap<>();
   protected Scanner scanner = new Scanner(System.in);

   @Override public Fraction visitStatDisplay(FracLangParser.StatDisplayContext ctx) {
      Fraction f = visit(ctx.expr());
      if (f != null) {
         System.out.println(f);
      }
      return null;
   }

   @Override public Fraction visitStatAssigment(FracLangParser.StatAssigmentContext ctx) {
      String varName = ctx.VARIABLE().getText();
      Fraction value = visit(ctx.expr());
      return variables.put(varName, value);
   }

   @Override public Fraction visitExprRead(FracLangParser.ExprReadContext ctx) {
      Fraction res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public Fraction visitExprVariable(FracLangParser.ExprVariableContext ctx) {
      String variable = ctx.VARIABLE().getText();
      if (!variables.containsKey(variable)) {
         System.err.printf("ERROR: Variable \"%s\" not found.", variable);
      }
      return variables.get(variable);
   }

   @Override public Fraction visitExprSumSub(FracLangParser.ExprSumSubContext ctx) {
      Fraction f1 = visit(ctx.e1);
      Fraction f2 = visit(ctx.e2);
      if (f1 != null && f2 != null) {
         if (ctx.op.getText().equals('+')) {
            return Fraction.sumFractions(f1, f2);
         }
         return Fraction.subtractFractions(f1, f2);
      }
      return null;
   }

   @Override public Fraction visitExprReduce(FracLangParser.ExprReduceContext ctx) {
      Fraction res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public Fraction visitExprUnary(FracLangParser.ExprUnaryContext ctx) {
      Fraction f1 = visit(ctx.expr());
      if (f1 == null) {
         return null;
      }
      if (ctx.sign.getText().equals("-")) {
         return Fraction.multiplyFractions(f1, new Fraction(-1, 1));
      }
      return f1;
   }

   @Override public Fraction visitExprFraction(FracLangParser.ExprFractionContext ctx) {
      return new Fraction(Integer.parseInt(ctx.NUM(0).getText()), Integer.parseInt(ctx.NUM(1).getText()));
   }

   @Override public Fraction visitExprParenthesis(FracLangParser.ExprParenthesisContext ctx) {
      return visit(ctx.expr());
   }

   @Override public Fraction visitExprNum(FracLangParser.ExprNumContext ctx) {
      return new Fraction(Integer.parseInt(ctx.NUM().getText()), 1);
   }

   @Override public Fraction visitExprMulDiv(FracLangParser.ExprMulDivContext ctx) {
      Fraction f1 = visit(ctx.e1);
      Fraction f2 = visit(ctx.e2);
      if (f1 != null && f2 != null) {
         if (ctx.op.getText().equals('*')) {
            return Fraction.multiplyFractions(f1, f2);
         }
         return Fraction.divideFractions(f1, f2);
      }
      return null;
   }
}
