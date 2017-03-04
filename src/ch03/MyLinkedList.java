package ch03;

import java.lang.Iterable;
import java.util.Iterator;

/**
 * Created by cookfront on 2017/2/21.
 */
public class MyLinkedList<AnyType> implements Iterable<AnyType> {
    /**
     * 链表节点类
     * @param <AnyType>
     */
    private static class Node<AnyType> {
        public AnyType data;
        public Node<AnyType> prev;
        public Node<AnyType> next;

        public Node(AnyType val, Node<AnyType> prevNode, Node<AnyType> nextNode) {
            data = val;
            prev = prevNode;
            next = nextNode;
        }
    }

    private int theSize;
    private int modCount = 0;
    private Node<AnyType> beginMarker;
    private Node<AnyType> endMarker;

    public MyLinkedList() {
        doClear();
    }

    public void clear() {
        doClear();
    }

    private void doClear() {
        beginMarker = new Node<AnyType>(null, null, null);
        endMarker = new Node<AnyType>(null, beginMarker, null);
        beginMarker.next = endMarker;

        theSize = 0;
        modCount++;
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean add(AnyType x) {
        add(size(), x);
        return true;
    }

    public void add(int index, AnyType x) {
        addBefore(getNode(index, 0, size()), x);
    }

    public AnyType get(int index) {
        return getNode(index).data;
    }

    public AnyType set(int index, AnyType newValue) {
        Node<AnyType> p = getNode(index);
        AnyType oldValue = p.data;
        p.data = newValue;
        return oldValue;
    }

    public AnyType remove(int index) {
        return remove(getNode(index));
    }

    public AnyType remove(Node<AnyType> p) {
        p.prev.next = p.next;
        p.next.prev = p.prev;
        theSize--;
        modCount++;

        return p.data;
    }

    private void addBefore(Node<AnyType> p, AnyType x) {
        Node<AnyType> newNode = new Node<>(x, p.prev, p);
        newNode.prev.next = newNode;
        p.prev = newNode;
        theSize++;
        modCount++;
    }

    /**
     * 获取对应索引的节点
     * @param index
     * @return
     */
    private Node<AnyType> getNode(int index) {
        return getNode(index, 0, size() - 1);
    }

    private Node<AnyType> getNode(int index, int lower, int upper) {
        Node<AnyType> p;

        if (index < lower || index > upper) {
            throw new IndexOutOfBoundsException();
        }

        if (index < size() / 2) {
            p = beginMarker.next;
            for (int i = 0; i < index; i++) {
                p = p.next;
            }
        } else {
            p = endMarker;
            for (int i = size(); i > index; i--) {
                p = p.prev;
            }
        }

        return p;
    }

    public Iterator iterator() {
        return new LinkerListIterator();
    }

    private class LinkerListIterator implements Iterator<AnyType> {
        private Node<AnyType> current = beginMarker.next;
        private int expectedModCount = modCount;
        private boolean okToRemove = false;

        public boolean hasNext() {
            return current != endMarker;
        }

        public AnyType next() {
            if (modCount != expectedModCount) {
                throw new java.util.ConcurrentModificationException();
            }
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }

            AnyType nextItem = current.data;
            current = current.next;
            okToRemove = true;
            return nextItem;
        }

        public void remove() {
            if (modCount != expectedModCount) {
                throw new java.util.ConcurrentModificationException();
            }
            if (!okToRemove) {
                throw new IllegalStateException();
            }

            MyLinkedList.this.remove(current.prev);
            expectedModCount++;
            okToRemove = false;
        }
    }
}
