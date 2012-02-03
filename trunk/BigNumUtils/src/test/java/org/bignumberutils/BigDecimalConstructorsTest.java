package org.bignumberutils;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

import org.bignumberutils.BigDecimalConstructors;
import org.junit.Test;

public class BigDecimalConstructorsTest {
	BigDecimal biA;
	BigDecimal biB;
	MathContext mathContext = new MathContext(12);

	@Test
	public void testBDBigInteger() {
		biA = new BigDecimal(BigInteger.valueOf(33));
		biB = BigDecimalConstructors.BD(BigInteger.valueOf(33));
		assertEquals(biA, biB);
	}

	@Test
	public void testBDBigIntegerInt() {
		biA = new BigDecimal(BigInteger.valueOf(33), 44);
		biB = BigDecimalConstructors.BD(BigInteger.valueOf(33), 44);
		assertEquals(biA, biB);
	}

	@Test
	public void testBDBigIntegerIntMathContext() {
		biA = new BigDecimal(BigInteger.valueOf(33), 44, mathContext);
		biB = BigDecimalConstructors
				.BD(BigInteger.valueOf(33), 44, mathContext);
		assertEquals(biA, biB);
	}

	@Test
	public void testBDBigIntegerMathContext() {
		biA = new BigDecimal(BigInteger.valueOf(33), mathContext);
		biB = BigDecimalConstructors.BD(BigInteger.valueOf(33), mathContext);
		assertEquals(biA, biB);
	}

	@Test
	public void testBDCharArray() {
		biA = new BigDecimal(new char[] { '1', '1', '1' });
		biB = BigDecimalConstructors.BD(new char[] { '1', '1', '1' });
		assertEquals(biA, biB);
	}

	@Test
	public void testBDCharArrayIntInt() {
		biA = new BigDecimal(new char[] { '1', '1', '1' }, 1, 2);
		biB = BigDecimalConstructors.BD(new char[] { '1', '1', '1' }, 1, 2);
		assertEquals(biA, biB);
	}

	@Test
	public void testBDCharArrayIntIntMathContext() {
		biA = new BigDecimal(new char[] { '1', '1', '1' }, 1, 2, mathContext);
		biB = BigDecimalConstructors.BD(new char[] { '1', '1', '1' }, 1, 2,
				mathContext);
		assertEquals(biA, biB);
	}

	@Test
	public void testBDCharArrayMathContext() {
		biA = new BigDecimal(new char[] { '1', '1', '1' }, mathContext);
		biB = BigDecimalConstructors.BD(new char[] { '1', '1', '1' },
				mathContext);
		assertEquals(biA, biB);
	}

	@Test
	public void testBDDouble() {
		biA = BigDecimal.valueOf(3.3);
		biB = BigDecimalConstructors.BD(3.3);
		assertEquals(biA, biB);
	}

	@Test
	public void testBDDoubleMathContext() {
		biA = new BigDecimal(3.3, mathContext);
		biB = BigDecimalConstructors.BD(3.3, mathContext);
		assertEquals(biA, biB);
	}

	@Test
	public void testBDInt() {
		biA = new BigDecimal(3);
		biB = BigDecimalConstructors.BD(3);
		assertEquals(biA, biB);
	}

	@Test
	public void testBDIntMathContext() {
		biA = new BigDecimal(3, mathContext);
		biB = BigDecimalConstructors.BD(3, mathContext);
		assertEquals(biA, biB);
	}

	@Test
	public void testBDLong() {
		biA = new BigDecimal(3L);
		biB = BigDecimalConstructors.BD(3L);
		assertEquals(biA, biB);
	}

	@Test
	public void testBDLongMathContext() {

		biA = new BigDecimal(3L, mathContext);
		biB = BigDecimalConstructors.BD(3L, mathContext);
		assertEquals(biA, biB);
	}

	@Test
	public void testBDString() {
		biA = new BigDecimal("3");
		biB = BigDecimalConstructors.BD("3");
		assertEquals(biA, biB);
	}

	@Test
	public void testBDStringMathContext() {
		biA = new BigDecimal("3", mathContext);
		biB = BigDecimalConstructors.BD("3", mathContext);
		assertEquals(biA, biB);
	}

	@Test
	public void testBDLongInt() {

		biA = BigDecimal.valueOf(3L, 4);
		biB = BigDecimalConstructors.BD(3L, 4);
		assertEquals(biA, biB);
	}

}
