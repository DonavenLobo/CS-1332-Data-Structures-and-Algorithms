import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;


/**
 * This is a basic set of unit tests for ArrayStack and LinkedStack.
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
 * @author Jose Castejon
 * @version 1.0
 */
public class JCStackStudentTest {

    private static final int TIMEOUT = 200;
    private ArrayStack<String> array;
    private LinkedStack<String> linked;

    @Before
    public void setup() {
        array = new ArrayStack<>();
        linked = new LinkedStack<>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        Assert.assertEquals(0, array.size());
        Assert.assertArrayEquals(new Object[ArrayStack.INITIAL_CAPACITY],
            array.getBackingArray());
        Assert.assertEquals(0, linked.size());
        assertNull(linked.getHead());
    }

    // Test if array resizes properly
    @Test(timeout = TIMEOUT)
    public void testArrayResize() {
        array.push("0a");   // 0a
        array.push("1a");   // 0a, 1a
        array.push("2a");   // 0a, 1a, 2a
        array.push("3a");   // 0a, 1a, 2a, 3a
        array.push("4a");   // 0a, 1a, 2a, 3a, 4a
        array.push("5a");   // 0a, 1a, 2a, 3a, 4a, 5a
        array.push("6a");   // 0a, 1a, 2a, 3a, 4a, 5a, 6a
        array.push("7a");   // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a
        array.push("8a");   // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a
        array.push("9a");   // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a, 9a (resize should happen here)

        Object[] expected = new Object[ArrayStack.INITIAL_CAPACITY * 2];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        expected[5] = "5a";
        expected[6] = "6a";
        expected[7] = "7a";
        expected[8] = "8a";
        expected[9] = "9a";
        Assert.assertArrayEquals(expected, array.getBackingArray());
        Assert.assertEquals(10, array.size());
    }

    // Tests that pushing null data results in IllegalArgumentException, and size is not modified.
    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testNullPush() {
        array.push(null);
        linked.push(null);
        assertEquals(0, array.size());
        assertEquals(0, linked.size());
    }


    // Tests that popping from empty stack results in NoSuchElementException, and size is not modified.
    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testEmptyPop() {
        array.pop();
        linked.pop();
        assertEquals(0, array.size());
        assertEquals(0, linked.size());
    }

    // Tests that peeking into empty stack results in NoSuchElementException, and size is not modified.
    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testEmptyPeek() {
        array.peek();
        linked.peek();
        assertEquals(0, array.size());
        assertEquals(0, linked.size());
    }


    //Tests that subsequent popping/pushing works correctly.
    @Test(timeout = TIMEOUT)
    public void testArrayPushAfterPop() {
        array.push("0a"); // 0a
        array.push("1a"); // 0a, 1a
        array.pop(); // 0a
        array.push("1b"); // 0a, 1b
        Object[] expected = new Object[ArrayStack.INITIAL_CAPACITY];
        expected[0] = "0a";
        expected[1] = "1b";
        Assert.assertArrayEquals(expected, array.getBackingArray());
        Assert.assertEquals(2, array.size());
    }

    // Tests that peek() funcitons properly after a pop and a subsequent push.
    @Test (timeout = TIMEOUT)
    public void testArrayPeekAfterPop() {
        array.push("0a"); // 0a
        array.push("1a"); // 0a, 1a
        array.pop(); // 0a
        Assert.assertEquals("0a", array.peek());
        array.push("1b"); // 0a, 1b
        Assert.assertEquals("1b", array.peek());
    }


