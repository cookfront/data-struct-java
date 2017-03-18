package ch07;

import ch07.Random;

/**
 * Created by cookfront on 2017/3/18.
 */
public final class Sort {
    public static <AnyType extends Comparable<? super AnyType>> void insertionSort(AnyType [] arr) {
        int len = arr.length;

        int i;
        int j;

        for (i = 1; i < len; i++) {
            AnyType temp = arr[i];
            for (j = i; j > 0 && temp.compareTo(arr[j - 1]) < 0; j--) {
                arr[j] = arr[j - 1];
            }
            arr[j] = temp;
        }
    }

    public static <AnyType extends Comparable<? super AnyType>> void shellSort(AnyType [] arr) {
        int len = arr.length;

        int i;
        int j;

        // 增量
        for (int gap = len / 2; gap > 0; gap /= 2) {
            for (i = gap; i < len; i ++) {
                AnyType temp = arr[i];
                for (j = i; j >= gap && temp.compareTo(arr[j - gap]) < 0; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = temp;
            }
        }
    }

    public static <AnyType extends Comparable<? super AnyType>> void mergeSort(AnyType [] arr) {
        AnyType [] tmpArray = (AnyType []) new Comparable[arr.length];
        mergeSort(arr, tmpArray, 0, arr.length - 1);
    }

    private static <AnyType extends Comparable<? super AnyType>> void mergeSort(AnyType [] arr, AnyType [] tmpArray, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(arr, tmpArray, left, center);
            mergeSort(arr, tmpArray, center + 1, right);
            merge(arr, tmpArray, left, center + 1, right);
        }
    }

    private static <AnyType extends Comparable<? super AnyType>> void merge(AnyType [] arr, AnyType [] tmpArray, int leftPos, int rightPos, int rightEnd) {
        int leftEnd = rightPos - 1;
        int tmpPos = leftPos;
        int numElements = rightEnd - leftPos + 1;

        while (leftPos <= leftEnd && rightPos <= rightEnd) {
            if (arr[leftPos].compareTo(arr[rightPos]) <= 0) {
                tmpArray[tmpPos++] = arr[leftPos++];
            } else {
                tmpArray[tmpPos++] = arr[rightPos++];
            }
        }

        while (leftPos <= leftEnd) {
            tmpArray[tmpPos++] = arr[leftPos++];
        }

        while (rightPos <= rightEnd) {
            tmpArray[tmpPos++] = arr[rightPos++];
        }

        // copy tmpArray back
        for (int i = 0; i < numElements; i++, rightEnd--) {
            arr[rightEnd] = tmpArray[rightEnd];
        }
    }

    public static <AnyType extends Comparable<? super AnyType>> void quickSort(AnyType [] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static <AnyType extends Comparable<? super AnyType>> void quickSort(AnyType [] arr, int left, int right) {
        AnyType pivot = median3(arr, left, right);
        int i = left;
        int j = right - 1;
        for (; ;) {
            while(arr[++i].compareTo(pivot) < 0) {}
            while(arr[--i].compareTo(pivot) > 0) {}
            if (i < j) {
                swapReferences(arr, i, j);
            } else {
                break;
            }
        }

        swapReferences(arr, i, right - 1);
        quickSort(arr, left, i - 1);
        quickSort(arr, i + 1, right);
    }

    private static <AnyType extends Comparable<? super AnyType>> AnyType median3(AnyType [] arr, int left, int right) {
        int center = (left + right) / 2;

        if (arr[center].compareTo(arr[left]) < 0) {
            swapReferences(arr, left, center);
        }
        if (arr[right].compareTo(arr[left]) < 0) {
            swapReferences(arr, left, right);
        }
        if (arr[right].compareTo(arr[center]) < 0) {
            swapReferences(arr, center, right);
        }

        swapReferences(arr, center, right - 1);
        return arr[right - 1];
    }

    public static <AnyType> void swapReferences(AnyType [ ] a, int index1, int index2) {
        AnyType tmp = a[ index1 ];
        a[ index1 ] = a[ index2 ];
        a[ index2 ] = tmp;
    }

    private static final int NUM_ITEMS = 1000;
    private static int theSeed = 1;

    private static void checkSort(Integer [ ] a) {
        for (int i = 0; i < a.length; i++)
            if (a[ i ] != i)
                System.out.println( "Error at " + i );
        System.out.println( "Finished checksort" );
    }

    public static void main(String ...args) {
        Integer [] arr = new Integer[NUM_ITEMS];

        for (int i = 0; i < arr.length; i++)
            arr[ i ] = i;

        Random.permute(arr);
        insertionSort(arr);
        checkSort(arr);

        Random.permute(arr);
        shellSort(arr);
        checkSort(arr);

        Random.permute(arr);
        mergeSort(arr);
        checkSort(arr);

        Random.permute(arr);
        quickSort(arr);
        checkSort(arr);
    }
}
