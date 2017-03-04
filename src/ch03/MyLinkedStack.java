package ch03;

import java.util.EmptyStackException;

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

        public boolean end() {
            return data == null && next == null;
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

    public void push(AnyType val) {
        theSize++;
        topOfStack = new Node<AnyType>(val, topOfStack);
    }

    public AnyType pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        AnyType result = topOfStack.data;
        if (!topOfStack.end()) {
            topOfStack = topOfStack.next;
        }
        theSize--;
        return result;
    }
}
