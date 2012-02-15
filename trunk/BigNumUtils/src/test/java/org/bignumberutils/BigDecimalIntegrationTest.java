package org.bignumberutils;

import java.math.BigDecimal;
import static org.junit.Assert.*;

import static org.bignumberutils.BigDecimalConstructors.*;
import static org.bignumberutils.BigDecimalOperator.*;

import org.junit.Test;

public class BigDecimalIntegrationTest {

	@Test
	public void testCuadratic(){
		//Mathematics: ax2 + bx + c
		//Basic: a * x ^ 2 + b * x + c
		//Double
		double da = 1.1;
		double db = 2.2;
		double dc = 3.3;
		double dx = 4.4;
		//Basic:          a *      x ^ 2      + b  * x  + c
		double dresult = da * Math.pow(dx, 2) + db * dx + dc;

		BigDecimal ba = BD(1.1);
		BigDecimal bb = BD(2.2);
		BigDecimal bc = BD(3.3);
		BigDecimal bx = BD(4.4);

		BigDecimal bresult = ba.multiply(bx.pow(2)).add(bb.multiply(bx)).add(bc);
		assertCloseEnough(dresult, bresult);

		//Basic:     a     *     x ^ 2      +     b    *     x     +    c
		bresult = op(ba, TIMES, bx.pow(2), PLUS, bb, TIMES, bx , PLUS, bc);
		assertCloseEnough(dresult, bresult);
	}


	@Test
	public void testMeanVelocity(){
		//Mathematics: (xf - xi)
		//             --------
		//             (tf - ti)
		//Basic: (xf - xi) / (tf - ti)
		//Double
		double dxf = 22.2;
		double dxi = 11.1;
		double dtf = 55.5;
		double dti = 33.3;
		double dresult = (dxf - dxi)/(dtf - dti);

		//BigDecimal
		BigDecimal bxf = BD(22.2);
		BigDecimal bxi = BD(11.1);
		BigDecimal btf = BD(55.5);
		BigDecimal bti = BD(33.3);

		BigDecimal bresult = bxf.subtract(bxi).divide(btf.subtract(bti));
		assertCloseEnough(dresult, bresult);
		//             (xf     -    xi )   /     ( tf    -    ti)
		bresult = op(op(bxf, MINUS, bxi), DIV, op(btf, MINUS, bti));
		assertCloseEnough(dresult, bresult);


	}

	@Test
	public void testAngularMovement(){
		//Mathematics: w(tf - ti) + 1/2a(tf - ti)2
		//Basic: w * (tf - ti) + 0.5 * a * (tf - ti) ^ 2
		//Double
		double dw = 11.1;
		double da = 22.2;
		double dtf = 55.5;
		double dti = 33.3;
		//Basic: w * (tf - ti) + 0.5 * a * (tf - ti) ^ 2
		double dresult =  dw * (dtf - dti) + 0.5 * da * Math.pow(dtf - dti, 2.0);
		//BigDecimal
		BigDecimal bw = BD(11.1);
		BigDecimal ba = BD(22.2);
		BigDecimal btf = BD(55.5);
		BigDecimal bti = BD(33.3);

		BigDecimal bresult = bw.multiply(btf.subtract(bti)).add(BD(0.5).multiply(ba).multiply(btf.subtract(bti).pow(2)));
		assertCloseEnough(dresult, bresult);
		//Basic:     w     *     ( tf    -     ti)    +     0.5      *    a     *      ( tf   -     ti )  ^  2
		bresult = op(bw,TIMES, op(btf, MINUS, bti), PLUS, BD(0.5), TIMES, ba ,TIMES, op(btf, MINUS, bti).pow(2));
		assertCloseEnough(dresult, bresult);
	}

	@Test
	public void testMoreComplicated(){
		//Mathematics:  ((a + b) / c)2.
		//Basic: ((a + b) / c) ^ 2.
		//Double
		double da = 1.1;
		double db = 2.2;
		double dc = 3.3;
		//Basic:
		double dresult = Math.pow((da + db) / dc, 2);

		BigDecimal ba = BD(1.1);
		BigDecimal bb = BD(2.2);
		BigDecimal bc = BD(3.3);

		BigDecimal bresult =  ba.add(bb).divide(bc).pow(2);
		assertCloseEnough(dresult, bresult);

		//Basic:  (  (   a    +   b )   /   c ) ^ 2.
		bresult = op(op(ba, PLUS, bb), DIV, bc).pow(2);
		assertCloseEnough(dresult, bresult);
	}



	@Test
	public void testMonster() {
		double df1 = 3.3;
		double dak1 = 5.5;
		//http://stackoverflow.com/questions/8202946/bigdecimal-symbols-parenthesis
		double dt = ((1.00 / df1) * ((((4.00 / (dak1 + 1.00)) - (2.00 / (dak1 + 4.00))) - (1.00 / (dak1 + 5.00))) - (1 / (dak1 + 6.00))));

		BigDecimal bf1 = BD(3.3);
		BigDecimal bak1 = BD(5.5);
		BigDecimal bt = op(
				op(BD(1.00), DIV, bf1),
				TIMES,
				op(op(op(op(BD(4.00), DIV, op(bak1, PLUS, BD(1.00))), MINUS,
						op(BD(2.00), DIV, op(bak1, PLUS, BD(4.00)))), MINUS,
						op(BD(1.00), DIV, op(bak1, PLUS, BD(5.00)))), MINUS,
						op(BD(1), DIV, op(bak1, PLUS, BD(6.00)))));
		assertCloseEnough(dt, bt);

	}

	private void assertCloseEnough(double dresult, BigDecimal bresult) {
		assertEquals(dresult, bresult.doubleValue(), 0.00001);
	}

}
