import org.junit.Test;
import org.junit.Before;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.List;

public class EmmaDangBstTest {
    private BST<String> bst;
    private static final int TIME_OUT = 200;

    @Before
    public void setUp() {
        bst = new BST<>();
    }

    @Test(timeout = TIME_OUT)
    public void testInitialization() {
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());
    }

    @Test(timeout = TIME_OUT)
    public void testConstructor1() {
        String[] list = {"m", "s", "h", "e", "b", "l", "r", "a", "n", "x"};
        bst = new BST<>(Arrays.asList(list));
        assertEquals(10, bst.size());
        /*
         ...................m..................
         .............h...........s...............
         ...........e...l.......r....x............
         ........b............n...................
         .....a..............................
         */
        BSTNode<String> root = bst.getRoot();
        assertEquals("m", root.getData());
        assertEquals("h", root.getLeft().getData());
        assertEquals("s", root.getRight().getData());

        BSTNode<String> h = root.getLeft();
        assertEquals("h", h.getData());
        assertEquals("e", h.getLeft().getData());
        assertEquals("l", h.getRight().getData());

        BSTNode<String> s = root.getRight();
        assertEquals("s", s.getData());
        assertEquals("r", s.getLeft().getData());
        assertEquals("x", s.getRight().getData());

        BSTNode<String> e = h.getLeft();
        assertEquals("e", e.getData());
        assertEquals("b", e.getLeft().getData());
        assertNull(e.getRight());

        BSTNode<String> l = h.getRight();
        assertEquals("l", l.getData());
        assertNull(l.getLeft());
        assertNull(l.getRight());

        BSTNode<String> r = s.getLeft();
        assertEquals("r", r.getData());
        assertEquals("n", r.getLeft().getData());
        assertNull(r.getRight());

        BSTNode<String> x = s.getRight();
        assertEquals("x", x.getData());
        assertNull(x.getLeft());
        assertNull(x.getRight());

        BSTNode<String> b = e.getLeft();
        assertEquals("b", b.getData());
        assertEquals("a", b.getLeft().getData());
        assertNull(b.getRight());

        BSTNode<String> n = r.getLeft();
        assertEquals("n", n.getData());
        assertNull(n.getLeft());
        assertNull(n.getRight());

        BSTNode<String> a = b.getLeft();
        assertEquals("a", a.getData());
        assertNull(a.getLeft());
        assertNull(a.getRight());
    }

    @Test(timeout = TIME_OUT)
    public void testConstructor2() {
        String[] list = {"0a", "1a", "2a", "3a", "4a", "5a", "6a", "7a", "8a", "9a"};
        bst = new BST<>(Arrays.asList(list));
        assertEquals(10, bst.size());
        /*
        This is a degenerate tree with no left branch
         */
        BSTNode<String> curr = bst.getRoot();
        for (int i = 0; i < 10; i++) {
            assertEquals(i + "a", curr.getData());
            assertNull(curr.getLeft());
            curr = curr.getRight();
        }
    }

    @Test(timeout = TIME_OUT)
    public void testConstructor3() {
        String[] list = {"9a", "8a", "7a", "6a", "5a", "4a", "3a", "2a", "1a", "0a"};
        bst = new BST<>(Arrays.asList(list));
        assertEquals(10, bst.size());
        /*
        this is a degenerate tree with no right branch
         */
        BSTNode<String> curr = bst.getRoot();
        for (int i = 9; i >=0; i--) {
            assertEquals(i + "a", curr.getData());
            assertNull(curr.getRight());
            curr = curr.getLeft();
        }
    }

    @Test(timeout = TIME_OUT)
    public void testAdd1() {
        // add 1 element
        bst.add("0a");
        assertEquals("0a", bst.getRoot().getData());
        assertEquals(1, bst.size());
        assertNull(bst.getRoot().getLeft());
        assertNull(bst.getRoot().getRight());
    }

    @Test(timeout = TIME_OUT)
    public void testAdd2() {
        // add random elements without duplicate
        bst.add("7a");
        bst.add("6a");
        bst.add("2a");
        bst.add("5a");
        bst.add("1a");
        assertEquals(5, bst.size());
        /*
        ................7a...................
        .............6a.......................
        .........2a...........................
        ......1a....5a........................
         */
        assertEquals("7a", bst.getRoot().getData());
        assertEquals("6a", bst.getRoot().getLeft().getData());
        assertNull(bst.getRoot().getRight());
        assertEquals("2a", bst.getRoot().getLeft().getLeft().getData());
        assertNull(bst.getRoot().getLeft().getRight());
        assertEquals("1a", bst.getRoot().getLeft().getLeft().getLeft().getData());
        assertEquals("5a", bst.getRoot().getLeft().getLeft().getRight().getData());
    }

    @Test(timeout = TIME_OUT)
    public void testAdd3() {
        // add ascending elements (degenerate tree) without duplicates
        bst.add("0a");
        bst.add("1a");
        bst.add("2a");
        bst.add("3a");
        assertEquals(4, bst.size());
        assertEquals("0a", bst.getRoot().getData());
        assertEquals("1a", bst.getRoot().getRight().getData());
        assertEquals("2a", bst.getRoot().getRight().getRight().getData());
        assertEquals("3a", bst.getRoot().getRight().getRight().getRight().getData());
        assertNull(bst.getRoot().getLeft());
        assertNull(bst.getRoot().getRight().getLeft());
        assertNull(bst.getRoot().getRight().getRight().getLeft());
    }

    @Test(timeout = TIME_OUT)
    public void testAdd4() {
        // add descending elements (degenerate tree) with duplicates
        bst.add("5a");
        bst.add("4a");
        bst.add("3a");
        bst.add("2a");
        bst.add("4a");
        bst.add("3a");
        assertEquals(4, bst.size());
        assertEquals("5a", bst.getRoot().getData());
        assertEquals("4a", bst.getRoot().getLeft().getData());
        assertEquals("3a", bst.getRoot().getLeft().getLeft().getData());
        assertEquals("2a", bst.getRoot().getLeft().getLeft().getLeft().getData());
        assertNull(bst.getRoot().getRight());
        assertNull(bst.getRoot().getLeft().getRight());
        assertNull(bst.getRoot().getLeft().getLeft().getRight());
    }

    @Test(timeout = TIME_OUT)
    public void testAdd5() {
        // add random elements with duplicates
        bst.add("4a");
        bst.add("7a");
        bst.add("3a");
        bst.add("4a");
        bst.add("7a");
        bst.add("3a");
        bst.add("4a");
        assertEquals(3, bst.size());
        assertEquals("4a", bst.getRoot().getData());
        assertEquals("7a", bst.getRoot().getRight().getData());
        assertEquals("3a", bst.getRoot().getLeft().getData());
    }

    /**
     * create a bst to test remove method
     */
    private void bstForTesting() {
        bst.add("72a");
        bst.add("26a");
        bst.add("0a");
        bst.add("55a");
        bst.add("44a");
        bst.add("59a");
        bst.add("21a");
        bst.add("19a");
        bst.add("24a");
        bst.add("98a");
        /*
        ...............................72a....................
        ....................26a....................98a.....
        .............0a..............55a......................
        ................21a......44a.....59a................
        .............19a....24a.....................
        .........................................
        .........................................
         */
    }

    @Test(timeout = TIME_OUT)
    public void testRemove1() {
        // remove a leaf node
        bstForTesting();
        bst.remove("98a");
        assertEquals(9, bst.size());
        assertNull(bst.getRoot().getRight());

        bst.remove("44a");
        assertEquals(8, bst.size());
        assertNull(bst.getRoot().getLeft().getRight().getLeft());

        bst.remove("24a");
        assertEquals(7, bst.size());
        assertNull(bst.getRoot().getLeft().getLeft().getRight().getRight());
    }

    @Test(timeout = TIME_OUT)
    public void testRemove2() {
        // remove a 1-child node
        bstForTesting();
        bst.remove("0a");
        assertEquals(9, bst.size());
        BSTNode<String> expected = bst.getRoot().getLeft().getLeft();
        assertEquals("21a", expected.getData());
        assertEquals("19a", expected.getLeft().getData());
        assertEquals("24a", expected.getRight().getData());
    }

    @Test(timeout = TIME_OUT)
    public void testRemove3() {
        // remove a 2-child node: root
        bstForTesting();
        bst.remove("72a");
        /*
        ...............................59a....................
        ....................26a....................98a.....
        .............0a..............55a......................
        ................21a......44a.....................
        .............19a....24a.....................
        .........................................
        .........................................
         */
        assertEquals(9, bst.size());
        assertEquals("59a", bst.getRoot().getData());
        assertNull(bst.getRoot().getLeft().getRight().getRight());

        bst.remove("59a");
        /*
        ...............................55a....................
        ....................26a....................98a.....
        .............0a.............44a......................
        ................21a.............................
        .............19a....24a.....................
        .........................................
        .........................................
         */
        assertEquals(8, bst.size());
        assertEquals("55a", bst.getRoot().getData());
        assertEquals("44a", bst.getRoot().getLeft().getRight().getData());

        bst.remove("26a");
        /*
        ...............................55a....................
        ....................24a....................98a.....
        .............0a.............44a......................
        ................21a.............................
        .............19a.........................
        .........................................
        .........................................
         */
        assertEquals(7, bst.size());
        assertEquals("24a", bst.getRoot().getLeft().getData());
        assertNull(bst.getRoot().getLeft().getLeft().getRight().getRight());
    }

    @Test(timeout = TIME_OUT)
    public void testGet1() {
        // test for elements in bst
        bstForTesting();
        assertEquals("98a", bst.get("98a"));
        assertEquals("24a", bst.get("24a"));
        assertEquals("59a", bst.get("59a"));
    }

    @Test(timeout = TIME_OUT, expected = NoSuchElementException.class)
    public void testGet2() {
        // test for elements not in bst. expect NoSuchElementException
        bstForTesting();
        bst.get("100a");
        bst.get("5a");
        bst.get("10a");
    }

    @Test(timeout = TIME_OUT)
    public void testContain() {
        bstForTesting();
        assertTrue(bst.contains("98a"));
        assertTrue(bst.contains("24a"));
        assertTrue(bst.contains("59a"));
        assertFalse(bst.contains("5a"));
        assertFalse(bst.contains("10a"));
        assertFalse(bst.contains("100a"));
    }

    @Test(timeout = TIME_OUT)
    public void testPreorder() {
        bstForTesting();
        String[] expected = {"72a", "26a", "0a", "21a", "19a", "24a", "55a", "44a", "59a", "98a"};
        List<String> actual = bst.preorder();
        assertEquals(expected.length, actual.size());
        for (int i = 0; i < 10; i++) {
            assertEquals(expected[i], actual.get(i));
        }
    }

    @Test(timeout = TIME_OUT)
    public void testInorder() {
        bstForTesting();
        String[] expected = {"0a", "19a", "21a", "24a", "26a", "44a", "55a", "59a", "72a", "98a"};
        List<String> actual = bst.inorder();
        for (int i = 0; i < 10; i++) {
            assertEquals(expected[i], actual.get(i));
        }
    }

    @Test(timeout = TIME_OUT)
    public void testPostorder() {
        bstForTesting();
        String[] expected = {"19a", "24a", "21a", "0a", "44a", "59a", "55a", "26a", "98a", "72a"};
        List<String> actual = bst.postorder();
        for (int i = 0; i < 10; i++) {
            assertEquals(expected[i], actual.get(i));
        }
    }

    @Test(timeout = TIME_OUT)
    public void testLevelorder() {
        bstForTesting();
        String[] expected = {"72a", "26a", "98a", "0a", "55a", "21a", "44a", "59a", "19a", "24a"};
        List<String> list = bst.levelorder();
        for (int i = 0; i < 10; i++) {
            assertEquals(expected[i], list.get(i));
        }
    }

    @Test(timeout = TIME_OUT)
    public void testHeight1() {
        bstForTesting();
        assertEquals(4, bst.height());
    }

    @Test(timeout = TIME_OUT)
    public void testHeight2() {
        testConstructor1();
        assertEquals(4, bst.height());
    }

    @Test(timeout = TIME_OUT)
    public void testHeight3() {
        testAdd3();
        assertEquals(3, bst.height());
    }

    @Test(timeout = TIME_OUT)
    public void testClear() {
        bstForTesting();
        bst.clear();
        assertNull(bst.getRoot());
        assertEquals(0, bst.size());
    }

    @Test(timeout = TIME_OUT)
    public void testIsBST1() {
        // test a valid BST
        bstForTesting();
        assertTrue(BST.isBST(bst.getRoot()));
    }

    @Test(timeout = TIME_OUT)
    public void testIsBST2() {
        /*
        test an invalid BST where there is a loop
         */
        BSTNode<String> root = new BSTNode<>("5a");
        BSTNode<String> left = new BSTNode<>("3a");
        BSTNode<String> right = new BSTNode<>("15a");
        root.setLeft(left);
        root.setRight(right);
        right.setLeft(root);
        assertFalse(BST.isBST(root));
    }

    @Test(timeout = TIME_OUT)
    public void testIsBST3() {
        // test an invalid BST where the parent-child relationship is not correct
        BSTNode<String> root = new BSTNode<>("4a");
        BSTNode<String> left = new BSTNode<>("6a");
        BSTNode<String> right = new BSTNode<>("15a");
        root.setLeft(left);
        root.setRight(right);
        assertFalse(BST.isBST(root));
    }

    @Test(timeout = TIME_OUT)
    public void testIsBST4() {
        // test an invalid BST where the parent-child relationship is correct but
        // the subtree rooted at left child contains node.data > parent.data
        // aka grandchild on the left is bigger than grandparent
        BSTNode<String> root = new BSTNode<>("7a");
        BSTNode<String> left = new BSTNode<>("3a");
        BSTNode<String> right = new BSTNode<>("15a");
        root.setLeft(left);
        root.setRight(right);
        left.setLeft(new BSTNode<>("1a"));
        left.setRight(new BSTNode<>("10a"));
        assertFalse(BST.isBST(root));
    }

    @Test(timeout = TIME_OUT)
    public void testIsBST5() {
        // test an invalid BST where there is duplicate
        BSTNode<String> root = new BSTNode<>("6a");
        BSTNode<String> left = new BSTNode<>("4a");
        BSTNode<String> right = new BSTNode<>("10a");
        root.setLeft(left);
        root.setRight(right);
        left.setLeft(new BSTNode<>("1a"));
        left.setRight(new BSTNode<>("6a"));
        assertFalse(BST.isBST(root));
    }
}
