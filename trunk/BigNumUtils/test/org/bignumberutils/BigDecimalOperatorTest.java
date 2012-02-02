package org.bignumberutils;

import static org.bignumberutils.BigDecimalConstructors.BD;
import static org.bignumberutils.BigDecimalOperator.DIV;
import static org.bignumberutils.BigDecimalOperator.MINUS;
import static org.bignumberutils.BigDecimalOperator.PLUS;
import static org.bignumberutils.BigDecimalOperator.REM;
import static org.bignumberutils.BigDecimalOperator.TIMES;
import static org.bignumberutils.BigDecimalOperator.abs;
import static org.bignumberutils.BigDecimalOperator.max;
import static org.bignumberutils.BigDecimalOperator.min;
import static org.bignumberutils.BigDecimalOperator.neg;
import static org.bignumberutils.BigDecimalOperator.op;
import static org.bignumberutils.BigDecimalOperator.prod;
import static org.bignumberutils.BigDecimalOperator.sgn;
import static org.bignumberutils.BigDecimalOperator.sum;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.bignumberutils.BigDecimalOperator;
import org.junit.Test;

public class BigDecimalOperatorTest {

	private static final BigDecimal bd1 = BD(1.1);
	private static final BigDecimal bd2 = BD(2.2);
	private static final BigDecimal bd3 = BD(3.3);
	private static final BigDecimal bd4 = BD(4.4);
	private static final BigDecimal bd5 = BD(5.5);
	private static final BigDecimal bd6 = BD(6.6);
	private static final BigDecimal bd7 = BD(7.7);
	private static final BigDecimal bd8 = BD(8.8);
	private static final BigDecimal bd9 = BD(9.9);


	/* Precedence */

	@Test
	public void testHasGreaterOrSamePrecedenceThanADDvsDIV() {
		assertFalse(BigDecimalOperator.PLUS
				.hasGreaterOrSamePrecedenceThan(BigDecimalOperator.DIV));
	}

	@Test
	public void testHasGreaterOrSamePrecedenceThanDIVvsADD() {
		assertTrue(BigDecimalOperator.DIV
				.hasGreaterOrSamePrecedenceThan(BigDecimalOperator.PLUS));
	}

	@Test
	public void testHasGreaterOrSamePrecedenceThanDIVvsADDMINUS() {
		assertTrue(BigDecimalOperator.DIV.hasGreaterOrSamePrecedenceThan(
				BigDecimalOperator.PLUS, BigDecimalOperator.MINUS));
	}

	@Test
	public void testHasGreaterOrSamePrecedenceThanADDvsDIVMINUS() {
		assertFalse(BigDecimalOperator.PLUS.hasGreaterOrSamePrecedenceThan(
				BigDecimalOperator.DIV, BigDecimalOperator.MINUS));
	}

	@Test
	public void testHasGreaterOrSamePrecedenceThanMINUSvsMINUS() {
		assertTrue(BigDecimalOperator.MINUS
				.hasGreaterOrSamePrecedenceThan(BigDecimalOperator.MINUS));
	}

	/* 1 operators */

	@Test
	public void testOp_15rem4() {
		// 15 % 4 = 3
		assertEquals(BD(3), op(BD(15), REM, BD(4)));
	}

	@Test
	public void testOp_2times3() {
		// 2.2 * 3.3 = 7.26
		assertEquals(BD(7.26), op(bd2, TIMES, bd3));
	}

	@Test
	public void testOp_1div3() {
		// 1 * 3 = 0.333...
		assertEquals(BD(0.333), op(bd1, DIV, bd3).setScale(3, RoundingMode.DOWN));
		//I'm just checking no arithmetic exception is thrown
	}

	/* 2 operators */

	@Test
	public void testOp_2plus3times4() {
		// 2.2 + 3.3 * 4.4 = 16.72
		assertEquals(BD(16.72), op(bd2, PLUS, bd3, TIMES, bd4));
	}

	@Test
	public void testOp_2times3plus4() {
		// 2.2 * 3.3 + 4.4 = 11.66
		assertEquals(BD(11.66), op(bd2, TIMES, bd3, PLUS, bd4));
	}

	/* 3 operators */

	@Test
	public void testOp_2times3plus4minus5() {
		// 2.2 * 3.3 + 4.4.4 - 5.5 = 6.16
		assertEquals(BD(6.16), op(bd2, TIMES, bd3, PLUS, bd4, MINUS, bd5));
	}

	@Test
	public void testOp_2plus3times4minus5() {
		// 2.2 + 3.3 * 4.4.4 - 5.5 = 11.22
		assertEquals(BD(11.22), op(bd2, PLUS, bd3, TIMES, bd4, MINUS, bd5));
	}

	@Test
	public void testOp_2plus3plus5div5() {
		// 2.2 + 3.3 + 5.5 / 5.5 = 6.5
		assertEquals(BD(6.5), op(bd2, PLUS, bd3, PLUS, bd5, DIV, bd5));
	}



	/* 4 operators */

	@Test
	public void testOp_2plus3plus4minus5plus7() {
		// 2.2 + 3.3 + 4.4 - 5.5 + 7.7 = 12.1
		assertEquals(BD(12.1), op(bd2, PLUS, bd3, PLUS, bd4, MINUS, bd5, PLUS, bd7));
	}

	@Test
	public void testOp_2plus3times4minus5plus7() {
		// 2.2 + 3.3 * 4.4 - 5.5 + 7.7 = 18.92
		assertEquals(BD(18.92),
				op(bd2, PLUS, bd3, TIMES, bd4, MINUS, bd5, PLUS, bd7));
	}

