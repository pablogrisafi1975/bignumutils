package org.bignumberutils;

import static org.bignumberutils.BigIntegerConstructors.*;
import static org.bignumberutils.BigIntegerOperator.*;
import static org.junit.Assert.*;

import java.math.BigInteger;

import org.bignumberutils.BigIntegerOperator;
import org.junit.Test;

public class BigIntegerOperatorTest {

	private static final BigInteger bi1 = BI(1);
	private static final BigInteger bi2 = BI(2);
	private static final BigInteger bi3 = BI(3);
	private static final BigInteger bi4 = BI(4);
	private static final BigInteger bi5 = BI(5);
	private static final BigInteger bi6 = BI(6);
	private static final BigInteger bi7 = BI(7);
	private static final BigInteger bi8 = BI(8);
	private static final BigInteger bi9 = BI(9);
	private static final BigInteger bi10 = BI(10);
	private static final BigInteger bi11 = BI(11);
	private static final BigInteger bi12 = BI(12);
	// private static final BigInteger bi13 = BI(13);
	private static final BigInteger bi14 = BI(14);
	private static final BigInteger bi15 = BI(15);
	private static final BigInteger bi16 = BI(16);
	// private static final BigInteger bi17 = BI(17);
	private static final BigInteger bi18 = BI(18);
	private static final BigInteger bi19 = BI(19);

	/* Precedence */

	@Test
	public void testHasGreaterOrSamePrecedenceThanADDvsDIV() {
		assertFalse(BigIntegerOperator.PLUS
				.hasGreaterOrSamePrecedenceThan(BigIntegerOperator.DIV));
	}

	@Test
	public void testHasGreaterOrSamePrecedenceThanDIVvsADD() {
		assertTrue(BigIntegerOperator.DIV
				.hasGreaterOrSamePrecedenceThan(BigIntegerOperator.PLUS));
	}

	@Test
	public void testHasGreaterOrSamePrecedenceThanDIVvsADDMINUS() {
		assertTrue(BigIntegerOperator.DIV.hasGreaterOrSamePrecedenceThan(
				BigIntegerOperator.PLUS, BigIntegerOperator.MINUS));
	}

	@Test
	public void testHasGreaterOrSamePrecedenceThanADDvsDIVMINUS() {
		assertFalse(BigIntegerOperator.PLUS.hasGreaterOrSamePrecedenceThan(
				BigIntegerOperator.DIV, BigIntegerOperator.MINUS));
	}

	@Test
	public void testHasGreaterOrSamePrecedenceThanMINUSvsMINUS() {
		assertTrue(BigIntegerOperator.MINUS
				.hasGreaterOrSamePrecedenceThan(BigIntegerOperator.MINUS));
	}

	/* 1 operators */

	@Test
	public void testOp_15rem4() {
		// 15 % 4 = 3
		assertEquals(bi3, op(bi15, REM, bi4));
	}

	@Test
	public void testOp_2times3() {
		// 2 * 3 = 6
		assertEquals(bi6, op(bi2, TIMES, bi3));
	}

	/* 2 operators */

	@Test
	public void testOp_2plus3times4() {
		// 2 + 3 * 4 = 14
		assertEquals(bi14, op(bi2, PLUS, bi3, TIMES, bi4));
	}

	@Test
	public void testOp_2times3plus4() {
		// 2 * 3 + 4 = 10
		assertEquals(bi10, op(bi2, TIMES, bi3, PLUS, bi4));
	}

	/* 3 operators */

	@Test
	public void testOp_2times3plus4minus5() {
		// 2 * 3 + 4 - 5 = 5
		assertEquals(bi5, op(bi2, TIMES, bi3, PLUS, bi4, MINUS, bi5));
	}

	@Test
	public void testOp_2plus3times4minus5() {
		// 2 + 3 * 4 - 5 = 9
		assertEquals(bi9, op(bi2, PLUS, bi3, TIMES, bi4, MINUS, bi5));
	}

	@Test
	public void testOp_2plus3plus5div5() {
		// 2 + 3 + 5 / 5 = 7
		assertEquals(bi6, op(bi2, PLUS, bi3, PLUS, bi5, DIV, bi5));
	}



	/* 4 operators */

	@Test
	public void testOp_2plus3plus4minus5plus7() {
		// 2 + 3 + 4 - 5 + 7 = 11
		assertEquals(bi11, op(bi2, PLUS, bi3, PLUS, bi4, MINUS, bi5, PLUS, bi7));
	}

	@Test
	public void testOp_2plus3times4minus5plus7() {
		// 2 + 3 * 4 - 5 + 7 = 16
		assertEquals(bi16,
				op(bi2, PLUS, bi3, TIMES, bi4, MINUS, bi5, PLUS, bi7));
	}

	@Test
	public void testOp_2plus3plus4times5minus7() {
		// 2 + 3 + 4 * 5 - 7 = 18
		assertEquals(bi18,
				op(bi2, PLUS, bi3, PLUS, bi4, TIMES, bi5, MINUS, bi7));
	}

