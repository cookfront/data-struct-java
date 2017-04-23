package ch03.exercises.e01;

import java.util.Iterator;
import java.util.List;
import java.util.Arrays;
import java.util.ArrayList;

/**
 * Created by cookfront on 2017/4/23.
 */
public class MyPrintLots<AnyType> {
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

    public static void main(String ...args) {
        List<String> L = new ArrayList<String>(Arrays.asList("a", "b", "c", "d", "e"));
        List<Integer> P = new ArrayList<Integer>(Arrays.asList(1, 3));

        printLots(L, P);
    }
}
