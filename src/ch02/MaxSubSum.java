package ch02;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Created by cookfront on 2017/2/14.
 */
public class MaxSubSum {
    public static void main(String ...args) {
        int[] subs = {-2, 11, -4, 13, -5, -2};
        System.out.println(maxSubSum1(subs));
        System.out.println(maxSubSum2(subs));
        System.out.println(maxSubSum4(subs));
    }

    public static int maxSubSum1(int[] arr) {
        int maxSum = 0;

        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                int thisSum = 0;

                for (int k = i; k <= j; k++) {
                    thisSum += arr[k];
                }

                if (thisSum > maxSum) {
                    maxSum = thisSum;
                }
            }
        }

        return maxSum;
    }

    public static int maxSubSum2(int[] arr) {
        int maxSum = 0;

        for (int i = 0; i < arr.length; i++) {
            int thisSum = 0;
            for (int j = i; j < arr.length; j++) {
                thisSum += arr[j];

                if (thisSum > maxSum) {
                    maxSum = thisSum;
                }
            }
        }

        return maxSum;
    }

    public static int maxSumRec(int[] arr, int left, int right) {
        if (left == right) {
            if (arr[left] > 0) {
                return arr[left];
            } else {
                return 0;
            }
        }

        int center = (left + right / 2);
        int maxLeftSum = maxSumRec(arr, left, center);
        int maxRightSum = maxSumRec(arr, center + 1, right);

        int maxLeftBorderSum = 0;
        int leftBorderSum = 0;
        for (int i = center; i >= left; i--) {
            leftBorderSum += arr[i];
            if (leftBorderSum > maxLeftBorderSum) {
                maxLeftBorderSum = leftBorderSum;
            }
        }

        int maxRightBorderSum = 0;
        int rightBorderSum = 0;
        for (int i = center + 1; i <= right; i++) {
            rightBorderSum += arr[i];
            if (rightBorderSum > maxRightBorderSum) {
                maxRightBorderSum = rightBorderSum;
            }
        }

        return max3(maxLeftSum, maxRightSum, maxLeftBorderSum + maxRightBorderSum);
    }

    public static int max3(int a, int b, int c) {
        if (a > b && a > c) {
            return a;
        } else if (b > a && b > c) {
            return b;
        } else if (c > a && c > b) {
            return c;
        }
        return Integer.MIN_VALUE;
    }

    public static int maxSubSum3(int[] arr) {
        return maxSumRec(arr, 0, arr.length - 1);
    }

    public static int maxSubSum4(int[] arr) {
        int maxSum = 0;
        int thisSum = 0;

        for (int i = 0; i < arr.length; i++) {
            thisSum += arr[i];

            if (thisSum > maxSum) {
                maxSum = thisSum;
            } else if (thisSum < 0) {
                thisSum = 0;
            }
        }

        return maxSum;
    }
}
