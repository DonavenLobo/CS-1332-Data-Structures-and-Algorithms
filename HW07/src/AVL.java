import java.util.Collection;

/**
 * Your implementation of an AVL.
 *
 * @author Donaven Lobo
 * @version 1.0
 * @userid dlobo6
 * @GTID 903490973
 *
 * Collaborators: LIST ALL COLLABORATORS YOU WORKED WITH HERE
 *
 * Resources: LIST ALL NON-COURSE RESOURCES YOU CONSULTED HERE
 */
public class AVL<T extends Comparable<? super T>> {

    // Do not add new instance variables or modify existing ones.
    private AVLNode<T> root;
    private int size;

    /**
     * Constructs a new AVL.
     *
     * This constructor should initialize an empty AVL.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public AVL() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new AVL.
     *
     * This constructor should initialize the AVL with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     *
     * @param data the data to add to the tree
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public AVL(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Input data collection was a null pointer");
        }
        for (T dataVal: data) {
            add(dataVal);
        }
    }

    /**
     * Adds the element to the tree.
     *
     * Start by adding it as a leaf like in a regular BST and then rotate the
     * tree as necessary.
     *
     * If the data is already in the tree, then nothing should be done (the
     * duplicate shouldn't get added, and size should not be incremented).
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after adding the element, making sure to rebalance if
     * necessary.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("The input data was a null pointer!");
        }
        root = addHelper(root, data);
    }

    /**
     * Removes and returns the element from the tree matching the given
     * parameter.
     *
     * There are 3 cases to consider:
     * 1: The node containing the data is a leaf (no children). In this case,
     * simply remove it.
     * 2: The node containing the data has one child. In this case, simply
     * replace it with its child.
     * 3: The node containing the data has 2 children. Use the successor to
     * replace the data, NOT predecessor. As a reminder, rotations can occur
     * after removing the successor node.
     *
     * Remember to recalculate heights and balance factors while going back
     * up the tree after removing the element, making sure to rebalance if
     * necessary.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not found
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Input data was a null pointer!");
        }
        AVLNode<T> removed = new AVLNode<T>(null);
        root = removeHelper(root, data, removed);
        return removed.getData();
    }

    /**
     * Returns the element from the tree matching the given parameter.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * @param data the data to search for in the tree
     * @return the data in the tree equal to the parameter
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T get(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data input was a null pointer!");
        }
        return getHelper(root, data);
    }

    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * @param data the data to search for in the tree.
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data input was a null pointer!");
        }
        return containsHelper(root, data);
    }

    /**
     * Returns the height of the root of the tree.
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        if (size == 0) {
            return -1;
        } else {
            return root.getHeight();
        }

    }

    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * Returns the data in the deepest node. If there is more than one node
     * with the same deepest depth, return the rightmost (i.e. largest) node with 
     * the deepest depth. 
     *
     * Must run in O(log n) for all cases.
     *
     * Example
     * Tree:
     *           2
     *        /    \
     *       0      3
     *        \
     *         1
     * Max Deepest Node:
     * 1 because it is the deepest node
     *
     * Example
     * Tree:
     *           2
     *        /    \
     *       0      4
     *        \    /
     *         1  3
     * Max Deepest Node:
     * 3 because it is the maximum deepest node (1 has the same depth but 3 > 1)
     *
     * @return the data in the maximum deepest node or null if the tree is empty
     */
    public T maxDeepestNode() {
        if (root == null) {
            return null;
        } else {
            return maxDeepestNodeHelper(root);
        }
    }

