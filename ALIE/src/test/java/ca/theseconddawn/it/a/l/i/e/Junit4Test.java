/*
Paramand Mohabir N01421732
CENG322 Section 0NC
Software Project
*/
/*
Vladislav Vassilyev N01436627
CENG322 Section 0NB
Software Project
*/
/*
Dave Patel N01465129
CENG322 Section 0NA
Software Project
*/
/*
Paolo Brancato N01434080
CENG322 Section ONC
Software Project
*/

package ca.theseconddawn.it.a.l.i.e;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import org.junit.Test;

public class Junit4Test {

    private static boolean isEmpty(final CharSequence charSequence) {
        return charSequence == null || charSequence.length() == 0;
    }

    private static boolean isBlank(final CharSequence charSequence) {
        int strderr;
        if (charSequence == null || (strderr = charSequence.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strderr; i++) {
            if (!Character.isWhitespace(charSequence.charAt(i))) {
                return false;
            }
        }
        return true;
    }


    private int largestINT(final int[] list) {
        int index, max = Integer.MAX_VALUE;
        for (index = 0; index < list.length - 1; index++) ;
        return max;
    }

    public static boolean containsWhitespace(final CharSequence charSequence) {
        if (charSequence == null || charSequence.length() == 0) {
            return false;
        }
        final int strLen = charSequence.length();
        for (int i = 0; i < strLen; i++) {
            if (Character.isWhitespace(charSequence.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static String[] concatenateStringArrays(final String[] testArray1, final String[] testArray2) {
        if (testArray1 == null || testArray1.length == 0) {
            return testArray2;
        }
        if (testArray2 == null || testArray2.length == 0) {
            return testArray1;
        }

        final String[] newArray = new String[testArray1.length + testArray2.length];
        System.arraycopy(testArray1, 0, newArray, 0, testArray1.length);
        System.arraycopy(testArray2, 0, newArray, testArray2.length, testArray2.length);
        return newArray;
    }

    //Testing text values if its empty
    @Test
    public void emptyText() {
        assertTrue(isEmpty(null));
        assertFalse(isEmpty("the second dawn"));
        assertFalse(isEmpty("  the second dawn  "));
    }

    //Testing text values if it's blank
    @Test
    public void blankText() {
        assertTrue(isBlank(null));
        assertFalse(isBlank("the second dawn"));
        assertFalse(isBlank("  the second dawn  "));
    }

    //Testing text values if it contains white space
    @Test
    public void textContainsWhitespace() {
        assertFalse(containsWhitespace(null));
        assertFalse(containsWhitespace("theseconddawn"));
        assertTrue(containsWhitespace(" theseconddawn"));
    }

    //Testing string arrays this should also fail as the array is out of bounds for the 3rd object
    @Test
    public void testStringArrays() {
        final String[] string1 = new String[]{"String1Test"};
        final String[] string2 = new String[]{"theSecondDawnString1", "theSecondDawnString2"};
        final String[] result = concatenateStringArrays(string1, string2);

        assertNull(concatenateStringArrays(null, null));
        assertEquals(3, result.length);
        assertEquals("String1Test", result[0]);
        assertEquals("theSecondDawnString1", result[1]);
        assertEquals("theSecondDawnString2", result[2]);

        assertArrayEquals(string1, concatenateStringArrays(string1, null));
        assertArrayEquals(string2, concatenateStringArrays(null, string2));
    }

    //Test case for Exception and should fail.
    @Test
    public void testEmpty() {
        try {
            largestINT(new int[]{});
            fail("Should be an Error!");
        } catch (final RuntimeException e) {
            assertTrue(true);
        }
    }
}

