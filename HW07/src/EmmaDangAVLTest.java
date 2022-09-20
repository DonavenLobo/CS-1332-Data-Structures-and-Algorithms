import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.NoSuchElementException;

import static org.junit.Assert.*;

public class EmmaDangAVLTest {
    private static final int TIME_OUT = 200;
    private AVL<String> tree;

    @Before
    public void setUp() {
        tree = new AVL<>();
    }

    @Test(timeout = TIME_OUT)
    public void testDefaultConstructor() {
        assertEquals(0, tree.size());
        assertNull(tree.getRoot());
    }

    @Test(timeout = TIME_OUT, expected = IllegalArgumentException.class)
    public void testAdd1() {
        tree.add(null);
    }

    @Test(timeout = TIME_OUT)
    public void testAdd2() {
        tree.add("0a");
        assertEquals(1, tree.size());
        assertEquals("0a", tree.getRoot().getData());
        assertEquals(0, tree.getRoot().getHeight());
        assertEquals(0, tree.getRoot().getBalanceFactor());
    }

    @Test(timeout = TIME_OUT)
    public void testAdd3() {
        tree.add("0a");
        tree.add("1a");
        assertEquals(2, tree.size());
        AVLNode<String> root = tree.getRoot();
        assertEquals("0a", root.getData());
        assertEquals(1, root.getHeight());
        assertEquals(-1, root.getBalanceFactor());
        assertEquals("1a", root.getRight().getData());
        assertEquals(0, root.getRight().getHeight());
        assertEquals(0, root.getRight().getBalanceFactor());
        assertNull(root.getLeft());
    }

