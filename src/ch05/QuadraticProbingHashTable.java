package ch05;

/**
 * Created by cookfront on 2017/3/14.
 */
public class QuadraticProbingHashTable<AnyType> {
    private static class HashEntry<AnyType> {
        public AnyType element;
        public boolean isActive;

        public HashEntry(AnyType e) {
            this(e, true);
        }

        public HashEntry(AnyType e, boolean active) {
            element = e;
            isActive = active;
        }
    }

    private static final int DEFAULT_TABLE_SIZE = 101;

    private HashEntry<AnyType> [] array;
    private int theSize;
    private int occupied;

    public QuadraticProbingHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    public QuadraticProbingHashTable(int size) {
        allocateArray(size);
        makeEmpty();
    }

    public boolean insert(AnyType x) {
        int currentPos = findPos(x);
        if (isActive(currentPos)) {
            return false;
        }

        if (array[currentPos] == null) {
            ++occupied;
        }
        array[currentPos] = new HashEntry<AnyType>(x, true);
        ++theSize;

        if (occupied > array.length / 2) {
            rehash();
        }

        return true;
    }

    private void rehash() {
        HashEntry<AnyType> [] oldArray = array;

        // Create a new double-sized, empty table
        allocateArray(2 * oldArray.length);
        occupied = 0;
        theSize = 0;

        // Copy table over
        for (HashEntry<AnyType> entry : oldArray)
            if (entry != null && entry.isActive)
                insert(entry.element);
    }

    public void makeEmpty() {
        doClear();
    }

    private void doClear() {
        theSize = 0;
        for (int i = 0; i < array.length; i++) {
            array[i] = null;
        }
    }

    private int myhash(AnyType x) {
        int hashVal = x.hashCode();

        hashVal %= array.length;
        if (hashVal < 0)
            hashVal += array.length;

        return hashVal;
    }

    private void allocateArray(int arraySize) {
        array = new HashEntry[nextPrime(arraySize)];
    }

    public boolean contains(AnyType x) {
        int currentPos = findPos(x);
        return isActive(currentPos);
    }

    private int findPos(AnyType x) {
        int offset = 1;
        int currentPos = myhash(x);

        while(array[currentPos] != null && !array[currentPos].element.equals(x)) {
            currentPos += offset;
            offset += 2;
            if (currentPos >= array.length) {
                currentPos -= array.length;
            }
        }

        return currentPos;
    }

    public boolean remove(AnyType x) {
        int currentPos = findPos(x);
        if (isActive(currentPos)) {
            array[ currentPos ].isActive = false;
            theSize--;
            return true;
        } else
            return false;
    }

    public int size() {
        return theSize;
    }

    public int capacity() {
        return array.length;
    }

    private boolean isActive(int currentPos) {
        return array[currentPos] != null && array[currentPos].isActive;
    }

    private static int nextPrime(int n) {
        if (n % 2 == 0)
            n++;

        for (; !isPrime( n ); n += 2)
            ;

        return n;

    }

    private static boolean isPrime(int n) {
        if (n == 2 || n == 3)
            return true;

        if (n == 1 || n % 2 == 0)
            return false;

        for (int i = 3; i * i <= n; i += 2)
            if (n % i == 0)
                return false;

        return true;
    }

    public static void main(String ...args) {
        System.out.println("test");
    }
}