    /**
     * In BSTs, you learned about the concept of the successor: the
     * smallest data that is larger than the current data. However, you only
     * saw it in the context of the 2-child remove case.
     *
     * This method should retrieve (but not remove) the successor of the data
     * passed in. There are 2 cases to consider:
     * 1: The right subtree is non-empty. In this case, the successor is the
     * leftmost node of the right subtree.
     * 2: The right subtree is empty. In this case, the successor is the lowest
     * ancestor of the node containing data whose left child is also
     * an ancestor of data.
     * 
     * The second case means the successor node will be one of the node(s) we 
     * traversed left from to find data. Since the successor is the SMALLEST element 
     * greater than data, the successor node is the lowest/last node 
     * we traversed left from on the path to the data node.
     *
     * This should NOT be used in the remove method.
     *
     * Ex:
     * Given the following AVL composed of Integers
     *                    76
     *                  /    \
     *                34      90
     *                  \    /
     *                  40  81
     * successor(76) should return 81
     * successor(81) should return 90
     * successor(40) should return 76
     *
     * @param data the data to find the successor of
     * @return the successor of data. If there is no larger data than the
     * one given, return null.
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T successor(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data input was a null pointer");
        }
        return successorHelper(root, data, null);
    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public AVLNode<T> getRoot() {
        // DO NOT MODIFY THIS METHOD!
        return root;
    }

    /**
     * Returns the size of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the tree
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    /**
     * Recursive helper method for the add method
     *
     * The data becomes a leaf in the tree.
     *
     * @param curr the base node to be examined before adding
     * @param data the data to add
     * @return the added node or node used for pointer reinforcement
     */
    private AVLNode<T> addHelper(AVLNode<T> curr, T data) {
        if (curr == null) {
            size++;
            AVLNode<T> newNode = new AVLNode<T>(data);
            newNode.setHeight(0);
            newNode.setBalanceFactor(0);
            return newNode;
        } else if (data.compareTo(curr.getData()) < 0) {
            curr.setLeft(addHelper(curr.getLeft(), data));
        } else if (data.compareTo(curr.getData()) > 0) {
            curr.setRight(addHelper(curr.getRight(), data));
        }
        update(curr);
        return balance(curr);
    }

    /**
     * Helper method that adjusts height of nodes to restore balance with a left rotation
     *
     * @param curr the node at the root of rebalancing
     * @return the node at the root of rebalancing
     */
    private AVLNode<T> leftRotation(AVLNode<T> curr) {
        AVLNode<T> holder = curr.getRight();
        curr.setRight(holder.getLeft());
        holder.setLeft(curr);
        update(curr);
        update(holder);
        return holder;
    }

    /**
     * Helper method that adjusts height of nodes to restore balance with a right rotation
     *
     * @param curr the node at the root of rebalancing
     * @return the node at the root of rebalancing
     */
    private AVLNode<T> rightRotation(AVLNode<T> curr) {
        AVLNode<T> holder = curr.getLeft();
        curr.setLeft(holder.getRight());
        holder.setRight(curr);
        update(curr);
        update(holder);
        return holder;
    }

    /**
     * Helper method that updates a nodes stored height and balance factor
     *
     * @param curr the node to update
     */
    private void update(AVLNode<T> curr) {
        int leftH;
        int rightH;
        if (curr.getLeft() == null) {
            leftH = -1;
        } else {
            leftH = curr.getLeft().getHeight();
        }
        if (curr.getRight() == null) {
            rightH = -1;
        } else {
            rightH = curr.getRight().getHeight();
        }
        curr.setHeight(Math.max(leftH, rightH) + 1);
        curr.setBalanceFactor(leftH - rightH);
    }

    /**
     * Helper method that rebalances a nodes subtree
     *
     * @param curr the node to be rebalanced
     * @return the rebalanced subtree
     */
    private AVLNode<T> balance(AVLNode<T> curr) {
        int leftBalance;
        int rightBalance;
        if (curr.getLeft() == null) {
            leftBalance = 0;
        } else {
            leftBalance = curr.getLeft().getBalanceFactor();
        }
        if (curr.getRight() == null) {
            rightBalance = 0;
        } else {
            rightBalance = curr.getRight().getBalanceFactor();
        }
        if (curr.getBalanceFactor() < -1) {
            if (rightBalance > 0) {
                curr.setRight(rightRotation(curr.getRight()));
            }
            return leftRotation(curr);
        }
        if (curr.getBalanceFactor() > 1) {
            if (leftBalance < 0) {
                curr.setLeft(leftRotation(curr.getLeft()));
            }
            return rightRotation(curr);
        }
        return curr;
    }

    /**
     * Recursive helper method for the remove method
     *
     * Removes and returns data from the tree matching the given parameter.
     *
     * @param curr the base node to be examined before removing
     * @param data the data to remove
     * @param removed the data removed
     * @return the removed node
     */
    private AVLNode<T> removeHelper(AVLNode<T> curr, T data, AVLNode<T> removed) {
        if (curr == null) {
            throw new java.util.NoSuchElementException("Data not found in AVL.");
        } else if (data.compareTo((curr.getData())) == 0) {
            removed.setData(curr.getData());
            size--;
            if (curr.getLeft() == null && curr.getRight() == null) {
                return null;
            } else if (curr.getLeft() == null) {
                update(curr.getRight());
                return balance(curr.getRight());
            } else if (curr.getRight() == null) {
                update(curr.getLeft());
                return balance(curr.getLeft());
            } else {
                AVLNode<T> temp = new AVLNode<>(null);
                curr.setRight(removeSuc(curr.getRight(), temp));
                curr.setData(temp.getData());
            }
        } else if (data.compareTo(curr.getData()) < 0) {
            curr.setLeft(removeHelper(curr.getLeft(), data, removed));
        } else if (data.compareTo(curr.getData()) > 0) {
            curr.setRight(removeHelper(curr.getRight(), data, removed));
        }
        update(curr);
        return balance(curr);
    }

