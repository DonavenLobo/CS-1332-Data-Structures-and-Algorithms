import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.*;

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
public class RudreshPatelLinkedBasedQueueTest {

    private static final int TIMEOUT = 200;
    private LinkedQueue<String> linked;

    @Before
    public void setup() {
        linked = new LinkedQueue<>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, linked.size());
        assertNull(linked.getHead());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedEnqueue() {

        linked.enqueue("0a");   // 0a

        //Edge Case: Head and tail proper assignment

        LinkedNode<String> cur = linked.getHead();
        assertEquals("0a", linked.getHead().getData());
        assertEquals(cur, linked.getTail());
        assertNull(cur.getNext());


        assertSame("0a", linked.peek());    // Peak Test

        linked.enqueue("1a");   // 0a, 1a
        linked.enqueue("2a");   // 0a, 1a, 2a
        linked.enqueue("3a");   // 0a, 1a, 2a, 3a
        linked.enqueue("4a");   // 0a, 1a, 2a, 3a, 4a
        linked.enqueue("5a");
        linked.enqueue("6a");
        linked.enqueue("7a");
        linked.enqueue("8a");  // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a

        assertEquals(9, linked.size());

        cur = linked.getHead();
        assertNotNull(cur);
        assertEquals("0a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("5a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("6a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("7a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("8a", cur.getData());

        assertEquals(cur, linked.getTail());

        cur = cur.getNext();
        assertNull(cur);
        assertSame("0a", linked.peek());    // Peak Test
        assertEquals("0a", linked.getHead().getData());
    }





    @Test(timeout = TIMEOUT)
    public void testLinkedDequeue() {
        linked.enqueue("0a");   // 0a

        //Start - copy from testLinkedEnqueue() above
        LinkedNode<String> cur = linked.getHead();
        assertEquals("0a", linked.getHead().getData());
        assertEquals(cur, linked.getTail());
        assertNull(cur.getNext());

        assertSame("0a", linked.peek());    // Peak Test

        linked.enqueue("1a");   // 0a, 1a
        linked.enqueue("2a");   // 0a, 1a, 2a
        linked.enqueue("3a");   // 0a, 1a, 2a, 3a
        linked.enqueue("4a");   // 0a, 1a, 2a, 3a, 4a
        linked.enqueue("5a");
        linked.enqueue("6a");
        linked.enqueue("7a");
        linked.enqueue("8a");  // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a

        assertEquals(9, linked.size());

        cur = linked.getHead();
        assertNotNull(cur);
        assertEquals("0a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("5a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("6a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("7a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("8a", cur.getData());

        assertEquals(cur, linked.getTail());

        cur = cur.getNext();
        assertNull(cur);
        assertSame("0a", linked.peek());    // Peak Test
        assertEquals("0a", linked.getHead().getData());
        //End

        assertSame("0a", linked.dequeue()); // 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a
        assertEquals(8, linked.size());

        cur = linked.getHead();
        assertNotNull(cur);
        assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("5a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("6a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("7a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("8a", cur.getData());

        assertEquals(cur, linked.getTail());

        cur = cur.getNext();
        assertNull(cur);
        assertSame("1a", linked.peek());    // Peak Test
        assertEquals("1a", linked.getHead().getData());


        for (int i = 0; i < 7; i++) {
            String str = (i + 1) + "a";
            assertEquals(str, linked.peek());
            linked.dequeue();
        }

        // 8a
        assertEquals(1, linked.size());


        cur = linked.getHead();
        assertNotNull(cur);
        assertEquals("8a", cur.getData());
        assertEquals(cur, linked.getTail());

        cur = cur.getNext();
        assertNull(cur);
        assertSame("8a", linked.peek());    // Peak Test
        assertEquals("8a", linked.getHead().getData());

        assertSame("8a", linked.dequeue());


        assertNull(linked.getHead());
        assertNull(linked.getTail());
        assertEquals(0, linked.size());

        try {
            linked.peek();
        } catch (NoSuchElementException e) {
            System.out.println("Peek Test: NoSuchElementException thrown: Size is zero (this was planned. line 238)");
        }
    }

    //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||------------------------------------
    //||||||||||||||||||||||||| EXCEPTION TESTS ||||||||||||||||||||||||||||||||||||------------------------------------
    //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||------------------------------------

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void enqueueNullDataExceptionTest() {
        linked.enqueue("0a");   // 0
        linked.enqueue(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void dequeueEmptyExceptionTest() {
        linked.enqueue("0a");
        assertSame("0a", linked.dequeue());
        linked.dequeue();
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void peekEmptyExceptionTest() {
        linked.enqueue("0a");
        assertSame("0a", linked.dequeue());
        linked.dequeue();
    }
}