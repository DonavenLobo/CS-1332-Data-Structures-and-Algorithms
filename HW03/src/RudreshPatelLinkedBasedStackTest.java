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
 * NOTE: Base template for these tests was taken from the "StackStudentTest.java" file provided by the TAs.
 *       Some tests maybe similar if not same to the provided student tests by the TAs.
 */
public class RudreshPatelLinkedBasedStackTest {
    
    private static final int TIMEOUT = 200;
    private LinkedStack<String> linked;

    @Before
    public void setup() {
        linked = new LinkedStack<>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, linked.size());
        assertNull(linked.getHead());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedPush() {

        linked.push("0a");   // 0a

        //Edge Case: Head and tail proper assignment

        LinkedNode<String> cur = linked.getHead();
        assertEquals("0a", linked.getHead().getData());
        assertNull(cur.getNext());


        assertSame("0a", linked.peek());    // Peak Test

        linked.push("1a");   // 0a, 1a
        linked.push("2a");   // 0a, 1a, 2a
        linked.push("3a");   // 0a, 1a, 2a, 3a
        linked.push("4a");   // 0a, 1a, 2a, 3a, 4a
        linked.push("5a");
        linked.push("6a");
        linked.push("7a");
        linked.push("8a");  // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a

        assertEquals(9, linked.size());

        cur = linked.getHead();
        assertNotNull(cur);
        assertEquals("8a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("7a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("6a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("5a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("0a", cur.getData());

        cur = cur.getNext();
        assertNull(cur);
        assertSame("8a", linked.peek());    // Peak Test
        assertEquals("8a", linked.getHead().getData());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedPop() {
        linked.push("0a");   // 0a

        //Start - copy from testLinkedPush() above
        LinkedNode<String> cur = linked.getHead();
        assertEquals("0a", linked.getHead().getData());
        assertNull(cur.getNext());
        assertSame("0a", linked.peek());    // Peak Test

        linked.push("1a");   // 0a, 1a
        linked.push("2a");   // 0a, 1a, 2a
        linked.push("3a");   // 0a, 1a, 2a, 3a
        linked.push("4a");   // 0a, 1a, 2a, 3a, 4a
        linked.push("5a");
        linked.push("6a");
        linked.push("7a");
        linked.push("8a");  // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a

        assertEquals(9, linked.size());

        cur = linked.getHead();
        assertNotNull(cur);
        assertEquals("8a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("7a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("6a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("5a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("0a", cur.getData());

        cur = cur.getNext();
        assertNull(cur);
        assertSame("8a", linked.peek());    // Peak Test
        assertEquals("8a", linked.getHead().getData());
        //End

        assertSame("8a", linked.pop()); // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a
        assertEquals(8, linked.size());

        cur = linked.getHead();
        assertNotNull(cur);
        assertEquals("7a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("6a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("5a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        assertEquals("0a", cur.getData());

        cur = cur.getNext();
        assertNull(cur);
        assertSame("7a", linked.peek());    // Peak Test
        assertEquals("7a", linked.getHead().getData());


        for (int i = 7; i > 0; i--) {
            String str = i + "a";
            assertEquals(str, linked.peek());
            linked.pop();
        }

        // 0a
        assertEquals(1, linked.size());


        cur = linked.getHead();
        assertNotNull(cur);
        assertEquals("0a", cur.getData());

        cur = cur.getNext();
        assertNull(cur);
        assertSame("0a", linked.peek());    // Peak Test
        assertEquals("0a", linked.getHead().getData());

        assertSame("0a", linked.pop());

        assertNull(linked.getHead());
        assertEquals(0, linked.size());

        try {
            linked.peek();
        } catch (NoSuchElementException e) {
            System.out.println("Peek Test: NoSuchElementException thrown: Size is zero (this was planned. line 234)");
        }
    }

    //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||------------------------------------
    //||||||||||||||||||||||||| EXCEPTION TESTS ||||||||||||||||||||||||||||||||||||------------------------------------
    //||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||------------------------------------

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void pushNullDataExceptionTest() {
        linked.push("0a");   // 0
        linked.push(null);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void popEmptyExceptionTest() {
        linked.push("0a");
        assertSame("0a", linked.pop());
        linked.pop();
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void peekEmptyExceptionTest() {
        linked.push("0a");
        assertSame("0a", linked.pop());
        linked.pop();
    }
}
