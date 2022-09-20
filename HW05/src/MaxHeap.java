import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Your implementation of a MaxHeap.
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
public class MaxHeap<T extends Comparable<? super T>> {

    /*
     * The initial capacity of the MaxHeap when created with the default
     * constructor.
     *
     * DO NOT MODIFY THIS VARIABLE!
     */
    public static final int INITIAL_CAPACITY = 13;

    /*
     * Do not add new instance variables or modify existing ones.
     */
    private T[] backingArray;
    private int size;

    /**
     * Constructs a new MaxHeap.
     *
     * The backing array should have an initial capacity of INITIAL_CAPACITY.
     */
    public MaxHeap() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
    }

    /**
     * Creates a properly ordered heap from a set of initial values.
     *
     * You must use the BuildHeap algorithm that was taught in lecture! Simply
     * adding the data one by one using the add method will not get any credit.
     * As a reminder, this is the algorithm that involves building the heap
     * from the bottom up by repeated use of downHeap operations.
     *
     * Before doing the algorithm, first copy over the data from the
     * ArrayList to the backingArray (leaving index 0 of the backingArray
     * empty). The data in the backingArray should be in the same order as it
     * appears in the passed in ArrayList before you start the BuildHeap
     * algorithm.
     *
     * The backingArray should have capacity 2n + 1 where n is the
     * number of data in the passed in ArrayList (not INITIAL_CAPACITY).
     * Index 0 should remain empty, indices 1 to n should contain the data in
     * proper order, and the rest of the indices should be empty.
     *
     * @param data a list of data to initialize the heap with
     * @throws java.lang.IllegalArgumentException if data or any element in data
     *                                            is null
     */
    public MaxHeap(ArrayList<T> data) {
        if (data == null) {
            throw new IllegalArgumentException("The data input was a null pointer!");
        }
        backingArray = (T[]) new Comparable[2 * data.size() + 1];
        for (T dataVal : data) {
            if (dataVal == null) {
                throw new java.lang.IllegalArgumentException("Cannot create heap from null data.");
            }
            size++;
            backingArray[size] = dataVal;
        }
        for (int i = size / 2; i > 0; i--) {
            downHeap(i);
        }
    }

    /**
     * Helper method that performs downHeap operation of incorrectly placed data values
     *
     * @param i the index of the current element
     */
    private void downHeap(int i) {
        if (i * 2 > size) {
            return;
        }
        if (backingArray[i * 2] == null && backingArray[i * 2 + 1] == null) {
            return;
        }
        int max = maxChild(i);
        if (backingArray[i].compareTo(backingArray[max]) < 0) {
            T temp = backingArray[i];
            backingArray[i] = backingArray[max];
            backingArray[max] = temp;
            downHeap(max);
        }
    }

    /**
     * Helper method that finds the index of the element's child with the highest value
     *
     * @param i the index of the current element
     * @return the index of the child with the highest value
     */
    private int maxChild(int i) {
        if (backingArray[i * 2] == null) {
            return i * 2 + 1;
        } else if (backingArray[i * 2 + 1] == null) {
            return i * 2;
        } else if (backingArray[i * 2].compareTo(backingArray[i * 2 + 1]) > 0) {
            return i * 2;
        } else {
            return i * 2 + 1;
        }
    }

    /**
     * Adds the data to the heap.
     *
     * If sufficient space is not available in the backing array (the backing
     * array is full except for index 0), resize it to double the current
     * length.
     *
     * @param data the data to add
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void add(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data input was a null pointer");
        }
        if (size + 1 == backingArray.length) {
            resizeArr();
        }
        size++;
        backingArray[size] = data;
        upHeap(size);
    }

    /**
     * Helper method that performs up heap operation of incorrectly placed data values
     *
     * @param i the index of the element in question
     */
    private void upHeap(int i) {
        if (i / 2 == 0) {
            return;
        }
        int parent = i / 2;
        if (backingArray[i].compareTo(backingArray[parent]) > 0) {
            T temp = backingArray[parent];
            backingArray[parent] = backingArray[i];
            backingArray[i] = temp;
            upHeap(parent);
        }
    }

    /**
     * Removes and returns the root of the heap.
     *
     * Do not shrink the backing array.
     *
     * Replace any unused spots in the array with null.
     *
     * @return the data that was removed
     * @throws java.util.NoSuchElementException if the heap is empty
     */
    public T remove() {
        T root = getMax();
        backingArray[1] = backingArray[size];
        backingArray[size] = null;
        size--;
        downHeap(1);
        return root;
    }

    /**
     * Returns the maximum element in the heap.
     *
     * @return the maximum element
     * @throws java.util.NoSuchElementException if the heap is empty
     */
    public T getMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("The heap is empty!");
        } else {
            return backingArray[1];
        }
    }

    /**
     * Returns whether or not the heap is empty.
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the heap.
     *
     * Resets the backing array to a new array of the initial capacity and
     * resets the size.
     */
    public void clear() {
        backingArray = (T[]) new Comparable[INITIAL_CAPACITY];
        size = 0;
    }

    /**
     * Returns the backing array of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the backing array of the list
     */
    public T[] getBackingArray() {
        // DO NOT MODIFY THIS METHOD!
        return backingArray;
    }

    /**
     * Returns the size of the heap.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY THIS METHOD!
        return size;
    }

    /**
     * This helper function is to resize an array if it reaches its capacity
     */
    private void resizeArr() {
        T[] newArr = (T[]) new Comparable[2 * backingArray.length];
        for (int i = 0; i < backingArray.length; i++) {
            newArr[i] = backingArray[i];
        }
        backingArray = newArr;
    }


}
