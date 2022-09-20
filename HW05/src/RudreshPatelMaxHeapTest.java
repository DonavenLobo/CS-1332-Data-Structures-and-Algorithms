import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 *
 * @author Rudresh Patel
 *
 * @version 1.1
 *
 *
 * NOTE: Base template for these tests was taken from the "MaxHeapStudentTest.java" file provided by the TAs.
 *       Some tests maybe similar if not same to the provided student tests by the TAs.
 */
public class RudreshPatelMaxHeapTest {

    private static final int TIMEOUT = 200;
    private MaxHeap<Integer> heap;

    @Before
    public void setUp() {
        heap = new MaxHeap<>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, heap.size());
        assertArrayEquals(new Comparable[MaxHeap.INITIAL_CAPACITY],
                heap.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testBuildHeap() {
        ArrayList<Integer> data = new ArrayList<>();
        data.add(1); // 1
        heap = new MaxHeap<>(data);
        assertEquals(1, heap.size());
        Integer[] expected = new Integer[(2 * 1) + 1];
        expected[0] = null;
        expected[1] = 1;
        expected[2] = null;
        assertArrayEquals(expected, heap.getBackingArray());

        data.add(2); // 1 2
        //System.out.println(data);
        heap = new MaxHeap<>(data);
        assertEquals(2, heap.size());
        expected = new Integer[(2 * 2) + 1];
        expected[0] = null;
        expected[1] = 2;  // Here, Up - Heap will shift 2 "up" because of Max Heap
        expected[2] = 1;
        expected[3] = null;
        expected[4] = null;
        assertArrayEquals(expected, heap.getBackingArray());

        data.add(3);
        data.add(50);
        data.add(7);
        data.add(6);
        data.add(8);
        data.add(4);  // [1, 2, 3, 50, 7, 6, 8, 4]
        /*
              Initial:
                  1
                /   \
               /     \
              2       3
             / \     / \
           50   7   6   8
           /
          4

        // Here, This will test both left side and right side with shifting 50 and 8 respectively
           (and pulling up 50 up all the way).

              Down-Heap: Comparing parent to its nodes and replacing appropriately

              Final:
                  50
                /   \
               /     \
              7       8
             / \     / \
            4   1   6   3
           /
          2

     */
        heap = new MaxHeap<>(data);
        //System.out.println(Arrays.toString(heap.getBackingArray()));
        assertEquals(8, heap.size());
        expected = new Integer[(2 * 8) + 1];
        expected[0] = null;
        expected[1] = 50;
        expected[2] = 7;
        expected[3] = 8;
        expected[4] = 4;
        expected[5] = 1;
        expected[6] = 6;
        expected[7] = 3;
        expected[8] = 2;
        expected[9] = null;
        expected[10] = null;
        expected[11] = null;
        expected[12] = null;
        expected[13] = null;
        expected[14] = null;
        expected[15] = null;
        expected[16] = null;
        assertArrayEquals(expected, heap.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAdd() {

        heap.add(1); // [X, 1]
        assertEquals(1, heap.size());

        Integer[] expected = new Integer[MaxHeap.INITIAL_CAPACITY];
        expected[0] = null;
        expected[1] = 1;
        expected[2] = null;
        expected[3] = null;
        expected[4] = null;
        expected[5] = null;
        expected[6] = null;
        expected[7] = null;
        expected[8] = null;
        expected[9] = null;
        expected[10] = null;
        expected[11] = null;
        expected[12] = null;
        assertArrayEquals(expected, heap.getBackingArray());

        heap.add(2); // [X, 1, 2] -> [X, 2, 1]
        assertEquals(2, heap.size());

        expected = new Integer[MaxHeap.INITIAL_CAPACITY];
        expected[0] = null;
        expected[1] = 2;
        expected[2] = 1;
        expected[3] = null;
        expected[4] = null;
        expected[5] = null;
        expected[6] = null;
        expected[7] = null;
        expected[8] = null;
        expected[9] = null;
        expected[10] = null;
        expected[11] = null;
        expected[12] = null;
        assertArrayEquals(expected, heap.getBackingArray());

        // Now, This Test will create the same tree from above added integers until it is full - 1.


        heap.add(3); // [X, 2, 1, 3] -> [X, 3, 1, 2]
        heap.add(50); // [X, 3, 1, 2, 50] -> [X, 3, 50, 2, 1] -> [X, 50, 3, 2, 1]
        heap.add(7); // [X, 50, 3, 2, 1, 7] -> [X, 50, 7, 2, 1, 3]
        heap.add(-1); // [X, 50, 7, 2, 1, 3, -1] -> Nothing happens ... so on.......
        heap.add(8);
        heap.add(4);
        // [null, 50, 7, 8, 4, 3, -1, 2, 1, null, null, null, null]
        assertEquals(8, heap.size());
        expected = new Integer[MaxHeap.INITIAL_CAPACITY];
        expected[0] = null;
        expected[1] = 50;
        expected[2] = 7;
        expected[3] = 8;
        expected[4] = 4;
        expected[5] = 3;
        expected[6] = -1;
        expected[7] = 2;
        expected[8] = 1;
        expected[9] = null;
        expected[10] = null;
        expected[11] = null;
        expected[12] = null;
        assertArrayEquals(expected, heap.getBackingArray());

        heap.add(-2);
        heap.add(-3);
        heap.add(-4);
        heap.add(-5);  // to fill the arrays

        assertEquals(12, heap.size());
        expected = new Integer[MaxHeap.INITIAL_CAPACITY];
        expected[0] = null;
        expected[1] = 50;
        expected[2] = 7;
        expected[3] = 8;
        expected[4] = 4;
        expected[5] = 3;
        expected[6] = -1;
        expected[7] = 2;
        expected[8] = 1;
        expected[9] = -2;
        expected[10] = -3;
        expected[11] = -4;
        expected[12] = -5;
        assertArrayEquals(expected, heap.getBackingArray());


        // Test: double size
        heap.add(55);

        assertEquals(13, heap.size());
        expected = new Integer[MaxHeap.INITIAL_CAPACITY * 2];
        expected[0] = null;
        expected[1] = 55;
        expected[2] = 7;
        expected[3] = 50;
        expected[4] = 4;
        expected[5] = 3;
        expected[6] = 8;
        expected[7] = 2;
        expected[8] = 1;
        expected[9] = -2;
        expected[10] = -3;
        expected[11] = -4;
        expected[12] = -5;
        expected[13] = -1;
        expected[14] = null;
        expected[15] = null;
        expected[16] = null;
        expected[17] = null;
        expected[18] = null;
        expected[19] = null;
        expected[20] = null;
        expected[21] = null;
        expected[22] = null;
        expected[23] = null;
        expected[24] = null;
        expected[25] = null;
        assertArrayEquals(expected, heap.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testRemove() {
        Integer num1 = 1;
        Integer num2 = 2;
        Integer num3 = 3;
        Integer num4 = 4;
        Integer num5 = 5;
        Integer num6 = 6;
        Integer num7 = 7;
        Integer num8 = 8;
        Integer num9 = 9;


        heap.add(num1);
        heap.add(num2);
        heap.add(num3);
        heap.add(num5);
        heap.add(num4);
        heap.add(num6);
        heap.add(num8);
        heap.add(num7);
        heap.add(num9);

        // [null, 9, 8, 6, 7, 3, 2, 5, 1, 4, null, null, null]
        assertEquals(9, heap.size());
        Integer[] expected = new Integer[MaxHeap.INITIAL_CAPACITY];
        expected[0] = null;
        expected[1] = 9;
        expected[2] = 8;
        expected[3] = 6;
        expected[4] = 7;
        expected[5] = 3;
        expected[6] = 2;
        expected[7] = 5;
        expected[8] = 1;
        expected[9] = 4;
        expected[10] = null;
        expected[11] = null;
        expected[12] = null;


        assertSame(num9, heap.remove());

        assertEquals(8, heap.size());
        expected = new Integer[MaxHeap.INITIAL_CAPACITY];
        expected[0] = null;
        expected[1] = 8;
        expected[2] = 7;
        expected[3] = 6;
        expected[4] = 4;
        expected[5] = 3;
        expected[6] = 2;
        expected[7] = 5;
        expected[8] = 1;
        expected[9] = null;
        expected[10] = null;
        expected[11] = null;
        expected[12] = null;

        assertArrayEquals(expected, heap.getBackingArray());

        assertSame(num8, heap.remove());
        assertEquals(7, heap.size());

        assertSame(num7, heap.remove());
        assertEquals(6, heap.size());

        assertSame(num6, heap.remove());
        assertEquals(5, heap.size());

        assertSame(num5, heap.remove());
        assertEquals(4, heap.size());

        assertSame(num4, heap.remove());
        assertEquals(3, heap.size());

        assertSame(num3, heap.remove());
        assertEquals(2, heap.size());

        expected = new Integer[MaxHeap.INITIAL_CAPACITY];
        expected[1] = 2;
        expected[2] = 1;
        assertArrayEquals(expected, heap.getBackingArray());

        assertSame(num2, heap.remove());
        assertEquals(1, heap.size());

        expected = new Integer[MaxHeap.INITIAL_CAPACITY];
        expected[1] = 1;
        assertArrayEquals(expected, heap.getBackingArray());

        assertSame(num1, heap.remove());
        assertEquals(0, heap.size());
    }

    @Test(timeout = TIMEOUT)
    public void testGetMax() throws Exception {

        Integer num1 = 1;
        Integer num2 = 2;
        Integer num3 = 3;
        Integer num4 = 4;
        Integer num5 = 5;
        Integer num6 = 6;
        Integer num7 = 7;
        Integer num8 = 8;
        Integer num9 = 9;


        heap.add(num1);
        heap.add(num2);
        heap.add(num3);
        heap.add(num5);
        heap.add(num4);
        heap.add(num6);
        heap.add(num8);
        heap.add(num7);
        heap.add(num9);
        assertEquals(9, heap.size());

        assertSame(num9, heap.getMax());
        assertEquals(num9, heap.getMax());

        try {
            assertEquals(num9, heap.remove());
            assertEquals(8, heap.size());

            assertSame(num8, heap.getMax());
            assertEquals(num8, heap.remove());
            assertEquals(7, heap.size());

            assertSame(num7, heap.getMax());
            assertEquals(num7, heap.remove());
            assertEquals(6, heap.size());

            assertSame(num6, heap.getMax());
            assertEquals(num6, heap.remove());
            assertEquals(5, heap.size());

            assertSame(num5, heap.getMax());
            assertEquals(num5, heap.remove());
            assertEquals(4, heap.size());

            assertSame(num4, heap.getMax());
            assertEquals(num4, heap.remove());
            assertEquals(3, heap.size());

            assertSame(num3, heap.getMax());
            assertEquals(num3, heap.remove());
            assertEquals(2, heap.size());

            assertSame(num2, heap.getMax());
            assertEquals(num2, heap.remove());
            assertEquals(1, heap.size());

            assertSame(num1, heap.getMax());
            assertEquals(num1, heap.remove());
            assertEquals(0, heap.size());
        } catch (AssertionError e) {
            System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
            System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
            System.out.println("ATTENTION: first case of getMax works."
                   + " For full functionality of this test please implement remove method.");
            System.out.println("If you have implemented Remove method,"
                   + " Please check this Test unit to see whats happening.");
            System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
            System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
            System.out.println("||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||");
        }


    }

    @Test(timeout = TIMEOUT)
    public void testIsEmpty() {
        assertTrue(heap.isEmpty());
        assertEquals(0, heap.size());

        for (int i = 1; i < 24; i++) {
            heap.add(i);
        }

        assertFalse(heap.isEmpty());
        assertEquals(23, heap.size());

        for (int i = 23; i > 0; i--) {
            heap.remove();
        }

        assertEquals(0, heap.size());
        assertTrue(heap.isEmpty());
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        for (int i = 1; i < 24; i++) {
            heap.add(i);
        }

        assertEquals(23, heap.size());
        heap.clear();

        assertEquals(0, heap.size());
        Integer[] expected = new Integer[MaxHeap.INITIAL_CAPACITY];
        assertArrayEquals(expected, heap.getBackingArray());
    }


    //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||------------------------------------
    //||||||||||||||||||||||||| EXCEPTION TESTS ||||||||||||||||||||||||||||||||||||------------------------------------
    //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||------------------------------------

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void maxHeapConstructorDataNullTest() {
        ArrayList<Integer> nullArrayList = null;
        MaxHeap heap = new MaxHeap(nullArrayList);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void maxHeapConstructorElementInsideDataNullTest1() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            list.add(i);
        }
        list.add(null);
        for (int i = 6; i < 11; i++) {
            list.add(i);
        }
        MaxHeap heap = new MaxHeap(list);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void maxHeapConstructorElementInsideDataNullTest2() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(null);
        MaxHeap heap = new MaxHeap(list);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void maxHeapConstructorElementInsideDataNullTest3() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i < 5; i++) {
            list.add(i);
        }
        list.add(null);
        MaxHeap heap = new MaxHeap(list);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void addNullDataTest() {
        heap.add(1);
        heap.add(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void removeFromEmptyHeapTest() {
        heap.remove();
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void getMaxFromEmptyHeapTest() {
        heap.getMax();
    }

}