package org.bignumberutils;

import static org.junit.Assert.*;

import java.math.BigInteger;
import java.util.Random;

import org.bignumberutils.BigIntegerConstructors;
import org.junit.Test;

public class BigIntegerConstructorsTest {
	BigInteger biA;
	BigInteger biB;

	@Test
	public void testBIByteArray() {
		biA = new BigInteger(new byte[] { 1, 2, 3, 4, 5 });
		biB = BigIntegerConstructors.BI(new byte[] { 1, 2, 3, 4, 5 });
		assertEquals(biA, biB);
	}

	@Test
	public void testBIIntByteArray() {
		biA = new BigInteger(1, new byte[] { 1, 2, 3, 4, 5 });
		biB = BigIntegerConstructors.BI(1, new byte[] { 1, 2, 3, 4, 5 });
		assertEquals(biA, biB);
	}


	@Test
	public void testBIIntRandom() {
		biA = new BigInteger(12,  new Random(333) );
		biB = BigIntegerConstructors.BI(12,  new Random(333) );
		assertEquals(biA, biB);
	}

	@Test
	public void testBIIntIntRandom() {
		biA = new BigInteger(12, 75, new Random(333) );
		biB = BigIntegerConstructors.BI(12, 75,  new Random(333) );
		assertEquals(biA, biB);
	}


	@Test
	public void testBIString() {
		biA = new BigInteger("333");
		biB = BigIntegerConstructors.BI("333");
		assertEquals(biA, biB);
	}

	@Test
	public void testBIStringInt() {
		biA = new BigInteger("333", 6);
		biB = BigIntegerConstructors.BI("333", 6);
		assertEquals(biA, biB);
	}

	@Test
	public void testBILong() {
		biA = BigInteger.valueOf(444L);
		biB = BigIntegerConstructors.BI(444L);
		assertEquals(biA, biB);
	}

	@Test
	public void testBIInt() {
		biA = BigInteger.valueOf(555);
		biB = BigIntegerConstructors.BI(555);
		assertEquals(biA, biB);
	}

}
