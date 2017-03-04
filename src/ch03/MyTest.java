package ch03;

import ch03.MyArrayList;
import ch03.MyLinkedList;
import ch03.MyArrayStack;

/**
 * Created by cookfront on 2017/3/2.
 */
public class MyTest {
    public static void main(String ...args) {
        // array list test
//        MyArrayList<Integer> arrList = new MyArrayList<Integer>();
//        System.out.println(arrList.isEmpty());
//        arrList.add(1);
//        System.out.println(arrList.isEmpty());
//        System.out.println(arrList.size());

        // linked list test
//        MyLinkedList<Integer> linkerList = new MyLinkedList<Integer>();
//        System.out.println(linkerList.isEmpty());
//        linkerList.add(1);
//        System.out.println(linkerList.isEmpty());
//        System.out.println(linkerList.size());

        // array stack test
        MyArrayStack<Integer> arrStack = new MyArrayStack<Integer>();
        System.out.println(arrStack.isEmpty());
        arrStack.push(1);
        arrStack.push(2);
        arrStack.push(3);
        arrStack.push(4);
        arrStack.push(5);
        arrStack.push(6);
        System.out.println(arrStack.pop());
        System.out.println(arrStack.size());
    }
}
