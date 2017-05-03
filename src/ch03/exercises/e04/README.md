# 3.4

## 题目

给定两个已排序的表L1和L2，只使用基本的表操作编写计算`L1∩L2`的过程。

## 思路

因为是取交集，所以只要任意一个表为空时，最后结果都返回空。
如果两个表都不为空，则同时遍历两个表，并比较当前元素，当前元素存在两种状态：

- 相等：说明是交集的元素，插入到结果中
- `itemL1 < itemL2`：此时iterL1前进一格
- `itemL1 > itemL2`：此时iterL2前进一格

## 答案

```java
public static <AnyType extends Comparable<? super AnyType>>
void intersection(List<AnyType> L1, List<AnyType> L2, List<AnyType> Intersect) {
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
            Intersect.add(itemL1);
            itemL1 = iterL1.hasNext() ? iterL1.next() : null;
            itemL2 = iterL2.hasNext() ? iterL2.next() : null;
        } else if (compareResule < 0) {
            itemL1 = iterL1.hasNext() ? iterL1.next() : null;
        } else {
            itemL2s = iterL2.hasNext() ? iterL2.next() : null;
        }
    }
}
```
