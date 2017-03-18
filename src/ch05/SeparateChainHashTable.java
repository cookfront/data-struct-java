package ch05;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by cookfront on 2017/3/5.
 */
public class SeparateChainHashTable<AnyType> {
    private static final int DEFAULT_TABLE_SIZE = 10;
    private List<AnyType>[] theLists;
    private int currentSize;

    public SeparateChainHashTable() {
        this(DEFAULT_TABLE_SIZE);
    }

    public SeparateChainHashTable(int size) {
        theLists = new LinkedList[nextPrime(size)];
        for (int i = 0; i < theLists.length; i++) {
            theLists[i] = new LinkedList<AnyType>();
        }
    }

    public void insert(AnyType x) {
        List<AnyType> whichList = theLists[myhash(x)];
        if (!whichList.contains(x)) {
            whichList.add(x);

            if (++currentSize > theLists.length) {
                rehash();
            }
        }
    }

    public void remove(AnyType x) {
        List<AnyType> whichList = theLists[myhash(x)];
        if (whichList.contains(x)) {
            whichList.remove(x);
            currentSize--;
        }
    }

    public boolean contains(AnyType x) {
        List<AnyType> whichList = theLists[myhash(x)];
        return whichList.contains(x);
    }

    public void makeEmpty() {
        for (int i = 0; i < theLists.length; i++)
            theLists[i].clear();
        currentSize = 0;
    }

    public static int hash(String key, int tableSize) {
        int hashVal = 0;

        for (int i = 0; i < key.length(); i++)
            hashVal = 37 * hashVal + key.charAt(i);

        hashVal %= tableSize;
        if (hashVal < 0)
            hashVal += tableSize;

        return hashVal;
    }

    private void rehash() {
        List<AnyType> [] oldLists = theLists;

        // Create new double-sized, empty table
        theLists = new List[nextPrime(2 * theLists.length)];
        for (int j = 0; j < theLists.length; j++)
            theLists[j] = new LinkedList<>();

        // Copy table over
        currentSize = 0;
        for (List<AnyType> list : oldLists)
            for (AnyType item : list)
                insert(item);
    }

    private int myhash(AnyType x) {
        int hashVal = x.hashCode();

        hashVal %= theLists.length;
        if (hashVal < 0) {
            hashVal += theLists.length;
        }
        return hashVal;
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
