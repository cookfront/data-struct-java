package ch07;

import ch07.Random;

/**
 * Created by cookfront on 2017/3/18.
 */
public final class Sort {
    public static <AnyType extends Comparable<? super AnyType>>
    void insertionSort(AnyType[] arr) {
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

    public static <AnyType extends Comparable<? super AnyType>>
    void shellSort(AnyType[] arr) {
        int len = arr.length;

        int i;
        int j;

        // 增量
        for (int gap = len / 2; gap > 0; gap /= 2) {
            for (i = gap; i < len; i++) {
                AnyType temp = arr[i];
                for (j = i; j >= gap && temp.compareTo(arr[j - gap]) < 0; j -= gap) {
                    arr[j] = arr[j - gap];
                }
                arr[j] = temp;
            }
        }
    }

    public static <AnyType extends Comparable<? super AnyType>>
    void mergeSort(AnyType[] arr) {
        AnyType[] tmpArray = (AnyType[]) new Comparable[arr.length];
        mergeSort(arr, tmpArray, 0, arr.length - 1);
    }

    private static <AnyType extends Comparable<? super AnyType>>
    void mergeSort(AnyType[] arr, AnyType[] tmpArray, int left, int right) {
        if (left < right) {
            int center = (left + right) / 2;
            mergeSort(arr, tmpArray, left, center);
            mergeSort(arr, tmpArray, center + 1, right);
            merge(arr, tmpArray, left, center + 1, right);
        }
    }

    private static <AnyType extends Comparable<? super AnyType>>
    void merge(AnyType[] arr, AnyType[] tmpArray, int leftPos, int rightPos, int rightEnd) {
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

    public static <AnyType extends Comparable<? super AnyType>>
    void quickSort(AnyType[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    private static <AnyType extends Comparable<? super AnyType>>
    void quickSort(AnyType[] a, int left, int right) {
        if (left == right) {
            return;
        }
        if (left + CUTOFF <= right) {
            AnyType pivot = median3(a, left, right);

            // Begin partitioning
            int i = left, j = right - 1;
            for (; ; ) {
                while (a[++i].compareTo(pivot) < 0) {
                }
                while (a[--j].compareTo(pivot) > 0) {
                }
                if (i < j)
                    swapReferences(a, i, j);
                else
                    break;
            }

            swapReferences(a, i, right - 1);   // Restore pivot

            quickSort(a, left, i - 1);    // Sort small elements
            quickSort(a, i + 1, right);
        } else {
            insertionSort(a);
        }
    }

    private static <AnyType extends Comparable<? super AnyType>>
    void insertionSort(AnyType[] a, int left, int right) {
        for (int p = left + 1; p <= right; p++) {
            AnyType tmp = a[p];
            int j;

            for (j = p; j > left && tmp.compareTo(a[j - 1]) < 0; j--)
                a[j] = a[j - 1];
            a[j] = tmp;
        }
    }

    private static <AnyType extends Comparable<? super AnyType>>
    AnyType median3(AnyType[] a, int left, int right) {
        int center = (left + right) / 2;
        if (a[center].compareTo(a[left]) < 0)
            swapReferences(a, left, center);
        if (a[right].compareTo(a[left]) < 0)
            swapReferences(a, left, right);
        if (a[right].compareTo(a[center]) < 0)
            swapReferences(a, center, right);

        // Place pivot at position right - 1
        swapReferences(a, center, right - 1);
        return a[right - 1];
    }

    public static <AnyType> void swapReferences(AnyType[] a, int index1, int index2) {
        AnyType tmp = a[index1];
        a[index1] = a[index2];
        a[index2] = tmp;
    }

    private static final int NUM_ITEMS = 1000;
    private static int theSeed = 1;
    private static final int CUTOFF = 3;

    private static void checkSort(Integer[] a) {
        for (int i = 0; i < a.length; i++)
            if (a[i] != i)
                System.out.println("Error at " + i);
        System.out.println("Finished checksort");
    }

    public static void main(String... args) {
        Integer[] arr = new Integer[NUM_ITEMS];

        for (int i = 0; i < arr.length; i++)
            arr[i] = i;

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
