package ch03;

/**
 * Created by cookfront on 2017/3/2.
 */
public class MyLinkedStack<AnyType> {
    /**
     * 栈节点类
     * @param <AnyType>
     */
    private static class Node<AnyType> {
        public AnyType data;
        public Node<AnyType> next;

        public Node(AnyType val, Node<AnyType> nextNode) {
            data = val;
            next = nextNode;
        }
    }

    private Node<AnyType> topOfStack;
    private int theSize;

    public MyLinkedStack() {
        doClear();
    }

    private void doClear() {
        topOfStack = new Node<AnyType>(null, null);
        theSize = 0;
    }

    public void clear() {
        doClear();
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return theSize == 0;
    }
}
