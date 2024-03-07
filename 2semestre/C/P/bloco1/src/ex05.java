import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class ex05 {
    private static Map<String, Integer> listNumbers = new HashMap<>();
    static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        openFile("numbers.txt");

        while (input.hasNextLine()) {
            String line = input.nextLine().toLowerCase();
            System.out.println(line + " -> " + convertToNumber(line));
        }
        input.close();
    }

    private static void openFile(String fileName) {
        try {
            Scanner fileScanner = new Scanner(new File(fileName));

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(" - ");
                if (parts.length == 2) {
                    int number = Integer.parseInt(parts[0].trim());
                    String word = parts[1].trim().toLowerCase();
                    listNumbers.put(word, number);
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERRO: Ficheiro não encontrado: " + fileName);
            System.exit(1);
        }
    }

    private static int convertToNumber(String description) {
        String[] words = description.split("\\s+");

        int result = 0;
        int currentNumber = 0;

        for (String word : words) {
            if (listNumbers.containsKey(word)) {
                int value = listNumbers.get(word);
                if (value >=1000000) {
                    result += currentNumber * value;
                    currentNumber = 0;
                } else if (value >= 1000) {
                    result += currentNumber * value;
                    currentNumber = 0;
                } else {
                    currentNumber += value;
                }
            }
        }

        result += currentNumber;
        return result;
    }
}
