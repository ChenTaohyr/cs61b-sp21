package randomizedtest;


import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

import static org.junit.Assert.*;


/**
 * Created by hug.
 */
public class TestBuggyAList {
    // YOUR TESTS HERE

    @Test
    public void testThreeAddThreeRemove() {
        AListNoResizing<Integer> test1 = new AListNoResizing<>();
        BuggyAList<Integer> test2 = new BuggyAList<>();
        test1.addLast(3);
        test1.addLast(4);
        test1.addLast(5);
        test2.addLast(3);
        test2.addLast(4);
        test2.addLast(5);
        int[] expected = new int[3];
        int[] result = new int[3];
        for (int i = 0; i < 3; i++) {
            expected[i] = test1.removeLast();
            result[i] = test2.removeLast();
        }
        assertEquals(test1.size(), test2.size());
        assertArrayEquals(expected,result);
    }

    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> L2 = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 3);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                L2.addLast(randVal);
//                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int size2 = L2.size();
//                System.out.println("size: " + size);
                assertEquals(size,size2);
            } else if (operationNumber == 2) {
                if(L.size() == 0 || L2.size() ==0)continue;
                int last = L.getLast();
                int last2 = L2.getLast();
                assertEquals(last,last2);
//                L.removeLast();
//                L2.removeLast();
                assertEquals(L.removeLast(),L2.removeLast());
//                System.out.println("removeLast (" + last + ")");

            }
        }
    }

}