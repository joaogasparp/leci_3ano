@SuppressWarnings("CheckReturnValue")
public class Interpreter extends CalculatorBaseVisitor<Double> {

   @Override public Double visitProgram(CalculatorParser.ProgramContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Double visitStat(CalculatorParser.StatContext ctx) {
      Double res = visit(ctx.expr());
      if (res != null)
         System.out.println(res);
      return res;
   }

   @Override public Double visitExprMulDivMod(CalculatorParser.ExprMulDivModContext ctx) {
      Double n1 = visit(ctx.expr(0));
      Double n2 = visit(ctx.expr(1));
      String op = ctx.op.getText();

      if (n1 == null || n2 == null)
         return null;

      switch (op) {
         case "*":
            return n1 * n2;
         case "/":
            return n2 == 0 ? null : n1 / n2;
         case "%":
            return n1 % n2;
         default:
            return null;
      }
   }

   @Override public Double visitExprAddSub(CalculatorParser.ExprAddSubContext ctx) {
      Double n1 = visit(ctx.expr(0));
      Double n2 = visit(ctx.expr(1));
      String op = ctx.op.getText();

      if (n1 == null || n2 == null)
         return null;

      switch (op) {
         case "+":
            return n1 + n2;
         case "-":
            return n1 - n2;
         default:
            return null;
      }
   }

   @Override public Double visitExprParent(CalculatorParser.ExprParentContext ctx) {
      return visit(ctx.expr());
   }

   @Override public Double visitExprUnary(CalculatorParser.ExprUnaryContext ctx) {
      Double n = visit(ctx.expr());
      String op = ctx.op.getText();
      if (n == null)
         return null;
      if (op.equals("-"))
         return -n;
      return n;
   }

   @Override public Double visitExprInteger(CalculatorParser.ExprIntegerContext ctx) {
      return Double.parseDouble(ctx.Integer().getText());
   }
}
