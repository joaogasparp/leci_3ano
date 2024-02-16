import java.util.Scanner;

public class ex01 {
    static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        double input1 = 0, input2 = 0, result = 0;
        String operator = null;

        System.out.printf("Operation (number op number): ");
        if (!input.hasNextDouble()) {
            System.out.printf("ERROR: read number failure\n");
            System.exit(1);
        } else {
            input1 = input.nextDouble();
        }

        if (!input.hasNext()) {
            System.out.printf("ERROR: read operator failure\n");
            System.exit(1);
        } else {
            operator = input.next();
        }

        if (!input.hasNextDouble()) {
            System.out.printf("ERROR: read number failure\n");
            System.exit(1);
        } else {
            input2 = input.nextDouble();
        }

        switch (operator) {
            case "+": result = input1 + input2; break;
            case "-": result = input1 - input2; break;
            case "*": result = input1 * input2; break;
            case "/": result = input1 / input2; break;
            default:
                System.out.printf("ERROR: invalid operator \"%s\"\n", operator);
                System.exit(1);
        }

        System.out.printf("%f %s %f = %f\n", input1, operator, input2, result);

    }
}
