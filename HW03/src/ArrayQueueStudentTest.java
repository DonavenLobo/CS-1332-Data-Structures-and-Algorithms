import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * This is a basic set of unit tests for ArrayQueue and LinkedQueue.
 *
 * Passing these tests doesn't guarantee any grade on these assignments. These
 * student JUnits that we provide should be thought of as a sanity check to
 * help you get started on the homework and writing JUnits in general.
 *
 * We highly encourage you to write your own set of JUnits for each homework
 * to cover edge cases you can think of for each data structure. Your code must
 * work correctly and efficiently in all cases, which is why it's important
 * to write comprehensive tests to cover as many cases as possible.
 *
 * @author CS 1332 TAs
 * @version 1.0
 */
public class ArrayQueueStudentTest {

    private static final int TIMEOUT = 200;
    private ArrayQueue<String> array;

    @Before
    public void setup() {
        array = new ArrayQueue<>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, array.size());
        assertArrayEquals(new Object[ArrayQueue.INITIAL_CAPACITY],
                array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayStartEnqueue1() {
        array.enqueue("0a");
        array.enqueue("1a");
        array.enqueue("2a");
        array.enqueue("3a");
        array.enqueue("4a");

        assertEquals(5, array.size());

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayStartEnqueue2() {
        array.enqueue("4a");
        array.enqueue("3a");
        array.enqueue("2a");
        array.enqueue("1a");
        array.enqueue("0a");

        assertEquals(5, array.size());

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[0] = "4a";
        expected[1] = "3a";
        expected[2] = "2a";
        expected[3] = "1a";
        expected[4] = "0a";
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayEnqueue1() {
        testArrayStartEnqueue1();
        array.enqueue("0a");
        array.enqueue("1a");
        array.enqueue("2a");
        array.enqueue("3a");
        array.enqueue("4a");

        assertEquals(10, array.size());

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY * 2];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        expected[5] = "0a";
        expected[6] = "1a";
        expected[7] = "2a";
        expected[8] = "3a";
        expected[9] = "4a";
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)            //***** Important edge case. Resizing multiple times.
    public void testArrayEnqueue2a() {
        testArrayStartEnqueue1();
        array.dequeue();
        array.dequeue();
        array.enqueue("0a");
        array.enqueue("1a");
        array.enqueue("2a");
        array.enqueue("3a");
        array.enqueue("4a");
        array.enqueue("5a");
        array.enqueue("6a");
        array.enqueue("0a");
        array.enqueue("1a");
        array.enqueue("2a");
        array.enqueue("3a");
        array.enqueue("4a");
        array.enqueue("5a");
        array.enqueue("6a");
        array.enqueue("0a");
        array.enqueue("1a");
        array.enqueue("2a");
        array.enqueue("3a");
        array.enqueue("4a");
        array.enqueue("5a");
        array.enqueue("6a");

        assertEquals(24, array.size());
        assertEquals(36, array.INITIAL_CAPACITY * 4);

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY * 4];
        expected[0] = "2a";
        expected[1] = "3a";
        expected[2] = "4a";
        expected[3] = "0a";
        expected[4] = "1a";
        expected[5] = "2a";
        expected[6] = "3a";
        expected[7] = "4a";
        expected[8] = "5a";
        expected[9] = "6a";
        expected[10] = "0a";
        expected[11] = "1a";
        expected[12] = "2a";
        expected[13] = "3a";
        expected[14] = "4a";
        expected[15] = "5a";
        expected[16] = "6a";
        expected[17] = "0a";
        expected[18] = "1a";
        expected[19] = "2a";
        expected[20] = "3a";
        expected[21] = "4a";
        expected[22] = "5a";
        expected[23] = "6a";
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)            //***** Important edge case. Resizing and front circling back around.
    public void testArrayEnqueue2b() {
        testArrayStartEnqueue1();
        array.dequeue();
        array.dequeue();
        array.enqueue("0a");
        array.enqueue("1a");
        array.enqueue("2a");
        array.enqueue("3a");
        array.enqueue("4a");
        array.enqueue("5a");
        array.enqueue("6a");

        assertEquals(10, array.size());

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY * 2];
        expected[0] = "2a";
        expected[1] = "3a";
        expected[2] = "4a";
        expected[3] = "0a";
        expected[4] = "1a";
        expected[5] = "2a";
        expected[6] = "3a";
        expected[7] = "4a";
        expected[8] = "5a";
        expected[9] = "6a";
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayEnqueue3() {
        testArrayStartEnqueue1();
        array.dequeue();
        array.dequeue();

        array.enqueue("0a");    // 0a
        array.enqueue("1a");    // 0a, 1a
        array.enqueue("2a");    // 0a, 1a, 2a
        array.enqueue("3a");    // 0a, 1a, 2a, 3a
        array.enqueue("4a");    // 0a, 1a, 2a, 3a, 4a

        assertEquals(8, array.size());

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        expected[5] = "0a";
        expected[6] = "1a";
        expected[7] = "2a";
        expected[8] = "3a";
        expected[0] = "4a";
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayEnqueue4() {           // Test Dequeuing and Enqueuing while circling back around.
        testArrayDequeue2();
        array.enqueue("6a");    // 0a, 1a, 2a, 3a
        array.enqueue("7a");    // 0a, 1a, 2a, 3a, 4a
        array.enqueue("8a");    // 0a, 1a, 2a, 3a, 4a, 5a
        array.enqueue("9a");    // 0a, 1a, 2a, 3a, 4a, 5a
        assertEquals(6, array.size());
        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[0] = "9a";
        expected[4] = "4a";
        expected[5] = "5a";
        expected[6] = "6a";
        expected[7] = "7a";
        expected[8] = "8a";
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayDequeue1() {
        testArrayStartEnqueue1();
        String temp = "0a";

        array.enqueue(temp);          // 0a
        array.enqueue("1a");    // 0a, 1a
        array.enqueue("2a");    // 0a, 1a, 2a
        array.enqueue("3a");    // 0a, 1a, 2a, 3a
        array.enqueue("4a");    // 0a, 1a, 2a, 3a, 4a
        array.enqueue("5a");    // 0a, 1a, 2a, 3a, 4a, 5a
        assertEquals(11, array.size());

        assertSame(temp, array.dequeue());  // 1a, 2a, 3a, 4a, 5a

        assertEquals(10, array.size());

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY * 2];
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        expected[5] = "0a";
        expected[6] = "1a";
        expected[7] = "2a";
        expected[8] = "3a";
        expected[9] = "4a";
        expected[10] = "5a";
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayDequeue2() {
        String temp = "0a";

        array.enqueue(temp);          // 0a
        array.enqueue("1a");    // 0a, 1a
        array.enqueue("2a");    // 0a, 1a, 2a
        array.enqueue("3a");    // 0a, 1a, 2a, 3a
        array.enqueue("4a");    // 0a, 1a, 2a, 3a, 4a
        array.enqueue("5a");    // 0a, 1a, 2a, 3a, 4a, 5a
        assertEquals(6, array.size());

        assertSame(temp, array.dequeue());
        assertSame("1a", array.dequeue());
        assertSame("2a", array.dequeue());
        assertSame("3a", array.dequeue());

        assertEquals(2, array.size());

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[4] = "4a";
        expected[5] = "5a";
        assertArrayEquals(expected, array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayPeek1() {
        testArrayEnqueue4();
        String temp = "4a";
        assertEquals(6, array.size());

        assertSame(temp, array.peek());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayPeek2() {
        testArrayEnqueue2b();
        String temp = "2a";
        assertEquals(10, array.size());

        assertSame(temp, array.peek());
    }
}
