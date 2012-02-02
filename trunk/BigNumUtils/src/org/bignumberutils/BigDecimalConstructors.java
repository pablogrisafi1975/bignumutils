package org.bignumberutils;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public final class BigDecimalConstructors {
	private BigDecimalConstructors() {
		// never create an instance
	}

	public static BigDecimal BD(BigInteger val) {
		return new BigDecimal(val);
	}

	public static BigDecimal BD(BigInteger unscaledVal, int scale) {
		return new BigDecimal(unscaledVal, scale);
	}

	public static BigDecimal BD(BigInteger unscaledVal, int scale,
			MathContext mc) {
		return new BigDecimal(unscaledVal, scale, mc);
	}

	public static BigDecimal BD(BigInteger val, MathContext mc) {
		return new BigDecimal(val, mc);
	}

	public static BigDecimal BD(char[] in) {
		return new BigDecimal(in);
	}

	public static BigDecimal BD(char[] in, int offset, int len) {
		return new BigDecimal(in, offset, len);
	}

	public static BigDecimal BD(char[] in, int offset, int len, MathContext mc) {
		return new BigDecimal(in, offset, len, mc);
	}

	public static BigDecimal BD(char[] in, MathContext mc) {
		return new BigDecimal(in, mc);
	}

	public static BigDecimal BD(double val) {
		return BigDecimal.valueOf(val);
	}

	public static BigDecimal BD(double val, MathContext mc) {
		return new BigDecimal(val, mc);
	}

	public static BigDecimal BD(int val) {
		return new BigDecimal(val);
	}

	public static BigDecimal BD(int val, MathContext mc) {
		return new BigDecimal(val, mc);
	}

	public static BigDecimal BD(long val) {
		return BigDecimal.valueOf(val);
	}

	public static BigDecimal BD(long val, MathContext mc) {
		return new BigDecimal(val, mc);
	}

	public static BigDecimal BD(String val) {
		return new BigDecimal(val);
	}

	public static BigDecimal BD(String val, MathContext mc) {
		return new BigDecimal(val, mc);
	}

	public static BigDecimal BD(long unscaledVal, int scale) {
		return BigDecimal.valueOf(unscaledVal, scale);
	}

}
