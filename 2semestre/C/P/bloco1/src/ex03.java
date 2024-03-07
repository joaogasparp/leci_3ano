import java.util.Stack;
import java.util.Scanner;

public class ex03 {
    static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        Stack<Double> stack = new Stack<Double>();

        while(input.hasNext()) {
            if(input.hasNextDouble()) {
                stack.push(input.nextDouble());
            } else {
                double n1, n2, result = 0.0;
                String op = input.next();

                n2 = stack.peek();
                stack.pop();

                n1 = stack.peek();
                stack.pop();

                switch(op) {
                    case "+": result = n1 + n2; break;
                    case "-": result = n1 - n2; break;
                    case "*": result = n1 * n2; break;
                    case "/": result = n1 / n2; break;
                }

                stack.push(result);
            }
            System.out.print("Stack: " + stack);
            System.out.println();
        }
        System.out.println("Result: " + stack.pop());
    }

    public static boolean isOperator(String str) {
        return str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/");
    }
}
