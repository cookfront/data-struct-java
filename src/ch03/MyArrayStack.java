package ch03;

/**
 * Created by cookfront on 2017/3/2.
 *
 * 数组实现的队列
 */
public class MyArrayStack<AnyType> {
    private static final int DEFAULT_CAPACITY = 5;

    private int topOfStack;
    private AnyType[] theItems;
    private int theSize;

    public MyArrayStack() {
        doClear();
    }

    private void doClear() {
        theSize = 0;
        topOfStack = -1;
        ensureCapacity(DEFAULT_CAPACITY);
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
        if (theItems.length == size()) {
            ensureCapacity(size() * 2 + 1);
        }
        theItems[++topOfStack] = val;
        theSize++;
    }

    public AnyType pop() {
        theSize--;
        return theItems[topOfStack--];
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
}
