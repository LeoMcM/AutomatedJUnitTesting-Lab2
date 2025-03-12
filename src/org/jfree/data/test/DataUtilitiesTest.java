package org.jfree.data.test;

import static org.junit.Assert.*;
import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.Values2D;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for DataUtilities class. Covers calculateColumnTotal using
 * Equivalence-Class Partitioning (ECP), Boundary-Value Analysis (BVA), and
 * Strong Equivalence-Class Testing (SECT).
 */
public class DataUtilitiesTest {

	private Values2D values2D;

	@Before
	public void setUp() {
		// Using DefaultKeyedValues2D as per assignment requirements
		DefaultKeyedValues2D testValues = new DefaultKeyedValues2D();
		testValues.addValue(1.0, 0, 0);
		testValues.addValue(4.0, 1, 0);
		values2D = testValues;
	}

	/**
	 * Test calculateColumnTotal with valid data.
	 */
	@Test
	public void testCalculateColumnTotalValidData() {
		double result = DataUtilities.calculateColumnTotal(values2D, 0);
		assertEquals("Column total should be 5.0", 5.0, result, 0.0000001d);
	}

	/**
	 * Test calculateColumnTotal with an out-of-bounds column index.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testCalculateColumnTotalInvalidColumnIndex() {
		DataUtilities.calculateColumnTotal(values2D, 2); // Only 1 column exists
	}

	/**
	 * Test calculateColumnTotal with an empty dataset.
	 */
	@Test
	public void testCalculateColumnTotalEmptyDataset() {
		DefaultKeyedValues2D emptyValues = new DefaultKeyedValues2D();
		double result = DataUtilities.calculateColumnTotal(emptyValues, 0);
		assertEquals("Empty dataset should return 0.0", 0.0, result, 0.0000001d);
	}

	/**
	 * Test calculateColumnTotal with a negative column index. Expected: Should
	 * throw IndexOutOfBoundsException.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testCalculateColumnTotalNegativeColumnIndex() {
		DataUtilities.calculateColumnTotal(values2D, -1);
	}

	/**
	 * Test calculateColumnTotal at the last valid column index.
	 */
	@Test
	public void testCalculateColumnTotalLastColumn() {
		DefaultKeyedValues2D multiColumnValues = new DefaultKeyedValues2D();
		multiColumnValues.addValue(2.0, 0, 0);
		multiColumnValues.addValue(3.0, 1, 0);
		multiColumnValues.addValue(5.0, 0, 1);
		multiColumnValues.addValue(7.0, 1, 1);

		double result = DataUtilities.calculateColumnTotal(multiColumnValues, 1);
		assertEquals("Last column total should be 12.0", 12.0, result, 0.0000001d);
	}

	/**
	 * Test calculateColumnTotal when dataset contains negative values.
	 */
	@Test
	public void testCalculateColumnTotalWithNegativeValues() {
		DefaultKeyedValues2D negativeValues = new DefaultKeyedValues2D();
		negativeValues.addValue(-3.0, 0, 0);
		negativeValues.addValue(-7.0, 1, 0);
		double result = DataUtilities.calculateColumnTotal(negativeValues, 0);
		assertEquals("Column total should correctly sum negatives (-10.0)", -10.0, result, 0.0000001d);
	}

	/**
	 * Test calculateColumnTotal when dataset contains zeros.
	 */
	@Test
	public void testCalculateColumnTotalWithZeros() {
		DefaultKeyedValues2D zeroValues = new DefaultKeyedValues2D();
		zeroValues.addValue(0.0, 0, 0);
		zeroValues.addValue(5.0, 1, 0);
		zeroValues.addValue(0.0, 2, 0);
		double result = DataUtilities.calculateColumnTotal(zeroValues, 0);
		assertEquals("Column total should be 5.0 when zeros are present", 5.0, result, 0.0000001d);
	}
}
