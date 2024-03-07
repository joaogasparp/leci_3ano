import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class ex02 {
    static final Scanner input = new Scanner(System.in);
    private static Map<String, Double> vars = new HashMap<String, Double>();

    public static void main(String[] args) {
        System.out.printf("Use spaces to separate tokens\n");
        while(input.hasNextLine()) {
            Scanner sc = new Scanner(input.nextLine());
            if (sc.hasNext()) {
                System.out.printf("result = " + processExpression(sc) + "\n");
            }
            sc.close();
        }
    }

    public static boolean isNumber(String token) {
        Scanner sc = new Scanner(token);
        boolean res = sc.hasNextDouble();
        sc.close();
        return res;
    }

    public static boolean isVar(String token) {
        boolean res = token.length() > 0 && Character.isLetter(token.charAt(0));
        for (int i = 1; res && i < token.length(); i++) {
            res = Character.isLetterOrDigit(token.charAt(i));
        }
        return res;
    }

    public static double processExpression(Scanner sc) {
        double n1 = 0, result = 0;
        String input1 = null;
        input1 = sc.next();
        
        if (isVar(input1)) {
            if (vars.containsKey(input1)) {
                n1 = vars.get(input1);
            }
        } else if (isNumber(input1)) {
            n1 = Double.parseDouble(input1);
        }
        
        if (sc.hasNext()) {
            String op;
            op = sc.next();
            if (op.equals("=")) {
                if (!isVar(input1)) {
                    System.out.printf("ERROR: invalid assigment\n");
                    System.exit(1);
                }
                result = processExpression(sc);
                vars.put(input1, result);
            } else {
                double n2 = processExpression(sc);
                switch (op) {
                    case "+": result = n1 + n2; break;
                    case "-": result = n1 - n2; break;
                    case "*": result = n1 * n2; break;
                    case "/": result = n1 / n2; break;
                    default:
                        System.out.printf("ERROR: invalid operator \"%s\"\n", op);
                        System.exit(1);
                }
            }
        } else {
            result = n1;
        }
        return result;
    }
}
