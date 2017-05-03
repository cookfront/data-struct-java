# 3.3

## 题目

实现MyLinkedList的contains例程。

## 答案

```java
public boolean contains(AnyType x) {
    Node<AnyType> node = beginMarker.next;

    while (node != endMarker && !node.data.equals(x)) {
        node = node.next;
    }

    return (node != endMarker);
}
```