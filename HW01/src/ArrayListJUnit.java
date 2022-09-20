import org.junit.Before;
import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


/**
 * ArrayList HW JUnit test by Mihir Kasmalkar.
 *
 * @author Mihir Kasmalkar
 * @version 1.0
 */
public class ArrayListJUnit {
    private static final int TIMEOUT = 200;
    private ArrayList<Integer> arrInts;
    private ArrayList<String> arrStrs;

    @Before
    public void setUp() {
        arrInts = new ArrayList<>();
        arrStrs = new ArrayList<>();
    }

    @Test(timeout = TIMEOUT)
    public void testConstructor() {
        assertEquals(0, arrInts.size());
        assertArrayEquals(arrInts.getBackingArray(), new Integer[ArrayList.INITIAL_CAPACITY]);

        assertEquals(0, arrStrs.size());
        assertArrayEquals(arrStrs.getBackingArray(), new String[ArrayList.INITIAL_CAPACITY]);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testAddAtIndexTooLowInts1() {
        System.out.println("testAddAtIndexTooLowInts1");
        arrInts.addAtIndex(-1, 4);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testAddAtIndexTooLowInts2() {
        System.out.println("testAddAtIndexTooLowInts2");
        arrInts.addAtIndex(-10, -100);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testAddAtIndexTooLowStrs1() {
        System.out.println("testAddAtIndexTooLowStrs1");
        arrStrs.addAtIndex(-1000, "test");
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testAddAtIndexTooLowStrs2() {
        System.out.println("testAddAtIndexTooLowStrs2");
        arrStrs.addAtIndex(-2, "hello");
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testAddAtIndexTooHighInts1() {
        System.out.println("testAddAtIndexTooHighInts1");
        arrInts.addAtIndex(1, 200);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testAddAtIndexTooHighInts2() {
        System.out.println("testAddAtIndexTooHighInts2");
        arrInts.addAtIndex(15, 197);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testAddAtIndexTooHighInts3() {
        System.out.println("testAddAtIndexTooHighInts3");
        arrInts.addToBack(30);
        arrInts.addToBack(1);
        arrInts.addToBack(33);
        arrInts.addToBack(-400);

        arrInts.addAtIndex(5, 140);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testAddAtIndexTooHighInts4() {
        System.out.println("testAddAtIndexTooHighInts4");
        arrInts.addToBack(1);
        arrInts.addToBack(-100);
        arrInts.addToBack(22);
        arrInts.addToBack(-101);

        arrInts.addAtIndex(100, 0);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testAddAtIndexTooHighInts5() {
        System.out.println("testAddAtIndexTooHighInts5");
        arrInts.addToBack(1234);
        arrInts.addToBack(5678);
        arrInts.addToBack(30);
        arrInts.addToBack(202);
        arrInts.removeFromBack();
        arrInts.removeAtIndex(1);

        arrInts.addAtIndex(3, 5);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testAddAtIndexTooHighInts6() {
        System.out.println("testAddAtIndexTooHighInts6");
        arrInts.addToBack(30);
        arrInts.addToBack(3);
        arrInts.addToBack(1234567);
        arrInts.addToBack(-100);
        arrInts.removeFromBack();
        arrInts.removeAtIndex(1);

        arrInts.addAtIndex(20, 114);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testAddAtIndexTooHighStrs1() {
        System.out.println("testAddAtIndexTooHighStrs1");
        arrStrs.addAtIndex(1, "qwerty");
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testAddAtIndexTooHighStrs2() {
        System.out.println("testAddAtIndexTooHighStrs2");
        arrStrs.addAtIndex(15, "asdfgh");
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testAddAtIndexTooHighStrs3() {
        System.out.println("testAddAtIndexTooHighStrs3");
        arrStrs.addToBack("zxcvbn");
        arrStrs.addToBack("ghjkl");
        arrStrs.addToBack("asdfgh");
        arrStrs.addToBack("qwerty");

        arrStrs.addAtIndex(5, "yuiop");
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testAddAtIndexTooHighStrs4() {
        System.out.println("testAddAtIndexTooHighStrs4");
        arrStrs.addToBack("tyuio");
        arrStrs.addToBack("fghjk");
        arrStrs.addToBack("zxcvbnm");
        arrStrs.addToBack("asdfghh");

        arrStrs.addAtIndex(100, "qetyuo[");
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testAddAtIndexTooHighStrs5() {
        System.out.println("testAddAtIndexTooHighStrs5");
        arrStrs.addToBack("30");
        arrStrs.addToBack("1");
        arrStrs.addToBack("33");
        arrStrs.addToBack("400");
        arrStrs.removeFromBack();
        arrStrs.removeAtIndex(1);

        arrStrs.addAtIndex(3, "30");
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testAddAtIndexTooHighStrs6() {
        System.out.println("testAddAtIndexTooHighStrs6");
        arrStrs.addToBack("30");
        arrStrs.addToBack("1");
        arrStrs.addToBack("33");
        arrStrs.addToBack("400");
        arrStrs.removeFromBack();
        arrStrs.removeAtIndex(1);

        arrStrs.addAtIndex(20, "30");
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddAtIndexBadDataInt() {
        System.out.println("testAddAtIndexBadDataInt");
        arrInts.addAtIndex(0, null);
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddAtIndexBadDataStr() {
        System.out.println("testAddAtIndexBadDataStr");
        arrStrs.addAtIndex(0, null);
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndex1() {
        arrInts.addAtIndex(0, 4);
        Integer[] expected1 = new Integer[ArrayList.INITIAL_CAPACITY];
        expected1[0] = 4;
        assertArrayEquals(expected1, arrInts.getBackingArray());
        assertEquals(1, arrInts.size());

        arrStrs.addAtIndex(0, "test");
        String[] expected2 = new String[ArrayList.INITIAL_CAPACITY];
        expected2[0] = "test";
        assertArrayEquals(expected2, arrStrs.getBackingArray());
        assertEquals(1, arrStrs.size());
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndex2a() {
        arrInts.addToBack(1);
        arrInts.addToBack(-10);
        arrInts.addToBack(0);
        arrInts.addToBack(33);

        arrInts.addAtIndex(0, 46);

        Integer[] expected = new Integer[ArrayList.INITIAL_CAPACITY];
        expected[0] = 46;
        expected[1] = 1;
        expected[2] = -10;
        expected[3] = 0;
        expected[4] = 33;

        assertArrayEquals(expected, arrInts.getBackingArray());
        assertEquals(5, arrInts.size());

        arrStrs.addToBack("1");
        arrStrs.addToBack("-10");
        arrStrs.addToBack("0");
        arrStrs.addToBack("33");

        arrStrs.addAtIndex(0, "46");

        String[] expected2 = new String[ArrayList.INITIAL_CAPACITY];
        expected2[0] = "46";
        expected2[1] = "1";
        expected2[2] = "-10";
        expected2[3] = "0";
        expected2[4] = "33";

        assertArrayEquals(expected2, arrStrs.getBackingArray());
        assertEquals(5, arrStrs.size());
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndex2b() {
        arrInts.addToBack(1);
        arrInts.addToBack(-10);
        arrInts.addToBack(0);
        arrInts.addToBack(33);

        arrInts.addAtIndex(2, 46);

        Integer[] expected = new Integer[ArrayList.INITIAL_CAPACITY];
        expected[0] = 1;
        expected[1] = -10;
        expected[2] = 46;
        expected[3] = 0;
        expected[4] = 33;

        assertArrayEquals(expected, arrInts.getBackingArray());
        assertEquals(5, arrInts.size());

        arrStrs.addToBack("1");
        arrStrs.addToBack("-10");
        arrStrs.addToBack("0");
        arrStrs.addToBack("33");

        arrStrs.addAtIndex(1, "46");

        String[] expected2 = new String[ArrayList.INITIAL_CAPACITY];
        expected2[0] = "1";
        expected2[1] = "46";
        expected2[2] = "-10";
        expected2[3] = "0";
        expected2[4] = "33";

        assertArrayEquals(expected2, arrStrs.getBackingArray());
        assertEquals(5, arrStrs.size());
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndex2c() {
        arrInts.addToBack(1);
        arrInts.addToBack(-10);
        arrInts.addToBack(0);
        arrInts.addToBack(33);

        arrInts.addAtIndex(3, 46);

        Integer[] expected = new Integer[ArrayList.INITIAL_CAPACITY];
        expected[0] = 1;
        expected[1] = -10;
        expected[2] = 0;
        expected[3] = 46;
        expected[4] = 33;

        assertArrayEquals(expected, arrInts.getBackingArray());
        assertEquals(5, arrInts.size());

        arrStrs.addToBack("1");
        arrStrs.addToBack("-10");
        arrStrs.addToBack("0");
        arrStrs.addToBack("33");

        arrStrs.addAtIndex(3, "46");

        String[] expected2 = new String[ArrayList.INITIAL_CAPACITY];
        expected2[0] = "1";
        expected2[1] = "-10";
        expected2[2] = "0";
        expected2[3] = "46";
        expected2[4] = "33";

        assertArrayEquals(expected2, arrStrs.getBackingArray());
        assertEquals(5, arrStrs.size());
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndex2d() {
        arrInts.addToBack(1);
        arrInts.addToBack(-10);
        arrInts.addToBack(0);
        arrInts.addToBack(33);

        arrInts.addAtIndex(4, 46);

        Integer[] expected = new Integer[ArrayList.INITIAL_CAPACITY];
        expected[0] = 1;
        expected[1] = -10;
        expected[2] = 0;
        expected[3] = 33;
        expected[4] = 46;

        assertArrayEquals(expected, arrInts.getBackingArray());
        assertEquals(5, arrInts.size());

        arrStrs.addToBack("1");
        arrStrs.addToBack("-10");
        arrStrs.addToBack("0");
        arrStrs.addToBack("33");

        arrStrs.addAtIndex(4, "46");

        String[] expected2 = new String[ArrayList.INITIAL_CAPACITY];
        expected2[0] = "1";
        expected2[1] = "-10";
        expected2[2] = "0";
        expected2[3] = "33";
        expected2[4] = "46";

        assertArrayEquals(expected2, arrStrs.getBackingArray());
        assertEquals(5, arrStrs.size());
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndex2e() {
        arrInts.addToBack(1);
        arrInts.addToBack(2);
        arrInts.addToBack(3);
        arrInts.addToBack(4);
        arrInts.addToBack(5);
        arrInts.addToBack(6);
        arrInts.addToBack(7);
        arrInts.addToBack(8);
        arrInts.addToBack(9);

        arrInts.addAtIndex(0, 0);

        Integer[] expected1 = new Integer[2 * ArrayList.INITIAL_CAPACITY];
        expected1[0] = 0;
        expected1[1] = 1;
        expected1[2] = 2;
        expected1[3] = 3;
        expected1[4] = 4;
        expected1[5] = 5;
        expected1[6] = 6;
        expected1[7] = 7;
        expected1[8] = 8;
        expected1[9] = 9;

        assertArrayEquals(expected1, arrInts.getBackingArray());
        assertEquals(10, arrInts.size());

        arrStrs.addToBack("1");
        arrStrs.addToBack("2");
        arrStrs.addToBack("3");
        arrStrs.addToBack("4");
        arrStrs.addToBack("5");
        arrStrs.addToBack("6");
        arrStrs.addToBack("7");
        arrStrs.addToBack("8");
        arrStrs.addToBack("9");

        arrStrs.addAtIndex(0, "0");

        String[] expected2 = new String[2 * ArrayList.INITIAL_CAPACITY];
        expected2[0] = "0";
        expected2[1] = "1";
        expected2[2] = "2";
        expected2[3] = "3";
        expected2[4] = "4";
        expected2[5] = "5";
        expected2[6] = "6";
        expected2[7] = "7";
        expected2[8] = "8";
        expected2[9] = "9";

        assertArrayEquals(expected2, arrStrs.getBackingArray());
        assertEquals(10, arrStrs.size());
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndex2f() {
        arrInts.addToBack(1);
        arrInts.addToBack(2);
        arrInts.addToBack(3);
        arrInts.addToBack(4);
        arrInts.addToBack(5);
        arrInts.addToBack(6);
        arrInts.addToBack(7);
        arrInts.addToBack(8);
        arrInts.addToBack(9);

        arrInts.addAtIndex(5, 0);

        Integer[] expected1 = new Integer[2 * ArrayList.INITIAL_CAPACITY];
        expected1[0] = 1;
        expected1[1] = 2;
        expected1[2] = 3;
        expected1[3] = 4;
        expected1[4] = 5;
        expected1[5] = 0;
        expected1[6] = 6;
        expected1[7] = 7;
        expected1[8] = 8;
        expected1[9] = 9;

        assertArrayEquals(expected1, arrInts.getBackingArray());
        assertEquals(10, arrInts.size());

        arrStrs.addToBack("1");
        arrStrs.addToBack("2");
        arrStrs.addToBack("3");
        arrStrs.addToBack("4");
        arrStrs.addToBack("5");
        arrStrs.addToBack("6");
        arrStrs.addToBack("7");
        arrStrs.addToBack("8");
        arrStrs.addToBack("9");

        arrStrs.addAtIndex(5, "0");

        String[] expected2 = new String[2 * ArrayList.INITIAL_CAPACITY];
        expected2[0] = "1";
        expected2[1] = "2";
        expected2[2] = "3";
        expected2[3] = "4";
        expected2[4] = "5";
        expected2[5] = "0";
        expected2[6] = "6";
        expected2[7] = "7";
        expected2[8] = "8";
        expected2[9] = "9";

        assertArrayEquals(expected2, arrStrs.getBackingArray());
        assertEquals(10, arrStrs.size());
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndex2g() {
        arrInts.addToBack(1);
        arrInts.addToBack(2);
        arrInts.addToBack(3);
        arrInts.addToBack(4);
        arrInts.addToBack(5);
        arrInts.addToBack(6);
        arrInts.addToBack(7);
        arrInts.addToBack(8);
        arrInts.addToBack(9);

        arrInts.addAtIndex(8, 0);

        Integer[] expected1 = new Integer[2 * ArrayList.INITIAL_CAPACITY];
        expected1[0] = 1;
        expected1[1] = 2;
        expected1[2] = 3;
        expected1[3] = 4;
        expected1[4] = 5;
        expected1[5] = 6;
        expected1[6] = 7;
        expected1[7] = 8;
        expected1[8] = 0;
        expected1[9] = 9;

        assertArrayEquals(expected1, arrInts.getBackingArray());
        assertEquals(10, arrInts.size());

        arrStrs.addToBack("1");
        arrStrs.addToBack("2");
        arrStrs.addToBack("3");
        arrStrs.addToBack("4");
        arrStrs.addToBack("5");
        arrStrs.addToBack("6");
        arrStrs.addToBack("7");
        arrStrs.addToBack("8");
        arrStrs.addToBack("9");

        arrStrs.addAtIndex(8, "0");

        String[] expected2 = new String[2 * ArrayList.INITIAL_CAPACITY];
        expected2[0] = "1";
        expected2[1] = "2";
        expected2[2] = "3";
        expected2[3] = "4";
        expected2[4] = "5";
        expected2[5] = "6";
        expected2[6] = "7";
        expected2[7] = "8";
        expected2[8] = "0";
        expected2[9] = "9";

        assertArrayEquals(expected2, arrStrs.getBackingArray());
        assertEquals(10, arrStrs.size());
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndex2h() {
        arrInts.addToBack(1);
        arrInts.addToBack(2);
        arrInts.addToBack(3);
        arrInts.addToBack(4);
        arrInts.addToBack(5);
        arrInts.addToBack(6);
        arrInts.addToBack(7);
        arrInts.addToBack(8);
        arrInts.addToBack(9);

        arrInts.addAtIndex(9, 0);

        Integer[] expected1 = new Integer[2 * ArrayList.INITIAL_CAPACITY];
        expected1[0] = 1;
        expected1[1] = 2;
        expected1[2] = 3;
        expected1[3] = 4;
        expected1[4] = 5;
        expected1[5] = 6;
        expected1[6] = 7;
        expected1[7] = 8;
        expected1[8] = 9;
        expected1[9] = 0;

        assertArrayEquals(expected1, arrInts.getBackingArray());
        assertEquals(10, arrInts.size());

        arrStrs.addToBack("1");
        arrStrs.addToBack("2");
        arrStrs.addToBack("3");
        arrStrs.addToBack("4");
        arrStrs.addToBack("5");
        arrStrs.addToBack("6");
        arrStrs.addToBack("7");
        arrStrs.addToBack("8");
        arrStrs.addToBack("9");

        arrStrs.addAtIndex(9, "0");

        String[] expected2 = new String[2 * ArrayList.INITIAL_CAPACITY];
        expected2[0] = "1";
        expected2[1] = "2";
        expected2[2] = "3";
        expected2[3] = "4";
        expected2[4] = "5";
        expected2[5] = "6";
        expected2[6] = "7";
        expected2[7] = "8";
        expected2[8] = "9";
        expected2[9] = "0";

        assertArrayEquals(expected2, arrStrs.getBackingArray());
        assertEquals(10, arrStrs.size());
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndex2i() {
        arrInts.addToBack(1);
        arrInts.addToBack(2);
        arrInts.addToBack(3);
        arrInts.addToBack(4);
        arrInts.addToBack(5);
        arrInts.addToBack(6);
        arrInts.addToBack(7);
        arrInts.addToBack(8);

        arrInts.addAtIndex(5, 0);

        Integer[] expected1 = new Integer[ArrayList.INITIAL_CAPACITY];
        expected1[0] = 1;
        expected1[1] = 2;
        expected1[2] = 3;
        expected1[3] = 4;
        expected1[4] = 5;
        expected1[5] = 0;
        expected1[6] = 6;
        expected1[7] = 7;
        expected1[8] = 8;

        assertArrayEquals(expected1, arrInts.getBackingArray());
        assertEquals(9, arrInts.size());

        arrStrs.addToBack("1");
        arrStrs.addToBack("2");
        arrStrs.addToBack("3");
        arrStrs.addToBack("4");
        arrStrs.addToBack("5");
        arrStrs.addToBack("6");
        arrStrs.addToBack("7");
        arrStrs.addToBack("8");

        arrStrs.addAtIndex(5, "0");

        String[] expected2 = new String[ArrayList.INITIAL_CAPACITY];
        expected2[0] = "1";
        expected2[1] = "2";
        expected2[2] = "3";
        expected2[3] = "4";
        expected2[4] = "5";
        expected2[5] = "0";
        expected2[6] = "6";
        expected2[7] = "7";
        expected2[8] = "8";

        assertArrayEquals(expected2, arrStrs.getBackingArray());
        assertEquals(9, arrStrs.size());
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtIndex3() {
        arrInts.addToBack(1);
        arrInts.addToBack(2);
        arrInts.addToBack(3);
        arrInts.addToBack(4);
        arrInts.addToBack(5);
        arrInts.addToBack(6);
        arrInts.addToBack(7);
        arrInts.addToBack(8);
        arrInts.addToBack(9);

        arrInts.addAtIndex(5, 0);

        Integer[] expected1 = new Integer[2 * ArrayList.INITIAL_CAPACITY];
        expected1[0] = 1;
        expected1[1] = 2;
        expected1[2] = 3;
        expected1[3] = 4;
        expected1[4] = 5;
        expected1[5] = 0;
        expected1[6] = 6;
        expected1[7] = 7;
        expected1[8] = 8;
        expected1[9] = 9;

        assertArrayEquals(expected1, arrInts.getBackingArray());
        assertEquals(10, arrInts.size());

        arrInts.addToBack(10);
        arrInts.addToBack(11);
        arrInts.addToBack(12);
        arrInts.addToBack(13);
        arrInts.addToBack(14);
        arrInts.addToBack(15);
        arrInts.addToBack(16);
        arrInts.addToBack(17);
        arrInts.addToBack(18);
        arrInts.addToBack(19);
        arrInts.addToBack(20);
        arrInts.addToBack(21);
        arrInts.addToBack(22);

        arrInts.addAtIndex(17, 0);

        Integer[] expected2 = new Integer[4 * ArrayList.INITIAL_CAPACITY];
        expected2[0] = 1;
        expected2[1] = 2;
        expected2[2] = 3;
        expected2[3] = 4;
        expected2[4] = 5;
        expected2[5] = 0;
        expected2[6] = 6;
        expected2[7] = 7;
        expected2[8] = 8;
        expected2[9] = 9;
        expected2[10] = 10;
        expected2[11] = 11;
        expected2[12] = 12;
        expected2[13] = 13;
        expected2[14] = 14;
        expected2[15] = 15;
        expected2[16] = 16;
        expected2[17] = 0;
        expected2[18] = 17;
        expected2[19] = 18;
        expected2[20] = 19;
        expected2[21] = 20;
        expected2[22] = 21;
        expected2[23] = 22;

        assertArrayEquals(expected2, arrInts.getBackingArray());
        assertEquals(24, arrInts.size());
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testAddAtIndex4() {
        arrStrs.addToBack("1");
        arrStrs.addToBack("2");
        arrStrs.addToBack("3");
        arrStrs.addToBack("4");
        arrStrs.addToBack("5");
        arrStrs.addToBack("6");
        arrStrs.addToBack("7");
        arrStrs.addToBack("8");
        arrStrs.addToBack("9");
        arrStrs.addToBack("10");
        arrStrs.addToBack("11");

        String[] expected1 = new String[ArrayList.INITIAL_CAPACITY * 2];
        expected1[0] = "1";
        expected1[1] = "2";
        expected1[2] = "3";
        expected1[3] = "4";
        expected1[4] = "5";
        expected1[5] = "6";
        expected1[6] = "7";
        expected1[7] = "8";
        expected1[8] = "9";
        expected1[9] = "10";
        expected1[10] = "11";

        assertArrayEquals(expected1, arrStrs.getBackingArray());
        assertEquals(11, arrStrs.size());

        arrStrs.addAtIndex(16, "test");
    }


    @Test(timeout = TIMEOUT)
    public void testAddToFront1() {
        arrInts.addToFront(4);
        Integer[] expected = new Integer[ArrayList.INITIAL_CAPACITY];
        expected[0] = 4;
        assertArrayEquals(expected, arrInts.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddToFront2() {
        arrStrs.addToBack("hi");
        arrStrs.addToBack("hello");
        arrStrs.addToBack("howdy");
        arrStrs.addToBack("yello");

        arrStrs.addToFront("testing");

        String[] expected = new String[ArrayList.INITIAL_CAPACITY];
        expected[0] = "testing";
        expected[1] = "hi";
        expected[2] = "hello";
        expected[3] = "howdy";
        expected[4] = "yello";

        assertArrayEquals(expected, arrStrs.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddToFront3() {
        arrInts.addToBack(1);
        arrInts.addToBack(2);
        arrInts.addToBack(3);
        arrInts.addToBack(4);
        arrInts.addToBack(5);
        arrInts.addToBack(6);
        arrInts.addToBack(7);
        arrInts.addToBack(8);

        arrInts.addToFront(0);

        Integer[] expected = new Integer[] {
                0, 1, 2, 3, 4, 5, 6, 7, 8
        };

        assertArrayEquals(expected, arrInts.getBackingArray());
        assertEquals(9, arrInts.size());
    }

    @Test(timeout = TIMEOUT)
    public void testAddToFront4() {
        arrStrs.addToBack("1");
        arrStrs.addToBack("2");
        arrStrs.addToBack("3");
        arrStrs.addToBack("4");
        arrStrs.addToBack("5");
        arrStrs.addToBack("6");
        arrStrs.addToBack("7");
        arrStrs.addToBack("8");
        arrStrs.addToBack("9");

        arrStrs.addToFront("0");

        String[] expected = new String[] {
                "0", "1", "2", "3", "4", "5",
                "6", "7", "8", "9", null, null, null,
                null, null, null, null, null
        };

        assertArrayEquals(expected, arrStrs.getBackingArray());
        assertEquals(10, arrStrs.size());
    }

    @Test(timeout = TIMEOUT)
    public void testAddToFront5() {
        for (int i = 0; i < 18; i++) {
            arrInts.addToBack(i);
        }

        assertEquals(18, arrInts.size());

        arrInts.addToFront(-1);

        Integer[] expected = new Integer[36];
        expected[0] = -1;
        for (int i = 0; i < 18; i++) {
            expected[i + 1] = i;
        }
        for (int j = 19; j < 36; j++) {
            expected[j] = null;
        }

        assertArrayEquals(expected, arrInts.getBackingArray());
        assertEquals(19, arrInts.size());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddToFront6() {
        arrInts.addToFront(null);
    }


    @Test(timeout = TIMEOUT)
    public void testAddToBack1() {
        arrInts.addToBack(4);
        Integer[] expected = new Integer[ArrayList.INITIAL_CAPACITY];
        expected[0] = 4;
        assertArrayEquals(expected, arrInts.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddToBack2() {
        arrStrs.addToFront("hi");
        arrStrs.addToFront("hello");
        arrStrs.addToFront("howdy");
        arrStrs.addToFront("yello");

        arrStrs.addToBack("testing");

        String[] expected = new String[ArrayList.INITIAL_CAPACITY];
        expected[0] = "yello";
        expected[1] = "howdy";
        expected[2] = "hello";
        expected[3] = "hi";
        expected[4] = "testing";

        assertArrayEquals(expected, arrStrs.getBackingArray());
    }

    @Test(timeout = TIMEOUT)
    public void testAddToBack3() {
        arrInts.addToFront(8);
        arrInts.addToFront(7);
        arrInts.addToFront(6);
        arrInts.addToFront(5);
        arrInts.addToFront(4);
        arrInts.addToFront(3);
        arrInts.addToFront(2);
        arrInts.addToFront(1);

        arrInts.addToBack(0);

        Integer[] expected = new Integer[] {
                1, 2, 3, 4, 5, 6, 7, 8, 0
        };

        assertArrayEquals(expected, arrInts.getBackingArray());
        assertEquals(9, arrInts.size());
    }

    @Test(timeout = TIMEOUT)
    public void testAddToBack4() {
        arrStrs.addToFront("9");
        arrStrs.addToFront("8");
        arrStrs.addToFront("7");
        arrStrs.addToFront("6");
        arrStrs.addToFront("5");
        arrStrs.addToFront("4");
        arrStrs.addToFront("3");
        arrStrs.addToFront("2");
        arrStrs.addToFront("1");

        arrStrs.addToBack("0");

        String[] expected = new String[] {
                "1", "2", "3", "4", "5", "6",
                "7", "8", "9", "0", null, null, null,
                null, null, null, null, null
        };

        assertArrayEquals(expected, arrStrs.getBackingArray());
        assertEquals(10, arrStrs.size());
    }

    @Test(timeout = TIMEOUT)
    public void testAddToBack5() {
        for (int i = 0; i < 18; i++) {
            arrInts.addToFront(i);
        }

        assertEquals(18, arrInts.size());

        arrInts.addToBack(-1);

        Integer[] expected = new Integer[36];
        int ctr = 0;
        for (int i = 17; i >= 0; i--) {
            expected[ctr] = i;
            ctr++;
        }
        expected[ctr] = -1;
        for (int i = 19; i < 36; i++) {
            expected[i] = null;
        }

        assertArrayEquals(expected, arrInts.getBackingArray());
        assertEquals(19, arrInts.size());
    }

    @Test(timeout = TIMEOUT, expected = IllegalArgumentException.class)
    public void testAddToBack6() {
        arrInts.addToBack(null);
    }


    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testRemoveFromIndex1() {
        arrInts.removeAtIndex(-1);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testRemoveFromIndex2a() {
        arrInts.removeAtIndex(0);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testRemoveFromIndex2b() {
        arrInts.removeAtIndex(5);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testRemoveFromIndex2c() {
        arrInts.addToFront(3);
        arrInts.addToFront(6);
        arrInts.removeAtIndex(2);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testRemoveFromIndex2d() {
        arrInts.addToFront(3);
        arrInts.addToFront(6);
        arrInts.addToFront(9);
        arrInts.removeAtIndex(50);
    }

    @Test(timeout = TIMEOUT)
    public void testRemovefromIndex3a() {
        arrStrs.addToBack("1");
        arrStrs.addToBack("2");
        arrStrs.addToBack("3");
        arrStrs.addToBack("4");
        arrStrs.addToBack("5");
        arrStrs.addToBack("6");
        arrStrs.addToBack("7");
        arrStrs.addToBack("8");

        String s = arrStrs.removeAtIndex(3);
        assertEquals("4", s);

        String[] expected = new String[] {
            "1", "2", "3", "5", "6", "7", "8", null, null
        };

        assertArrayEquals(expected, arrStrs.getBackingArray());
        assertEquals(7, arrStrs.size());
    }


    @Test(timeout = TIMEOUT)
    public void testRemovefromIndex3b() {
        arrStrs.addToBack("1");
        arrStrs.addToBack("2");
        arrStrs.addToBack("3");
        arrStrs.addToBack("4");
        arrStrs.addToBack("5");
        arrStrs.addToBack("6");
        arrStrs.addToBack("7");
        arrStrs.addToBack("8");
        arrStrs.addToBack("9");
        arrStrs.addToBack("10");

        String s = arrStrs.removeAtIndex(0);
        assertEquals("1", s);

        String[] expected = new String[] {
                "2", "3", "4",
                "5", "6", "7",
                "8" ,"9", "10",
                null, null, null,
                null, null, null,
                null, null, null
        };

        assertArrayEquals(expected, arrStrs.getBackingArray());
        assertEquals(9, arrStrs.size());
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveFromFront1() {
        arrStrs.removeFromFront();
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromFront2() {
        arrStrs.addToBack("1");
        arrStrs.addToBack("2");
        arrStrs.addToBack("3");
        arrStrs.addToBack("4");
        arrStrs.addToBack("5");

        String s1 = arrStrs.removeFromFront();
        assertEquals("1", s1);

        String[] expected1 = new String[] {
                "2", "3", "4", "5", null, null, null, null, null
        };

        assertArrayEquals(expected1, arrStrs.getBackingArray());
        assertEquals(4, arrStrs.size());

        arrStrs.addToFront("0");
        arrStrs.addToBack("6");
        arrStrs.addToBack("7");
        arrStrs.addToBack("8");
        arrStrs.addToBack("9");
        arrStrs.addToBack("10");
        arrStrs.addToBack("11");
        arrStrs.addToBack("12");
        arrStrs.addToBack("13");

        String s2 = arrStrs.removeFromFront();
        assertEquals("0", s2);

        String[] expected2 = new String[] {
             "2", "3", "4", "5", "6",
             "7", "8", "9", "10", "11", "12", "13",
             null, null, null, null, null, null
        };

        assertArrayEquals(expected2, arrStrs.getBackingArray());
        assertEquals(12, arrStrs.size());
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveFromBack1() {
        arrStrs.removeFromBack();
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveFromBack2() {
        arrStrs.addToBack("1");
        arrStrs.addToBack("2");
        arrStrs.addToBack("3");
        arrStrs.addToBack("4");
        arrStrs.addToBack("5");

        String s1 = arrStrs.removeFromBack();
        assertEquals("5", s1);

        String[] expected1 = new String[] {
                "1", "2", "3", "4", null, null, null, null, null
        };

        assertArrayEquals(expected1, arrStrs.getBackingArray());
        assertEquals(4, arrStrs.size());

        arrStrs.addToFront("0");
        arrStrs.addToBack("5");
        arrStrs.addToBack("6");
        arrStrs.addToBack("7");
        arrStrs.addToBack("8");
        arrStrs.addToBack("9");
        arrStrs.addToBack("10");
        arrStrs.addToBack("11");
        arrStrs.addToBack("12");

        String s2 = arrStrs.removeFromBack();
        assertEquals("12", s2);

        String[] expected2 = new String[] {
                "0", "1", "2", "3", "4",
                "5", "6", "7", "8", "9", "10", "11",
                null, null, null, null, null, null
        };

        assertArrayEquals(expected2, arrStrs.getBackingArray());
        assertEquals(12, arrStrs.size());
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testGetError1() {
        arrInts.addToBack(5);
        arrInts.addToBack(10);
        arrInts.addToBack(15);
        arrInts.get(-5);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testGetError2() {
        arrInts.addToBack(5);
        arrInts.addToBack(10);
        arrInts.addToBack(15);
        arrInts.get(30);
    }

    @Test(timeout = TIMEOUT, expected = IndexOutOfBoundsException.class)
    public void testGetError3() {
        arrInts.addToBack(5);
        arrInts.addToBack(10);
        arrInts.addToBack(15);
        arrInts.get(3);
    }

    @Test(timeout = TIMEOUT)
    public void testGet() {
        arrInts.addToBack(1);
        arrInts.addToBack(2);
        arrInts.addToBack(3);
        arrInts.addToBack(4);
        arrInts.addToBack(5);
        arrInts.addToBack(6);
        arrInts.addToBack(7);
        arrInts.addToBack(8);
        arrInts.addToBack(9);
        arrInts.addToBack(10);

        assertEquals((Integer)9, (Integer)arrInts.get(8));
        assertEquals((Integer)3, (Integer)arrInts.get(2));
        assertEquals((Integer)4, (Integer)arrInts.get(3));
        assertEquals((Integer)1, (Integer)arrInts.get(0));
        assertEquals((Integer)6, (Integer)arrInts.get(5));
        assertEquals((Integer)10, (Integer)arrInts.get(9));
        assertEquals((Integer)7, (Integer)arrInts.get(6));
        assertEquals((Integer)2, (Integer)arrInts.get(1));
        assertEquals((Integer)5, (Integer)arrInts.get(4));
        assertEquals((Integer)8, (Integer)arrInts.get(7));
    }

    @Test(timeout = TIMEOUT)
    public void testIsEmptyInts() {
        assertTrue("isEmpty returns false when it should return true", arrInts.isEmpty());
        arrInts.addToBack(5);
        assertFalse("isEmpty returns true when it should return false", arrInts.isEmpty());
    }

    @Test(timeout = TIMEOUT)
    public void testIsEmptyStrs() {
        assertTrue("isEmpty returns false when it should return true", arrStrs.isEmpty());
        arrStrs.addToBack("5");
        assertFalse("isEmpty returns true when it should return false", arrStrs.isEmpty());
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        arrInts.addToFront(4);
        arrInts.addToFront(67);
        arrInts.addAtIndex(1, -55);
        arrInts.addAtIndex(0, 33);
        arrInts.addToBack(50);
        arrInts.addToBack(10000000);

        arrInts.clear();
        assertArrayEquals(new Integer[ArrayList.INITIAL_CAPACITY], arrInts.getBackingArray());
        assertEquals(0, arrInts.size());

        arrStrs.addToFront("4");
        arrStrs.addToFront("67");
        arrStrs.addAtIndex(1, "-55");
        arrStrs.addAtIndex(0, "33");
        arrStrs.addToBack("50");
        arrStrs.addToBack("\n\t");

        arrStrs.clear();
        assertArrayEquals(new String[ArrayList.INITIAL_CAPACITY], arrStrs.getBackingArray());
        assertEquals(0, arrStrs.size());
    }
}