package org.bignumberutils;

import java.math.BigDecimal;

import static org.bignumberutils.BigDecimalConstructors.*;
import static org.bignumberutils.BigDecimalOperator.*;

import org.junit.Test;

public class BigDecimalIntegrationTest {
	@Test
	public void testIntegration1() {
		double f1 = 3.3;
		double ak1 = 5.5;
//http://stackoverflow.com/questions/8202946/bigdecimal-symbols-parenthesis
		double t = ((1.00 / f1) * ((((4.00 / (ak1 + 1.00)) - (2.00 / (ak1 + 4.00))) - (1.00 / (ak1 + 5.00))) - (1 / (ak1 + 6.00))));
		System.out.println(t);

		BigDecimal bdF1 = BD(3.3);
		BigDecimal bdAk1 = BD(5.5);
		BigDecimal bdT = op(
				op(BD(1.00), DIV, bdF1),
				TIMES,
				op(op(op(op(BD(4.00), DIV, op(bdAk1, PLUS, BD(1.00))), MINUS,
						op(BD(2.00), DIV, op(bdAk1, PLUS, BD(4.00)))), MINUS,
						op(BD(1.00), DIV, op(bdAk1, PLUS, BD(5.00)))), MINUS,
						op(BD(1), DIV, op(bdAk1, PLUS, BD(6.00)))));
		System.out.println(bdT);

	}

}