	@Test
	public void testOp_2plus3plus7times5minus6() {
		// 2.2 + 3.3 + 4.4 * 7.7 - 6.6 = 32.78
		assertEquals(BD(32.78),
				op(bd2, PLUS, bd3, PLUS, bd4, TIMES, bd7, MINUS, bd6));
	}

	@Test
	public void testOp_19plus3plus4minus5plus7() {
		// 19 + 3.3 + 4.4 - 5.5 * 7.7 = -15.65
		assertEquals(BD(-15.65),
				op(BD(19), PLUS, bd3, PLUS, bd4, MINUS, bd5, TIMES, bd7));
	}

	@Test
	public void testOp_2times3plus4times8div2() {
		// 2.2 * 3.3 + 4.4 * 8.8 / 2.2 = 24.86
		assertEquals(BD(24.86), op(bd2, TIMES, bd3, PLUS, bd4, TIMES, bd8, DIV, bd2));
	}

	@Test
	public void testOp_2times3times4plus9mod3() {
		// 2.2 * 3.3 * 4.4 + 9.9 % 3.3 = 31.944
		assertEquals(BD(31.944),
				op(bd2, TIMES, bd3, TIMES, bd4, PLUS, bd9, REM, bd3));
	}

	/* 5 operators */

	@Test
	public void testOp_2plus3plus4minus5plus7minus6() {
		// 2.2 + 3.3 + 4.4 - 5.5 + 7.7 - 6.6 = 5.5
		assertEquals(
				BD(5.5),
				op(bd2, PLUS, bd3, PLUS, bd4, MINUS, bd5, PLUS, bd7, MINUS, bd6));
	}

	@Test
	public void testOp_2times3plus4minus5plus7minus6() {
		// 2.2 * 3.3 + 4.4 - 5.5 + 7.7 - 6.6 = 7.26
		assertEquals(
				BD(7.26),
				op(bd2, TIMES, bd3, PLUS, bd4, MINUS, bd5, PLUS, bd7, MINUS,
						bd6));
	}

	@Test
	public void testOp_2plus3times4minus5plus7minus6() {
		// 2.2 + 3.3 * 4.4 - 5.5 + 7.7 - 6.6 = 12.32
		assertEquals(
				BD(12.32),
				op(bd2, PLUS, bd3, TIMES, bd4, MINUS, bd5, PLUS, bd7, MINUS,
						bd6));
	}

	@Test
	public void testOp_2plus3plus4times7plus5minus6() {
		// 2.2 + 3.3 + 4.4 * 7.7 + 5.5 - 6.6 = 38.28
		assertEquals(
				BD(38.28),
				op(bd2, PLUS, bd3, PLUS, bd4, TIMES, bd7, PLUS, bd5, MINUS, bd6));
	}

	@Test
	public void testOp_2plus3plus4minus5times7minus6() {
		// 2.2 + 3.3 + 4.4 - 5.5 * 7.7 - 6.6 = -39.5
		assertEquals(
				BD(-39.05),
				op(bd2, PLUS, bd3, PLUS, bd4, MINUS, bd5, TIMES, bd7, MINUS,
						bd6));
	}

	@Test
	public void testOp_2plus3plus4minus5plus7times6() {
		// 2.2 + 3.3 + 4.4 - 5.5 + 7.7 * 6.6= 55.22
		assertEquals(
				BD(55.22),
				op(bd2, PLUS, bd3, PLUS, bd4, MINUS, bd5, PLUS, bd7, TIMES, bd6));
	}

	/*  Helper Methods */

	@Test
	public void testProd() {
		assertEquals(BD(35.1384), prod(bd1, bd3, bd2, bd4));
	}

	@Test
	public void testProd2() {
		assertEquals(BD(14.52), prod(bd4, bd3));
	}

	@Test
	public void testSgnNegative() {
		assertEquals(-1, sgn(BD(-2)));
	}

	@Test
	public void testSgnPositive() {
		assertEquals(1, sgn(bd2));
	}

	@Test
	public void testSgnZero() {
		assertEquals(0, sgn(BigDecimal.ZERO));
	}

	@Test
	public void testSum() {
		assertEquals(BD(13.2), sum(bd4, bd3, bd3, bd2));
	}

	@Test
	public void testSum2() {
		assertEquals(bd7, sum(bd4, bd3));
	}

	@Test
	public void testAbsNegative() {
		assertEquals(bd2, abs(BD(-2.2)));
	}

	@Test
	public void testAbsPositive() {
		assertEquals(bd2, abs(bd2));
	}

	@Test
	public void testMax2First() {
		assertEquals(bd4, max(bd4, bd2));
	}

	@Test
	public void testMax2Second() {
		assertEquals(bd5, max(bd4, bd5));
	}

	@Test
	public void testMaxFirst() {
		assertEquals(bd4, max(bd4, bd2, bd1, bd3));
	}

	@Test
	public void testMaxOther() {
		assertEquals(bd5, max(bd4, bd2, bd5, bd3));
	}

	@Test
	public void testMin2First() {
		assertEquals(bd4, min(bd4, bd5));
	}

	@Test
	public void testMin2Second() {
		assertEquals(bd3, min(bd4, bd3));
	}

	@Test
	public void testMinFirst() {
		assertEquals(bd1, min(bd1, bd2, bd5, bd3));
	}

	@Test
	public void testMinOther() {
		assertEquals(bd1, min(bd4, bd2, bd5, bd1));
	}

	@Test
	public void testNegNegative() {
		assertEquals(bd2, neg(BD(-2.2)));
	}

	@Test
	public void testNegPositive() {
		assertEquals(BD(-2.2), neg(bd2));
	}

	@Test
	public void testNegZero() {
		assertEquals(BigDecimal.ZERO, neg(BigDecimal.ZERO));
	}

}
