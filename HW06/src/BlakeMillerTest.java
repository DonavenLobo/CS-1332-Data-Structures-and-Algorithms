import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class BlakeMillerTest {

    private static final int TIMEOUT = 200;
    private QuadraticProbingHashMap<Integer, Integer> map;
    private QuadraticProbingHashMap<Integer, String> map2;
    private QuadraticProbingHashMap<Integer, String> map3;
    private QuadraticProbingHashMap<Integer, String> map4;
    private QuadraticProbingHashMap<Integer, String> map5;
    private QuadraticProbingHashMap<Integer, Integer> map6;
    private QuadraticProbingHashMap<Integer, String> map7;

    @Before
    public void setUp() {
        map = new QuadraticProbingHashMap<>();
        map2 = new QuadraticProbingHashMap<>(7);
        map3 = new QuadraticProbingHashMap<>(7);
        map4 = new QuadraticProbingHashMap<>(10);
        map5 = new QuadraticProbingHashMap<>(8);
        map6 = new QuadraticProbingHashMap<>();
        map7 = new QuadraticProbingHashMap<>();

        map3.put(45, "test 45");
        map3.put(19, "test 19");
        map3.put(12, "test 12");

        map6.put(126, 126);
        map6.put(37, 37);
        map6.put(149, 149);
        map6.put(92, 92);
        map6.put(212, 212);
        map6.put(13, 13);
        map6.put(197, 197);
        map6.put(26, 26);
        map6.put(444, 444);
        map6.put(1000, 1000);
        map6.put(798, 798);

        map7.put(0, "test 0");
        map7.put(1, "test 1");
        map7.put(2, "test 2");
    }

    @Test(timeout = TIMEOUT)
    public void testAddNoResizeOrWrap() {
        map2.put(2, "test 2");
        map2.put(4, "test 4");
        map2.put(5, "test 5");

        QuadraticProbingMapEntry[] expected = new QuadraticProbingMapEntry[7];
        expected[2] = new QuadraticProbingMapEntry(2, "test 2");
        expected[4] = new QuadraticProbingMapEntry(4, "test 4");
        expected[5] = new QuadraticProbingMapEntry(5, "test 5");

        assertArrayEquals(expected, map2.getTable());
    }

    @Test(timeout = TIMEOUT)
    public void testAddNoResizeWithCollision() {
        QuadraticProbingMapEntry[] expected = new QuadraticProbingMapEntry[7];
        expected[3] = new QuadraticProbingMapEntry(45, "test 45");
        expected[5] = new QuadraticProbingMapEntry(19, "test 19");
        expected[6] = new QuadraticProbingMapEntry(12, "test 12");

        assertArrayEquals(expected, map3.getTable());
    }

    @Test(timeout = TIMEOUT)
    public void testGetNoResizeWithCollision() {
        QuadraticProbingMapEntry[] expected = new QuadraticProbingMapEntry[7];

        assertEquals(map3.get(45), "test 45");
        assertEquals(map3.get(19), "test 19");
        assertEquals(map3.get(12), "test 12");
    }

    @Test(timeout = TIMEOUT)
    public void testAddCollisionChain() {
        QuadraticProbingHashMap<Integer, Integer> test = new QuadraticProbingHashMap<>(100);
        test.put(0, 0);
        test.put(1, 1);
        test.put(4, 4);
        test.put(9, 9);
        test.put(16, 16);

        test.put(100, 100);

        assertEquals(new QuadraticProbingMapEntry<Integer, Integer>(100, 100), test.getTable()[25]);
    }

    @Test(timeout = TIMEOUT)
    public void testResizeAtRightTime() {
        map4.put(0, "test 0");
        map4.put(1, "test 1");
        map4.put(2, "test 2");
        map4.put(3, "test 3");
        map4.put(4, "test 4");
        map4.put(5, "test 5");

        assertEquals(10, map4.getTable().length);

        map4.put(6, "test 6");
        assertEquals(21, map4.getTable().length);
    }

    @Test(timeout = TIMEOUT)
    public void testDefaultConstructor() {
        map.put(0, 0);
        map.put(4, 4);
        map.put(6, 6);

        QuadraticProbingMapEntry[] expected = new QuadraticProbingMapEntry[13];
        expected[0] = new QuadraticProbingMapEntry(0, 0);
        expected[4] = new QuadraticProbingMapEntry(4, 4);
        expected[6] = new QuadraticProbingMapEntry(6, 6);

        assertArrayEquals(expected, map.getTable());
    }

    @Test(timeout = TIMEOUT)
    // check answers for this
    public void testAddResizeWithoutCollision() {
        QuadraticProbingMapEntry[] expected = new QuadraticProbingMapEntry[27];

        expected[1] = new QuadraticProbingMapEntry(1000, 1000);
        expected[8] = new QuadraticProbingMapEntry(197, 197);
        expected[10] = new QuadraticProbingMapEntry(37, 37);
        expected[11] = new QuadraticProbingMapEntry(92, 92);
        expected[12] = new QuadraticProbingMapEntry(444, 444);
        expected[13] = new QuadraticProbingMapEntry(13, 13);
        expected[14] = new QuadraticProbingMapEntry(149, 149);
        expected[15] = new QuadraticProbingMapEntry(798, 798);
        expected[18] = new QuadraticProbingMapEntry(126, 126);
        expected[23] = new QuadraticProbingMapEntry(212, 212);
        expected[26] = new QuadraticProbingMapEntry(26, 26);

        assertArrayEquals(expected, map6.getTable());
    }

    @Test(timeout = TIMEOUT)
    public void testGetResizeWithoutCollision() {
        assertEquals(Integer.valueOf(126), map6.get(126));
        assertEquals(Integer.valueOf(37), map6.get(37));
        assertEquals(Integer.valueOf(149), map6.get(149));
        assertEquals(Integer.valueOf(92), map6.get(92));
        assertEquals(Integer.valueOf(212), map6.get(212));
        assertEquals(Integer.valueOf(13), map6.get(13));
        assertEquals(Integer.valueOf(197), map6.get(197));
        assertEquals(Integer.valueOf(26), map6.get(26));
        assertEquals(Integer.valueOf(444), map6.get(444));
        assertEquals(Integer.valueOf(1000), map6.get(1000));
        assertEquals(Integer.valueOf(798), map6.get(798));
    }

    @Test(timeout = TIMEOUT)
    public void testAddResizeWithLongCollision() {
        map5.put(0, "test 0");
        map5.put(1, "test 1");
        map5.put(4, "test 4");

        QuadraticProbingMapEntry[] expected = new QuadraticProbingMapEntry[8];
        expected[0] = new QuadraticProbingMapEntry<>(0, "test 0");
        expected[1] = new QuadraticProbingMapEntry<>(1, "test 1");
        expected[4] = new QuadraticProbingMapEntry<>(4, "test 4");

        assertArrayEquals(expected, map5.getTable());

        map5.put(8, "test 8");

        expected = new QuadraticProbingMapEntry[17];
        expected[0] = new QuadraticProbingMapEntry<>(0, "test 0");
        expected[1] = new QuadraticProbingMapEntry<>(1, "test 1");
        expected[4] = new QuadraticProbingMapEntry<>(4, "test 4");
        expected[8] = new QuadraticProbingMapEntry<>(8, "test 8");

        assertArrayEquals(expected, map5.getTable());
    }

    @Test(timeout = TIMEOUT)
    public void testAddResizeWithLongCollisionThenAnotherCollision() {
        map5.put(8, "test 8");
        map5.put(1, "test 1");
        map5.put(7, "test 7");
        map5.put(24, "test 24");

        QuadraticProbingMapEntry[] expected = new QuadraticProbingMapEntry[8];
        expected[0] = new QuadraticProbingMapEntry<>(8, "test 8");
        expected[1] = new QuadraticProbingMapEntry<>(1, "test 1");
        expected[4] = new QuadraticProbingMapEntry<>(24, "test 24");
        expected[7] = new QuadraticProbingMapEntry<>(7, "test 7");

        assertArrayEquals(expected, map5.getTable());

        map5.put(0, "test 0");

        assertEquals("test 0", map5.get(0));
        assertEquals("test 8", map5.get(8));
        assertEquals("test 1", map5.get(1));
        assertEquals("test 7", map5.get(7));
        assertEquals("test 24", map5.get(24));

        expected = new QuadraticProbingMapEntry[17];
        expected[0] = new QuadraticProbingMapEntry<>(0, "test 0");
        expected[1] = new QuadraticProbingMapEntry<>(1, "test 1");
        expected[7] = new QuadraticProbingMapEntry<>(24, "test 24");
        expected[8] = new QuadraticProbingMapEntry<>(8, "test 8");
        expected[11] = new QuadraticProbingMapEntry<>(7, "test 7");

        assertArrayEquals(expected, map5.getTable());
    }

    @Test(timeout = TIMEOUT)
    public void testRemove() {
        map3.remove(19);
        assertEquals(2, map3.size());
        assertEquals("test 45", map3.get(45));
        assertEquals("test 12", map3.get(12));
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testRemoveAlreadyRemoved() {
        map3.remove(19);
        map3.remove(19);
    }

    @Test(timeout = TIMEOUT, expected = NoSuchElementException.class)
    public void testGetAlreadyRemoved() {
        map3.remove(19);
        map3.get(19);
    }

    @Test(timeout = TIMEOUT)
    public void testRemove2() {
        QuadraticProbingHashMap<Integer, Integer> testMap = new QuadraticProbingHashMap<>();
        testMap.put(0, 0);
        testMap.put(1, 1);
        testMap.put(4, 4);
        testMap.put(13, 13);
        testMap.remove(4);

        assertEquals(Integer.valueOf(13), testMap.get(13));
        assertEquals(3, testMap.size());
    }

    @Test(timeout = TIMEOUT)
    public void testClear() {
        assertEquals(3, map3.size());
        map3.clear();
        assertEquals(0, map3.size());
        assertArrayEquals(new QuadraticProbingMapEntry[13], map3.getTable());
    }

    @Test(timeout = TIMEOUT)
    public void testContainsKey() {
        QuadraticProbingHashMap<Integer, String> test = new QuadraticProbingHashMap<>();
        test.put(0, "test 0");
        test.put(1, "test 1");
        test.put(19, "test 19");
        test.put(11, "test 11");

        assertTrue(test.containsKey(0));
        assertTrue(test.containsKey(1));
        assertTrue(test.containsKey(19));
        assertTrue(test.containsKey(11));
    }

    @Test(timeout = TIMEOUT)
    public void testContainsKeyResize() {
        QuadraticProbingHashMap<Integer, String> test = new QuadraticProbingHashMap<>();

        for (int i = 0; i < 100; i++) {
            test.put(i, "test " + i);
        }

        for (int i = 0; i < 100; i++) {
            assertTrue(test.containsKey(i));
        }

        assertFalse(test.containsKey(100));
    }

    @Test(timeout = TIMEOUT)
    public void testContainsKeyWithStrings() {
        QuadraticProbingHashMap<String, String> test = new QuadraticProbingHashMap<>();
        test.put("cat", "test cat");
        test.put("dog", "test dog");
        test.put("bird", "test bird");
        test.put("fish", "test fish");

        assertTrue(test.containsKey("cat"));
        assertTrue(test.containsKey("dog"));
        assertTrue(test.containsKey("bird"));
        assertTrue(test.containsKey("fish"));

        assertFalse(test.containsKey("car"));
        assertFalse(test.containsKey("house"));
    }

    @Test(timeout = TIMEOUT)
    public void testKeySet() {
        map3.put(46, "to be immediately removed");
        map3.remove(46);

        HashSet<Integer> keyResult = new HashSet<>();
        keyResult.add(45);
        keyResult.add(19);
        keyResult.add(12);

        ArrayList<String> valueResult = new ArrayList<>();
        valueResult.add("test 45");
        valueResult.add("test 19");
        valueResult.add("test 12");


        assertEquals(keyResult, map3.keySet());
        assertEquals(valueResult, map3.values());
    }

    @Test(timeout = TIMEOUT)
    public void testKeySet2() {
        HashSet<Integer> keyResult = new HashSet<>();
        ArrayList<String> valueResult = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            keyResult.add(i);
            valueResult.add("value " + i);
        }

        QuadraticProbingHashMap<Integer, String> test = new QuadraticProbingHashMap<>();

        for (int i = 0; i < 100; i++) {
            test.put(i, "value " + i);
        }

        assertEquals(keyResult, test.keySet());
        assertEquals(valueResult, test.values());
    }

    @Test(timeout = TIMEOUT)
    public void testPutReplace() {
        String temp = "test 26";
        String temp2 = "test 26 again";

        map3.put(26, temp);
        assertEquals(temp, map3.put(26, temp2));
        assertEquals(temp2, map3.put(26, "test 26 again again"));
        assertNull(map3.put(25, "Was not in list before."));
    }

    @Test(timeout = TIMEOUT)
    public void testRemoveReturn() {
        QuadraticProbingHashMap<String, String> test = new QuadraticProbingHashMap<>();

        String temp = "test String";
        test.put("test", temp);
        assertEquals(temp, test.remove("test"));
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtRemoved() {
        map7.put(0, "test 0");
        map7.put(1, "test 1");
        map7.put(2, "test 2");
        map7.remove(1);
        map7.put(14, "test 14");

        assertEquals("test 14", map7.getTable()[1].getValue());
    }

    @Test(timeout = TIMEOUT)
    public void testAddAtRemovedWithDuplicate() {
        map7.remove(1);
        map7.put(1, "test 1 again");

        assertEquals("test 1 again", map7.getTable()[1].getValue());
    }

    @Test(timeout = TIMEOUT)
    public void testAddDuplicateWithRemoved() {
        QuadraticProbingHashMap<Integer, String> test = new QuadraticProbingHashMap<>();
        test.put(0, "test 0");
        test.put(14, "test 14");
        test.put(2, "test 2");
        test.put(1, "test 1");

        assertEquals("test 1", test.getTable()[5].getValue());

        test.remove(14);
        test.put(1, "test 1 again");

        assertEquals("test 14", test.getTable()[1].getValue());
        assertTrue(test.getTable()[1].isRemoved());
        assertEquals("test 1 again", test.getTable()[5].getValue());
    }

    // test for negative numbers?
}
