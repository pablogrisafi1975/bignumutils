package org.bignumberutils.utils;

import static org.junit.Assert.*;

import java.math.BigInteger;

import org.junit.Test;

public class ComparablesTest {

	BigInteger bi1 = new BigInteger("1");
	BigInteger bi2 = new BigInteger("2");
	BigInteger bi2Again = new BigInteger("2");
	BigInteger bi3 = new BigInteger("3");

	@Test
	public void testIs() {
		Comparables<BigInteger> comparator = Comparables
				.is(bi2);
		assertNotNull(comparator);
	}

	@Test
	public void testGreaterThanTrue() {
		assertTrue(Comparables.is(bi2).greaterThan(bi1));
	}

	@Test
	public void testGreaterThanFalse() {
		assertFalse(Comparables.is(bi2).greaterThan(bi3));
	}

	@Test
	public void testGreaterThanFalseEquals() {
		assertFalse(Comparables.is(bi2).greaterThan(bi2Again));
	}

	@Test
	public void testGreaterOrEqualThanTrue() {
		assertTrue(Comparables.is(bi2).greaterOrEqualThan(bi1));
	}

	@Test
	public void testGreaterOrEqualThanTrueEquals() {
		assertTrue(Comparables.is(bi2).greaterOrEqualThan(bi2Again));
	}

	@Test
	public void testGreaterOrEqualThanFalse() {
		assertFalse(Comparables.is(bi2).greaterOrEqualThan(bi3));
	}

	@Test
	public void testSmallerThanTrue() {
		assertTrue(Comparables.is(bi2).smallerThan(bi3));
	}

	@Test
	public void testSmallerThanFalse() {
		assertFalse(Comparables.is(bi2).smallerThan(bi1));
	}

	@Test
	public void testSmallerThanFalseEquals() {
		assertFalse(Comparables.is(bi2).smallerThan(bi2Again));
	}

	@Test
	public void testSmallerOrEqualThanTrue() {
		assertTrue(Comparables.is(bi2).smallerOrEqualThan(bi3));
	}

	@Test
	public void testSmallerOrEqualThanTrueEquals() {
		assertTrue(Comparables.is(bi2).smallerOrEqualThan(bi2Again));
	}

	@Test
	public void testSmallerOrEqualThanFalse() {
		assertFalse(Comparables.is(bi2).smallerOrEqualThan(bi1));
	}
}
