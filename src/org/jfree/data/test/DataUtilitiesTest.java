package org.jfree.data.test;

import static org.junit.Assert.*;
import org.jfree.data.DataUtilities;
import org.jfree.data.DefaultKeyedValues;
import org.jfree.data.DefaultKeyedValues2D;
import org.jfree.data.KeyedValues;
import org.jfree.data.Values2D;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit tests for DataUtilities class. Covers calculateColumnTotal and
 * calculateRowTotal using Equivalence-Class Partitioning (ECP), Boundary-Value
 * Analysis (BVA), and Strong Equivalence-Class Testing (SECT).
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

	// ========== Test Cases for calculateColumnTotal(Values2D data, int column)
	// ==========

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

	// ========== Test Cases for calculateRowTotal(Values2D data, int row)
	// ==========

	/**
	 * Test calculateRowTotal with valid data.
	 */
	@Test
	public void testCalculateRowTotalValidData() {
		double result = DataUtilities.calculateRowTotal(values2D, 0);
		assertEquals("Row total should be 5.0", 5.0, result, 0.0000001d);
	}

	/**
	 * Test calculateRowTotal with an out-of-bounds row index.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testCalculateRowTotalInvalidRowIndex() {
		DataUtilities.calculateRowTotal(values2D, 2); // Only 2 rows exist
	}

	/**
	 * Test calculateRowTotal with an empty dataset.
	 */
	@Test
	public void testCalculateRowTotalEmptyDataset() {
		DefaultKeyedValues2D emptyValues = new DefaultKeyedValues2D();
		double result = DataUtilities.calculateRowTotal(emptyValues, 0);
		assertEquals("Empty dataset should return 0.0", 0.0, result, 0.0000001d);
	}

	/**
	 * Test calculateRowTotal with a negative row index. Expected: Should throw
	 * IndexOutOfBoundsException.
	 */
	@Test(expected = IndexOutOfBoundsException.class)
	public void testCalculateRowTotalNegativeRowIndex() {
		DataUtilities.calculateRowTotal(values2D, -1);
	}

	/**
	 * Test calculateRowTotal at the last valid row index.
	 */
	@Test
	public void testCalculateRowTotalLastRow() {
		DefaultKeyedValues2D multiRowValues = new DefaultKeyedValues2D();
		multiRowValues.addValue(2.0, 0, 0);
		multiRowValues.addValue(3.0, 0, 1);
		multiRowValues.addValue(5.0, 1, 0);
		multiRowValues.addValue(7.0, 1, 1);

		double result = DataUtilities.calculateRowTotal(multiRowValues, 1);
		assertEquals("Last row total should be 12.0", 12.0, result, 0.0000001d);
	}

	/**
	 * Test calculateRowTotal when dataset contains negative values.
	 */
	@Test
	public void testCalculateRowTotalWithNegativeValues() {
		DefaultKeyedValues2D negativeValues = new DefaultKeyedValues2D();
		negativeValues.addValue(-3.0, 0, 0);
		negativeValues.addValue(-7.0, 0, 1);
		double result = DataUtilities.calculateRowTotal(negativeValues, 0);
		assertEquals("Row total should correctly sum negatives (-10.0)", -10.0, result, 0.0000001d);
	}

	/**
	 * Test calculateRowTotal when dataset contains zeros.
	 */
	@Test
	public void testCalculateRowTotalWithZeros() {
		DefaultKeyedValues2D zeroValues = new DefaultKeyedValues2D();
		zeroValues.addValue(0.0, 0, 0);
		zeroValues.addValue(5.0, 0, 1);
		zeroValues.addValue(0.0, 0, 2);
		double result = DataUtilities.calculateRowTotal(zeroValues, 0);
		assertEquals("Row total should be 5.0 when zeros are present", 5.0, result, 0.0000001d);
	}

	// ========== Test Cases for createNumberArray(double[] data) ==========

	/**
	 * Test createNumberArray with valid data.
	 */
	@Test
	public void testCreateNumberArrayValidData() {
		double[] data = { 1.0, 2.0, 3.0 };
		Number[] result = DataUtilities.createNumberArray(data);
		assertEquals("Array length should be 3", 3, result.length);
		assertEquals("First element should be 1.0", 1.0, result[0]);
	}

	/**
	 * Test createNumberArray with an empty array.
	 */
	@Test
	public void testCreateNumberArrayEmptyArray() {
		double[] data = {};
		Number[] result = DataUtilities.createNumberArray(data);
		assertEquals("Empty array should return an empty result", 0, result.length);
	}

	/**
	 * Test createNumberArray with a single element.
	 */
	@Test
	public void testCreateNumberArraySingleElement() {
		double[] data = { 5.0 };
		Number[] result = DataUtilities.createNumberArray(data);
		assertEquals("Array length should be 1", 1, result.length);
		assertEquals("Single element should be 5.0", 5.0, result[0]);
	}

	/**
	 * Test createNumberArray with null input. Expected: Should throw
	 * IllegalArgumentException.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCreateNumberArrayNullInput() {
		DataUtilities.createNumberArray(null);
	}

// ========== Test Cases for createNumberArray2D(double[][] data) ==========

	/**
	 * Test createNumberArray2D with valid 2D array data.
	 */
	@Test
	public void testCreateNumberArray2DValidData() {
		double[][] data = { { 1.0, 2.0 }, { 3.0, 4.0 } };
		Number[][] result = DataUtilities.createNumberArray2D(data);
		assertEquals("Array should have 2 rows", 2, result.length);
		assertEquals("First row should have 2 elements", 2, result[0].length);
		assertEquals("First element should be 1.0", 1.0, result[0][0]);
	}

	/**
	 * Test createNumberArray2D with an empty 2D array.
	 */
	@Test
	public void testCreateNumberArray2DEmptyArray() {
		double[][] data = {};
		Number[][] result = DataUtilities.createNumberArray2D(data);
		assertEquals("Empty 2D array should return an empty result", 0, result.length);
	}

	/**
	 * Test createNumberArray2D with a single row.
	 */
	@Test
	public void testCreateNumberArray2DSingleRow() {
		double[][] data = { { 1.0, 2.0, 3.0 } };
		Number[][] result = DataUtilities.createNumberArray2D(data);
		assertEquals("Array should have 1 row", 1, result.length);
		assertEquals("Row should have 3 elements", 3, result[0].length);
		assertEquals("First element should be 1.0", 1.0, result[0][0]);
	}

	/**
	 * Test createNumberArray2D with a single column.
	 */
	@Test
	public void testCreateNumberArray2DSingleColumn() {
		double[][] data = { { 1.0 }, { 2.0 }, { 3.0 } };
		Number[][] result = DataUtilities.createNumberArray2D(data);
		assertEquals("Array should have 3 rows", 3, result.length);
		assertEquals("First row should have 1 element", 1, result[0].length);
		assertEquals("First element should be 1.0", 1.0, result[0][0]);
	}

	/**
	 * Test createNumberArray2D with null input. Expected: Should throw
	 * IllegalArgumentException.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCreateNumberArray2DNullInput() {
		DataUtilities.createNumberArray2D(null);
	}

	// ========== Test Cases for getCumulativePercentages(KeyedValues data)
	// ==========

	/**
	 * Test getCumulativePercentages with valid data.
	 */
	@Test
	public void testGetCumulativePercentagesValidData() {
		DefaultKeyedValues data = new DefaultKeyedValues();
		data.addValue("A", 2);
		data.addValue("B", 3);
		data.addValue("C", 5);

		KeyedValues result = DataUtilities.getCumulativePercentages(data);

		assertEquals("Cumulative percentage for A should be 0.2", 0.2, result.getValue("A").doubleValue(), 0.0001);
		assertEquals("Cumulative percentage for B should be 0.5", 0.5, result.getValue("B").doubleValue(), 0.0001);
		assertEquals("Cumulative percentage for C should be 1.0", 1.0, result.getValue("C").doubleValue(), 0.0001);
	}

	/**
	 * Test getCumulativePercentages with an empty dataset.
	 */
	@Test
	public void testGetCumulativePercentagesEmptyData() {
		DefaultKeyedValues data = new DefaultKeyedValues();
		KeyedValues result = DataUtilities.getCumulativePercentages(data);
		assertEquals("Empty dataset should return zero length", 0, result.getItemCount());
	}

	/**
	 * Test getCumulativePercentages with a single entry.
	 */
	@Test
	public void testGetCumulativePercentagesSingleEntry() {
		DefaultKeyedValues data = new DefaultKeyedValues();
		data.addValue("A", 10);

		KeyedValues result = DataUtilities.getCumulativePercentages(data);
		assertEquals("Single entry should be 100% (1.0)", 1.0, result.getValue("A").doubleValue(), 0.0001);
	}

	/**
	 * Test getCumulativePercentages with negative values.
	 */
	@Test
	public void testGetCumulativePercentagesNegativeValues() {
		DefaultKeyedValues data = new DefaultKeyedValues();
		data.addValue("A", -3);
		data.addValue("B", 6);
		data.addValue("C", -2);

		KeyedValues result = DataUtilities.getCumulativePercentages(data);

		assertEquals("Cumulative percentage for A should be -0.3333", -0.3333, result.getValue("A").doubleValue(),
				0.0001);
		assertEquals("Cumulative percentage for B should be 0.3333", 0.3333, result.getValue("B").doubleValue(),
				0.0001);
		assertEquals("Cumulative percentage for C should be 1.0", 1.0, result.getValue("C").doubleValue(), 0.0001);
	}

	/**
	 * Test getCumulativePercentages with null input. Expected: Should throw
	 * IllegalArgumentException.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testGetCumulativePercentagesNullInput() {
		DataUtilities.getCumulativePercentages(null);
	}
}