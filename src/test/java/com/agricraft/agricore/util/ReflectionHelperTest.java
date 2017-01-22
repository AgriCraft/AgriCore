/*
 */
package com.agricraft.agricore.util;

import com.google.common.util.concurrent.AtomicDouble;
import java.util.concurrent.atomic.AtomicInteger;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Ryan
 */
public class ReflectionHelperTest {
    
    private final int int_a = 1;
    private final int int_b = 2;
    private final int int_c = 3;
    
    private final double double_a = 1.0;
    private final double double_b = 2.0;
    private final double double_c = 3.0;

    /**
     * Test of forEachIn method, of class ReflectionHelper.
     */
    @Test
    public void testForEachValue() {
        // Test Integers
        final int int_sum = int_a + int_b + int_c;
        final AtomicInteger intCounter = new AtomicInteger();
        ReflectionHelper.forEachValueIn(this, Integer.class, intCounter::addAndGet);
        assertEquals("Sum all of type int.", int_sum, intCounter.get());
        
        // Test Doubles
        final double double_sum = double_a + double_b + double_c;
        final AtomicDouble doubleCounter = new AtomicDouble();
        ReflectionHelper.forEachValueIn(this, Double.class, doubleCounter::addAndGet);
        assertEquals("Sum all of type int.", double_sum, doubleCounter.get(), 0.0);
        
        // Test All
        final AtomicDouble numberCounter = new AtomicDouble();
        ReflectionHelper.forEachValueIn(this, Number.class, n -> numberCounter.addAndGet(n.doubleValue()));
        assertEquals("Sum all of numbers.", int_sum + double_sum, numberCounter.get(), 0.0);
    }
    
}
