package com.gupao.mydemo.sort;

/**
 * @ClassName LinkedListTest
 * @Author LIUHANPENG
 * @Date 2019/11/22 0022 13:33
 **/
public class LinkedListTest {

    public static void main(String[] args) {
        Node A = new Node("A");
        Node B = new Node("B");
        Node C = new Node("C");
        Node D = new Node("D");
        Node E = new Node("E");
        Node F = new Node("F");
        A.next = B;
        B.next = C;
        C.next = D;
        D.next = E;
        E.next = F;
        print(A);

        LinkedListReversor reversor = LinkedListReversor.RECURSION;
        System.out.println(reversor.getStrategy() + ":");
        Node tmp = reversor.execute(A);
        print(tmp);

        reversor = LinkedListReversor.NO_RECURSION;
        System.out.println(reversor.getStrategy() + ":");
        print(reversor.execute(tmp));
    }

    private static void print(Node node) {
        while (node != null) {
            System.out.print(node.value);
            node = node.next;
            if (node != null) {
                System.out.print("->");
            } else {
                System.out.println();
            }
        }
    }

}

class Node {
    public String value;
    public Node next;

    public Node(String value) {
        this.value = value;
    }
}

enum LinkedListReversor {
    RECURSION("递归") {
        @Override
        public Node execute(Node node) {
            Node prev = null;
            if (node == null || node.next == null) {
                prev = node;
            } else {
                Node tmp = execute(node.next);
                node.next.next = node;
                node.next = null;
                prev = tmp;
            }
            return prev;
        }
    },
    NO_RECURSION("非递归") {
        @Override
        public Node execute(Node node) {
            Node prev = null;
            while (node != null) {
                Node tmp = node;
                node = node.next;
                tmp.next = prev;
                prev = tmp;
            }
            return prev;
        }
    };
    private String strategy;

    private LinkedListReversor(String strategy) {
        this.strategy = strategy;
    }

    public abstract Node execute(Node node);

    public String getStrategy() {
        return strategy;
    }
}
