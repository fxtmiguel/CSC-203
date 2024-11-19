public class Expressions {

    public static Linked_List add_nums(Linked_List listOne, Linked_List listTwo) {
        Linked_List result = new Linked_List();
        Node nodeOne = listOne.getHead();
        Node nodeTwo = listTwo.getHead();
        int carry = 0;

        while (nodeOne != null || nodeTwo != null || carry != 0) {
            int summation = carry;
            if (nodeOne != null) {
                summation += nodeOne.getData();
                nodeOne = nodeOne.getNext();
            }
            if (nodeTwo != null) {
                summation += nodeTwo.getData();
                nodeTwo = nodeTwo.getNext();
            }
            carry = summation / 10;
            summation %= 10;
            result.add(new Node(summation));
        }
        return result;
    }

    public static Linked_List multiply_nums(Linked_List listOne, Linked_List listTwo) {
        Linked_List result = new Linked_List();
        Node nodeOne = listOne.getHead();
        Node nodeTwo = listTwo.getHead();
        int times = 0;

        while (nodeOne != null) {
            int carryOver = 0;
            Linked_List product = new Linked_List();
            for (int i = 0; i < times; i++) {
                product.add(new Node(0));
            }
            while (nodeTwo != null || carryOver != 0) {
                int currentProduct = carryOver;

                if (nodeTwo != null) {
                    currentProduct += nodeOne.getData() * nodeTwo.getData();
                    nodeTwo = nodeTwo.getNext();
                }

                carryOver = currentProduct / 10;
                currentProduct %= 10;
                product.add(new Node(currentProduct));
            }

            result = add_nums(result, product);
            times++;
            nodeOne = nodeOne.getNext();
            nodeTwo = listTwo.getHead();

        }

        return result;
    }

    public static Linked_List exponent_nums(Linked_List base_num, int n) {
        Linked_List result = new Linked_List();

        if (n == 0) {
            result.add(new Node(1));
        } else if (n == 1) {
            return base_num;
        } else {
            Linked_List power = multiply_nums(base_num, base_num);
            if (n % 2 == 0) {
                result = exponent_nums(power, n / 2);
            } else {
                power = exponent_nums(power, (n - 1) / 2);
                result = Expressions.multiply_nums(base_num, power);
            }
        }

        return result;
    }}