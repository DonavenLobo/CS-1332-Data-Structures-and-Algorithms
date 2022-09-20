import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertArrayEquals;


/**
 * This is a basic set of unit tests for BST.
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
 * @author CS 1332 TAs & zchng3
 * @version 1.1
 */
public class BSTAdditionalTests {

    private static final int TIMEOUT = 200;
    private BST<Integer> bst;

    @Before
    public void setup() {
        bst = new BST<>();
    }

    @Test(timeout = TIMEOUT)
    public void testInitialization() {
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());
    }

    @Test(timeout = TIMEOUT)
    public void testConstructorAndClear() {
        /*
                     2
                    /
                   0
                    \
                     1
        */

        List<Integer> toAdd = new ArrayList<>();
        toAdd.add(2);
        toAdd.add(0);
        toAdd.add(1);
        bst = new BST<>(toAdd);

        assertEquals((Integer) 2, bst.getRoot().getData());
        assertEquals((Integer) 0, bst.getRoot().getLeft().getData());
        assertEquals((Integer) 1, bst.getRoot().getLeft().getRight().getData());

        bst.clear();
        assertEquals(0, bst.size());
        assertNull(bst.getRoot());
    }

    /* 3-layer full and complete BST
                        4
                2                6
           1         3      5         7
     */
    @Test
    public void testConstructor3LayerComplete() {
        //build a 3 layer complete BST and test the constructor on that
        bst = new BST<>(nLayerFullOrder(3));
        List<Integer> bstPreorder = bst.preorder();
        //preorder should follow nLayerFullOrder output (insert node then go to the left then right, so the way
        // things are inserted are the same order as preorder in this case)
        assertArrayEquals(bstPreorder.toArray(), nLayerFullOrder(3).toArray());

        assertEquals(bst.size(), 7);
        //root
        assertEquals((Integer) 4, bst.getRoot().getData());
        //left of root
        assertEquals((Integer) 2, bst.getRoot().getLeft().getData());
        //right of root
        assertEquals((Integer) 6, bst.getRoot().getRight().getData());
        //left-left
        assertEquals((Integer) 1, bst.getRoot().getLeft().getLeft().getData());
        //left-right
        assertEquals((Integer) 3, bst.getRoot().getLeft().getRight().getData());
        //right-left
        assertEquals((Integer) 5, bst.getRoot().getRight().getLeft().getData());
        //right-right
        assertEquals((Integer) 7, bst.getRoot().getRight().getRight().getData());
    }

    @Test
    public void testAdd3LayerComplete() {
        //following that the above test is correct, we now check that the add method gives the same output as the
        // above constructor (note that preorder and postorder traversals are uniquely defined for a given tree
        // structure)
        bst = new BST<>();
        BST<Integer> constructorBST = new BST<>(nLayerFullOrder(3));
        List<Integer> complete3LayerInsertOrder = nLayerFullOrder(3);
        for (Integer curr: complete3LayerInsertOrder) {
            bst.add(curr);
        }
        assertArrayEquals(constructorBST.preorder().toArray(), bst.preorder().toArray());
        assertArrayEquals(constructorBST.postorder().toArray(), bst.postorder().toArray());
    }

    @Test
    public void testAdd7LayerComplete() {
        //bored checking
        bst = new BST<>();
        BST<Integer> constructorBST = new BST<>(nLayerFullOrder(7));
        List<Integer> complete7LayerInsertOrder = nLayerFullOrder(7);
        for (Integer curr: complete7LayerInsertOrder) {
            bst.add(curr);
        }
        assertArrayEquals(constructorBST.preorder().toArray(), bst.preorder().toArray());
        assertArrayEquals(constructorBST.postorder().toArray(), bst.postorder().toArray());
    }

    @Test
    public void testAdd5LayerDegenerateLeft() {
        for (int i = 5; i >= 1; i--) {
            bst.add(i);
        }
        assertArrayEquals(new Integer[]{5, 4, 3, 2, 1}, bst.preorder().toArray());
    }

    @Test
    public void testAdd5LayerDegenerateRight() {
        for (int i = 1; i <= 5; i++) {
            bst.add(i);
        }
        assertArrayEquals(new Integer[]{1, 2, 3, 4, 5}, bst.preorder().toArray());
    }


    @Test(timeout = TIMEOUT)
    public void testAdd() {
        /*
                      1
                     / \
                    0   2
        */

        bst.add(1);
        bst.add(0);
        bst.add(2);

        assertEquals(3, bst.size());
        assertEquals((Integer) 1, bst.getRoot().getData());
        assertEquals((Integer) 0, bst.getRoot().getLeft().getData());
        assertEquals((Integer) 2, bst.getRoot().getRight().getData());
    }

    @Test(timeout = TIMEOUT)
    public void testRemove() {
        Integer temp2 = 2;
        Integer temp = 3;

        /*
                      2                    2
                     / \                  / \
                    1   3                1   4
                         \     ->             \
                          4                    5
                           \
                            5
        */

        bst.add(temp2);
        bst.add(1);
        bst.add(temp);
        bst.add(4);
        bst.add(5);

        //add test for height
        assertEquals(3, bst.height());

        assertSame(temp, bst.remove(3));
        assertEquals(4, bst.size());
        assertEquals((Integer) 2, bst.getRoot().getData());
        assertEquals((Integer) 1, bst.getRoot().getLeft().getData());
        assertEquals((Integer) 4, bst.getRoot().getRight().getData());
        assertEquals((Integer) 5, bst.getRoot().getRight()
            .getRight().getData());

        //add test for height
        assertEquals(2, bst.height());

        assertSame(temp2, bst.remove(2));
        assertEquals(3, bst.size());
        assertEquals((Integer) 1, bst.getRoot().getData());
        assertEquals((Integer) 4, bst.getRoot().getRight().getData());
        assertEquals((Integer) 5, bst.getRoot().getRight().getRight().getData());
        assertEquals(2, bst.height());
    }

    @Test
    public void testRemoveTwoChildrenPredecessorChild() {
        //there are two children but the predecessor is the child (i.e. child to be deleted has no right node so go
        // left but no traversal to the right). This is the case when deleting from 2nd layer of complete 3 layer BST
        bst = new BST<>(nLayerFullOrder(3));
        bst.remove(2);

        /* 3-layer full and complete BST
                                    4
                            2                6
                       1         3      5         7

                                      to

                                    4
                             3               6
                       1                5           7
         */
        assertArrayEquals(new Integer[]{4, 1, 3, 6, 5, 7}, bst.preorder().toArray());

    }

    @Test
    public void testRemoveTwoChildrenPredecessorGrandchild() {
        //the predecessor of the node to be deleted is the grandchild
        //for a 3 layer complete BST, this would mean the root is deleted
        bst = new BST<>(nLayerFullOrder(3));
        bst.remove(4);

        /* 3-layer full and complete BST
                                    4
                            2                6
                       1         3      5         7

                                      to

                                    3
                             2               6
                       1                5           7
         */

        assertArrayEquals(new Integer[]{3, 2, 1, 6, 5, 7}, bst.preorder().toArray());
        assertEquals(6, bst.size());

        bst.remove(6);
        /*
                                    3
                            2                6
                       1               5         7

                                      to

                                    3
                             2              5
                       1                           7
         */


        assertArrayEquals(new Integer[]{3, 2, 1, 5, 7}, bst.preorder().toArray());
        assertEquals(5, bst.size());

        bst.remove(2);
        assertArrayEquals(new Integer[]{3, 1, 5, 7}, bst.preorder().toArray());
        assertEquals(4, bst.size());

        bst.remove(5);
        assertArrayEquals(new Integer[]{3, 1, 7}, bst.preorder().toArray());
        assertEquals(3, bst.size());

        bst.remove(3);
        assertArrayEquals(new Integer[]{1, 7}, bst.preorder().toArray());
        assertEquals(2, bst.size());

        bst.remove(7);
        assertArrayEquals(new Integer[]{1}, bst.preorder().toArray());
        assertEquals(1, bst.size());

        bst.remove(1);
        assertArrayEquals(new Integer[]{}, bst.preorder().toArray());
        assertEquals(0, bst.size());
    }

    @Test
    public void testRemoveTwoChildrenPredecessorGreatGrandchild() {
        //need a 4-layer full BST and delete the root, checking what happens if predecessor has a left child, is
        // it brought up
        bst = new BST<>(nLayerFullOrder(4));
        bst.remove(8);

        /* 4-layer full and complete BST
                                                  8
                        4                                                      12
             2                     6                             10                                 14
       1          3         5            7               9               11                 13               15

                                                to

                                                  7
                        4                                                      12
             2                     6                            10                                 14
       1          3         5                           9               11                 13               15

         */
        assertEquals(14, bst.size());
        assertArrayEquals(new Integer[]{7, 4, 2, 1, 3, 6, 5, 12, 10, 9, 11, 14, 13, 15}, bst.preorder().toArray());

        bst.remove(7);
        /*
                                                  7
                        4                                                      12
             2                     6                             10                                14
       1          3         5                          9               11                 13               15

                                                    to

                                                  6
                        4                                                      12
             2                     5                             10                                14
       1          3                                   9               11                 13               15
         */
        //the predecessor has a left child here, 6 has a left child of 5, so it should be brought up
        assertEquals(13, bst.size());
        assertArrayEquals(new Integer[]{6, 4, 2, 1, 3, 5, 12, 10, 9, 11, 14, 13, 15}, bst.preorder().toArray());
        assertEquals(3, bst.height());
    }

    @Test(timeout = TIMEOUT)
    public void testGetAndContains() {
        Integer temp4 = 4;
        Integer temp1 = 1;
        Integer temp2 = 2;
        Integer temp3 = 3;
        Integer temp7 = 7;
        Integer temp5 = 5;
        Integer temp6 = 6;

        /*
                       4
                    /     \
                   1       7
                    \     /
                     2   5
                      \   \
                       3   6
        */

        bst.add(temp4);
        bst.add(temp1);
        bst.add(temp2);
        bst.add(temp3);
        bst.add(temp7);
        bst.add(temp5);
        bst.add(temp6);

        // We want to make sure the data we retrieve is the one from the tree,
        // not the data that was passed in.

        assertSame(temp4, bst.get(4));
        assertTrue(bst.contains(4));
        assertSame(temp1, bst.get(1));
        assertTrue(bst.contains(1));
        assertSame(temp2, bst.get(2));
        assertTrue(bst.contains(2));
        assertEquals(temp3, bst.get(3));
        assertTrue(bst.contains(3));
        assertEquals(temp7, bst.get(7));
        assertTrue(bst.contains(7));
        assertEquals(temp5, bst.get(5));
        assertTrue(bst.contains(5));
        assertEquals(temp6, bst.get(6));
        assertTrue(bst.contains(6));
    }

    @Test(timeout = TIMEOUT)
    public void testPreorder() {
        /*
                       4
                    /     \
                   1       9
                    \     /
                     2   5
                      \   \
                       3   7
                          / \
                         6   8
        */

        bst.add(4);
        bst.add(1);
        bst.add(2);
        bst.add(3);
        bst.add(9);
        bst.add(5);
        bst.add(7);
        bst.add(6);
        bst.add(8);

        List<Integer> preorder = new ArrayList<>();
        preorder.add(4);
        preorder.add(1);
        preorder.add(2);
        preorder.add(3);
        preorder.add(9);
        preorder.add(5);
        preorder.add(7);
        preorder.add(6);
        preorder.add(8);

        // Should be [4, 1, 2, 3, 9, 5, 7, 6, 8]
        assertEquals(preorder, bst.preorder());
    }

    @Test(timeout = TIMEOUT)
    public void testInorder() {
        /*
                       4
                    /     \
                   1       9
                    \     /
                     2   5
                      \   \
                       3   7
                          / \
                         6   8
        */

        bst.add(4);
        bst.add(1);
        bst.add(2);
        bst.add(3);
        bst.add(9);
        bst.add(5);
        bst.add(7);
        bst.add(6);
        bst.add(8);

        List<Integer> inorder = new ArrayList<>();
        inorder.add(1);
        inorder.add(2);
        inorder.add(3);
        inorder.add(4);
        inorder.add(5);
        inorder.add(6);
        inorder.add(7);
        inorder.add(8);
        inorder.add(9);

        // Should be [1, 2, 3, 4, 5, 6, 7, 8, 9]
        assertEquals(inorder, bst.inorder());
    }

    @Test(timeout = TIMEOUT)
    public void testPostorder() {
        /*
                       4
                    /     \
                   1       9
                    \     /
                     2   5
                      \   \
                       3   7
                          / \
                         6   8
        */

        bst.add(4);
        bst.add(1);
        bst.add(2);
        bst.add(3);
        bst.add(9);
        bst.add(5);
        bst.add(7);
        bst.add(6);
        bst.add(8);

        List<Integer> postorder = new ArrayList<>();
        postorder.add(3);
        postorder.add(2);
        postorder.add(1);
        postorder.add(6);
        postorder.add(8);
        postorder.add(7);
        postorder.add(5);
        postorder.add(9);
        postorder.add(4);

        // Should be [3, 2, 1, 6, 8, 7, 5, 9, 4]
        assertEquals(postorder, bst.postorder());
    }

    @Test(timeout = TIMEOUT)
    public void testLevelorder() {
        /*
                       4
                    /     \
                   1       9
                    \     /
                     2   5
                      \   \
                       3   7
                          / \
                         6   8
        */

        bst.add(4);
        bst.add(1);
        bst.add(2);
        bst.add(3);
        bst.add(9);
        bst.add(5);
        bst.add(7);
        bst.add(6);
        bst.add(8);

        List<Integer> levelorder = new ArrayList<>();
        levelorder.add(4);
        levelorder.add(1);
        levelorder.add(9);
        levelorder.add(2);
        levelorder.add(5);
        levelorder.add(3);
        levelorder.add(7);
        levelorder.add(6);
        levelorder.add(8);

        // Should be [4, 1, 9, 2, 5, 3, 7, 6, 8]
        assertEquals(levelorder, bst.levelorder());
    }

    /* 3-layer full and complete BST
                        4
                2                6
           1         3      5         7
     */
    @Test
    public void test3LayerFullPreorder() {
        Integer[] preordered = {4, 2, 1, 3, 6, 5, 7};
        bst = new BST<>(nLayerFullOrder(3));
        assertArrayEquals(preordered, bst.preorder().toArray());
    }

    @Test
    public void test3LayerFullPostorder() {
        Integer[] postordered = {1, 3, 2, 5, 7, 6, 4};
        bst = new BST<>(nLayerFullOrder(3));
        assertArrayEquals(postordered, bst.postorder().toArray());
    }

    @Test
    public void test3LayerFullInorder() {
        Integer[] inordered = {1, 2, 3, 4, 5, 6, 7};
        bst = new BST<>(nLayerFullOrder(3));
        assertArrayEquals(inordered, bst.inorder().toArray());
    }

    @Test
    public void test3LayerFullLevelorder() {
        Integer[] levelordered = {4, 2, 6, 1, 3, 5, 7};
        bst = new BST<>(nLayerFullOrder(3));
        assertArrayEquals(levelordered, bst.levelorder().toArray());
    }

    @Test(timeout = TIMEOUT)
    public void testHeight() {
        /*
                     3
                    /
                   1
                    \
                     2
        */

        bst.add(3);
        bst.add(1);
        bst.add(2);

        assertEquals(2, bst.height());
    }

    @Test
    public void testHeightMultiplePaths() {
        //height starts from 0
        bst = new BST<>(nLayerFullOrder(3));
        assertEquals(bst.height(), 2);
        //does not change the height since not the whole layer is removed
        bst.remove(1);
        assertEquals(bst.height(), 2);
        bst.add(10);
        //add to another location, now the height should be 3
        assertEquals(bst.height(), 3);
    }

    @Test
    public void testHeightSmallTree() {
        //test cases for size of 0 and size 1
        bst = new BST<>();
        //empty tree
        assertEquals(-1, bst.height());
        assertEquals(0, bst.size());
        bst.add(5);
        //only root
        assertEquals(0, bst.height());
        assertEquals(1, bst.size());
    }

    @Test(timeout = TIMEOUT)
    public void testIsBST() {
        /*
                    50
                  /    \   should return false
                75      25
        */

        BSTNode<Integer> root = new BSTNode<>(50);
        root.setLeft(new BSTNode<>(75));
        root.setRight(new BSTNode<>(25));

        assertEquals(false, BST.isBST(root));

        /*
                    50
                  /    \   should return true
                25      75
        */

        root = new BSTNode<>(50);
        root.setLeft(new BSTNode<>(25));
        root.setRight(new BSTNode<>(75));
        BST.isBST(root);

        assertEquals(true, BST.isBST(root));

        /* there is a failing case such as this if and only if the predecessor or successor is a failing case,
        assuming that the tree could otherwise be mistaken for a BST

                   50
                      \
                      75
                     /
                    25
         */

        root = new BSTNode<>(50);
        root.setRight(new BSTNode<>(75));
        root.getRight().setLeft(new BSTNode<>(25));

        assertEquals(false, BST.isBST(root));

        /*
                 50
                /
               25
                 \
                  75
         */

        root = new BSTNode<>(50);
        root.setLeft(new BSTNode<>(25));
        root.getLeft().setRight(new BSTNode<>(75));
        assertEquals(false, BST.isBST(root));

        //check if 5 layer full and complete BST is true
        BST<Integer> bst = new BST<>(nLayerFullOrder(5));
        assertEquals(true, BST.isBST(bst.getRoot()));
    }

    //---------------Exception handling test cases--------------------------

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNullData() {
        bst = new BST<>(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNullElement() {
        ArrayList<Integer> toInsert= new ArrayList<>();
        toInsert.add(1);
        toInsert.add(2);
        toInsert.add(null);
        toInsert.add(4);
        toInsert.add(5);
        bst = new BST<>(toInsert);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNull() {
        bst.add(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNull() {
        bst.remove(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void testRemoveNotInTreeEmpty() {
        //empty tree, remove an element
        bst.remove(5);
    }

    @Test
    public void testRemoveNotInTreeNonEmpty() {
        bst.add(5);
        bst.add(3);
        bst.add(7);
        //test a few cases, rightmost element, leftmost element and in between
        try {
            bst.remove(10);
            fail();
        } catch (NoSuchElementException e) { }
        try {
            bst.remove(1);
            fail();
        } catch (NoSuchElementException e) { }
        try {
            bst.remove(6);
            fail();
        } catch (NoSuchElementException e) { }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetNull() {
        bst.get(null);
    }

    @Test(expected = NoSuchElementException.class)
    public void testGetNotInTreeEmpty() {
        //empty tree, remove an element
        bst.get(5);
    }

    @Test
    public void testGetNotInTreeNonEmpty() {
        bst.add(5);
        bst.add(3);
        bst.add(7);
        //test a few cases, rightmost element, leftmost element and in between
        try {
            bst.get(10);
            fail();
        } catch (NoSuchElementException e) { }
        try {
            bst.get(1);
            fail();
        } catch (NoSuchElementException e) { }
        try {
            bst.get(6);
            fail();
        } catch (NoSuchElementException e) { }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testContainsNull() {
        bst.contains(null);
    }

    //----------------Private helper methods------------------------------

//    @Test
//    public void testFullOrder() {
//        Integer[] list1 = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
//        //output is [6, 3, 2, 1, 5, 4, 9, 8, 7, 10]
//        System.out.println(completeOrder(list1));
//        Integer[] list2 = new Integer[]{1, 2, 3, 4, 5, 6, 7};
//        //output is [4, 2, 1, 3, 6, 5, 7]
//        System.out.println(completeOrder(list2));
//        //output is [4, 2, 1, 3, 6, 5, 7], same as above
//        System.out.println(nLayerFullOrder(3));
//
//        System.out.println(nLayerFullOrder(4));
//    }

    private List<Integer> nLayerFullOrder(int n) {
        //helps to create array for input of nLayers with the nodes from 1 to 2^n - 1
        //casting to int, the double takes a little error into account
        Integer[] nodeArray = new Integer[(int) Math.pow(2, n) - 1];
        for (int i = 0; i < (int) Math.pow(2, n) - 1; i++) {
            nodeArray[i] = i + 1;
        }
        return completeOrder(nodeArray);
    }

    private <T> List<T> completeOrder(T[] sortedList) {
        //take in list in sorted order and output list in order which when inserted to BST will get
        //to do this, take the middle element, biased to the right (i.e. if there's an even number of elements, take
        // the right one), recurse on left and right. Intuitively, this splits the inputs into left and right
        // subtree, with the left subtree always larger
        List<T> completeBSTList = new ArrayList<>();

        completeOrderHelper(sortedList, 0, sortedList.length - 1, completeBSTList);
        return completeBSTList;
    }

    //generic method in non-generic class
    private <T> void completeOrderHelper(T[] sortedList, int start, int end, List<T> completeList) {
        if (start > end) {
            //base case, do nothing
            return;
        }
        //working on sortedList[start:end], exclusive of end
        int middle = (end - start) / 2 + start;
        //if there's an even number of elements (end - start + 1 is the number of elements since inclusive of start
        // and exclusive of end)
        if ((end - start) % 2 == 1) {
            middle++;
        }
        //add middle to list since it's in the middle (so it should be the next node added)
        completeList.add(sortedList[middle]);
        //look left, [start:middle - 1]
        completeOrderHelper(sortedList, start, middle - 1, completeList);
        //look right, [middle + 1: end]
        completeOrderHelper(sortedList, middle + 1, end, completeList);
    }
}