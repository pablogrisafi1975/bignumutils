# Examples #

All this examples belong to BigDecimalIntegrationTest class. That is to say, they work. But I changed variable names here and there to make things more readable.

## Cuadratic polynomial ##
Let's start for something simple, a function like
y = ax<sup>2</sup> + bx + c.
That was the mathematical notation, there is no programming language I'm aware of that allows you to write things like that. But good old BASIC comes really close:
```
//Basic: a * x ^ 2 + b * x + c
```
Java does not have on language support for exponents, so even using doubles becomes somewhat odd
```
double a = 1.1;
double b = 2.2;
double c = 3.3;
double x = 4.4;
double y = a * Math.pow(x, 2) + b * x + dc;
```
But if you try to use BigDecimals things get ugly really fast:
```
BigDecimal a = new BigDecimal("1.1");
BigDecimal b = new BigDecimal("2.2");
BigDecimal c = new BigDecimal("3.3");
BigDecimal x = new BigDecimal("4.4");

BigDecimal y = a.multiply(x.pow(2)).add(b.multiply(x)).add(c);

```
I'm not saying this is hard to read, I'm saying it is way to different from standard mathematical notation people is used to. Check how BigNumUtils work
```
BigDecimal ba = BD(1.1);
BigDecimal bb = BD(2.2);
BigDecimal bc = BD(3.3);
BigDecimal bx = BD(4.4);

//Basic:         a    *     x ^ 2     +    b    *    x    +    c
BigDecimal y= op(a, TIMES, x.pow(2), PLUS, b, TIMES, x , PLUS, c);
```
Check how close to basic (or to mathematics) it looks. Of course you need to mentally remove the ',' and the 'op' at the beginning, but you an read it out load and _everyone_ (programmers, business analysts, mathematicians, your mum) will understand it.

## Mean Velocity ##
This is a basic physics formula: s = (x<sub>f</sub> - x<sub>i</sub>)/(t<sub>f</sub> - t<sub>i</sub>)
Using java doubles will be nice
```
double xf = 22.2;
double xi = 11.1;
double tf = 55.5;
double ti = 33.3;
double y = (xf - xi)/(tf - ti);
```
But again, BigDecimals ...
```
BigDecimal s = xf.subtract(xi).divide(tf.subtract(ti));
```
Not that it I don't need to use the first parenthesis, so notation becomes less cluttered, but not simpler. With BigNumUtils
```
//                   (xf    -   xi )   /     (tf    -    ti) 
BigDecimal s  = op(op(xf, MINUS, xi), DIV, op(tf, MINUS, ti));
```
Again, we need to mentally remove the ',' and the 'op', but by using the same operator precedence than mathematics you get a more readable solution.

## Angular Movement ##
The original formula looks like this: m = w(tf - ti) + 1/2a(tf - ti)<sup>2</sup>
(ok, I'm using 'w' for omega and 'a' for alfa but you get the point)
With doubles
```
double m =  w * (tf - ti) + 0.5 * a * Math.pow(tf - ti, 2.0);
```
With BigDecimals
```
BigDecimal m = w.multiply(tf.subtract(ti)).add(new BigDecimal("0.5").multiply(a).multiply(tf.subtract(ti).pow(2)));
```
Seriously, WTF!
With BigNumUtils
```
                  w    *      ( tf   -    ti)    +     0.5      *    a    *     (tf   -    ti ) ^  2
BigDecimal m = op(w, TIMES, op(tf, MINUS, ti), PLUS, BD(0.5), TIMES, a ,TIMES, op(tf, MINUS, ti).pow(2));
```
## Generic rules ##
I'm confident enough to try some generic ideas to translate a formula from double/natural/mathematical/infix notation to BigNumUtils notation:
  1. Surround the whole thing in parenthesis if needed
  1. Every time a parenthesis opens, add an 'op'
  1. Surround every number in 'BD()'. You should not use magic numbers!
  1. Change the operators
    1. '+'  becomes ', PLUS,' (Yes, remember the ',' before and after)
    1. '-'  becomes ', MINUS,' (Yes, remember the ',' before and after)
    1. '`*`'  becomes ', TIMES,' (Yes, remember the ',' before and after)
    1. '/'  becomes ', DIV,' (Yes, remember the ',' before and after)
  1. Whatever else you need
In the spirit of a good pirate, these are guidelines more than strict rules. Use you brain!

## The monster ##
From [StackOverflow](http://stackoverflow.com/questions/8202946/bigdecimal-symbols-parenthesis) came this monster:
```
double f1 = 3.3;
double ak1 = 5.5;
double t = ((1.00 / f1) * ((((4.00 / (ak1 + 1.00)) - (2.00 / (ak1 + 4.00))) - (1.00 / (ak1 + 5.00))) - (1 / (ak1 + 6.00))));
```
I'm not even going to try to translate that to BigDecimal notation. But following the guidelines, I can attempt a BigNumUtils translation:
1  - Parenthesis are already there!
2  - After adding op to opening parenthesis I got
```
op(op(1.00 / f1) * op(op(op(op(4.00 / op(ak1 + 1.00)) - op(2.00 / op(ak1 + 4.00))) - op(1.00 / op(ak1 + 5.00))) - op(1 / op(ak1 + 6.00))));
```
I did that using a text editor

3 - After surrounding number with BD() I got
```
op(op(BD(1.00) / f1) * op(op(op(op(BD(4.00) / op(ak1 + BD(1.00))) - op(BD(2.00) / op(ak1 + BD(4.00)))) - op(BD(1.00) / op(ak1 + BD(5.00)))) - op(BD(1) / op(ak1 + BD(6.00)))));
```
Again, with a text editor
4 - And after changing the operators I got
```
op(op(BD(1.00) , DIV,  f1) , TIMES,  op(op(op(op(BD(4.00) , DIV,  op(ak1 , PLUS,  BD(1.00))) , MINUS,  op(BD(2.00) , DIV,  op(ak1 , PLUS,  BD(4.00)))) , MINUS,  op(BD(1.00) , DIV,  op(ak1 , PLUS,  BD(5.00)))) , MINUS,  op(BD(1) , DIV,  op(ak1 , PLUS,  BD(6.00)))));
```

I'm not even trying to read this thing out load, I really advice you to use partial formulas and avoid magic numbers...but just for testing, you can try and see the result works as expected. The important point is  thar I was able to  translate this horrible formula without even using my mind, just copy/paste and search/replace.
Good work!