    /**
     * Recursive helper method for the removeHelper method
     *
     * Finds and removes the successor node to be shifted.
     *
     * @param curr the base node to be examined before removing
     * @param temp the temporary node to carry the successor's data
     * @return the data removed or the node used for pointer reinforcement
     */
    private AVLNode<T> removeSuc(AVLNode<T> curr, AVLNode<T> temp) {
        if (curr.getLeft() == null) {
            temp.setData(curr.getData());
            return curr.getRight();
        } else {
            curr.setLeft(removeSuc(curr.getLeft(), temp));
        }
        update(curr);
        return balance(curr);
    }

    /**
     * Recursive helper method for the getmethod
     *
     * Returns data from the tree matching the given parameter.
     *
     * @param curr the base node to be examined
     * @param data the data to search for
     * @return the node that was got
     */
    private T getHelper(AVLNode<T> curr, T data) {
        if (curr == null) {
            throw new java.util.NoSuchElementException("Data not found in AVL.");
        } else if (data.compareTo(curr.getData()) < 0) {
            return getHelper(curr.getLeft(), data);
        } else if (data.compareTo(curr.getData()) > 0) {
            return getHelper(curr.getRight(), data);
        } else {
            return curr.getData();
        }
    }

    /**
     * Recursive helper method for the contains method
     *
     * Returns data from the tree matching the given parameter.
     *
     * @param curr the base node to be examined
     * @param data the data to search for
     * @return the node that matches the data, if found
     */
    private boolean containsHelper(AVLNode<T> curr, T data) {
        if (curr == null) {
            return false;
        } else if (data.compareTo(curr.getData()) < 0) {
            return containsHelper(curr.getLeft(), data);
        } else if (data.compareTo(curr.getData()) > 0) {
            return containsHelper(curr.getRight(), data);
        } else {
            return true;
        }
    }

    /**
     * Helper recursive method to find the max Deepest Node
     * @param curr The current Node we are checking
     * @return The data value at the bottom
     */
    private T maxDeepestNodeHelper(AVLNode<T> curr) {
        if (curr.getHeight() == 0) {
            return curr.getData();
        }
        if (curr.getRight() == null) {
            return maxDeepestNodeHelper(curr.getLeft());
        } else if (curr.getLeft() == null) {
            return maxDeepestNodeHelper(curr.getRight());
        } else if (curr.getLeft().getHeight() > curr.getRight().getHeight()) {
            return maxDeepestNodeHelper(curr.getLeft());
        } else {
            return maxDeepestNodeHelper(curr.getRight());
        }
    }

    /**
     * This is a helper method for the successor function
     * @param curr this is the node containing the data we want to find the successor for
     * @param data this is the data we want to find the successor for
     * @param lastLeft this is the last Left the tree has recursed through
     * @return the successors data
     */
    private T successorHelper(AVLNode<T> curr, T data, AVLNode<T> lastLeft) {
        if (curr == null) {
            throw new java.util.NoSuchElementException("Data not found in AVL.");
        } else if (data.compareTo(curr.getData()) < 0) {
            return successorHelper(curr.getLeft(), data, curr);
        } else if (data.compareTo(curr.getData()) > 0) {
            return successorHelper(curr.getRight(), data, lastLeft);
        } else {
            if (curr.getRight() != null) {
                AVLNode<T> temp = new AVLNode<>(null);
                return removeSucHelper(curr.getRight(), temp).getData();
            } else {
                if (lastLeft == null) {
                    return null;
                } else {
                    return lastLeft.getData();
                }
            }
        }
    }

    /**
     * Recursive helper method for the Successor Helper method
     *
     * Finds and returns the successor data.
     *
     * @param curr the base node to be examined before removing
     * @param temp the temporary node to carry the successor's data
     * @return the data removed
     */
    private AVLNode<T> removeSucHelper(AVLNode<T> curr, AVLNode<T> temp) {
        if (curr.getLeft() == null) {
            temp.setData(curr.getData());
            return curr;
        } else {
            return removeSucHelper(curr.getLeft(), temp);
        }
    }
}
