# 3.5

## 题目

给定两个已排序的表L1和L2，只使用基本的表操作编写计算`L1∪L2`的过程。

## 思路

和交集不同的是，当当前比较的两个元素不同时，也要向结果集中插入元素，并将对应迭代器向前推进。

## 答案

```java
public static <AnyType extends Comparable<? super AnyType>>
void union(List<AnyType> L1, List<AnyType> L2, List<AnyType> Result) {
    ListIterator<AnyType> iterL1 = L1.listIterator();
    ListIterator<AnyType> iterL2 = L2.listIterator();
    
    AnyType itemL1 = null, itemL2 = null;
    if (iterL1.hasNext() && iterL2.hasNext()) {
        itemL1 = iterL1.next();
        itemL2 = iterL2.next();
    }
    
    while (itemL1 != null && itemL2 != null) {
        int compareResult = itemL1.compareTo(itemL2);
        
        if (compareResult == 0) {
            Result.add(itemL1);
            itemL1 = iterL1.hasNext() ? iterL1.next() : null;
            itemL2 = iterL2.hasNext() ? iterL2.next() : null;
        } else if (compareResule < 0) {
            Result.add(itemL1);
            itemL1 = iterL1.hasNext() ? iterL1.next() : null;
        } else {
            Result.add(itemL2);
            itemL2s = iterL2.hasNext() ? iterL2.next() : null;
        }
    }
}
```
