package org.bignumberutils;

import java.math.BigInteger;
import java.util.Random;


public final class BigIntegerConstructors {
	private BigIntegerConstructors(){
		//never create an instance
	}

	public static BigInteger BI(byte[] val) {
		return new BigInteger(val);
	}

	public static BigInteger BI(int signum, byte[] magnitude) {
		return new BigInteger(signum, magnitude);
	}

	public static BigInteger BI(int bitLength, int certainty, Random rnd) {
		return new BigInteger(bitLength, certainty, rnd);
	}

	public static BigInteger BI(int numBits, Random rnd) {
		return new BigInteger(numBits, rnd);
	}

	public static BigInteger BI(String val) {
		return new BigInteger(val);
	}

	public static BigInteger BI(String val, int radix) {
		return new BigInteger(val, radix);
	}

	public static BigInteger BI(long val) {
		return BigInteger.valueOf(val);
	}

	public static BigInteger BI(int val) {
		return BigInteger.valueOf(val);
	}
}
