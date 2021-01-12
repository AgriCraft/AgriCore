/*
 */
package com.agricraft.agricore.util;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ryan
 */
public class TypeHelperTest {
    
    private final int int_a = 1;
    private final int int_b = 2;
    private final int int_c = 3;
    
    private final double double_a = 1.0;
    private final double double_b = 2.0;
    private final double double_c = 3.0;

    private final String[] array_string = { "abc", "123", "you and me" };
    private final List<String> list_string = Arrays.asList(array_string);
    private final Set<String> set_string = Arrays.stream(array_string).collect(Collectors.toSet());

    /**
     * Test of forEachIn method, of class ReflectionHelper.
     */
    @Test
    public void testAsArray() {
        // Test Strings
        assertArrayEquals("Conversion from list to array works.", array_string, TypeHelper.asArray(list_string, String.class));
        final String[] set_expected = Arrays.copyOf(array_string, array_string.length);
        Arrays.sort(set_expected);
        final String[] set_result = TypeHelper.asArray(set_string, String.class);
        Arrays.sort(set_result);
        assertArrayEquals("Conversion from set to array works.", set_expected, set_result);
    }
    
}
