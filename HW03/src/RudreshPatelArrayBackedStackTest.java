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
 * NOTE: Base template for these tests was taken from the "StackStudentTest.java" file provided by the TAs.
 *       Some tests maybe similar if not same to the provided student tests by the TAs.
 */
public class RudreshPatelArrayBackedStackTest {

    private static final int TIMEOUT = 200;
    private ArrayStack<String> array;

    @Before
    public void setup() {
        array = new ArrayStack<>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, array.size());
        assertArrayEquals(new Object[ArrayQueue.INITIAL_CAPACITY],
                array.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testArrayPush() {
        //Edge Case: the front variable wraps around when there is space in the "beginning of the list"
        array.push("0a");    // 0a
        array.push("1a");    // 0a, 1a
        array.push("2a");    // 0a, 1a, 2a
        array.push("3a");    // 0a, 1a, 2a, 3a
        array.push("4a");    // 0a, 1a, 2a, 3a, 4a
        array.push("5a");
        array.push("6a");
        array.push("7a");
        array.push("8a");  // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a
        assertEquals(9, array.size());

        assertSame("8a", array.peek()); // Peek Test

        assertSame("8a", array.pop()); // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a
        assertSame("7a", array.pop()); // 0a, 1a, 2a, 3a, 4a, 5a, 6a
        assertSame("6a", array.pop()); // 0a, 1a, 2a, 3a, 4a, 5a
        assertEquals(6, array.size());

        assertSame("5a", array.peek()); // Peek Test

        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        expected[5] = "5a";
        expected[6] = null;
        expected[7] = null;
        expected[8] = null;
        assertArrayEquals(expected, array.getBackingArray());

        array.push("6b");
        assertEquals(7, array.size());

        assertSame("6b", array.peek()); // Peek Test

        expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        expected[5] = "5a";
        expected[6] = "6b";
        expected[7] = null;
        expected[8] = null;
        assertArrayEquals(expected, array.getBackingArray());

        array.push("7b");
        assertEquals(8, array.size());

        assertSame("7b", array.peek()); // Peek Test

        expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        expected[5] = "5a";
        expected[6] = "6b";
        expected[7] = "7b";
        expected[8] = null;
        assertArrayEquals(expected, array.getBackingArray());

        //Edge Case: Resize and making sure the elements are copied in sequence
        array.push("8b");
        array.push("9b");
        array.push("10b"); //  0a, 1a, 2a, 3a, 4a, 5a, 6b, 7b, 8b, 9b, 10b
        assertEquals(11, array.size());

        assertSame("10b", array.peek()); // Peek Test

        expected = new Object[ArrayQueue.INITIAL_CAPACITY * 2];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        expected[5] = "5a";
        expected[6] = "6b";
        expected[7] = "7b";
        expected[8] = "8b";
        expected[9] = "9b";
        expected[10] = "10b";
        assertArrayEquals(expected, array.getBackingArray());
    }


    @Test(timeout = TIMEOUT)
    public void testArrayPop() {
        //Peek Tests are inside this JUnit

        array.push("0a");    // 0a
        array.push("1a");    // 0a, 1a
        array.push("2a");    // 0a, 1a, 2a
        array.push("3a");    // 0a, 1a, 2a, 3a
        array.push("4a");    // 0a, 1a, 2a, 3a, 4a
        array.push("5a");    // 0a, 1a, 2a, 3a, 4a, 5a
        array.push("6a");
        assertEquals(7, array.size());

        assertSame("6a", array.peek()); // Peek Test

        //Edge Case: front should not be zero when the queue is empty
        assertSame("6a", array.pop());
        assertSame("5a", array.pop());
        assertSame("4a", array.pop());
        assertSame("3a", array.pop());
        assertSame("2a", array.pop());
        assertSame("1a", array.pop());
        assertSame("0a", array.pop());
        assertEquals(0, array.size());

        try{
            array.peek();
        } catch (NoSuchElementException e) {
            System.out.println("Peek Test: NoSuchElementException thrown: Size is zero (this was planned. line 160)");
        }


        array.push("0b");
        assertEquals(1, array.size());
        assertSame("0b", array.peek()); // Peek Test


        Object[] expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[0] = "0b";
        expected[1] = null;
        expected[2] = null;
        expected[3] = null;
        expected[4] = null;
        expected[5] = null;
        expected[6] = null;
        expected[7] = null;
        expected[8] = null;
        assertArrayEquals(expected, array.getBackingArray());

        //Test: Checking if the front is changed.
        array.push("1b");
        assertEquals(2, array.size());
        assertSame("1b", array.peek()); // Peek Test


        expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[0] = "0b";
        expected[1] = "1b";
        expected[2] = null;
        expected[3] = null;
        expected[4] = null;
        expected[5] = null;
        expected[6] = null;
        expected[7] = null;
        expected[8] = null;
        assertArrayEquals(expected, array.getBackingArray());




        assertSame("1b", array.pop());
        assertEquals(1, array.size());
        assertSame("0b", array.peek()); // Peek Test


        expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[0] = "0b";
        expected[1] = null;
        expected[2] = null;
        expected[3] = null;
        expected[4] = null;
        expected[5] = null;
        expected[6] = null;
        expected[7] = null;
        expected[8] = null;
        assertArrayEquals(expected, array.getBackingArray());

        array.push("1c");
        array.push("2c");
        array.push("3c");
        array.push("4c");
        array.push("5c");
        array.push("6c");
        array.push("7c");
        array.push("8c");


        assertEquals(9, array.size());
        assertSame("8c", array.peek()); // Peek Test


        expected = new Object[ArrayQueue.INITIAL_CAPACITY];
        expected[0] = "0b";
        expected[1] = "1c";
        expected[2] = "2c";
        expected[3] = "3c";
        expected[4] = "4c";
        expected[5] = "5c";
        expected[6] = "6c";
        expected[7] = "7c";
        expected[8] = "8c";
        assertArrayEquals(expected, array.getBackingArray());

        //resize and dequeue
        array.push("9c"); // 0b, 1c, 2c, 3c, 4c, 5c, 6c, 7c, 8c, 9c
        array.push("10c");
        assertEquals(11, array.size());

        expected = new Object[ArrayQueue.INITIAL_CAPACITY * 2];
        expected[0] = "0b";
        expected[1] = "1c";
        expected[2] = "2c";
        expected[3] = "3c";
        expected[4] = "4c";
        expected[5] = "5c";
        expected[6] = "6c";
        expected[7] = "7c";
        expected[8] = "8c";
        expected[9] = "9c";
        expected[10] = "10c";

        assertArrayEquals(expected, array.getBackingArray());
        assertEquals(11, array.size());

        assertSame("10c", array.peek()); // Peek Test

        assertSame("10c", array.pop());

        expected = new Object[ArrayQueue.INITIAL_CAPACITY * 2];
        expected[0] = "0b";
        expected[1] = "1c";
        expected[2] = "2c";
        expected[3] = "3c";
        expected[4] = "4c";
        expected[5] = "5c";
        expected[6] = "6c";
        expected[7] = "7c";
        expected[8] = "8c";
        expected[9] = "9c";
        expected[10] = null;
        assertArrayEquals(expected, array.getBackingArray());
        assertEquals(10, array.size());
        assertSame("9c", array.peek()); // Peek Test
    }

    //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||------------------------------------
    //||||||||||||||||||||||||| EXCEPTION TESTS ||||||||||||||||||||||||||||||||||||------------------------------------
    //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||------------------------------------

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void pushNullDataExceptionTest() {
        array.push("0a");   // 0a
        array.push(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void popEmptyExceptionTest() {
        array.push("0a");
        assertSame("0a", array.pop());
        array.pop();
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void peekEmptyExceptionTest() {
        array.push("0a");
        assertSame("0a", array.pop());
        array.pop();
    }
}
