import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.LinkedList;

/**
 * Your implementation of a BST.
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
public class BST<T extends Comparable<? super T>> {

    // Do not add new instance variables or modify existing ones.
    private BSTNode<T> root;
    private int size;

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize an empty BST.
     *
     * Since instance variables are initialized to their default values, there
     * is no need to do anything for this constructor.
     */
    public BST() {
        // DO NOT IMPLEMENT THIS CONSTRUCTOR!
    }

    /**
     * Constructs a new BST.
     *
     * This constructor should initialize the BST with the data in the
     * Collection. The data should be added in the same order it is in the
     * Collection.
     *
     * Hint: Not all Collections are indexable like Lists, so a regular for loop
     * will not work here. However, all Collections are Iterable, so what type
     * of loop would work?
     *
     * @param data the data to add to the tree
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public BST(Collection<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("Data value is a null pointer!");
        } else {
            for (T dataVal : data) {
                if (dataVal == null) {
                    throw new IllegalArgumentException("Data value is a null pointer!");
                } else {
                    add(dataVal);
                }
            }
        }
    }

    /**
     * Adds the element to the tree.
     *
     * The data becomes a leaf in the tree.
     *
     * Traverse the tree to find the appropriate location. If the data is
     * already in the tree, then nothing should be done (the duplicate
     * shouldn't get added, and size should not be incremented).
     *
     * Must be O(log n) for a balanced tree and O(n) for worst case.
     * 
     * This method must be implemented recursively.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data value is a null pointer!");
        } else {
            root = add(root, data);
        }
    }

    /**
     * Helper recursive function for the add function
     *
     * @param parent the current parent node
     * @param data the new data we are trying to add
     * @return the updated parent/root of the BST
     */
    private BSTNode<T> add(BSTNode<T> parent, T data) {
        if (parent == null) {
            size++;
            return new BSTNode<T>(data);
        } else if (data.compareTo(parent.getData()) > 0) {
            parent.setRight(add(parent.getRight(), data));
        } else if (data.compareTo(parent.getData()) < 0) {
            parent.setLeft(add(parent.getLeft(), data));
        }
        return parent;
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
     * 3: The node containing the data has 2 children. Use the predecessor to
     * replace the data. You MUST use recursion to find and remove the
     * predecessor (you will likely need an additional helper method to
     * handle this case efficiently).
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for a balanced tree and O(n) for worst case.
     * 
     * This method must be implemented recursively.
     *
     * @param data the data to remove
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if the data is not in the tree
     */
    public T remove(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Remove condition is a null pointer!");
        } else {
            BSTNode<T> dummy = new BSTNode<>(null);
            root = remove(root, data, dummy);
            return dummy.getData();
        }
    }

    /**
     * Private helper method implementation of the remove method
     *
     * @param curr current node in recursion
     * @param data the data we want to remove
     * @param dummy dummy node to hold important information when adjusting the tree
     * @return reinforced node
     */
    private BSTNode<T> remove(BSTNode<T> curr, T data, BSTNode<T> dummy) {
        if (curr == null) {
            throw new NoSuchElementException("Data is not in the Tree!");
        } else if (data.compareTo(curr.getData()) < 0) {
            curr.setLeft(remove(curr.getLeft(), data, dummy));
        } else if (data.compareTo(curr.getData()) > 0) {
            curr.setRight(remove(curr.getRight(), data, dummy));
        } else {
            dummy.setData(curr.getData());
            size--;
            if (curr.getLeft() == null && curr.getRight() == null) {
                return null;
            } else if (curr.getLeft() != null && curr.getRight() == null) {
                return curr.getLeft();
            } else if (curr.getLeft() == null && curr.getRight() != null) {
                return curr.getRight();
            } else {
                BSTNode<T> dummy2 = new BSTNode<>(null);
                curr.setLeft(removePredecessor(curr.getLeft(), dummy2));
                curr.setData(dummy2.getData());
            }
        }
        return curr;
    }

    /**
     * Private helper method for the remove method, for the case when both children nodes exist.
     *
     * @param curr current node in recursion
     * @param dummy dummy node to hold important information when recursion occurs
     * @return reinforced node
     */
    private BSTNode<T> removePredecessor(BSTNode<T> curr, BSTNode<T> dummy) {
        if (curr.getRight() == null) {
            dummy.setData(curr.getData());
            return curr.getLeft();
        } else {
            curr.setRight(removePredecessor(curr.getRight(), dummy));
        }
        return curr;
    }

    /**
     * Returns the element from the tree matching the given parameter.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for a balanced tree and O(n) for worst case.
     * 
     * This method must be implemented recursively.
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
            throw new IllegalArgumentException("Data value is null pointer!");
        } else {
            if (!get(root, data)) {
                throw new NoSuchElementException("The data is not located in the BST.");
            } else {
                return data;
            }
        }
    }

    /**
     * Helper recursive function to see if data exists in BST
     *
     * @param currNode the current node we are comparing with in the BST
     * @param data The data we are looking for
     * @return boolean on whether the data exists
     */
    private boolean get(BSTNode<T> currNode, T data) {
        if (currNode == null) {
            return false;
        } else if (data.compareTo(currNode.getData()) == 0) {
            return true;
        } else {
            if (currNode.getData().compareTo(data) > 0) {
                return get(currNode.getLeft(), data);
            } else {
                return get(currNode.getRight(), data);
            }
        }
    }

    /**
     * Returns whether or not data matching the given parameter is contained
     * within the tree.
     *
     * Hint: Should you use value equality or reference equality?
     *
     * Must be O(log n) for a balanced tree and O(n) for worst case.
     * 
     * This method must be implemented recursively.
     *
     * @param data the data to search for in the tree.
     * @return true if the parameter is contained within the tree, false
     * otherwise
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public boolean contains(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data input is a null pointer");
        } else {
            return get(root, data);
        }
    }

    /**
     * Generate a pre-order traversal of the tree.
     *
     * Must be O(n).
     *
     * This method must be implemented recursively.
     *
     * @return the preorder traversal of the tree
     */
    public List<T> preorder() {
        List<T> returnList = new ArrayList<>(size);
        returnList = preorder(root, returnList);
        return returnList;
    }

    /**
     * Helper Method for recursive calls for preorder function
     *
     * @param node the current node of the recursion
     * @param returnList the partially filled list
     * @return return the partially filled list
     */
    private List<T> preorder(BSTNode<T> node, List<T> returnList) {
        if (node != null) {
            returnList.add(node.getData());
            returnList = preorder(node.getLeft(), returnList);
            returnList = preorder(node.getRight(), returnList);
        }
        return returnList;
    }

    /**
     * Generate a in-order traversal of the tree.
     *
     * Must be O(n).
     * 
     * This method must be implemented recursively.
     *
     * @return the inorder traversal of the tree
     */
    public List<T> inorder() {
        List<T> returnList = new ArrayList<>(size);
        returnList = inorder(root, returnList);
        return returnList;
    }

    /**
     * Helper Method for recursive calls for inorder function
     *
     * @param node the current node of the recursion
     * @param returnList the partially filled list
     * @return return the partially filled list
     */
    private List<T> inorder(BSTNode<T> node, List<T> returnList) {
        if (node != null) {
            returnList = inorder(node.getLeft(), returnList);
            returnList.add(node.getData());
            returnList = inorder(node.getRight(), returnList);
        }
        return returnList;
    }

    /**
     * Generate a post-order traversal of the tree.
     *
     * Must be O(n).
     * 
     * This method must be implemented recursively.
     *
     * @return the postorder traversal of the tree
     */
    public List<T> postorder() {
        List<T> returnList = new ArrayList<>(size);
        returnList = postorder(root, returnList);
        return returnList;
    }

    /**
     * Helper Method for recursive calls for postorder function
     *
     * @param node the current node of the recursion
     * @param returnList the partially filled list
     * @return return the partially filled list
     */
    private List<T> postorder(BSTNode<T> node, List<T> returnList) {
        if (node != null) {
            returnList = postorder(node.getLeft(), returnList);
            returnList = postorder(node.getRight(), returnList);
            returnList.add(node.getData());
        }
        return returnList;
    }


    /**
     * Generate a level-order traversal of the tree.
     *
     * This does not need to be done recursively.
     *
     * Hint: You will need to use a queue of nodes. Think about what initial
     * node you should add to the queue and what loop / loop conditions you
     * should use.
     *
     * Must be O(n).
     *
     * @return the level order traversal of the tree
     */
    public List<T> levelorder() {
        Queue<BSTNode<T>> q = new LinkedList<>();
        List<T> levelOrderList = new ArrayList<>(size);
        q.add(root);
        while (!q.isEmpty()) {
            BSTNode<T> curr = q.remove();
            if (curr != null) {
                levelOrderList.add(curr.getData());
                q.add(curr.getLeft());
                q.add(curr.getRight());
            }
        }
        return levelOrderList;
    }


    /**
     * Returns the height of the root of the tree.
     *
     * A node's height is defined as max(left.height, right.height) + 1. A
     * leaf node has a height of 0 and a null child should be -1.
     *
     * Must be O(n).
     * 
     * This method must be implemented recursively.
     *
     * @return the height of the root of the tree, -1 if the tree is empty
     */
    public int height() {
        if (root == null) {
            return -1;
        } else {
            return height(root);
        }
    }

    /**
     * Private Helper method to find the height of the tree
     *
     * @param curr current node of the recursion
     * @return the height of the tree
     */
    private int height(BSTNode<T> curr) {
        if (curr == null) {
            return -1;
        }
        int leftHeight = height(curr.getLeft());
        int rightHeight = height(curr.getRight());

        if (leftHeight > rightHeight) {
            return leftHeight + 1;
        } else {
            return rightHeight + 1;
        }
    }

    /**
     * Clears the tree.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        root = null;
        size = 0;
    }

    /**
     * This method checks whether a binary tree meets the criteria for being
     * a binary search tree.
     *
     * This method is a static method that takes in a BSTNode called
     * {@code treeRoot}, which is the root of the tree that you should check.
     *
     * You may assume that the tree passed in is a proper binary tree; that is,
     * there are no loops in the tree, the parent-child relationship is
     * correct, that there are no duplicates, and that every parent has at
     * most 2 children. So, what you will have to check is that the order
     * property of a BST is still satisfied.
     *
     * Should run in O(n). However, you should stop the check as soon as you
     * find evidence that the tree is not a BST rather than checking the rest
     * of the tree.
     * 
     * This method must be implemented recursively.
     *
     * @param <T> the generic typing
     * @param treeRoot the root of the binary tree to check
     * @return true if the binary tree is a BST, false otherwise
     */
    public static <T extends Comparable<? super T>> boolean isBST(
            BSTNode<T> treeRoot) {
        if (treeRoot == null) {
            return true;
        }
        if (treeRoot.getLeft() != null && treeRoot.getLeft().getData().compareTo(treeRoot.getData()) > 0) {
            return false;
        }
        if (treeRoot.getRight() != null && treeRoot.getRight().getData().compareTo(treeRoot.getData()) < 0) {
            return false;
        }
        return isBST(treeRoot.getLeft()) && isBST(treeRoot.getRight());
    }

    /**
     * List<T> checkList = new ArrayList<>();
     *         checkList = isBSTHelper(treeRoot, checkList);
     *         if (checkList.size() > 1 && checkList.get(checkList.size() - 1).compareTo(checkList.get(checkList.size()
     *                 - 2)) < 0) {
     *             return false;
     *         }
     *         return true;
     */

    /**
     *Private static helper method to check if tree is correct and within bounds
     *
     * @param treeRoot the root of the tree
     * @param <T> generic tupe
     * @param checkList list to hold inorder list for checking
     * @return checkList list to check if bst is correct
     */
    private static <T extends Comparable<? super T>> List<T> isBSTHelper(
            BSTNode<T> treeRoot, List<T> checkList) {
        if (treeRoot != null) {
            checkList = isBSTHelper(treeRoot.getLeft(), checkList);
            if (checkList.size() > 1 && checkList.get(checkList.size() - 1).compareTo(checkList.get(checkList.size()
                    - 2)) < 0) {
                return checkList;
            }
            checkList.add(treeRoot.getData());

            checkList = isBSTHelper(treeRoot.getRight(), checkList);
        }
        return checkList;

    }

    /**
     * Returns the root of the tree.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the root of the tree
     */
    public BSTNode<T> getRoot() {
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
}
