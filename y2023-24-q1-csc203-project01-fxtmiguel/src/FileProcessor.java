import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.*;
import java.util.Collections;

public class FileProcessor {
    /**
     * Processes arithmetic expressions line-by-line in the given file.
     *
     * @param filePath Path to a file containing arithmetic expressions.
     */
    public static void processFile(String filePath) {
        File infile = new File(filePath);
        try (Scanner scan = new Scanner(infile)) {

            while (scan.hasNext()) {
                String line = scan.nextLine();
                String[] expression = line.split("\\s+");
                List<String> expressionList = new ArrayList<>(Arrays.asList(expression));
                expressionList.removeAll(Collections.singletonList(""));
                Linked_List operand1 = new Linked_List();
                Linked_List operand2 = new Linked_List();
                if (expressionList.contains("+")) {
                    operand1.createList(expressionList.get(0));
                    operand2.createList(expressionList.get(2));
                    Linked_List result = Expressions.add_nums(operand1, operand2);

                    System.out.println(operand1.returnLS() + " + " + operand2.returnLS() + " = " + result.returnLS());

                } else if (expressionList.contains("*")) {
                    operand1.createList(expressionList.get(0));
                    operand2.createList(expressionList.get(2));

                    Linked_List result = Expressions.multiply_nums(operand1, operand2);
                    System.out.println(operand1.returnLS() + " * " + operand2.returnLS() + " = " + result.returnLS());
                }
                else if (expressionList.contains("^")) {
                    operand1.createList(expressionList.get(0));
                    operand2.createList(expressionList.get(2));

                    Linked_List result = Expressions.exponent_nums( operand1 ,Integer.parseInt(operand2.returnLS()));
                    System.out.println(operand1.returnLS() + " ^ " + operand2.returnLS() + " = " + result.returnLS());
                }
            }
        } catch(FileNotFoundException e){
            System.out.println("File not found: " + infile.getPath());
        }
    }
}
