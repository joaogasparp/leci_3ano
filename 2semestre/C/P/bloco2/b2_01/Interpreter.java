@SuppressWarnings("CheckReturnValue")
public class Interpreter extends HelloBaseVisitor<String> {

   @Override public String visitProgram(HelloParser.ProgramContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitGreetings(HelloParser.GreetingsContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitHello(HelloParser.HelloContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }

   @Override public String visitBye(HelloParser.ByeContext ctx) {
      String res = null;
      return visitChildren(ctx);
      //return res;
   }
}
