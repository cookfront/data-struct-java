# 3.2

## 题目

通过只调整链来交换两个相邻的元素，使用

a. 单链表
b. 双链表

## 答案

具体运行示例请看MyLinkedList类。

```java
public static void swapSingleWithNext(Node beforeNode) {
    Node node, afterNode;

    node = beforeNode.next;
    afterNode = node.next;

    node.next = afterNode.next;
    afterNode.next = node;
    beforeNode.next = afterNode;
}

public static void swapDoubleWithNext(Node node) {
    Node beforeNode, afterNode;

    beforeNode = node.prev;
    afterNode = node.next;

    node.next = afterNode.next;
    afterNode.next.prev = node;
    afterNode.next = node;
    node.prev = afterNode;
    beforeNode.next = afterNode;
    afterNode.prev = beforeNode;
}

```