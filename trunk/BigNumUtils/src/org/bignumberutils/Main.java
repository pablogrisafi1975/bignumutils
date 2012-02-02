package org.bignumberutils;

import static org.bignumberutils.BigIntegerConstructors.BI;
import static org.bignumberutils.BigIntegerOperator.DIV;
import static org.bignumberutils.BigIntegerOperator.MINUS;
import static org.bignumberutils.BigIntegerOperator.PLUS;
import static org.bignumberutils.BigIntegerOperator.REM;
import static org.bignumberutils.BigIntegerOperator.TIMES;
import static org.bignumberutils.BigIntegerOperator.max;
import static org.bignumberutils.BigIntegerOperator.min;
import static org.bignumberutils.BigIntegerOperator.op;
import static org.bignumberutils.utils.Comparables.is;

import java.math.BigInteger;

public class Main {
	public static void main(String[] args) {
		BigInteger bi1 = BI("1");
		BigInteger bi2 = new BigInteger("2");
		BigInteger bi3 = new BigInteger("3");
		BigInteger bi4 = new BigInteger("4");
		BigInteger bi5 = new BigInteger("5");
		// ((1 + 2) / 3) % 4.
		BigInteger result = bi1.add(bi2).divide(bi3).remainder(bi4);
		System.out.println(result);

		result = op(op(op(bi1, PLUS, bi2), DIV, bi3), REM, bi4);
		System.out.println(result);

		// 2 + 3 * 4 = 14
		result = op(bi2, PLUS, bi3, TIMES, bi4);
		System.out.println(result);

		// 2 * 3 + 4 = 10
		result = op(bi2, TIMES, bi3, PLUS, bi4);
		System.out.println(result);

		// (2 + 3) ^ 2 + (2 - 3) * 4 =
		// 5 ^ 2 + (-1) * 4
		// 25 + - 4 ? 21
		result = op(op(bi2, PLUS, bi3).pow(2), PLUS, op(bi2, MINUS, bi3),
				TIMES, bi4);
		System.out.println(result);

		result = bi2.add(bi3).pow(2).add((bi2.subtract(bi3)).multiply(bi4));
		System.out.println(result);

		System.out.println(max(bi1, bi2, bi3, bi5));
		System.out.println(max(bi1, bi2, bi5, bi4));
		System.out.println(max(bi1, bi5, bi3, bi4));
		System.out.println(max(bi5, bi2, bi3, bi4));

		System.out.println(min(bi1, bi3, bi4, bi5));
		System.out.println(min(bi2, bi1, bi4, bi5));
		System.out.println(min(bi2, bi3, bi1, bi5));
		System.out.println(min(bi2, bi3, bi4, bi1));

		if (is(bi1).smallerThan(bi4)) {
			System.out.println("smaller");
		}

	}

}
