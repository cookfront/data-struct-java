package ch06;

/**
 * Created by cookfront on 2017/3/26.
 */
public class BinaryHeap<AnyType extends Comparable<? super AnyType>> {
    private static final int DEFAULT_CAPACITY = 10;
    private int currentSize;
    private AnyType [ ] array;

    public BinaryHeap() {
        this(DEFAULT_CAPACITY);
    }

    public BinaryHeap(int capacity) {
        currentSize = 0;
        array = (AnyType[]) new Comparable[capacity + 1];
    }

    public BinaryHeap(AnyType [] items) {
        currentSize = items.length;
        array = (AnyType[]) new Comparable[(currentSize + 2) * 11 / 10];

        int i = 1;
        for (AnyType item : items) {
            array[i++] = item;
        }
        buildHeap();
    }

    private void buildHeap() {
        for (int i = currentSize / 2; i > 0; i--) {
            percolateDown(i);
        }
    }

    private void percolateDown(int hole) {
        int child;
        AnyType tmp = array[hole];

        for (; hole * 2 <= currentSize; hole = child) {
            child = hole * 2;
            if (child != currentSize && array[child + 1].compareTo(array[child]) < 0) {
                child++;
            }
            if (array[child].compareTo(array[hole]) < 0) {
                array[hole] = array[child];
            } else {
                break;
            }
        }
        array[hole] = tmp;
    }

    public void insert(AnyType x) {
        if (currentSize == array.length - 1) {
            enlargeArray(array.length * 2 + 1);
        }

        int hole = ++currentSize;
        for (array[0] = x; x.compareTo(array[hole / 2]) < 0; hole /= 2) {
            array[hole] = array[hole / 2];
        }
        array[hole] = x;
    }

    private void enlargeArray(int newSize) {
        AnyType [] oldArr = array;
        array = (AnyType []) new Comparable[newSize];

        for (int i = 0; i < oldArr.length; i++) {
            array[i] = oldArr[i];
        }
    }

    public AnyType findMin() {
//        if (isEmpty()) {
//            throw new Exception();
//        }
        return array[1];
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public AnyType deleteMin() {
//        if (isEmpty()) {
//            throw new Exception();
//        }

        AnyType minItem = array[1];
        array[1] = array[currentSize--];
        percolateDown(1);;

        return minItem;
    }

    public void makeEmpty() {
        currentSize = 0;
    }

    public static void main(String ...args) {
        int numItems = 10000;
        BinaryHeap<Integer> h = new BinaryHeap<>();
        int i = 37;

        for (i = 37; i != 0; i = (i + 37) % numItems)
            h.insert(i);
        for (i = 1; i < numItems; i++)
            if (h.deleteMin() != i)
                System.out.println("Oops! " + i);
    }
}
