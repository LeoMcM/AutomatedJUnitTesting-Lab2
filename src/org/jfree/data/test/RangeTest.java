package org.jfree.data.test;

import static org.junit.Assert.*;
import org.jfree.data.Range;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for the `contains(double value)` method in the `Range` class.
 * 
 * Test cases follow Black-Box Testing techniques:
 * - Equivalence Class Partitioning (ECP)
 * - Boundary Value Analysis (BVA)
 * 
 * Test scenarios include:
 * - Values inside the range
 * - Boundary values (lower and upper)
 * - Values outside the range
 * - Special cases (negative values, zero)
 */
public class RangeTest {

    private Range testRange; // Test object

    /** 
     * Setup: Initializes a `Range` object before each test.
     */
    @Before
    public void setUp() {
        testRange = new Range(2, 8); // Test range from 2 to 8
    }

    /**
     * Tests a value inside the range (ECP - valid input).
     */
    @Test
    public void testContainsInsideRange() {
        assertTrue("5.0 should be inside the range (2,8)", testRange.contains(5.0));
    }

    /**
     * Tests a value just below the lower bound (BVA - failure case).
     */
    @Test
    public void testContainsBelowLowerBound() {
        // Value 1.9 is below lower bound 2.0, so should return false.
        assertFalse("1.9 should be outside the range (2,8)", testRange.contains(1.9));
    }

    /**
     * Tests a value exactly at the lower bound (BVA - success case).
     */
    @Test
    public void testContainsAtLowerBound() {
        assertTrue("2.0 should be inside the range (2,8)", testRange.contains(2.0));
    }

    /**
     * Tests a value exactly at the upper bound (BVA - success case).
     */
    @Test
    public void testContainsAtUpperBound() {
        assertTrue("8.0 should be inside the range (2,8)", testRange.contains(8.0));
    }

    /**
     * Tests a value just above the upper bound (BVA - failure case).
     */
    @Test
    public void testContainsAboveUpperBound() {
        assertFalse("8.1 should be outside the range (2,8)", testRange.contains(8.1));
    }

    /**
     * Tests a negative number outside the range (ECP - invalid input).
     */
    @Test
    public void testContainsNegativeValue() {
        Range negativeRange = new Range(0, 10);
        assertFalse("-1.0 should be outside the range (0,10)", negativeRange.contains(-1.0));
    }

    /**
     * Tests zero inside the range (BVA - special case).
     */
    @Test
    public void testContainsZeroBoundary() {
        Range zeroBoundaryRange = new Range(-5, 5);
        assertTrue("0.0 should be inside the range (-5,5)", zeroBoundaryRange.contains(0.0));
    }
}
