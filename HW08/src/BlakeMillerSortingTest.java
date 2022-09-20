import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

/**
 * Based off TA JUnits, may be some duplicated/adapted code
 *
 * @author Blake Miller
 * @version 1.0
 */
public class BlakeMillerSortingTest {

    private static final int TIMEOUT = 200;
    private Integer[] unsorted;
    private Integer[] sorted;

    private String[] preSorted;
    private String[] sortedPreSorted;

    private Integer[] revSorted;
    private Integer[] sortedRevSorted;

    private String[] stableUnsorted;
    private String[] stableSorted;

    private String a1;
    private String b1;
    private String a2;
    private String b2;
    private String a6;
    private String b6;

    private ComparatorPlus<Integer> comp;
    private ComparatorPlus<String> compStr;

    @Before
    public void setUp() {
        // "Regular" array
        unsorted = new Integer[9];
        unsorted[0] = 22;
        unsorted[1] = 10;
        unsorted[2] = 19;
        unsorted[3] = 11;
        unsorted[4] = 15;
        unsorted[5] = 6;
        unsorted[6] = 7;
        unsorted[7] = 8;
        unsorted[8] = 2;

        sorted = new Integer[9];
        sorted[0] = 2;
        sorted[1] = 6;
        sorted[2] = 7;
        sorted[3] = 8;
        sorted[4] = 10;
        sorted[5] = 11;
        sorted[6] = 15;
        sorted[7] = 19;
        sorted[8] = 22;

        // Pre-sorted array
        preSorted = new String[12];
        preSorted[0] = "1";
        preSorted[1] = "2";
        preSorted[2] = "3";
        preSorted[3] = "4";
        preSorted[4] = "5";
        preSorted[5] = "6";
        preSorted[6] = "7";
        preSorted[7] = "8";
        preSorted[8] = "9";
        preSorted[9] = "a";
        preSorted[10] = "b";
        preSorted[11] = "c";

        sortedPreSorted = new String[12];
        for (int i = 0; i < preSorted.length; i++) {
            sortedPreSorted[i] = preSorted[i];
        }

        // Array sorted in reverse
        revSorted = new Integer[8];
        revSorted[0] = 7;
        revSorted[1] = 6;
        revSorted[2] = 5;
        revSorted[3] = 4;
        revSorted[4] = 3;
        revSorted[5] = 2;
        revSorted[6] = 1;
        revSorted[7] = 0;

        sortedRevSorted = new Integer[8];
        sortedRevSorted[0] = 0;
        sortedRevSorted[1] = 1;
        sortedRevSorted[2] = 2;
        sortedRevSorted[3] = 3;
        sortedRevSorted[4] = 4;
        sortedRevSorted[5] = 5;
        sortedRevSorted[6] = 6;
        sortedRevSorted[7] = 7;

        // Pre-sorted array
        stableUnsorted = new String[6];

        a1 = new String("1");
        b1 = new String("1");
        a2 = new String("2");
        b2 = new String("2");
        a6 = new String("6");
        b6 = new String("6");

        stableUnsorted[0] = a6;
        stableUnsorted[1] = b6;
        stableUnsorted[2] = a1;
        stableUnsorted[3] = b1;
        stableUnsorted[4] = a2;
        stableUnsorted[5] = b2;

        stableSorted = new String[6];
        stableSorted[0] = a1;
        stableSorted[1] = b1;
        stableSorted[2] = a2;
        stableSorted[3] = b2;
        stableSorted[4] = a6;
        stableSorted[5] = b6;



        // Initialize the comparator
        comp = new ComparatorPlus<Integer>();
        compStr = new ComparatorPlus<String>();
    }

