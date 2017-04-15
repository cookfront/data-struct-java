package ch03;

import ch03.exercises.MyLinkedList_3;

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
        MyLinkedList_3<Integer> linkerList = new MyLinkedList_3<Integer>();
        linkerList.add(1);
        linkerList.add(2);
        linkerList.add(3);
        linkerList.add(4);
        System.out.println(linkerList.contains(5));

        // array stack test
//        MyArrayStack<Integer> arrStack = new MyArrayStack<Integer>();
//        System.out.println(arrStack.isEmpty());
//        arrStack.push(1);
//        arrStack.push(2);
//        arrStack.push(3);
//        arrStack.push(4);
//        arrStack.push(5);
//        arrStack.push(6);
//        System.out.println(arrStack.pop());
//        System.out.println(arrStack.size());

        // linked stack test
//        MyLinkedStack<Integer> linkedStack = new MyLinkedStack<Integer>();
//        System.out.println(linkedStack.isEmpty());
//        linkedStack.push(1);
//        linkedStack.push(2);
//        linkedStack.push(3);
//        linkedStack.push(4);
//        linkedStack.push(5);
//        linkedStack.push(6);
//        System.out.println(linkedStack.pop());
//        System.out.println(linkedStack.pop());
//        System.out.println(linkedStack.size());
    }
}