    // Tests that ArrayStack pops the correct data after a resize, and maintains the expected structure.
    @Test(timeout = TIMEOUT)
    public void testArrayPopAfterResize() {
        array.push("0a");   // 0a
        array.push("1a");   // 0a, 1a
        array.push("2a");   // 0a, 1a, 2a
        array.push("3a");   // 0a, 1a, 2a, 3a
        array.push("4a");   // 0a, 1a, 2a, 3a, 4a
        array.push("5a");   // 0a, 1a, 2a, 3a, 4a, 5a
        array.push("6a");   // 0a, 1a, 2a, 3a, 4a, 5a, 6a
        array.push("7a");   // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a
        array.push("8a");   // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a
        array.push("9a");   // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a, 9a (resize should happen here)
        Assert.assertEquals("9a", array.pop()); // 0a, 1a, 2a, 3a, 4a, 5a, 6a, 7a, 8a

        Object[] expected = new Object[ArrayStack.INITIAL_CAPACITY * 2];
        expected[0] = "0a";
        expected[1] = "1a";
        expected[2] = "2a";
        expected[3] = "3a";
        expected[4] = "4a";
        expected[5] = "5a";
        expected[6] = "6a";
        expected[7] = "7a";
        expected[8] = "8a";
        Assert.assertArrayEquals(expected, array.getBackingArray());
        Assert.assertEquals(9, array.size());
    }


    @Test(timeout = TIMEOUT)
    public void testArrayPeek() {
        String temp = "4a";

        array.push("0a");   // 0a
        array.push("1a");   // 0a, 1a
        array.push("2a");   // 0a, 1a, 2a
        array.push("3a");   // 0a, 1a, 2a, 3a
        array.push(temp);        // 0a, 1a, 2a, 3a, 4a
        Assert.assertEquals(5, array.size());

        assertSame(temp, array.peek());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedPush() {
        linked.push("0a");  // 0a
        linked.push("1a");  // 1a, 0a
        linked.push("2a");  // 2a, 1a 0a
        linked.push("3a");  // 3a, 2a, 1a 0a
        linked.push("4a");  // 4a, 3a, 2a, 1a 0a

        Assert.assertEquals(5, linked.size());

        LinkedNode<String> cur = linked.getHead();
        assertNotNull(cur);
        Assert.assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        Assert.assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        Assert.assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        Assert.assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        Assert.assertEquals("0a", cur.getData());

        cur = cur.getNext();
        assertNull(cur);
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedPop() {
        String temp = "5a";

        linked.push("0a");  // 0a
        linked.push("1a");  // 1a, 0a
        linked.push("2a");  // 2a, 1a, 0a
        linked.push("3a");  // 3a, 2a, 1a, 0a
        linked.push("4a");  // 4a, 3a, 2a, 1a, 0a
        linked.push(temp);        // 5a, 4a, 3a, 2a, 1a, 0a
        Assert.assertEquals(6, linked.size());

        assertSame(temp, linked.pop()); // 4a, 3a, 2a, 1a, 0a

        Assert.assertEquals(5, linked.size());

        LinkedNode<String> cur = linked.getHead();
        assertNotNull(cur);
        Assert.assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        Assert.assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        Assert.assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        Assert.assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        Assert.assertEquals("0a", cur.getData());

        cur = cur.getNext();
        assertNull(cur);
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedPushAfterPop() {
        linked.push("0a");  // 0a
        linked.push("1a");  // 1a, 0a
        linked.push("2a");  // 2a, 1a 0a
        linked.push("3a");  // 3a, 2a, 1a 0a
        linked.push("4a");  // 4a, 3a, 2a, 1a 0a

        Assert.assertEquals(5, linked.size());

        LinkedNode<String> cur = linked.getHead();
        assertNotNull(cur);
        Assert.assertEquals("4a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        Assert.assertEquals("3a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        Assert.assertEquals("2a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        Assert.assertEquals("1a", cur.getData());

        cur = cur.getNext();
        assertNotNull(cur);
        Assert.assertEquals("0a", cur.getData());

        cur = cur.getNext();
        assertNull(cur);

        assertEquals("4a", linked.pop());
        linked.push("4b");

        assertEquals("4b", linked.getHead().getData());
        assertEquals("3a", linked.getHead().getNext().getData());
        assertEquals(5, linked.size());
    }

    @Test(timeout = TIMEOUT)
    public void testLinkedPopLastElement() {
        linked.push("0a");
        linked.pop();
        assertEquals(0, linked.size());
        assertNull(linked.getHead());
    }

}
