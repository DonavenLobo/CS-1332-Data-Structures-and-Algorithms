import java.util.NoSuchElementException;

/**
 * Your implementation of a CircularSinglyLinkedList without a tail pointer.
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
public class CircularSinglyLinkedList<T> {


    // Do not add new instance variables or modify existing ones.
    private CircularSinglyLinkedListNode<T> head;
    private int size;

    // Do not add a constructor.

    /**
     * Adds the data to the specified index.
     *
     * Must be O(1) for indices 0 and size and O(n) for all other cases.
     *
     * @param index the index at which to add the new data
     * @param data  the data to add at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index > size
     * @throws java.lang.IllegalArgumentException  if data is null
     */
    public void addAtIndex(int index, T data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Data can't be added at given index!");
        } else if (data == null) {
            throw new IllegalArgumentException("Data is a null object!");
        } else {
            if (size == 0) {
                head = new CircularSinglyLinkedListNode<>(data);
                head.setNext(head);
            } else {
                if (index == 0 || index == size) {
                    CircularSinglyLinkedListNode<T> holder = new CircularSinglyLinkedListNode<>(head.getData(),
                            head.getNext());
                    head.setData(data);
                    head.setNext(holder);
                    if (index == size) {
                        head = holder;
                    }
                } else {
                    CircularSinglyLinkedListNode<T> current = head.getNext();
                    for (int i = 1; i < size; i++) {
                        if (i == index) {
                            CircularSinglyLinkedListNode<T> holder = new CircularSinglyLinkedListNode<>(
                                    current.getData(), current.getNext());
                            current.setData(data);
                            current.setNext(holder);
                        } else {
                            current = current.getNext();
                        }
                    }
                }
            }
            size++;
        }

    }

    /**
     * Adds the data to the front of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the front of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToFront(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is a null object!");
        } else {
            addAtIndex(0, data);
        }
    }

    /**
     * Adds the data to the back of the list.
     *
     * Must be O(1).
     *
     * @param data the data to add to the back of the list
     * @throws java.lang.IllegalArgumentException if data is null
     */
    public void addToBack(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Data is a null object!");
        } else {
            addAtIndex(size, data);
        }
    }

    /**
     * Removes and returns the data at the specified index.
     *
     * Must be O(1) for index 0 and O(n) for all other cases.
     *
     * @param index the index of the data to remove
     * @return the data formerly located at the specified index
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T removeAtIndex(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("The index is not within the bounds of the list");
        } else {
            T dataVal = null;
            if (index == 0) {
                dataVal = head.getData();
                if (size == 1) {
                    head = null;
                } else {
                    head.setData(head.getNext().getData());
                    head.setNext(head.getNext().getNext());
                }
            } else {
                CircularSinglyLinkedListNode<T> current = head.getNext();
                for (int i = 1; i < size; i++) {
                    if (i == index) {
                        dataVal = current.getData();
                        if (index == size - 1) {
                            current.setData(head.getData());
                            current.setNext(head.getNext());
                            head = current;
                        } else {
                            current.setData(current.getNext().getData());
                            current.setNext(current.getNext().getNext());
                        }
                    }
                    current = current.getNext();
                }
            }
            size--;
            return dataVal;
        }
    }

    /**
     * Removes and returns the first data of the list.
     *
     * Must be O(1).
     *
     * @return the data formerly located at the front of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromFront() {
        if (isEmpty()) {
            throw new NoSuchElementException("The list is empty! No data to be returned");
        } else {
            return removeAtIndex(0);
        }
    }

    /**
     * Removes and returns the last data of the list.
     *
     * Must be O(n).
     *
     * @return the data formerly located at the back of the list
     * @throws java.util.NoSuchElementException if the list is empty
     */
    public T removeFromBack() {
        if (isEmpty()) {
            throw new NoSuchElementException("The list is empty! No data to be returned");
        } else {
            return removeAtIndex(size - 1);
        }
    }

    /**
     * Returns the data at the specified index.
     *
     * Should be O(1) for index 0 and O(n) for all other cases.
     *
     * @param index the index of the data to get
     * @return the data stored at the index in the list
     * @throws java.lang.IndexOutOfBoundsException if index < 0 or index >= size
     */
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index isn't within list bounds");
        } else {
            T dataVal = null;
            if (index == 0) {
                dataVal = head.getData();
            } else {
                CircularSinglyLinkedListNode<T> current = head.getNext();
                for (int i = 1; i < size; i++) {
                    if (i == index) {
                        dataVal = current.getData();
                    } else {
                        current = current.getNext();
                    }
                }
            }
            return dataVal;
        }

    }

    /**
     * Returns whether or not the list is empty.
     *
     * Must be O(1).
     *
     * @return true if empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Clears the list.
     *
     * Clears all data and resets the size.
     *
     * Must be O(1).
     */
    public void clear() {
        head = null;
        size = 0;
    }

    /**
     * Removes and returns the last copy of the given data from the list.
     *
     * Do not return the same data that was passed in. Return the data that
     * was stored in the list.
     *
     * Must be O(n).
     *
     * @param data the data to be removed from the list
     * @return the data that was removed
     * @throws java.lang.IllegalArgumentException if data is null
     * @throws java.util.NoSuchElementException   if data is not found
     */
    public T removeLastOccurrence(T data) {
        if (data == null) {
            throw new IllegalArgumentException("Input value was a null pointer!");
        } else {
            int lastFound = -1;
            CircularSinglyLinkedListNode<T> current = head;
            for (int i = 0; i < size; i++) {
                if (current.getData().equals(data)) {
                    lastFound = i;
                }
                current = current.getNext();
            }
            if (lastFound == -1) {
                throw new NoSuchElementException("Couldn't find the data value in the list");
            } else {
                return removeAtIndex(lastFound);
            }
        }
    }

    /**
     * Returns an array representation of the linked list.
     *
     * Must be O(n) for all cases.
     *
     * @return the array of length size holding all of the data (not the
     * nodes) in the list in the same order
     */
    public T[] toArray() {
        T[] newArr = (T[]) new Object[size];
        CircularSinglyLinkedListNode<T> current = head;
        for (int i = 0; i < size; i++) {
            newArr[i] = current.getData();
            current = current.getNext();
        }
        return newArr;
    }

    /**
     * Returns the head node of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the node at the head of the list
     */
    public CircularSinglyLinkedListNode<T> getHead() {
        // DO NOT MODIFY!
        return head;
    }

    /**
     * Returns the size of the list.
     *
     * For grading purposes only. You shouldn't need to use this method since
     * you have direct access to the variable.
     *
     * @return the size of the list
     */
    public int size() {
        // DO NOT MODIFY!
        return size;
    }
}
