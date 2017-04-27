# 3.1

## 题目

给定一个表L和另一个表P，它们包含以升序排列的整数。操作`printLots(L, P)`将打印L中那些由P所指定的位置上的元素。例如，如果`P=1,3,4,6`，那么L中位于第1、第3、第4和第6个位置上的元素被打印出来。写出过程`printLots(L, P)。只可使用public型的Collection API容器操作。该过程的运行时间是多少？

## 答案

```java
public static <AnyType> void printLots(List<AnyType> L, List<Integer> P) {
    Iterator<AnyType> iterL = L.iterator();
    Iterator<Integer> iterP = P.iterator();

    AnyType itemL = null;
    Integer itemP;
    int start = 0;

    while (iterL.hasNext() && iterP.hasNext()) {
        itemP = iterP.next();

        System.out.println("Looking for position " + itemP);

        while (start < itemP && iterL.hasNext()) {
            start++;
            itemL = iterL.next();
        }
        System.out.println(itemL);
    }
}
```

运行时间为`O(n2)`。

