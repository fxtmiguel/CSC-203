import java.util.ArrayList;
import java.util.Collections;

public class Linked_List {

    private Node head;
    private int size;

    public Linked_List() {
        this.head = null;
        this.size = 0;


    }

    public void add(Node node) {
        if (head == null) {
            head = node;
        } else {
            Node current = head;
            while (current.getNext() != null) {
                current = current.getNext();
            }
            current.setNext(node);
        }
    }

    public void createList(String s) {
        char[] digits = new StringBuilder(s).reverse().toString().toCharArray();
        for (char digit : digits) {
            int value = Character.getNumericValue(digit);
            add(new Node(value));
        }
    }


    public String returnLS() {
        StringBuilder reversedDigits = new StringBuilder();
        Node currentNode = head;
        ArrayList<Integer> digitsList = new ArrayList<>();

        while (currentNode != null) {
            digitsList.add(currentNode.getData());
            currentNode = currentNode.getNext();
        }

        Collections.reverse(digitsList);
        removeZeros(digitsList);

        for (int digit : digitsList) {
            reversedDigits.append(digit);
        }

        return reversedDigits.toString();
    }

    public String returnString() {
        StringBuilder reversedDigits = new StringBuilder();
        Node currentNode = head;
        ArrayList<Integer> digitsList = new ArrayList<>();

        while (currentNode != null) {
            digitsList.add(currentNode.getData());
            currentNode = currentNode.getNext();
        }

        removeZeros(digitsList);

        for (int digit : digitsList) {
            reversedDigits.append(digit);
        }

        return reversedDigits.toString();
    }

    public void removeZeros(ArrayList<Integer> listOfNums) {
        int nonZeroIndex = 0;
        while (nonZeroIndex < listOfNums.size() && listOfNums.get(nonZeroIndex) == 0) {
            nonZeroIndex++;
        }
        if (nonZeroIndex == listOfNums.size()) {
            listOfNums.subList(1, listOfNums.size()).clear();
        } else {
            listOfNums.subList(0, nonZeroIndex).clear();
        }
    }
    public Node getHead() {
        return head;
    }


}