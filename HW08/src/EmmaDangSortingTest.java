import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class EmmaDangSortingTest {
    private static final int TIME_OUT = 200;
    private ComparatorPlus<Element> nameComparator;
    private ComparatorPlus<Element> numberComparator;
    // arr is a random, unordered array. sorted the the sorted version of arr
    private Element[] arr;
    private Element[] sorted;
    // arr2 is a long array of 100 equal elements. sorted the sorted version of arr2
    private Element[] arr2;
    private Element[] sorted2;
    // arr3 and sorted3 are the same arrays where elements are sorted in the ascending order
    private Element[] arr3;
    private Element[] sorted3;
    // arr4 and sorted4 are the same arrays where elements are sorted in the descending order
    private Element[] arr4;
    private Element[] sorted4;

    @Before
    public void setUp() {
        nameComparator = Element.getNameComparator();
        numberComparator = Element.getNumberComparator();
        arr = new Element[10];
        arr[0] = new Element("A", 6);
        arr[1] = new Element("", 4);
        arr[2] = new Element("", 2);
        arr[3] = new Element("", 5);
        arr[4] = new Element("", 3);
        arr[5] = new Element("", 7);
        arr[6] = new Element("B", 6);
        arr[7] = new Element("", 8);
        arr[8] = new Element("", 9);
        arr[9] = new Element("", 10);

        sorted = new Element[10];
        sorted[0] = arr[2];
        sorted[1] = arr[4];
        sorted[2] = arr[1];
        sorted[3] = arr[3];
        sorted[4] = arr[0];
        sorted[5] = arr[6];
        sorted[6] = arr[5];
        sorted[7] = arr[7];
        sorted[8] = arr[8];
        sorted[9] = arr[9];

        arr2 = new Element[100];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = new Element("", 1);
        }
        sorted2 = new Element[100];
        for (int i = 0; i < sorted2.length; i++) {
            sorted2[i] = new Element("", 1);
        }

        arr3 = new Element[10];
        for (int i = 0; i < arr3.length; i++) {
            arr3[i] = new Element("", i);
        }
        sorted3 = new Element[10];
        for (int i = 0; i < sorted3.length; i++) {
            sorted3[i] = new Element("", i);
        }

        arr4 = new Element[10];
        for (int i = arr4.length - 1; i >= 0; i--) {
            arr4[i] = new Element("", i);
        }
        sorted4 = new Element[10];
        for (int i = 0; i < sorted4.length; i++) {
            sorted4[i] = new Element("", i);
        }
    }

    @Test(timeout = TIME_OUT, expected = IllegalArgumentException.class)
    public void testSelectionSortNullArray() {
        Sorting.selectionSort(null, nameComparator);
    }

    @Test(timeout = TIME_OUT, expected = IllegalArgumentException.class)
    public void testSelectionSortNullComparator() {
        Element[] arr = {};
        Sorting.selectionSort(arr, null);
    }

    @Test(timeout = TIME_OUT)
    public void testSelectionSortEmptyArray() {
        Element[] arr = {};
        Element[] sorted = {};
        Sorting.selectionSort(arr, nameComparator);
        assertArrayEquals(sorted, arr);
    }

    @Test(timeout = TIME_OUT)
    public void testSelectionSort1() {
        Element[] arr = {new Element("A", 1)};
        Element[] sorted = {new Element("A", 1)};
        Sorting.selectionSort(arr, nameComparator);
        assertArrayEquals(arr, sorted);
        assertTrue("Number of counts: " + nameComparator.getCount(),
                nameComparator.getCount() == 0);
    }

    @Test(timeout = TIME_OUT)
    public void testSelectionSort2() {
        // this tests assumes an implementation that only update maxIndex if 
        // the inspected element is strictly bigger than arr[maxIndex]
        Element temp = sorted[4];
        sorted[4] = sorted[5];
        sorted[5] = temp;
        Sorting.selectionSort(arr, numberComparator);
        assertArrayEquals(arr, sorted);
        assertTrue("Number of counts: " + numberComparator.getCount(),
                numberComparator.getCount() <= 45 && numberComparator.getCount() != 0);
    }

    @Test(timeout = TIME_OUT)
    public void testSelectionSort3() {
        Sorting.selectionSort(arr2, numberComparator);
        assertArrayEquals(sorted2, arr2);
        assertTrue("Number of counts: " + numberComparator.getCount(),
                numberComparator.getCount() <= 100 * 101 / 2 && numberComparator.getCount() != 0);
    }

    @Test(timeout = TIME_OUT)
    public void testSelectionSort4() {
        Sorting.selectionSort(arr3, numberComparator);
        assertArrayEquals(sorted3, arr3);
        assertTrue("Number of counts: " + numberComparator.getCount(),
                numberComparator.getCount() <= 45 && numberComparator.getCount() != 0);
    }

    @Test(timeout = TIME_OUT, expected = IllegalArgumentException.class)
    public void testCocktailSortNullComparator() {
        Element[] arr = {};
        Sorting.cocktailSort(arr, null);
    }

    @Test(timeout = TIME_OUT, expected = IllegalArgumentException.class)
    public void testCocktailSortNullArray() {
        Sorting.cocktailSort(null, nameComparator);
    }

    @Test(timeout = TIME_OUT)
    public void testCocktailSort1() {
        Element[] arr = {};
        Element[] sorted = {};
        Sorting.cocktailSort(arr, nameComparator);
        assertArrayEquals(sorted, arr);
        assertTrue("Number of counts: " + nameComparator.getCount(),
                nameComparator.getCount() == 0);
    }

    @Test(timeout = TIME_OUT)
    public void testCocktailSort2() {
        Element[] arr = {new Element("", 0)};
        Element[] sorted = {new Element("", 0)};
        Sorting.cocktailSort(arr, nameComparator);
        assertArrayEquals(sorted, arr);
        assertTrue("Number of counts: " + nameComparator.getCount(),
                nameComparator.getCount() == 0);
    }

    @Test(timeout = TIME_OUT)
    public void testCocktailSort3() {
        Sorting.cocktailSort(arr, numberComparator);
        assertArrayEquals(sorted, arr);
        assertTrue("Number of counts: " + numberComparator.getCount(),
                numberComparator.getCount() <= 18 && numberComparator.getCount() != 0);
    }

    @Test(timeout = TIME_OUT)
    public void testCocktailSort4() {
        Sorting.cocktailSort(arr2, numberComparator);
        assertArrayEquals(sorted2, arr2);
        assertTrue("Number of counts: " + numberComparator.getCount(),
                numberComparator.getCount() <= 99 && numberComparator.getCount() != 0);
    }

    @Test(timeout = TIME_OUT)
    public void testCocktailSort5() {
        Sorting.cocktailSort(arr3, numberComparator);
        assertArrayEquals(sorted3, arr3);
        assertTrue("Number of counts: " + numberComparator.getCount(),
                numberComparator.getCount() <= 9 && numberComparator.getCount() != 0);
    }

    @Test(timeout = TIME_OUT)
    public void testCocktailSor6() {
        Sorting.cocktailSort(arr4, numberComparator);
        assertArrayEquals(sorted4, arr4);
        assertTrue("Number of counts: " + numberComparator.getCount(),
                numberComparator.getCount() <= 45 && numberComparator.getCount() != 0);
    }

    @Test(timeout = TIME_OUT, expected = IllegalArgumentException.class)
    public void testMergeSortNullComparator() {
        Element[] arr = {};
        Sorting.mergeSort(arr, null);
    }

    @Test(timeout = TIME_OUT, expected = IllegalArgumentException.class)
    public void testMergeSortNullArray() {
        Sorting.mergeSort(null, nameComparator);
    }

    @Test(timeout = TIME_OUT)
    public void testMergeSort1() {
        Element[] arr = {};
        Element[] sorted = {};
        Sorting.mergeSort(arr, nameComparator);
        assertArrayEquals(arr, sorted);
        assertTrue("Number of counts: " + nameComparator.getCount(),
                nameComparator.getCount() == 0);
    }

    @Test(timeout = TIME_OUT)
    public void testMergeSort2() {
        Element[] arr = {new Element("", 0)};
        Element[] sorted = {new Element("", 0)};
        Sorting.mergeSort(arr, nameComparator);
        assertArrayEquals(sorted, arr);
        assertTrue("Number of counts: " + nameComparator.getCount(),
                nameComparator.getCount() == 0);
    }

    @Test(timeout = TIME_OUT)
    public void testMergeSort3() {
        Sorting.mergeSort(arr, numberComparator);
        assertArrayEquals(sorted, arr);
        assertTrue("Number of counts: " + numberComparator.getCount(),
                numberComparator.getCount() <= 17 && numberComparator.getCount() != 0);
    }

    @Test(timeout = TIME_OUT)
    public void testMergeSort4() {
        Sorting.mergeSort(arr2, numberComparator);
        assertArrayEquals(sorted2, arr2);
        assertTrue("Number of counts: " + numberComparator.getCount(),
                numberComparator.getCount() < 100 * Math.log(100) && numberComparator.getCount() != 0);
    }

    @Test(timeout = TIME_OUT)
    public void testMergeSort5() {
        Sorting.mergeSort(arr3, numberComparator);
        assertArrayEquals(sorted3, arr3);
        assertTrue("Number of counts: " + numberComparator.getCount(),
                numberComparator.getCount() <= 15 && numberComparator.getCount() != 0);
    }

    @Test(timeout = TIME_OUT)
    public void testMergeSort6() {
        Sorting.mergeSort(arr4, numberComparator);
        assertArrayEquals(sorted4, arr4);
        assertTrue("Number of counts: " + numberComparator.getCount(),
                numberComparator.getCount() <= 19 && numberComparator.getCount() != 0);
    }

    @Test(timeout = TIME_OUT, expected = IllegalArgumentException.class)
    public void testQuickSortNullComparator() {
        Element[] arr = {};
        Sorting.quickSort(arr, null, new Random());
    }

    @Test(timeout = TIME_OUT, expected = IllegalArgumentException.class)
    public void testQuickSortNullArray() {
        Sorting.quickSort(null, nameComparator, new Random());
    }

    @Test(timeout = TIME_OUT, expected = IllegalArgumentException.class)
    public void testQuickSortNullRandom() {
        Element[] arr = {};
        Sorting.quickSort(arr, nameComparator, null);
    }

    @Test(timeout = TIME_OUT)
    public void testQuickSort1() {
        Element[] arr = {};
        Element[] sorted = {};
        Sorting.quickSort(arr, nameComparator, new Random());
        assertArrayEquals(sorted, arr);
        assertTrue("Number of counts: " + nameComparator.getCount(),
                nameComparator.getCount() == 0);
    }

    @Test(timeout = TIME_OUT)
    public void testQuickSort2() {
        Element[] arr = {new Element("", 0)};
        Element[] sorted = {new Element("", 0)};
        Sorting.quickSort(arr, nameComparator, new Random());
        assertArrayEquals(sorted, arr);
        assertTrue("Number of counts: " + nameComparator.getCount(),
                nameComparator.getCount() == 0);
    }

    @Test(timeout = TIME_OUT)
    public void testQuickSort3() {
        Sorting.quickSort(arr, numberComparator, new Random(10));
        for (int i = 0; i < arr.length; i++) {
            if (i != 4 && i != 5) {
                assertEquals(sorted[i], arr[i]);
            } else {
                assertEquals(sorted[i].getNumber(), arr[i].getNumber());
            }
        }
        assertTrue("Number of comparisons: " + numberComparator.getCount(),
                numberComparator.getCount() <= 23 && numberComparator.getCount() != 0);
    }

    @Test(timeout = TIME_OUT)
    public void testQuickSort4() {
        Sorting.quickSort(arr2, numberComparator, new Random());
        assertArrayEquals(sorted2, arr2);
        assertTrue("Number of comparisons: " + numberComparator.getCount(),
                numberComparator.getCount() <= 99 * 100 / 2 && numberComparator.getCount() != 0);
    }

    @Test(timeout = TIME_OUT)
    public void testQuickSort5() {
        Sorting.quickSort(arr3, numberComparator, new BadRandom());
        assertArrayEquals(sorted3, arr3);
        assertTrue("Number of comparisons: " + numberComparator.getCount(),
                numberComparator.getCount() == 45);
    }

    @Test(timeout = TIME_OUT)
    public void testQuickSort6() {
        Sorting.quickSort(arr4, numberComparator, new BadRandom());
        assertArrayEquals(sorted4, arr4);
        assertTrue("Number of comparisons: " + numberComparator.getCount(),
                numberComparator.getCount() == 45);
    }

    @Test(timeout = TIME_OUT, expected = IllegalArgumentException.class)
    public void testRadixSortNullArray() {
        Sorting.lsdRadixSort(null);
    }

    @Test(timeout = TIME_OUT)
    public void testRadixSort1() {
        int[] arr = {};
        int[] sorted = {};
        Sorting.lsdRadixSort(arr);
        assertArrayEquals(sorted, arr);
    }

    @Test(timeout = TIME_OUT)
    public void testRadixSort2() {
        int[] arr = {151, 327, 413, 58, 73, 136, 542, 210, 113, 4, 151};
        int[] sorted = {4, 58, 73, 113, 136, 151, 151, 210, 327, 413, 542};
        Sorting.lsdRadixSort(arr);
        assertArrayEquals(sorted, arr);
    }

    @Test(timeout = TIME_OUT)
    public void testRadixSort3() {
        int[] arr = {12345678, 734893783, 12324890};
        int[] sorted = {12324890, 12345678, 734893783};
        Sorting.lsdRadixSort(arr);
        assertArrayEquals(sorted, arr);
    }

    @Test(timeout = TIME_OUT, expected = IllegalArgumentException.class)
    public void testHeapSortNullArray() {
        Sorting.heapSort(null);
    }

    @Test(timeout = TIME_OUT)
    public void testHeapSort1() {
        List<Integer> data = new ArrayList<>();
        data.add(31); //
        data.add(37); //
        data.add(14); //
        data.add(35); //
        data.add(70); //
        data.add(64); //
        data.add(19); //
        data.add(26); //
        data.add(42); //
        data.add(93); //
        data.add(57); //
        int[] expected = {14, 19, 26, 31, 35, 37, 42, 57, 64, 70, 93};
        int[] actual;
        actual = Sorting.heapSort(data);
        assertArrayEquals(expected, actual);
    }

    @Test(timeout = TIME_OUT)
    public void testHeapSort2() {
        List<Integer> data = new ArrayList<>();
        Random rand = new Random();
        for (int i = 0; i < 1000; i++) {
            data.add(rand.nextInt(1000));
        }
        int[] actual = Sorting.heapSort(data);
        assertTrue(isSorted(actual));
    }

    /**
     * private helper method to check if the array is sorted in ascending order.
     * @param arr - an array of integer.
     * @return - a boolean to indicate whether the array is sorted.
     */
    private boolean isSorted(int[] arr) {
        boolean sorted = true;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                sorted = false;
            }
        }
        return sorted;
    }

    /**
     * Class for a bad Random object.
     * nextInt(int bound) will always return bound - 1.
     */
    private static class BadRandom extends Random {
        @Override
        public int nextInt(int bound) {
            return bound - 1;
        }
    }

    /**
     * Class for testing proper sorting.
     */
    private static class Element {
        private String name;
        private int number;

        /**
         * Create a teaching assistant.
         * @param number number of the element.
         * @param name name of TA.
         */
        public Element(String name, int number) {
            this.name = name;
            this.number = number;
        }

        /**
         * Get the name of the element.
         *
         * @return name of the element
         */
        public String getName() {
            return name;
        }

        /**
         * Get the number of the element.
         * @return number of the element.
         */
        public int getNumber() {
            return number;
        }

        /**
         * Set the name of the element.
         *
         * @param name name of the element.
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * Set the number of the element.
         *
         * @param number number of the element.
         */
        public void setNumber(int number) {
            this.number = number;
        }

        /**
         * Create a comparator that compares the names of the element.
         *
         * @return comparator that compares the names of the element.
         */
        public static ComparatorPlus<Element> getNameComparator() {
            return new ComparatorPlus<Element>() {
                @Override
                public int compare(Element e1,
                                   Element e2) {
                    incrementCount();
                    return e1.getName().compareTo(e2.getName());
                }
            };
        }

        /**
         * Create a comparator that compares the number of the element.
         *
         * @return comparator that compares the number of the element.
         */
        public static ComparatorPlus<Element> getNumberComparator() {
            return new ComparatorPlus<Element>() {
                @Override
                public int compare(Element e1,
                                   Element e2) {
                    incrementCount();
                    return e1.getNumber() - e2.getNumber();
                }
            };
        }


        @Override
        public String toString() {
            return "Name: " + name + "; Number: " + number;
        }

        @Override
        public boolean equals(Object other) {
            if (other == null) {
                return false;
            }
            if (this == other) {
                return true;
            }
            return other instanceof Element
                    && ((Element) other).name.equals(this.name)
                    && ((Element) other).number == this.number;
        }
    }

    /**
     * Inner class that allows counting how many comparisons were made.
     */
    private abstract static class ComparatorPlus<T> implements Comparator<T> {
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
    }
}
