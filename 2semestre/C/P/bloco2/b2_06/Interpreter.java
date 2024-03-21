import java.util.HashMap;
import java.util.Map;

@SuppressWarnings("CheckReturnValue")
public class Interpreter extends CalculatorBaseVisitor<Double> {

   HashMap<String, Double> map = new HashMap<>();

   @Override public Double visitProgram(CalculatorParser.ProgramContext ctx) {
      return visitChildren(ctx);
   }

   @Override public Double visitStat(CalculatorParser.StatContext ctx) {
      Double res = null;
      if (ctx.expr() != null)
         System.out.println(res = visit(ctx.expr()));
      if (ctx.assignment() != null)
         System.out.println(res = visit(ctx.assignment()));
      return res;
   }

   @Override public Double visitAssignment(CalculatorParser.AssignmentContext ctx) {
      String var = ctx.ID().getText();
      Double res = visit(ctx.expr());
      map.put(var, res);
      return visitChildren(ctx);
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

   @Override public Double visitExprID(CalculatorParser.ExprIDContext ctx) {
      String key = ctx.ID().getText();
      if (map.containsKey(key))
         return map.get(key);
      System.out.println(key + " not found");
      return null;
   }
}
