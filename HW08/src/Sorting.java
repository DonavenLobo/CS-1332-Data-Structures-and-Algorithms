import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * Your implementation of various sorting algorithms.
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
public class Sorting {

    /**
     * Implement selection sort.
     *
     * It should be:
     * in-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n^2)
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("The input argument was a null pointer!");
        }
        for (int i = arr.length - 1; i > 0; i--) {
            int maxIndex = 0;
            for (int j = 1; j <= i; j++) {
                if (comparator.compare(arr[j], arr[maxIndex]) > 0) {
                    maxIndex = j;
                }
            }
            swap(arr, maxIndex, i);
        }

    }

    /**
     * Implement cocktail sort.
     *
     * It should be:
     * in-place
     * stable
     * adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n)
     *
     * NOTE: See pdf for last swapped optimization for cocktail sort. You
     * MUST implement cocktail sort with this optimization
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void cocktailSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("An input was a null pointer!");
        }
        boolean swapsMade = true;
        int startIndex = 0;
        int endIndex = arr.length - 1;
        int lastSwapForward = 0;
        int lastSwapBackward = arr.length - 1;
        while (swapsMade) {
            swapsMade = false;
            for (int i = startIndex; i < endIndex; i++) {
                if (comparator.compare(arr[i], arr[i + 1]) > 0) {
                    swap(arr, i, i + 1);
                    swapsMade = true;
                    lastSwapForward = i;
                }
            }
            endIndex = lastSwapForward;
            if (swapsMade) {
                swapsMade = false;
                for (int j = endIndex; j > startIndex; j--) {
                    if (comparator.compare(arr[j - 1], arr[j]) > 0) {
                        swap(arr, j - 1, j);
                        swapsMade = true;
                        lastSwapBackward = j;
                    }
                }
                startIndex = lastSwapBackward;
            }
        }
    }

    /**
     * Implement merge sort.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n log n)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * You can create more arrays to run merge sort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * Hint: If two data are equal when merging, think about which subarray
     * you should pull from first
     *
     * @param <T>        data type to sort
     * @param arr        the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     * @throws java.lang.IllegalArgumentException if the array or comparator is
     *                                            null
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Given input was a null pointer!");
        }
        if (arr.length <= 1) {
            return;
        }
        int arrLength = arr.length;
        int midIndex = arrLength / 2;
        T[] leftArr = (T[]) new Object[midIndex];
        T[] rightArr = (T[]) new Object[arrLength - midIndex];
        for (int i = 0; i < midIndex; i++) {
            leftArr[i] = arr[i];
        }
        int a = 0;
        for (int i = midIndex; i < arr.length; i++) {
            rightArr[a] = arr[i];
            a++;
        }
        mergeSort(leftArr, comparator);
        mergeSort(rightArr, comparator);
        int i = 0;
        int j = 0;
        while (i < leftArr.length && j < rightArr.length) {
            if (comparator.compare(leftArr[i], rightArr[j]) <= 0) {
                arr[i + j] = leftArr[i];
                i++;
            } else {
                arr[i + j] = rightArr[j];
                j++;
            }
        }
        while (i < leftArr.length) {
            arr[i + j] = leftArr[i];
            i++;
        }
        while (j < rightArr.length) {
            arr[i + j] = rightArr[j];
            j++;
        }

    }

    /**
     * Implement quick sort.
     *
     * Use the provided random object to select your pivots. For example if you
     * need a pivot between a (inclusive) and b (exclusive) where b > a, use
     * the following code:
     *
     * int pivotIndex = rand.nextInt(b - a) + a;
     *
     * If your recursion uses an inclusive b instead of an exclusive one,
     * the formula changes by adding 1 to the nextInt() call:
     *
     * int pivotIndex = rand.nextInt(b - a + 1) + a;
     *
     * It should be:
     * in-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n^2)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not receive
     * credit if you do not use the one we have taught you!
     *
     * @param <T>        data type to sort
     * @param arr        the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand       the Random object used to select pivots
     * @throws java.lang.IllegalArgumentException if the array or comparator or
     *                                            rand is null
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand) {
        if (arr == null || comparator == null || rand == null) {
            throw new IllegalArgumentException("An input argument was a null pointer!");
        }
        quickSortHelper(arr, comparator, rand, 0, arr.length);
    }

    /**
     * Implement LSD (least significant digit) radix sort.
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not get full
     * credit if you do not implement the one we have taught you!
     *
     * Remember you CANNOT convert the ints to strings at any point in your
     * code! Doing so may result in a 0 for the implementation.
     *
     * It should be:
     * out-of-place
     * stable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(kn)
     *
     * And a best case running time of:
     * O(kn)
     *
     * You are allowed to make an initial O(n) passthrough of the array to
     * determine the number of iterations you need. The number of iterations
     * can be determined using the number with the largest magnitude.
     *
     * At no point should you find yourself needing a way to exponentiate a
     * number; any such method would be non-O(1). Think about how how you can
     * get each power of BASE naturally and efficiently as the algorithm
     * progresses through each digit.
     *
     * Refer to the PDF for more information on LSD Radix Sort.
     *
     * You may use ArrayList or LinkedList if you wish, but it may only be
     * used inside radix sort and any radix sort helpers. Do NOT use these
     * classes with other sorts. However, be sure the List implementation you
     * choose allows for stability while being as efficient as possible.
     *
     * Do NOT use anything from the Math class except Math.abs().
     *
     * @param arr the array to be sorted
     * @throws java.lang.IllegalArgumentException if the array is null
     */
    public static void lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("The input array was a null pointer!");
        }
        if (arr.length <= 1) {
            return;
        }
        LinkedList<Integer>[] buckets = new LinkedList[19];
        int longest = Math.abs(arr[0]);
        for (int val : arr) {
            if (Math.abs(val) > longest && val > Integer.MIN_VALUE + 1) {
                longest = Math.abs(val);
            }
        }
        int digits = 1;
        while ((longest / 10) > 0) {
            longest = longest / 10;
            digits++;
        }
        int currDigit;
        for (int iter = 0; iter < digits; iter++) {
            for (int i = 0; i < arr.length; i++) {
                int power = 1;
                for (int pow = 0; pow < iter; pow++) {
                    power = power * 10;
                }
                currDigit = (arr[i] / power) % 10 + 9;
                if (buckets[currDigit] == null) {
                    buckets[currDigit] = new LinkedList<>();
                }
                buckets[currDigit].addLast(arr[i]);
            }
            int index = 0;
            for (currDigit = 0; currDigit < 19; currDigit++) {
                while (buckets[currDigit] != null && !buckets[currDigit].isEmpty()) {
                    arr[index] = buckets[currDigit].removeFirst();
                    index++;
                }
            }
        }


    }

    /**
     * Implement heap sort.
     *
     * It should be:
     * out-of-place
     * unstable
     * not adaptive
     *
     * Have a worst case running time of:
     * O(n log n)
     *
     * And a best case running time of:
     * O(n log n)
     *
     * Use java.util.PriorityQueue as the heap. Note that in this
     * PriorityQueue implementation, elements are removed from smallest
     * element to largest element.
     *
     * Initialize the PriorityQueue using its build heap constructor (look at
     * the different constructors of java.util.PriorityQueue).
     *
     * Return an int array with a capacity equal to the size of the list. The
     * returned array should have the elements in the list in sorted order.
     *
     * @param data the data to sort
     * @return the array with length equal to the size of the input list that
     * holds the elements from the list is sorted order
     * @throws java.lang.IllegalArgumentException if the data is null
     */
    public static int[] heapSort(List<Integer> data) {
        if (data == null) {
            throw new IllegalArgumentException("Data input was a null pointer!");
        }
        if (data.size() == 0) {
            return new int[0];
        }
        PriorityQueue<Integer> minHeap = new PriorityQueue<>(data.size());
        minHeap.addAll(data);
        int[] sortedArr = new int[data.size()];
        for (int i = 0; i < data.size(); i++) {
            sortedArr[i] = minHeap.remove();
        }
        return sortedArr;
    }

    /**
     * Helper method to swap two given indicies in an array
     * @param arr the input array to swap items
     * @param i the index 1 to be swapped
     * @param j the index 2 to be swapped
     * @param <T> Data type of the swapped values
     */

    private static <T> void swap(T[] arr, int i, int j) {
        T temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    /**
     * This is a recursive helper method for the quickSort algorithm
     *
     * @param arr the arr the must be sorted
     * @param comparator the Comparator object used to compare data in the arr
     * @param rand the Random object used to select pivots
     * @param start the start index
     * @param end the end index
     * @param <T> data typr to sort
     */
    private static <T> void quickSortHelper(T[] arr, Comparator<T> comparator,
                                           Random rand, int start, int end) {
        if (end - start <= 1) {
            return;
        }
        int pivotIdx = rand.nextInt(end - start) + start;
        T pivotVal = arr[pivotIdx];
        swap(arr, start, pivotIdx);
        int leftInd = start + 1;
        int rightInd = end - 1;
        while (leftInd <= rightInd) {
            while (leftInd <= rightInd && comparator.compare(arr[leftInd], pivotVal) <= 0) {
                leftInd++;
            }
            while (leftInd <= rightInd && comparator.compare(arr[rightInd], pivotVal) >= 0) {
                rightInd--;
            }
            if (leftInd <= rightInd) {

                swap(arr, leftInd, rightInd);
                leftInd++;
                rightInd--;
            }
        }

        swap(arr, start, rightInd);
        quickSortHelper(arr, comparator, rand, start, rightInd);
        quickSortHelper(arr, comparator, rand, rightInd + 1, end);
    }
}
