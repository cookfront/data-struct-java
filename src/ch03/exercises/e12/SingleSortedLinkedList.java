package ch03.exercises.e12;

/**
 * Created by cookfront on 2017/5/14.
 */
public class SingleSortedLinkedList {
    private static class Node<Comparable> {
        public Comparable data;
        public Node<Comparable> next;

        public Node() {
            this(null, null);
        }

        public Node(Comparable data) {
            this(data, null);
        }

        public Node(Comparable data, Node<Comparable> next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node<Comparable> head;
    private int theSize;

    public SingleSortedLinkedList() {
        theSize = 0;
        head = new Node<Comparable>();
        head.next = null;
    }

    public boolean contains(Comparable x) {
        Node<Comparable> p = head.next;
        while (p != null && p.data.compareTo(x) <= 0) {
            if (x.equals(p.data)) {
                return true;
            } else {
                p = p.next;
            }
        }
        return false;
    }

    public void print() {
        Node<Comparable> p = head.next;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
        System.out.println();
    }

    public int size() {
        return theSize;
    }

    public boolean add(Comparable x) {
        if (contains(x)) {
            return false;
        }

        Node<Comparable> prev = head;
        Node<Comparable> p = head.next;
        while (p != null && p.data.compareTo(x) < 0) {
            prev = p;
            p = p.next;
        }

        Node<Comparable> node = new Node<Comparable>(x);
        prev.next = node;
        node.next = p;
        theSize++;

        return true;
    }

    public boolean remove(Comparable x) {
        if (!contains(x)) {
            return false;
        }

        Node<Comparable> prev = head;
        Node<Comparable> p = head.next;
        while (p != null && !p.data.equals(x)) {
            prev = p;
            p = p.next;
        }

        prev.next = p.next;
        theSize--;
        return true;
    }

    public static void main(String ...args) {
        SingleSortedLinkedList testList = new SingleSortedLinkedList();

        System.out.println("list size: " + testList.size());

        testList.add(2);
        testList.add(3);
        testList.add(1);

        testList.print();

        if (testList.contains(3)) {
            System.out.println("test list have 3!");
        }

        testList.remove(2);

        testList.print();
    }
}
