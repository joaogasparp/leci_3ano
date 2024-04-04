import java.util.Map;
import java.util.HashMap;

@SuppressWarnings("CheckReturnValue")

public class NumberMappings extends NumbersBaseListener {
   @Override public void exitLine(NumbersParser.LineContext ctx) {
      String name = ctx.NAME().getText();
      Integer value = Integer.parseInt(ctx.NUM().getText());
      if (exists(name)) {
         System.out.println("Error: " + name + " already exists");
         System.exit(1);
      }
      mappings.put(name, value);
   }

   public boolean exists(String name) {
      assert name != null;

      return mappings.containsKey(name);
   }

   public Integer value(String name) {
      assert name != null;
      assert exists(name);

      return mappings.get(name);
   }

   protected Map<String, Integer> mappings = new HashMap<String, Integer>();
}
