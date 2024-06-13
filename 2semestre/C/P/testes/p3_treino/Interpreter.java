import java.util.HashMap;
import java.util.List;
import java.util.Map;

import .antlr.VectorParser;

@SuppressWarnings("CheckReturnValue")
public class Interpreter extends VectorBaseVisitor<Value> {
   protected Map<String, Value> variables = new HashMap<>();

   @Override public Value visitStatShow(VectorParser.StatShowContext ctx) {
      Value res = visit(ctx.expr());
      if (res != null) {
         System.out.println(res);
      }
      return null;
   }

   @Override public Value visitStatAssigment(VectorParser.StatAssigmentContext ctx) {
      Value value = visit(ctx.expr());
      String varName = ctx.VARIABLE().getText();
      return variables.put(varName, value);
   }

   @Override public Value visitExprInternalProduct(VectorParser.ExprInternalProductContext ctx) {
      Value res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public Value visitExprSumSub(VectorParser.ExprSumSubContext ctx) {
      Value v1 = visit(ctx.e1);
      Value v2 = visit(ctx.e2);
      String op = ctx.op.getText();
      if (v1 != null && v2 != null) {
         if (v1.getClass().equals(v2.getClass())) {
            if (op.equals("+")) {
               return v1.sum(v2);
            } else {
               return v1.subtract(v2);
            }
         } else {
            System.err.printf("ERROR: Use the same types of values.\n");
         }
      }
      return null;
   }

   @Override
   public Value visitExprVariable(VectorParser.ExprVariableContext ctx) {
      String variable = ctx.VARIABLE.getText();
      Value value = variables.get(variable);
      if (value == null) {
         System.err.printf("ERROR: no variable %s.\n", variable);
      }
      return value;
   }

   @Override public Value visitExprUnary(VectorParser.ExprUnaryContext ctx) {
      Value v = visit(ctx.expr());
      String op = ctx.op.getText();
      if (v != null && op.equals("-")) {
         return v.symmetric();
      }
      return v;
   }

   @Override public Value visitExprParenthesis(VectorParser.ExprParenthesisContext ctx) {
      return visit(ctx.expr());
   }

   @Override public Value visitExprNumber(VectorParser.ExprNumberContext ctx) {
      Double number = Double.parseDouble(ctx.NUMBER().getText());
      return new Scalar(number);
   }

   @Override public Value visitExprVector(VectorParser.ExprVectorContext ctx) {
      List<Double> vector = Double.parseDouble(ctx.VECTOR().getText());
      try {
         return new Vector(vector);
      } catch (Exception e) {
         System.err.printf("ERROR: bad vector %s.\n", vector);
      }
      return null;
   }

   @Override public Value visitExprMultiply(VectorParser.ExprMultiplyContext ctx) {
      Value res = null;
      return visitChildren(ctx);
      //return res;
   }
}
