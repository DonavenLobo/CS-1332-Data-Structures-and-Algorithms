import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

/**
 *
 * @author Rudresh Patel
 *
 * @version 1.0
 *
 * To all the Ramblin Traders who invested in GME, Diamond Hands.
 * Disclaimer: This is not a FINANCIAL ADVICE. It is my opinion.
 *
 * NOTE: Base template for these tests was taken from the "QueueStudentTest.java" file provided by the TAs.
 *       Some tests maybe similar if not same to the provided student tests by the TAs.
 */
public class RudreshPatelArrayBackedQueueTest {

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
    public void testArrayEnqueue() {
        //Edge Case: the front variable wraps around when there is space in the "beginning of the list"
        array.enqueue("0a");    // 0a
        array.enqueue("1a");    // 0a, 1a
        array.enqueue("2a");    // 0a, 1a, 2a
        array.enqueue("3a");    // 0a, 1a, 2a, 3a
        array.enqueue("4a");    // 0a, 1a, 2a, 3a, 4a
        array.enqueue("5a");
        array.enqueue("6a");
        array.enqueue("7a");
        array.enqueue("8a");  // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a
        assertEquals(9, array.size());

        assertSame("0a", array.peek()); // Peek Test

        assertSame("0a", array.dequeue()); // 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a
        assertSame("1a", array.dequeue()); // 2a, 3a, 4a, 5a, 6a, 7a, 8a
        assertSame("2a", array.dequeue()); // 3a, 4a, 5a, 6a, 7a, 8a
        assertEquals(6, array.size());

        assertSame("3a", array.peek()); // Peek Test

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[0] = null;
        expected[1] = null;
        expected[2] = null;
        expected[3] = "3a";
        expected[4] = "4a";
        expected[5] = "5a";
        expected[6] = "6a";
        expected[7] = "7a";
        expected[8] = "8a";
        assertArrayEquals(expected, array.getBackingArray());

        array.enqueue("9a");  //wrap around to index 0
        assertEquals(7, array.size());

        assertSame("3a", array.peek()); // Peek Test

        expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[0] = "9a";
        expected[1] = null;
        expected[2] = null;
        expected[3] = "3a";
        expected[4] = "4a";
        expected[5] = "5a";
        expected[6] = "6a";
        expected[7] = "7a";
        expected[8] = "8a";
        assertArrayEquals(expected, array.getBackingArray());

        array.enqueue("10a"); //wrap around to index 1
        assertEquals(8, array.size());

        assertSame("3a", array.peek()); // Peek Test

        expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[0] = "9a";
        expected[1] = "10a";
        expected[2] = null;
        expected[3] = "3a";
        expected[4] = "4a";
        expected[5] = "5a";
        expected[6] = "6a";
        expected[7] = "7a";
        expected[8] = "8a";
        assertArrayEquals(expected, array.getBackingArray());

        //Edge Case: Resize and making sure the elements are copied in sequence
        array.enqueue("11a");
        array.enqueue("12a");
        array.enqueue("13a"); //  3a, 4a, 5a, 6a, 7a, 8a -> wrap around ->  9a, 10a, 11a, 12a, 13a
        assertEquals(11, array.size());

        assertSame("3a", array.peek()); // Peek Test

        expected = new Object[ArrayQueue.INITIAL_CAPACITY * 2];
        expected[0] = "3a";
        expected[1] = "4a";
        expected[2] = "5a";
        expected[3] = "6a";
        expected[4] = "7a";
        expected[5] = "8a";
        expected[6] = "9a";
        expected[7] = "10a";
        expected[8] = "11a";
        expected[9] = "12a";
        expected[10] = "13a";
        assertArrayEquals(expected, array.getBackingArray());
    }


