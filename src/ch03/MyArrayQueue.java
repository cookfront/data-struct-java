package ch03;

import java.lang.Iterable;
import java.util.Iterator;

/**
 * Created by cookfront on 2017/3/2.
 *
 * 数组实现的队列
 */
public class MyArrayQueue<AnyType> implements Iterable<AnyType> {
    private static final int DEFAULT_CAPACITY = 10;

    private int topOfStack;
    private AnyType[] theItems;
    private int theSize;

    public MyArrayQueue() {
        doClear();
    }

    private void doClear() {
        theSize = 0;
        topOfStack = -1;
        ensureCapacity(DEFAULT_CAPACITY);
    }

    public int size() {
        return theSize;
    }

    public boolean isEmpty() {
        return theSize == 0;
    }

    public void push(AnyType val) {
        if (theItems.length == size()) {
            ensureCapacity(size() * 2 + 1);
        }
        theItems[++topOfStack] = val;
    }

    public AnyType pop() {
        return theItems[--topOfStack];
    }

    public void ensureCapacity(int newCapacity) {
        if (newCapacity < theSize) {
            return;
        }

        AnyType[] old = theItems;
        theItems = (AnyType []) new Object[newCapacity];
        for (int i = 0; i < size(); i++) {
            theItems[i] = old[i];
        }
    }

    public Iterator<AnyType> iterator() {
        return new ArrayQueueIterator();
    }

    private class ArrayQueueIterator implements Iterator<AnyType> {

        @Override
        public boolean hasNext() {
            return false;
        }

        @Override
        public AnyType next() {
            return null;
        }
    }
}
