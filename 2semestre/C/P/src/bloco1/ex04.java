import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class ex04 {
    private static Map<String, String> listNumbers = new HashMap<>();
    static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        openFile("numbers.txt");

        while (input.hasNext()) {
            String word = input.next().toLowerCase();
            
            if (listNumbers.containsKey(word)) {
                System.out.print(listNumbers.get(word) + " ");
            } else {
                System.out.print(word + " ");
            }
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
                    String word = parts[1].trim().toLowerCase();
                    listNumbers.put(word, parts[0].trim());
                }
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("ERRO: Ficheiro não encontrado: " + fileName);
            System.exit(1);
        }
    }
}
