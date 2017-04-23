package ch06;

/**
 * Created by cookfront on 2017/3/26.
 */
public class BinomialQueue<AnyType extends Comparable<? super AnyType>> {
    private static final int DEFAULT_TREES = 1;
    private int currentSize;
    private BinNode<AnyType> [] theTrees;

    public static class BinNode<AnyType> {
        BinNode(AnyType theElement) {
            element = theElement;
        }

        BinNode(AnyType theElement, BinNode<AnyType> lt, BinNode<AnyType> nt) {
            element = theElement;
            leftChild = lt;
            nextSibling = nt;
        }

        AnyType element;
        BinNode<AnyType> leftChild;
        BinNode<AnyType> nextSibling;
    }

    public BinomialQueue() {
        theTrees = new BinNode[DEFAULT_TREES];
        makeEmpty();
    }

    public BinomialQueue(AnyType item) {
        currentSize = 1;
        theTrees = new BinNode[1];
        theTrees[0] = new BinNode<AnyType>(item, null, null);
    }

    public void makeEmpty() {
        currentSize = 0;
        for (int i = 0; i < theTrees.length; i++) {
            theTrees[i] = null;
        }
    }

    /**
     * 扩张树
     *
     * @param newNumTrees
     */
    private void expandTheTrees(int newNumTrees) {
        BinNode<AnyType> [] old = theTrees;
        int oldNumTrees = theTrees.length;

        theTrees = new BinNode[newNumTrees];
        for (int i = 0; i < Math.min(oldNumTrees, newNumTrees); i++)
            theTrees[i] = old[i];
        for (int i = oldNumTrees; i < newNumTrees; i++)
            theTrees[i] = null;
    }

    public void merge(BinomialQueue<AnyType> rhs) {
        if (this == rhs) {
            return;
        }

        currentSize += rhs.currentSize;
        if (currentSize > capacity()) {
            int newNumTrees = Math.max(theTrees.length, rhs.theTrees.length) + 1;
            expandTheTrees(newNumTrees);
        }
    }

    public void insert(AnyType x) {
        merge(new BinomialQueue<>(x));
    }

    public AnyType deleteMin() {
        if (isEmpty()) {
            System.out.println("error");
        }

        int minIndex = findMinIndex();
        AnyType minItem = theTrees[minIndex].element;

        BinNode<AnyType> deletedTree = theTrees[minIndex].leftChild;

        // Construct H''
        BinomialQueue<AnyType> deletedQueue = new BinomialQueue<>();
        deletedQueue.expandTheTrees(minIndex);

        deletedQueue.currentSize = (1 << minIndex) - 1;
        for (int j = minIndex - 1; j >= 0; j--) {
            deletedQueue.theTrees[ j ] = deletedTree;
            deletedTree = deletedTree.nextSibling;
            deletedQueue.theTrees[ j ].nextSibling = null;
        }

        // Construct H'
        theTrees[minIndex] = null;
        currentSize -= deletedQueue.currentSize + 1;

        merge(deletedQueue);

        return minItem;
    }

    public AnyType findMin() {
        if (isEmpty()) {
            System.out.println("error");
        }
        return theTrees[findMinIndex()].element;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    private int findMinIndex() {
        int i;
        int minIndex;

        for (i = 0; theTrees[i] == null; i++)
            ;

        for (minIndex = i; i < theTrees.length; minIndex++) {
            if (theTrees[i] != null && theTrees[i].element.compareTo(theTrees[minIndex].element) < 0) {
                minIndex = i;
            }
        }

        return minIndex;
    }

    private int capacity() {
        return (1 << theTrees.length) - 1;
    }
}