    @Test(timeout = TIMEOUT)
    public void testArrayDequeue() {
        //Peek Tests are inside this JUnit

        array.enqueue("0a");    // 0a
        array.enqueue("1a");    // 0a, 1a
        array.enqueue("2a");    // 0a, 1a, 2a
        array.enqueue("3a");    // 0a, 1a, 2a, 3a
        array.enqueue("4a");    // 0a, 1a, 2a, 3a, 4a
        array.enqueue("5a");    // 0a, 1a, 2a, 3a, 4a, 5a
        array.enqueue("6a");
        assertEquals(7, array.size());

        assertSame("0a", array.peek()); // Peek Test

        //Edge Case: front should not be zero when the queue is empty
        assertSame("0a", array.dequeue());
        assertSame("1a", array.dequeue());
        assertSame("2a", array.dequeue());
        assertSame("3a", array.dequeue());
        assertSame("4a", array.dequeue());
        assertSame("5a", array.dequeue());
        assertSame("6a", array.dequeue());
        assertEquals(0, array.size());

        try{
            array.peek();
        } catch (NoSuchElementException e) {
            System.out.println("Peek Test: NoSuchElementException thrown: Size is zero (this was planned. line 150)");
        }


        array.enqueue("7a");
        assertEquals(1, array.size());
        assertSame("7a", array.peek()); // Peek Test


        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[0] = null;
        expected[1] = null;
        expected[2] = null;
        expected[3] = null;
        expected[4] = null;
        expected[5] = null;
        expected[6] = null;
        expected[7] = "7a";
        expected[8] = null;
        assertArrayEquals(expected, array.getBackingArray());

        //Test: Checking if the front is changed.
        array.enqueue("8a");
        assertSame("7a", array.dequeue());
        assertEquals(1, array.size());
        assertSame("8a", array.peek()); // Peek Test


        expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[0] = null;
        expected[1] = null;
        expected[2] = null;
        expected[3] = null;
        expected[4] = null;
        expected[5] = null;
        expected[6] = null;
        expected[7] = null;
        expected[8] = "8a";
        assertArrayEquals(expected, array.getBackingArray());

        array.enqueue("9a");
        array.enqueue("10a");
        array.enqueue("11a");
        array.enqueue("12a");
        array.enqueue("13a");
        array.enqueue("14a");
        array.enqueue("15a");
        array.enqueue("16a");


        assertEquals(9, array.size());
        assertSame("8a", array.peek()); // Peek Test


        expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[0] = "9a";
        expected[1] = "10a";
        expected[2] = "11a";
        expected[3] = "12a";
        expected[4] = "13a";
        expected[5] = "14a";
        expected[6] = "15a";
        expected[7] = "16a";
        expected[8] = "8a";
        assertArrayEquals(expected, array.getBackingArray());

        //resize and dequeue
        array.enqueue("17a"); // 8a -> wrap around -> 9a, 10a, 11a, 12a, 13a, 14a, 15a, 16a, 17a
        assertEquals(10, array.size());

        assertSame("8a", array.dequeue());

        expected = new Object[ArrayQueue.INITIAL_CAPACITY * 2];
        expected[0] = null;
        expected[1] = "9a";
        expected[2] = "10a";
        expected[3] = "11a";
        expected[4] = "12a";
        expected[5] = "13a";
        expected[6] = "14a";
        expected[7] = "15a";
        expected[8] = "16a";
        expected[9] = "17a";
        assertArrayEquals(expected, array.getBackingArray());
        assertEquals(9, array.size());
        assertSame("9a", array.peek()); // Peek Test
    }

    //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||------------------------------------
    //||||||||||||||||||||||||| EXCEPTION TESTS ||||||||||||||||||||||||||||||||||||------------------------------------
    //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||------------------------------------

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void enqueueNullDataExceptionTest() {
        array.enqueue("0a");   // 0
        array.enqueue(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void dequeueEmptyExceptionTest() {
        array.enqueue("0a");
        assertSame("0a", array.dequeue());
        array.dequeue();
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void peakEmptyExceptionTest() {
        array.enqueue("0a");
        assertSame("0a", array.dequeue());
        array.dequeue();
    }
}