    @Test(timeout = TIME_OUT)
    public void testAdd4() {
        testAdd3();
        tree.add("2a");
        assertEquals(3, tree.size());
        AVLNode<String> root = tree.getRoot();
        assertEquals("1a", root.getData());
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getBalanceFactor());
        AVLNode<String> left = root.getLeft();
        assertEquals("0a", left.getData());
        assertEquals(0, left.getHeight());
        assertEquals(0, left.getBalanceFactor());
        AVLNode<String> right = root.getRight();
        assertEquals("2a", right.getData());
        assertEquals(0, right.getBalanceFactor());
        assertEquals(0, right.getHeight());
    }

    @Test(timeout = TIME_OUT)
    public void testAdd5() {
        tree.add("3a");
        tree.add("2a");
        assertEquals(2, tree.size());
        assertEquals("3a", tree.getRoot().getData());
        assertEquals(1, tree.getRoot().getHeight());
        assertEquals(1, tree.getRoot().getBalanceFactor());
        assertEquals("2a", tree.getRoot().getLeft().getData());
        assertEquals(0, tree.getRoot().getLeft().getHeight());
        assertEquals(0, tree.getRoot().getLeft().getBalanceFactor());
    }

    @Test(timeout = TIME_OUT)
    public void testAdd6() {
        testAdd5();
        tree.add("1a");
        AVLNode<String> root = tree.getRoot();
        assertEquals("2a", root.getData());
        assertEquals(1, root.getHeight());
        assertEquals(0, root.getBalanceFactor());
        AVLNode<String> left = root.getLeft();
        assertEquals("1a", left.getData());
        assertEquals(0, left.getHeight());
        assertEquals(0, left.getBalanceFactor());
        AVLNode<String> right = root.getRight();
        assertEquals("3a", right.getData());
        assertEquals(0, right.getBalanceFactor());
        assertEquals(0, right.getHeight());
    }

    @Test(timeout = TIME_OUT)
    public void testAdd7() {
        tree.add("4a");
        tree.add("5a");
        tree.add("1a");
        tree.add("0a");
        tree.add("2a");
        tree.add("3a");
        assertEquals(6, tree.size());
        AVLNode<String> root = tree.getRoot();
        assertEquals("2a", root.getData());
        assertEquals(2, root.getHeight());
        assertEquals(0, root.getBalanceFactor());
        AVLNode<String> left = root.getLeft();
        assertEquals("1a", left.getData());
        assertEquals(1, left.getHeight());
        assertEquals(1, left.getBalanceFactor());
        assertNull(left.getRight());
        assertEquals("0a", left.getLeft().getData());
        assertEquals(0, left.getLeft().getHeight());
        assertEquals(0, left.getLeft().getBalanceFactor());
        AVLNode<String> right = root.getRight();
        assertEquals("4a", right.getData());
        assertEquals(1, right.getHeight());
        assertEquals(0, right.getBalanceFactor());
        assertEquals("3a", right.getLeft().getData());
        assertEquals(0, right.getLeft().getHeight());
        assertEquals(0, right.getLeft().getBalanceFactor());
        assertEquals("5a", right.getRight().getData());
        assertEquals(0, right.getRight().getHeight());
        assertEquals(0, right.getRight().getBalanceFactor());
    }

    @Test(timeout = TIME_OUT)
    public void testAdd8() {
        tree.add("2a");
        tree.add("1a");
        tree.add("5a");
        tree.add("6a");
        tree.add("3a");
        tree.add("4a");
        assertEquals(6, tree.size());
        AVLNode<String> root = tree.getRoot();
        assertEquals("3a", root.getData());
        assertEquals(2, root.getHeight());
        assertEquals(0, root.getBalanceFactor());
        AVLNode<String> left = root.getLeft();
        assertEquals("2a", left.getData());
        assertEquals(1, left.getHeight());
        assertEquals(1, left.getBalanceFactor());
        assertNull(left.getRight());
        assertEquals("1a", left.getLeft().getData());
        assertEquals(0, left.getLeft().getHeight());
        assertEquals(0, left.getLeft().getBalanceFactor());
        AVLNode<String> right = root.getRight();
        assertEquals("5a", right.getData());
        assertEquals(1, right.getHeight());
        assertEquals(0, right.getBalanceFactor());
        assertEquals("4a", right.getLeft().getData());
        assertEquals(0, right.getLeft().getHeight());
        assertEquals(0, right.getLeft().getBalanceFactor());
        assertEquals("6a", right.getRight().getData());
        assertEquals(0, right.getRight().getHeight());
        assertEquals(0, right.getRight().getBalanceFactor());
    }

    @Test(timeout = TIME_OUT)
    public void testSecondConstructor() {
        Collection<String> list = new ArrayList<>();
        list.add("2a");
        list.add("1a");
        list.add("3a");
        list.add("4a");
        list.add("6a");
        list.add("5a");
        tree = new AVL<>(list);
        assertEquals(6, tree.size());
        AVLNode<String> root = tree.getRoot();
        assertEquals("4a", root.getData());
        assertEquals(2, root.getHeight());
        assertEquals(0, root.getBalanceFactor());
        AVLNode<String> left = root.getLeft();
        assertEquals("2a", left.getData());
        assertEquals(1, left.getHeight());
        assertEquals(0, left.getBalanceFactor());
        assertEquals("3a", left.getRight().getData());
        assertEquals(0, left.getRight().getHeight());
        assertEquals(0, left.getRight().getBalanceFactor());
        assertEquals("1a", left.getLeft().getData());
        assertEquals(0, left.getLeft().getHeight());
        assertEquals(0, left.getLeft().getBalanceFactor());
        AVLNode<String> right = root.getRight();
        assertEquals("6a", right.getData());
        assertEquals(1, right.getHeight());
        assertEquals(1, right.getBalanceFactor());
        assertEquals("5a", right.getLeft().getData());
        assertEquals(0, right.getLeft().getHeight());
        assertEquals(0, right.getLeft().getBalanceFactor());
        assertNull(right.getRight());
    }

    @Test(timeout = TIME_OUT, expected = IllegalArgumentException.class)
    public void testSecondConstructor1() {
        Collection<String> list = new ArrayList<>();
        list.add(null);
        tree = new AVL<>(list);
    }

    @Test(timeout = TIME_OUT, expected = IllegalArgumentException.class)
    public void testRemove1() {
        tree.remove(null);
    }

    @Test(timeout = TIME_OUT, expected = NoSuchElementException.class)
    public void testRemove2() {
        tree.remove("0a");
    }

    @Test(timeout = TIME_OUT)
    public void testRemove3() {
        tree.add("0a");
        tree.remove("0a");
        assertEquals(0, tree.size());
        assertNull(tree.getRoot());
    }

    @Test(timeout = TIME_OUT)
    public void testRemove4() {
        testAdd4();
        tree.remove("2a");
        assertEquals(2, tree.size());
        assertEquals("1a", tree.getRoot().getData());
        assertEquals(1, tree.getRoot().getHeight());
        assertEquals(1, tree.getRoot().getBalanceFactor());
        assertNull(tree.getRoot().getRight());
        assertEquals("0a", tree.getRoot().getLeft().getData());
        assertEquals(0, tree.getRoot().getLeft().getHeight());
        assertEquals(0, tree.getRoot().getLeft().getBalanceFactor());
    }

    @Test(timeout = TIME_OUT)
    public void testRemove5() {
        testAdd7();
        String removedValue = tree.remove("1a");
        assertEquals("1a", removedValue);
        assertEquals(5, tree.size());
        AVLNode<String> root = tree.getRoot();
        assertEquals("2a", root.getData());
        assertEquals(2, root.getHeight());
        assertEquals(-1, root.getBalanceFactor());
        AVLNode<String> left = root.getLeft();
        assertEquals("0a", left.getData());
        assertEquals(0, left.getHeight());
        assertEquals(0, left.getBalanceFactor());
        AVLNode<String> right = root.getRight();
        assertEquals("4a", right.getData());
        assertEquals(1, right.getHeight());
        assertEquals(0, right.getBalanceFactor());
        assertEquals("3a", right.getLeft().getData());
        assertEquals(0, right.getLeft().getHeight());
        assertEquals(0, right.getLeft().getBalanceFactor());
        assertEquals("5a", right.getRight().getData());
        assertEquals(0, right.getRight().getHeight());
        assertEquals(0, right.getRight().getBalanceFactor());
    }

    @Test(timeout = TIME_OUT)
    public void testRemove6() {
        testAdd8();
        String removedValue = tree.remove("5a");
        assertEquals("5a", removedValue);
        assertEquals(5, tree.size());
        AVLNode<String> root = tree.getRoot();
        assertEquals("3a", root.getData());
        assertEquals(2, root.getHeight());
        assertEquals(0, root.getBalanceFactor());
        AVLNode<String> left = root.getLeft();
        assertEquals("2a", left.getData());
        assertEquals(1, left.getHeight());
        assertEquals(1, left.getBalanceFactor());
        assertNull(left.getRight());
        assertEquals("1a", left.getLeft().getData());
        assertEquals(0, left.getLeft().getHeight());
        assertEquals(0, left.getLeft().getBalanceFactor());
        AVLNode<String> right = root.getRight();
        assertEquals("6a", right.getData());
        assertEquals(1, right.getHeight());
        assertEquals(1, right.getBalanceFactor());
        assertNull(right.getRight());
        assertEquals("4a", right.getLeft().getData());
        assertEquals(0, right.getLeft().getHeight());
        assertEquals(0, right.getLeft().getBalanceFactor());
    }

    @Test(timeout = TIME_OUT)
    public void testRemove7() {
        testAdd7();
        String removedValue = tree.remove("2a");
        assertEquals("2a", removedValue);
        assertEquals(5, tree.size());
        AVLNode<String> root = tree.getRoot();
        assertEquals("3a", root.getData());
        assertEquals(2, root.getHeight());
        assertEquals(0, root.getBalanceFactor());
        AVLNode<String> left = root.getLeft();
        assertEquals("1a", left.getData());
        assertEquals(1, left.getHeight());
        assertEquals(1, left.getBalanceFactor());
        assertNull(left.getRight());
        assertEquals("0a", left.getLeft().getData());
        assertEquals(0, left.getLeft().getHeight());
        assertEquals(0, left.getLeft().getBalanceFactor());
        AVLNode<String> right = root.getRight();
        assertEquals("4a", right.getData());
        assertEquals(1, right.getHeight());
        assertEquals(-1, right.getBalanceFactor());
        assertNull(right.getLeft());
        assertEquals("5a", right.getRight().getData());
        assertEquals(0, right.getRight().getHeight());
        assertEquals(0, right.getRight().getBalanceFactor());
    }

    @Test(timeout = TIME_OUT)
    public void testRemove8() {
        testSecondConstructor();
        String removedValue = tree.remove("4a");
        assertEquals("4a", removedValue);
        assertEquals(5, tree.size());
        AVLNode<String> root = tree.getRoot();
        assertEquals("5a", root.getData());
        assertEquals(2, root.getHeight());
        assertEquals(1, root.getBalanceFactor());
        AVLNode<String> left = root.getLeft();
        assertEquals("2a", left.getData());
        assertEquals(1, left.getHeight());
        assertEquals(0, left.getBalanceFactor());
        assertEquals("3a", left.getRight().getData());
        assertEquals(0, left.getRight().getHeight());
        assertEquals(0, left.getRight().getBalanceFactor());
        assertEquals("1a", left.getLeft().getData());
        assertEquals(0, left.getLeft().getHeight());
        assertEquals(0, left.getLeft().getBalanceFactor());
        AVLNode<String> right = root.getRight();
        assertEquals("6a", right.getData());
        assertEquals(0, right.getHeight());
        assertEquals(0, right.getBalanceFactor());
        assertNull(right.getLeft());
        assertNull(right.getRight());
    }

    @Test(timeout = TIME_OUT, expected = IllegalArgumentException.class)
    public void testGet1() {
        tree.get(null);
    }

    @Test(timeout = TIME_OUT)
    public void testGet2() {
        testAdd7();
        assertEquals("4a", tree.get("4a"));
        assertEquals(6, tree.size());
    }

    @Test(timeout = TIME_OUT, expected = NoSuchElementException.class)
    public void testGet3() {
        testAdd7();
        tree.get("10a");
    }

    @Test(timeout = TIME_OUT, expected = IllegalArgumentException.class)
    public void testContains1() {
        tree.contains(null);
    }

    @Test(timeout = TIME_OUT)
    public void testContains2() {
        testAdd7();
        assertTrue(tree.contains("4a"));
    }

    @Test(timeout = TIME_OUT)
    public void testContains3() {
        testAdd7();
        assertFalse(tree.contains("10a"));
    }

    @Test(timeout = TIME_OUT)
    public void testHeight1() {
        assertEquals(-1, tree.height());
    }

    @Test(timeout = TIME_OUT)
    public void testHeight2() {
        testAdd2();
        assertEquals(0, tree.height());
    }

    @Test(timeout = TIME_OUT)
    public void testHeight3() {
        testAdd3();
        assertEquals(1, tree.height());
    }

    @Test(timeout = TIME_OUT)
    public void testHeight4() {
        testAdd7();
        assertEquals(2, tree.height());
    }

    @Test(timeout = TIME_OUT)
    public void testClear() {
        testAdd7();
        tree.clear();
        assertNull(tree.getRoot());
        assertEquals(0, tree.size());
        assertEquals(-1, tree.height());
    }

    @Test(timeout = TIME_OUT)
    public void testMaxDeepestNode1() {
        assertNull(tree.maxDeepestNode());
    }

    @Test(timeout = TIME_OUT)
    public void testMaxDeepestNode2() {
        // tree with a single node. The max deepest node is the root
        testAdd2();
        assertEquals("0a", tree.maxDeepestNode());
    }

    @Test(timeout = TIME_OUT)
    public void testMaxDeepestNode3() {
        //    0a
        //       \
        //       1a
        testAdd3();
        assertEquals("1a", tree.maxDeepestNode());
    }

    @Test(timeout = TIME_OUT)
    public void testMaxDeepestNode4() {
        //       3a
        //     /
        //    2a
        testAdd5();
        assertEquals("2a", tree.maxDeepestNode());
    }

    @Test(timeout = TIME_OUT)
    public void testMaxDeepestNode5() {
        //        3a
        //      /    \
        //     2a    6a
        //    /     /
        //   1a    4a
        testRemove6();
        assertEquals("4a", tree.maxDeepestNode());
    }

    @Test(timeout = TIME_OUT)
    public void testMaxDeepestNode6() {
        //       5a
        //     /    \
        //    2a    6a
        //   /  \
        //  1a  3a
        testRemove8();
        assertEquals("3a", tree.maxDeepestNode());
    }

    @Test(timeout = TIME_OUT)
    public void testMaxDeepestNode7() {
        //          2a
        //        /   \
        //       0a    3a
        //        \
        //         1a
        tree.add("2a");
        tree.add("0a");
        tree.add("3a");
        tree.add("1a");
        assertEquals("1a", tree.maxDeepestNode());
    }

    @Test(timeout = TIME_OUT)
    public void testMaxDeepestNode8() {
        //           2a
        //         /    \
        //        0a     4a
        //         \     /
        //          1a  3a
        tree.add("2a");
        tree.add("0a");
        tree.add("4a");
        tree.add("1a");
        tree.add("3a");
        assertEquals("3a", tree.maxDeepestNode());
    }

    @Test(timeout = TIME_OUT, expected = IllegalArgumentException.class)
    public void testSuccessor1() {
        tree.successor(null);
    }

    @Test(timeout = TIME_OUT, expected = NoSuchElementException.class)
    public void testSuccessor2() {
        tree.successor("0a");
    }

    @Test(timeout = TIME_OUT)
    public void testSuccessor3() {
        //              76a
        //            /    \
        //          34a     90a
        //            \    /
        //            40a 81a
        tree.add("76a");
        tree.add("34a");
        tree.add("90a");
        tree.add("40a");
        tree.add("81a");
        assertEquals("81a", tree.successor("76a"));
    }

    @Test(timeout = TIME_OUT)
    public void testSuccessor4() {
        testSuccessor3();
        assertEquals("90a", tree.successor("81a"));
    }

    @Test(timeout = TIME_OUT)
    public void testSuccessor5() {
        testSuccessor3();
        assertNull(tree.successor("90a"));
    }

    @Test(timeout = TIME_OUT)
    public void testSuccessor6() {
        testSuccessor3();
        assertEquals("76a", tree.successor("40a"));
    }

    @Test(timeout = TIME_OUT)
    public void testSuccessor7() {
        testSuccessor3();
        assertEquals("40a", tree.successor("34a"));
    }

    @Test(timeout = TIME_OUT, expected = NoSuchElementException.class)
    public void testSuccessor8() {
        testSuccessor3();
        tree.successor("100a");
    }
}