    @Test(timeout = TIMEOUT)
    public void testSelectionSort() {
        Sorting.selectionSort(unsorted, comp);
        assertArrayEquals(sorted, unsorted);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() == 36 && comp.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testSelectionSort2() {
        Sorting.selectionSort(preSorted, compStr);
        assertArrayEquals(sortedPreSorted, preSorted);
        assertTrue("Number of comparisons: " + compStr.getCount(),
                compStr.getCount() == 66 && compStr.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testSelectionSort3() {
        Sorting.selectionSort(revSorted, comp);
        assertArrayEquals(sortedRevSorted, revSorted);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() == 28 && comp.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testSelectionSort4() {
        Sorting.selectionSort(stableUnsorted, compStr);
        assertArrayEquals(stableSorted, stableUnsorted);

        assertTrue("Number of comparisons: " + compStr.getCount(),
                compStr.getCount() == 15 && compStr.getCount() != 0);
    }


    @Test(timeout = TIMEOUT)
    public void testCocktailSort() {
        Sorting.cocktailSort(unsorted, comp);
        assertArrayEquals(sorted, unsorted);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() == 36 && comp.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testCocktailSort2() {
        Sorting.cocktailSort(preSorted, compStr);
        assertArrayEquals(sortedPreSorted, preSorted);
        assertTrue("Number of comparisons: " + compStr.getCount(),
                compStr.getCount() == 11 && compStr.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testCocktailSort3() {
        Sorting.cocktailSort(revSorted, comp);
        assertArrayEquals(sortedRevSorted, revSorted);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() == 28 && comp.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testCocktailSort4() {
        Sorting.cocktailSort(stableUnsorted, compStr);
        assertArrayEquals(stableSorted, stableUnsorted);

        for (int i = 0; i < stableSorted.length; i++) {
            assertSame(stableSorted[i], stableUnsorted[i]);
        }

        assertTrue("Number of comparisons: " + compStr.getCount(),
                compStr.getCount() == 14 && compStr.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSort() {
        Sorting.mergeSort(unsorted, comp);
        assertArrayEquals(sorted, unsorted);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() == 20 && comp.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSort2() {
        Sorting.mergeSort(preSorted, compStr);
        assertArrayEquals(sortedPreSorted, preSorted);
        assertTrue("Number of comparisons: " + compStr.getCount(),
                compStr.getCount() == 20 && compStr.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSort3() {
        Sorting.mergeSort(revSorted, comp);
        assertArrayEquals(sortedRevSorted, revSorted);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() == 12 && comp.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testMergeSort4() {
        Sorting.mergeSort(stableUnsorted, compStr);
        assertArrayEquals(stableSorted, stableUnsorted);

        for (int i = 0; i < stableSorted.length; i++) {
            assertSame(stableSorted[i], stableUnsorted[i]);
        }

        assertTrue("Number of comparisons: " + compStr.getCount(),
                compStr.getCount() == 9 && compStr.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testQuickSort() {
        Sorting.quickSort(unsorted, comp, new ChooseFirstElement());
        assertArrayEquals(sorted, unsorted);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 29 && comp.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testQuickSort2() {
        Sorting.quickSort(preSorted, compStr, new ChooseLastElement());
        assertArrayEquals(sortedPreSorted, preSorted);
        assertTrue("Number of comparisons: " + compStr.getCount(),
                compStr.getCount() == 66 && compStr.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testQuickSort3() {
        Sorting.quickSort(revSorted, comp, new ChooseFirstElement());
        assertArrayEquals(sortedRevSorted, revSorted);
        assertTrue("Number of comparisons: " + comp.getCount(),
                comp.getCount() <= 31 && comp.getCount() != 0);
    }

    @Test(timeout = TIMEOUT)
    public void testQuickSort4() {
        Sorting.quickSort(stableUnsorted, compStr, new ChooseFirstElement());
        assertArrayEquals(stableSorted, stableUnsorted);
        assertTrue("Number of comparisons: " + compStr.getCount(),
                compStr.getCount() == 11 && compStr.getCount() != 0);
    }


    @Test(timeout = TIMEOUT)
    public void testLsdRadixSort() {
        int[] unsortedArr = new int[unsorted.length];
        int[] sortedArr = new int[sorted.length];
        for (int i = 0; i < unsorted.length; i++) {
            unsortedArr[i] = Integer.valueOf(unsorted[i]);
            sortedArr[i] = Integer.valueOf(sorted[i]);
        }

        Sorting.lsdRadixSort(unsortedArr);
        assertArrayEquals(sortedArr, unsortedArr);
    }

    @Test(timeout = TIMEOUT)
    public void testLsdRadixSort2() {
        int[] unsortedArr = new int[revSorted.length];
        int[] sortedArr = new int[sortedRevSorted.length];
        for (int i = 0; i < revSorted.length; i++) {
            unsortedArr[i] = Integer.valueOf(revSorted[i]);
            sortedArr[i] = Integer.valueOf(sortedRevSorted[i]);
        }

        Sorting.lsdRadixSort(unsortedArr);
        assertArrayEquals(sortedArr, unsortedArr);
    }

    @Test(timeout = TIMEOUT)
    public void testLsdRadixSort3() {
        int[] unsorted = {-10, 100, -5000, 2903, -1, 2, 3, -40};
        int[] sorted = {-5000, -40, -10, -1, 2, 3, 100, 2903};

        Sorting.lsdRadixSort(unsorted);
        assertArrayEquals(sorted, unsorted);
    }

    @Test(timeout = TIMEOUT)
    public void testLsdRadixSort4() {
        int[] unsorted = {-Integer.MIN_VALUE, Integer.MAX_VALUE, 2, 5, -100, 40, -1000, 14};
        int[] sorted = {-Integer.MIN_VALUE, -1000, -100, 2, 5, 14, 40, Integer.MAX_VALUE};

        Sorting.lsdRadixSort(unsorted);
        assertArrayEquals(sorted, unsorted);
    }

    @Test(timeout = TIMEOUT)
    public void testHeapSort() {
        ArrayList<Integer> unsortedList = new ArrayList<>();
        int[] sortedArray = new int[sorted.length];

        for (int i = 0; i < unsorted.length; i++) {
            unsortedList.add(unsorted[i]);
            sortedArray[i] = Integer.valueOf(sorted[i]);
        }
        assertEquals(unsortedList.size(), 9);
        assertEquals(sorted.length, 9);

        int[] unsortedArray = Sorting.heapSort(unsortedList);
        assertArrayEquals(sortedArray, unsortedArray);
    }

    @Test(timeout = TIMEOUT)
    public void testHeapSort2() {
        ArrayList<Integer> unsortedList = new ArrayList<>();
        int[] sortedArray = new int[sortedRevSorted.length];

        for (int i = 0; i < revSorted.length; i++) {
            unsortedList.add(revSorted[i]);
            sortedArray[i] = Integer.valueOf(sortedRevSorted[i]);
        }

        int[] unsortedArray = Sorting.heapSort(unsortedList);
        assertArrayEquals(sortedArray, unsortedArray);
    }

    @Test(timeout = TIMEOUT)
    public void testHeapSort3() {
        int[] unsorted = {-10, 100, -5000, 2903, -1, 2, 3, -40};
        int[] sorted = {-5000, -40, -10, -1, 2, 3, 100, 2903};

        ArrayList<Integer> unsortedList = new ArrayList<>();

        for (int i = 0; i < unsorted.length; i++) {
            unsortedList.add(unsorted[i]);
        }

        unsorted = Sorting.heapSort(unsortedList);
        assertArrayEquals(sorted, unsorted);
    }

    @Test(timeout = TIMEOUT)
    public void testHeapSort4() {
        int[] unsorted = {-Integer.MIN_VALUE, Integer.MAX_VALUE, 2, 5, -100, 40, -1000, 14};
        int[] sorted = {-Integer.MIN_VALUE, -1000, -100, 2, 5, 14, 40, Integer.MAX_VALUE};

        ArrayList<Integer> unsortedList = new ArrayList<>();

        for (int i = 0; i < unsorted.length; i++) {
            unsortedList.add(unsorted[i]);
        }

        unsorted = Sorting.heapSort(unsortedList);
        assertArrayEquals(sorted, unsorted);
    }

    /**
     * Inner class that allows counting how many comparisons were made.
     */
    private class ComparatorPlus<T extends Comparable> implements Comparator<T> {
        private int count;

        /**
         * Get the number of comparisons made.
         *
         * @return number of comparisons made
         */
        public int getCount() {
            return count;
        }

        /**
         * Increment the number of comparisons made by one. Call this method in
         * your compare() implementation.
         */
        public void incrementCount() {
            count++;
        }

        @Override
        public int compare(T o1, T o2) {
            incrementCount();
            System.out.println("Compared: " + o1 + " and " + o2);
            return o1.compareTo(o2);
        }
    }

    private class ChooseFirstElement extends Random {
        @Override
        public int nextInt(int val) {
            return 0;
        }
    }

    private class ChooseLastElement extends Random {
        @Override
        public int nextInt(int val) {
            return val - 1;
        }
    }
}
