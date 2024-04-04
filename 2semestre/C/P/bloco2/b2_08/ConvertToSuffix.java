@SuppressWarnings("CheckReturnValue")
public class ConvertToSuffix extends CalculatorBaseVisitor<String> {

   @Override public String visitStatExpr(CalculatorParser.StatExprContext ctx) {
      String res = visit(ctx.expr());
      System.out.println(ctx.expr().getText() + " -> " + res);
      return res;
   }

   @Override public String visitAssignment(CalculatorParser.AssignmentContext ctx) {
      String res = visit(ctx.expr());
      String id = ctx.ID().getText();
      System.out.println(id + " = " + ctx.expr().getText() + " -> " + id + " = " + res);
      return res;
   }

   @Override public String visitExprMulDivMod(CalculatorParser.ExprMulDivModContext ctx) {
      return visit(ctx.expr(0)) + " " + visit(ctx.expr(1)) + " " + ctx.op.getText();
   }

   @Override public String visitExprAddSub(CalculatorParser.ExprAddSubContext ctx) {
      return visit(ctx.expr(0)) + " " + visit(ctx.expr(1)) + " " + ctx.op.getText();
   }

   @Override public String visitExprParent(CalculatorParser.ExprParentContext ctx) {
      return visit(ctx.expr());
   }

   @Override public String visitExprUnary(CalculatorParser.ExprUnaryContext ctx) {
      return visit(ctx.expr()) + " " + "!" + ctx.op.getText();
   }

   @Override public String visitExprInteger(CalculatorParser.ExprIntegerContext ctx) {
      return ctx.Integer().getText();
   }

   @Override public String visitExprID(CalculatorParser.ExprIDContext ctx) {
      return ctx.ID().getText();
   }
}
