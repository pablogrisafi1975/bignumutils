package org.bignumberutils;

import java.math.BigInteger;

public enum BigIntegerOperator {
	PLUS(2) {
		@Override
		public BigInteger execute(BigInteger a, BigInteger b) {
			return a.add(b);
		}
	},
	MINUS(2) {
		@Override
		public BigInteger execute(BigInteger a, BigInteger b) {
			return a.subtract(b);
		}
	},
	TIMES(3) {
		@Override
		public BigInteger execute(BigInteger a, BigInteger b) {
			return a.multiply(b);
		}
	},
	DIV(3) {
		@Override
		public BigInteger execute(BigInteger a, BigInteger b) {
			return a.divide(b);
		}
	},
	REM(3) {
		@Override
		public BigInteger execute(BigInteger a, BigInteger b) {
			return a.remainder(b);
		}
	},
	MOD(3) {
		@Override
		public BigInteger execute(BigInteger a, BigInteger b) {
			return a.mod(b);
		}
	};

	public abstract BigInteger execute(BigInteger a, BigInteger b);

	public final int precedence;

	private BigIntegerOperator(int precedence) {
		this.precedence = precedence;
	}

	boolean hasGreaterOrSamePrecedenceThan(BigIntegerOperator... operators) {
		for (BigIntegerOperator anotherOperator : operators) {
			if (anotherOperator.precedence > this.precedence) {
				return false;
			}
		}
		return true;
	}

	public static BigInteger max(BigInteger a, BigInteger b) {
		return a.max(b);
	}

	public static BigInteger max(BigInteger a, BigInteger... others) {
		BigInteger max = a;
		for (BigInteger b : others) {
			max = max.max(b);
		}
		return max;
	}

	public static BigInteger min(BigInteger a, BigInteger b) {
		return a.min(b);
	}

	public static BigInteger min(BigInteger a, BigInteger... others) {
		BigInteger min = a;
		for (BigInteger b : others) {
			min = min.min(b);
		}
		return min;
	}

	public static BigInteger neg(BigInteger a) {
		return a.negate();
	}

	public static BigInteger op(BigInteger a, BigIntegerOperator op,
			BigInteger b) {
		return op.execute(a, b);
	}

	public static BigInteger op(BigInteger bi0, BigIntegerOperator op0,
			BigInteger bi1, BigIntegerOperator op1, BigInteger bi2) {
		if (op0.precedence >= op1.precedence) {
			return op(op(bi0, op0, bi1), op1, bi2);
		}
		return op(bi0, op0, op(bi1, op1, bi2));
	}

	public static BigInteger op(BigInteger bi0, BigIntegerOperator op0,
			BigInteger bi1, BigIntegerOperator op1, BigInteger bi2,
			BigIntegerOperator op2, BigInteger bi3) {
		if (op0.hasGreaterOrSamePrecedenceThan(op1, op2)) {
			return op(op(bi0, op0, bi1), op1, bi2, op2, bi3);
		}
		if (op1.hasGreaterOrSamePrecedenceThan(op0, op2)) {
			return op(bi0, op0, op(bi1, op1, bi2), op2, bi3);
		}
		return op(bi0, op0, bi1, op1, op(bi2, op2, bi3));
	}

	public static BigInteger op(BigInteger bi0, BigIntegerOperator op0,
			BigInteger bi1, BigIntegerOperator op1, BigInteger bi2,
			BigIntegerOperator op2, BigInteger bi3, BigIntegerOperator op3,
			BigInteger bi4) {
		if (op0.hasGreaterOrSamePrecedenceThan(op1, op2, op3)) {
			return op(op(bi0, op0, bi1), op1, bi2, op2, bi3, op3, bi4);
		}
		if (op1.hasGreaterOrSamePrecedenceThan(op0, op2, op3)) {
			return op(bi0, op0, op(bi1, op1, bi2), op2, bi3, op3, bi4);
		}
		if (op2.hasGreaterOrSamePrecedenceThan(op0, op1, op3)) {
			return op(bi0, op0, bi1, op1, op(bi2, op2, bi3), op3, bi4);
		}
		return op(bi0, op0, bi1, op1, bi2, op2, op(bi3, op3, bi4));
	}

	public static BigInteger op(BigInteger bi0, BigIntegerOperator op0,
			BigInteger bi1, BigIntegerOperator op1, BigInteger bi2,
			BigIntegerOperator op2, BigInteger bi3, BigIntegerOperator op3,
			BigInteger bi4, BigIntegerOperator op4, BigInteger bi5) {

		if (op0.hasGreaterOrSamePrecedenceThan(op1, op2, op3, op4)) {
			return op(op(bi0, op0, bi1), op1, bi2, op2, bi3, op3, bi4, op4, bi5);
		}
		if (op1.hasGreaterOrSamePrecedenceThan(op0, op2, op3, op4)) {
			return op(bi0, op0, op(bi1, op1, bi2), op2, bi3, op3, bi4, op4, bi5);
		}
		if (op2.hasGreaterOrSamePrecedenceThan(op0, op1, op3, op4)) {
			return op(bi0, op0, bi1, op1, op(bi2, op2, bi3), op3, bi4, op4, bi5);
		}
		if (op3.hasGreaterOrSamePrecedenceThan(op0, op1, op2, op4)) {
			return op(bi0, op0, bi1, op1, bi2, op2, op(bi3, op3, bi4), op4, bi5);
		}
		return op(bi0, op0, bi1, op1, bi2, op2, bi3, op3, op(bi4, op4, bi5));
	}

	public static BigInteger prod(BigInteger a, BigInteger b) {
		return a.multiply(b);
	}

	public static BigInteger prod(BigInteger a, BigInteger... others) {
		BigInteger total = a;
		for (BigInteger b : others) {
			total = total.multiply(b);
		}
		return total;
	}

	public static int sgn(BigInteger a) {
		return a.signum();
	}

	public static BigInteger sum(BigInteger a, BigInteger b) {
		return a.add(b);
	}

	public static BigInteger sum(BigInteger a, BigInteger... others) {
		BigInteger total = a;
		for (BigInteger b : others) {
			total = total.add(b);
		}
		return total;
	}

	public static BigInteger abs(BigInteger a) {
		return a.abs();
	}

}