	@Test
	public void testOp_19plus3plus4minus5plus7() {
		// 19 + 3 + 4 - 5 * 7 = -9
		assertEquals(BI(-9),
				op(bi19, PLUS, bi3, PLUS, bi4, MINUS, bi5, TIMES, bi7));
	}

	@Test
	public void testOp_2times3plus4times5div10() {
		// 2 * 3 + 4 * 5 / 10 = 8
		assertEquals(bi8, op(bi2, TIMES, bi3, PLUS, bi4, TIMES, bi5, DIV, bi10));
	}

	@Test
	public void testOp_2times3times4plus9mod3() {
		// 2 * 3 * 4 + 9 % 3 = 24
		assertEquals(BI(24),
				op(bi2, TIMES, bi3, TIMES, bi4, PLUS, bi9, MOD, bi3));
	}

	/* 5 operators */

	@Test
	public void testOp_2plus3plus4minus5plus7minus6() {
		// 2 + 3 + 4 - 5 + 7 - 6 = 5
		assertEquals(
				bi5,
				op(bi2, PLUS, bi3, PLUS, bi4, MINUS, bi5, PLUS, bi7, MINUS, bi6));
	}

	@Test
	public void testOp_2times3plus4minus5plus7minus6() {
		// 2 * 3 + 4 - 5 + 7 - 6 = 6
		assertEquals(
				bi6,
				op(bi2, TIMES, bi3, PLUS, bi4, MINUS, bi5, PLUS, bi7, MINUS,
						bi6));
	}

	@Test
	public void testOp_2plus3times4minus5plus7minus6() {
		// 2 + 3 * 4 - 5 + 7 - 6 = 10
		assertEquals(
				bi10,
				op(bi2, PLUS, bi3, TIMES, bi4, MINUS, bi5, PLUS, bi7, MINUS,
						bi6));
	}

	@Test
	public void testOp_2plus3plus4times5plus7minus6() {
		// 2 + 3 + 4 * 5 + 7 - 6 = 26
		assertEquals(
				BI(26),
				op(bi2, PLUS, bi3, PLUS, bi4, TIMES, bi5, PLUS, bi7, MINUS, bi6));
	}

	@Test
	public void testOp_2plus3plus4minus5times7minus6() {
		// 2 + 3 + 4 - 5 * 7 - 6 = -32
		assertEquals(
				BI(-32),
				op(bi2, PLUS, bi3, PLUS, bi4, MINUS, bi5, TIMES, bi7, MINUS,
						bi6));
	}

	@Test
	public void testOp_2plus3plus4minus5plus7times6() {
		// 2 + 3 + 4 - 5 + 7 * 6 = 46
		assertEquals(
				BI(46),
				op(bi2, PLUS, bi3, PLUS, bi4, MINUS, bi5, PLUS, bi7, TIMES, bi6));
	}

	/*  Helper Methods */

	@Test
	public void testProd() {
		assertEquals(BI(24), prod(bi1, bi3, bi2, bi4));
	}

	@Test
	public void testProd2() {
		assertEquals(bi12, prod(bi4, bi3));
	}

	@Test
	public void testSgnNegative() {
		assertEquals(-1, sgn(BI(-2)));
	}

	@Test
	public void testSgnPositive() {
		assertEquals(1, sgn(bi2));
	}

	@Test
	public void testSgnZero() {
		assertEquals(0, sgn(BigInteger.ZERO));
	}

	@Test
	public void testSum() {
		assertEquals(bi12, sum(bi4, bi3, bi3, bi2));
	}

	@Test
	public void testSum2() {
		assertEquals(bi7, sum(bi4, bi3));
	}

	@Test
	public void testAbsNegative() {
		assertEquals(bi2, abs(BI(-2)));
	}

	@Test
	public void testAbsPositive() {
		assertEquals(bi2, abs(bi2));
	}

	@Test
	public void testMax2First() {
		assertEquals(bi4, max(bi4, bi2));
	}

	@Test
	public void testMax2Second() {
		assertEquals(bi5, max(bi4, bi5));
	}

	@Test
	public void testMaxFirst() {
		assertEquals(bi4, max(bi4, bi2, bi1, bi3));
	}

	@Test
	public void testMaxOther() {
		assertEquals(bi5, max(bi4, bi2, bi5, bi3));
	}

	@Test
	public void testMin2First() {
		assertEquals(bi4, min(bi4, bi5));
	}

	@Test
	public void testMin2Second() {
		assertEquals(bi3, min(bi4, bi3));
	}

	@Test
	public void testMinFirst() {
		assertEquals(bi1, min(bi1, bi2, bi5, bi3));
	}

	@Test
	public void testMinOther() {
		assertEquals(bi1, min(bi4, bi2, bi5, bi1));
	}

	@Test
	public void testNegNegative() {
		assertEquals(bi2, neg(BI(-2)));
	}

	@Test
	public void testNegPositive() {
		assertEquals(BI(-2), neg(bi2));
	}

	@Test
	public void testNegZero() {
		assertEquals(BigInteger.ZERO, neg(BigInteger.ZERO));
	}

}
