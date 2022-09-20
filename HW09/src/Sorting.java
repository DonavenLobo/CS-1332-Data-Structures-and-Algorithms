import java.util.Comparator;
import java.util.LinkedList;
import java.util.Random;

/**
 * Your implementation of various sorting algorithms.
 *
 * @author Alan Matias
 * @userid amatias3
 * @GTID 903410895
 * @version 1.0
 */
public class Sorting {

    /**
     * Implement insertion sort.
     *
     * It should be:
     *  in-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n)
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void insertionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Cannot sort null "
                    + "arr or comparator");
        }

        //for loop to go through each element
        for (int i = 1; i < arr.length; i++) {
            int j = i;

            //take the next (unsorted) element and find its place
            while (j > 0
                    && comparator.compare(arr[j], arr[j - 1]) < 0) {
                T tmp = arr[j - 1];
                arr[j - 1] = arr[j];
                arr[j] = tmp;
                j--;
            }
        }


    }

    /**
     * Implement selection sort.
     *
     * It should be:
     *  in-place
     *  unstable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n^2)
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void selectionSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Cannot sort null "
                    + "arr or comparator");
        }
        int length = arr.length;

        //take next minimum and put it in its place
        for (int i = 0; i < length; i++) {
            int minIdx = i;

            for (int j = (i + 1); j < arr.length; j++) {
                if (comparator.compare(arr[j], arr[minIdx]) < 0) {
                    minIdx = j;
                }
            }
            //minIndex found, swap with0 index i
            T tmp = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = tmp;
        }


    }

    /**
     * Implement merge sort.
     *
     * It should be:
     *  out-of-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(n log n)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * You can create more arrays to run mergesort, but at the end, everything
     * should be merged back into the original T[] which was passed in.
     *
     * When splitting the array, if there is an odd number of elements, put the
     * extra data on the right side.
     *
     * @throws IllegalArgumentException if the array or comparator is null
     * @param <T> data type to sort
     * @param arr the array to be sorted
     * @param comparator the Comparator used to compare the data in arr
     */
    public static <T> void mergeSort(T[] arr, Comparator<T> comparator) {
        if (arr == null || comparator == null) {
            throw new IllegalArgumentException("Cannot sort null "
                    + "arr or comparator");
        }
        //base case
        if (arr.length <= 1) {
            return;
        }


        int length = arr.length;
        int midIndex = length / 2;
        T[] leftArray = (T[]) new Object[midIndex];
        T[] rightArray = (T[]) new Object[length - midIndex];

        //populate arrays
        for (int i = 0; i < midIndex; i++) {
            leftArray[i] = arr[i];
        }

        int idx = 0;
        for (int i = midIndex; i < arr.length; i++) {
            rightArray[idx] = arr[i];
            idx++;
        }

        mergeSort(leftArray, comparator);
        mergeSort(rightArray, comparator);

        int leftIndex = 0;
        int rightIndex = 0;
        int currIndex = 0;

        //implementation from saikrishna slides
        while (leftIndex < midIndex
            && rightIndex < (length - midIndex)) {
            if (comparator.compare(
                    leftArray[leftIndex], rightArray[rightIndex]) <= 0) {
                arr[currIndex] = leftArray[leftIndex];
                leftIndex++;
            } else {
                arr[currIndex] = rightArray[rightIndex];
                rightIndex++;
            }
            currIndex++;
        }

        while (leftIndex < midIndex) {
            arr[currIndex] = leftArray[leftIndex];
            leftIndex++;
            currIndex++;
        }

        while (rightIndex < (length - midIndex)) {
            arr[currIndex] = rightArray[rightIndex];
            rightIndex++;
            currIndex++;
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
     *  in-place
     *  unstable
     *
     * Have a worst case running time of:
     *  O(n^2)
     *
     * And a best case running time of:
     *  O(n log n)
     *
     * Make sure you code the algorithm as you have been taught it in class.
     * There are several versions of this algorithm and you may not receive
     * credit if you do not use the one we have taught you!
     *
     * @throws IllegalArgumentException if the array or comparator or rand is
     * null
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     */
    public static <T> void quickSort(T[] arr, Comparator<T> comparator,
                                     Random rand) {
        if (arr == null || comparator == null || rand == null) {
            throw new IllegalArgumentException("Cannot sort null "
                    + "arr or comparator");
        }

        qsRecurse(arr, comparator, rand, 0, arr.length);
    }

    /**
     * recursive helper method for quickSort using left and right
     * markers for greater or less items
     *
     * @param <T> data type to sort
     * @param arr the array that must be sorted after the method runs
     * @param comparator the Comparator used to compare the data in arr
     * @param rand the Random object used to select pivots
     * @param left marker for items greater than arr[rand]
     * @param right marker for items less than arr[rand]
     */
    private static <T> void qsRecurse(T[] arr, Comparator<T> comparator,
                                     Random rand, int left, int right) {
        //when array is only 1 length
        if (right - left < 2) {
            return;
        }

        int pivotIndex = rand.nextInt(right - left) + left;
        T pivot = arr[pivotIndex];
        arr[pivotIndex] = arr[left];
        arr[left] = pivot;

        int leftIndex = left + 1;
        int rightIndex = right - 1;

        while (leftIndex <= rightIndex) {

            //increment leftIndex until greater than pivot or indexes cross
            while (leftIndex <= rightIndex
                    && comparator.compare(arr[leftIndex], pivot) <= 0) {
                leftIndex++;
            }

            //decrement rightIndex until less than pivot or indexes cross
            while (leftIndex <= rightIndex
                    && comparator.compare(arr[rightIndex], pivot) >= 0) {
                rightIndex--;
            }

            // swap items so items to the right of pivot are greater
            // and items to the left of the pivot are less
            if (leftIndex <= rightIndex) {
                T tmp = arr[leftIndex];
                arr[leftIndex] = arr[rightIndex];
                arr[rightIndex] = tmp;

                leftIndex++;
                rightIndex--;
            }
        }
        //swap pivot with right marker
        arr[left] = arr[rightIndex];
        arr[rightIndex] = pivot;


        qsRecurse(arr, comparator, rand, left, rightIndex);
        qsRecurse(arr, comparator, rand, (rightIndex + 1), right);




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
     *  out-of-place
     *  stable
     *
     * Have a worst case running time of:
     *  O(kn)
     *
     * And a best case running time of:
     *  O(kn)
     *
     * You are allowed to make an initial O(n) passthrough of the array to
     * determine the number of iterations you need.
     *
     * Refer to the PDF for more information on LSD Radix Sort.
     *
     * You may use {@code java.util.ArrayList} or {@code java.util.LinkedList}
     * if you wish, but it may only be used inside radix sort and any radix sort
     * helpers. Do NOT use these classes with other sorts.
     *
     * Do NOT use anything from the Math class except Math.abs().
     *
     * @throws IllegalArgumentException if the array is null
     * @param arr the array to be sorted
     */
    public static void lsdRadixSort(int[] arr) {
        if (arr == null) {
            throw new IllegalArgumentException("Array is null");
        }
        if (arr.length <= 1) {
            return;
        }

        //buckets for all negative/positive numbers
        LinkedList<Integer>[] buckets = new LinkedList[19];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }

        int div = 1;
        boolean cont = true;

        while (cont) {
            cont = false;
            for (int num: arr) {
                int bucket = num / div;
                if (bucket / 10 != 0) {
                    cont = true;
                }
                buckets[bucket % 10 + 9].add(num);
            }
            int arrIdx = 0;
            for (int i = 0; i < buckets.length; i++) {
                if (buckets[i] != null) {
                    for (int num: buckets[i]) {
                        arr[arrIdx] = num;
                        arrIdx++;
                    }
                }
                buckets[i].clear();
            }
            div *= 10;
        }
    }
}