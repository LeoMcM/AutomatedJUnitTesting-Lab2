package org.jfree.data.test;

import static org.junit.Assert.*;
import org.jfree.data.Range;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for the `contains(double value)` method in the `Range` class.
 * 
 * Test cases follow Black-Box Testing techniques: - Equivalence Class
 * Partitioning (ECP) - Boundary Value Analysis (BVA)
 * 
 * Test scenarios include: - Values inside the range - Boundary values (lower
 * and upper) - Values outside the range - Special cases (negative values, zero)
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

	// ========== Test Cases for intersects(double lower, double upper) ==========

	/**
	 * Tests a case where the input range is exactly the same as `this` range. (ECP
	 * - Valid Case)
	 */
	@Test
	public void testIntersectsExactOverlap() {
		assertTrue("Range (2,8) should intersect with itself", testRange.intersects(2, 8));
	}

	/**
	 * Tests a case where the input range partially overlaps on the left. (ECP -
	 * Partial Overlap)
	 */
	@Test
	public void testIntersectsPartialOverlapLeft() {
		assertTrue("Range (2,8) should intersect with (0,4)", testRange.intersects(0, 4));
	}

	/**
	 * Tests a case where the input range partially overlaps on the right. (ECP -
	 * Partial Overlap)
	 */
	@Test
	public void testIntersectsPartialOverlapRight() {
		assertTrue("Range (2,8) should intersect with (6,10)", testRange.intersects(6, 10));
	}

	/**
	 * Tests a case where the input range is fully inside `this` range. (ECP - Valid
	 * Case)
	 */
	@Test
	public void testIntersectsRangeInside() {
		assertTrue("Range (2,8) should intersect with (3,6)", testRange.intersects(3, 6));
	}

	/**
	 * Tests a case where the input range just touches the lower bound. (BVA - Lower
	 * Boundary)
	 */
	@Test
	public void testIntersectsTouchingLowerBound() {
		assertTrue("Range (2,8) should intersect with (0,2)", testRange.intersects(0, 2));
	}

	/**
	 * Tests a case where the input range just touches the upper bound. (BVA - Upper
	 * Boundary)
	 */
	@Test
	public void testIntersectsTouchingUpperBound() {
		assertTrue("Range (2,8) should intersect with (8,10)", testRange.intersects(8, 10));
	}

	/**
	 * Tests a case where the input range is completely below `this` range. (ECP -
	 * Invalid Case)
	 */
	@Test
	public void testIntersectsNoOverlapBelow() {
		assertFalse("Range (2,8) should not intersect with (-2,1.9)", testRange.intersects(-2, 1.9));
	}

	/**
	 * Tests a case where the input range is completely above `this` range. (ECP -
	 * Invalid Case)
	 */
	@Test
	public void testIntersectsNoOverlapAbove() {
		assertFalse("Range (2,8) should not intersect with (8.1,12)", testRange.intersects(8.1, 12));
	}

	/**
	 * Tests a case where the inputs are flipped (upper < lower). (Edge Case)
	 */
	@Test
	public void testIntersectsFlippedInputs() {
		assertFalse("Range (2,8) should not intersect with invalid range (6,4)", testRange.intersects(6, 4));
	}

	/**
	 * Tests a case where the input range is extremely large. (Edge Case - Extreme
	 * Values)
	 */
	@Test
	public void testIntersectsExtremeRange() {
		assertTrue("Range (2,8) should intersect with a massive range (-1000,1000)", testRange.intersects(-1000, 1000));
	}

// ========== Test Cases for expand(Range range, double lowerMargin, double upperMargin) ==========

	/**
	 * Expands a range normally with positive lower and upper margins.
	 */
	@Test
	public void testExpandNormalExpansion() {
		Range expanded = Range.expand(testRange, 0.5, 0.25);
		assertEquals("Lower bound should be -1", -1.0, expanded.getLowerBound(), 0.00001);
		assertEquals("Upper bound should be 9.5", 9.5, expanded.getUpperBound(), 0.00001);
	}

	/**
	 * Tests expansion with zero margins (range should remain unchanged).
	 */
	@Test
	public void testExpandNoChange() {
		Range expanded = Range.expand(testRange, 0.0, 0.0);
		assertEquals("Lower bound should remain 2", 2.0, expanded.getLowerBound(), 0.00001);
		assertEquals("Upper bound should remain 8", 8.0, expanded.getUpperBound(), 0.00001);
	}

	/**
	 * Tests negative margins (contracts instead of expanding).
	 */
	@Test
	public void testExpandNegativeMargins() {
		Range expanded = Range.expand(testRange, -0.5, -0.5);
		assertEquals("Lower bound should be 4", 4.0, expanded.getLowerBound(), 0.00001);
		assertEquals("Upper bound should be 6", 6.0, expanded.getUpperBound(), 0.00001);
	}

	/**
	 * Tests a huge expansion (100% increase).
	 */
	@Test
	public void testExpandHugeExpansion() {
		Range expanded = Range.expand(testRange, 1.0, 1.0);
		assertEquals("Lower bound should be -4", -4.0, expanded.getLowerBound(), 0.00001);
		assertEquals("Upper bound should be 14", 14.0, expanded.getUpperBound(), 0.00001);
	}

	/**
	 * Tests negative lower margin and positive upper margin.
	 */
	@Test
	public void testExpandMixedMargins() {
		Range expanded = Range.expand(testRange, -0.25, 0.5);
		assertEquals("Lower bound should be 3.5", 3.5, expanded.getLowerBound(), 0.00001);
		assertEquals("Upper bound should be 11", 11.0, expanded.getUpperBound(), 0.00001);
	}

	/**
	 * Tests expansion with a null input (should throw IllegalArgumentException).
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testExpandNullRange() {
		Range.expand(null, 0.5, 0.5);
	}
}