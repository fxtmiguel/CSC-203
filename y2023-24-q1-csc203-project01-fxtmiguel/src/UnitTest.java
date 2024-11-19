import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UnitTest {
    @Test
    public void testGetData() {
        Node node = new Node(5);
        assertEquals(5, node.getData());
    }
    @Test
    public void testSetNext() {
        Node node1 = new Node(5);
        Node node2 = new Node(10);
        node1.setNext(node2);
        assertEquals(node2, node1.getNext());
    }

    private Linked_List list;

    @Test
    public void testAdd() {
        list = new Linked_List();
        list.add(new Node(5));
        list.add(new Node(2));
        list.add(new Node(5));
        list.add(new Node(9));
        Assertions.assertEquals("9525", list.returnLS());
    }

    @Test
    public void testAdd1() {
        list = new Linked_List();
        list.add(new Node(8));
        list.add(new Node(6));
        list.add(new Node(5));
        list.add(new Node(7));
        Assertions.assertEquals("7568", list.returnLS());
    }





    @Test
    public void testReturnListString() {
        list = new Linked_List();
        list.createList("15105");
        Assertions.assertEquals("50151", list.returnString());
    }

    @Test
    public void testGetHead() {
        list = new Linked_List();
        list.add(new Node(5));
        list.add(new Node(10));
        list.add(new Node(15));
        list.add(new Node(20));
        Assertions.assertEquals(5, list.getHead().getData());
    }

    @Test
    public void testAdd2() {
        Linked_List listOne = new Linked_List();
        listOne.add(new Node(4));
        listOne.add(new Node(3));

        Linked_List listTwo = new Linked_List();
        listTwo.add(new Node(2));
        listTwo.add(new Node(2));

        Linked_List result = Expressions.add_nums(listOne, listTwo);

        Assertions.assertEquals("56", result.returnLS());
    }

    @Test
    public void testMultiply() {
        Linked_List listOne = new Linked_List();
        listOne.add(new Node(3));
        listOne.add(new Node(1));

        Linked_List listTwo = new Linked_List();
        listTwo.add(new Node(1));
        listTwo.add(new Node(5));

        Linked_List result = Expressions.multiply_nums(listOne, listTwo);

        Assertions.assertEquals("663", result.returnLS());
    }

    @Test
    public void testExponentiation() {
        Linked_List base = new Linked_List();
        base.createList("12");
        int exponent = 3;

        Linked_List result = Expressions.exponent_nums(base, exponent);

        Assertions.assertEquals("1728", result.returnLS());
    }

    @Test
    public void testExponentiation2() {
        Linked_List base = new Linked_List();
        base.createList("19");
        int exponent = 0;

        Linked_List result = Expressions.exponent_nums(base, exponent);

        Assertions.assertEquals("1", result.returnLS());
    }

    @Test
    public void testExponentiation3() {
        Linked_List base = new Linked_List();
        base.createList("500");
        int exponent = 1;

        Linked_List result = Expressions.exponent_nums(base, exponent);

        Assertions.assertEquals("500", result.returnLS());
    }



}