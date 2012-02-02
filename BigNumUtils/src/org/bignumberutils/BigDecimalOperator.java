package org.bignumberutils;

import java.math.BigDecimal;
import java.math.MathContext;

public enum BigDecimalOperator {
	PLUS(2) {
		@Override
		public BigDecimal execute(BigDecimal a, BigDecimal b) {
			return a.add(b);
		}
	},
	MINUS(2) {
		@Override
		public BigDecimal execute(BigDecimal a, BigDecimal b) {
			return a.subtract(b);
		}
	},
	TIMES(3) {
		@Override
		public BigDecimal execute(BigDecimal a, BigDecimal b) {
			return a.multiply(b);
		}
	},
	DIV(3) {
		@Override
		public BigDecimal execute(BigDecimal a, BigDecimal b) {
			return a.divide(b, MathContext.DECIMAL128);
		}
	},
	REM(3) {
		@Override
		public BigDecimal execute(BigDecimal a, BigDecimal b) {
			return a.remainder(b);
		}
	};


	public abstract BigDecimal execute(BigDecimal a, BigDecimal b);

	public final int precedence;

	private BigDecimalOperator(int precedence) {
		this.precedence = precedence;
	}

	boolean hasGreaterOrSamePrecedenceThan(BigDecimalOperator... operators) {
		for (BigDecimalOperator anotherOperator : operators) {
			if (anotherOperator.precedence > this.precedence) {
				return false;
			}
		}
		return true;
	}

	public static BigDecimal max(BigDecimal a, BigDecimal b) {
		return a.max(b);
	}

	public static BigDecimal max(BigDecimal a, BigDecimal... others) {
		BigDecimal max = a;
		for (BigDecimal b : others) {
			max = max.max(b);
		}
		return max;
	}

	public static BigDecimal min(BigDecimal a, BigDecimal b) {
		return a.min(b);
	}

	public static BigDecimal min(BigDecimal a, BigDecimal... others) {
		BigDecimal min = a;
		for (BigDecimal b : others) {
			min = min.min(b);
		}
		return min;
	}

	public static BigDecimal neg(BigDecimal a) {
		return a.negate();
	}

	public static BigDecimal op(BigDecimal a, BigDecimalOperator op,
			BigDecimal b) {
		return op.execute(a, b);
	}

	public static BigDecimal op(BigDecimal bd0, BigDecimalOperator op0,
			BigDecimal bd1, BigDecimalOperator op1, BigDecimal bd2) {
		if (op0.precedence >= op1.precedence) {
			return op(op(bd0, op0, bd1), op1, bd2);
		}
		return op(bd0, op0, op(bd1, op1, bd2));
	}

	public static BigDecimal op(BigDecimal bd0, BigDecimalOperator op0,
			BigDecimal bd1, BigDecimalOperator op1, BigDecimal bd2,
			BigDecimalOperator op2, BigDecimal bd3) {
		if (op0.hasGreaterOrSamePrecedenceThan(op1, op2)) {
			return op(op(bd0, op0, bd1), op1, bd2, op2, bd3);
		}
		if (op1.hasGreaterOrSamePrecedenceThan(op0, op2)) {
			return op(bd0, op0, op(bd1, op1, bd2), op2, bd3);
		}
		return op(bd0, op0, bd1, op1, op(bd2, op2, bd3));
	}

	public static BigDecimal op(BigDecimal bd0, BigDecimalOperator op0,
			BigDecimal bd1, BigDecimalOperator op1, BigDecimal bd2,
			BigDecimalOperator op2, BigDecimal bd3, BigDecimalOperator op3,
			BigDecimal bd4) {
		if (op0.hasGreaterOrSamePrecedenceThan(op1, op2, op3)) {
			return op(op(bd0, op0, bd1), op1, bd2, op2, bd3, op3, bd4);
		}
		if (op1.hasGreaterOrSamePrecedenceThan(op0, op2, op3)) {
			return op(bd0, op0, op(bd1, op1, bd2), op2, bd3, op3, bd4);
		}
		if (op2.hasGreaterOrSamePrecedenceThan(op0, op1, op3)) {
			return op(bd0, op0, bd1, op1, op(bd2, op2, bd3), op3, bd4);
		}
		return op(bd0, op0, bd1, op1, bd2, op2, op(bd3, op3, bd4));
	}

	public static BigDecimal op(BigDecimal bd0, BigDecimalOperator op0,
			BigDecimal bd1, BigDecimalOperator op1, BigDecimal bd2,
			BigDecimalOperator op2, BigDecimal bd3, BigDecimalOperator op3,
			BigDecimal bd4, BigDecimalOperator op4, BigDecimal bd5) {

		if (op0.hasGreaterOrSamePrecedenceThan(op1, op2, op3, op4)) {
			return op(op(bd0, op0, bd1), op1, bd2, op2, bd3, op3, bd4, op4, bd5);
		}
		if (op1.hasGreaterOrSamePrecedenceThan(op0, op2, op3, op4)) {
			return op(bd0, op0, op(bd1, op1, bd2), op2, bd3, op3, bd4, op4, bd5);
		}
		if (op2.hasGreaterOrSamePrecedenceThan(op0, op1, op3, op4)) {
			return op(bd0, op0, bd1, op1, op(bd2, op2, bd3), op3, bd4, op4, bd5);
		}
		if (op3.hasGreaterOrSamePrecedenceThan(op0, op1, op2, op4)) {
			return op(bd0, op0, bd1, op1, bd2, op2, op(bd3, op3, bd4), op4, bd5);
		}
		return op(bd0, op0, bd1, op1, bd2, op2, bd3, op3, op(bd4, op4, bd5));
	}

	public static BigDecimal prod(BigDecimal a, BigDecimal b) {
		return a.multiply(b);
	}

	public static BigDecimal prod(BigDecimal a, BigDecimal... others) {
		BigDecimal total = a;
		for (BigDecimal b : others) {
			total = total.multiply(b);
		}
		return total;
	}

	public static int sgn(BigDecimal a) {
		return a.signum();
	}

	public static BigDecimal sum(BigDecimal a, BigDecimal b) {
		return a.add(b);
	}

	public static BigDecimal sum(BigDecimal a, BigDecimal... others) {
		BigDecimal total = a;
		for (BigDecimal b : others) {
			total = total.add(b);
		}
		return total;
	}

	public static BigDecimal abs(BigDecimal a) {
		return a.abs();
	}

}
