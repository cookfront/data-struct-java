# 3.7

## 题目

下列程序的运行时间是多少？

```java
public static List<Integer> makeList(int N)
{
    ArrayList<Integer> lst = new ArrayList<>();
    
    for (int i = 0; i < N; i++)
    {
        lst.add(i);
        lst.trimToSize();
    }
}
```

## 答案

`O(N2)`。因为`trimToSize`需要`O(N)`。

