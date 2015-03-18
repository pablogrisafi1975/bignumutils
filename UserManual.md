# User Manual #

## The basics ##

Of course you need to download and add bignumutils-x.x.x.jar to your classpath, whatever that may be in your particular system. Jar file is just a few KB, maybe you prefer to add the source files directly and see whats going on.
After that you need to add a single static import to your java file. Either use
```
import static org.bignumberutils.BigDecimalOperator.*;
```
or
```
import static org.bignumberutils.BigIntegerOperator.*;
```

For basic operation, the magic words are **PLUS**, **MINUS**, **TIMES**, **DIV** and **REM** (and **MOD** for BigInteger only). And the master key that makes magic happen it's called **op()**, from operation.

So if you want to make a + b `*` c - d, do not worry about anything and just write
```
BigDecimal result = op(a, PLUS, b, TIMES, c, MINUS, d);
```
And the multiplication will be solved first, as you can expect.
Lets say you jave a operation which involves parenthesis, like (a + b) / (c + d). Every parenthesis requires a new op(), so this is just as easy as
```
BigDecimal result = op(op(a, PLUS, b), DIV, op( c, PLUS, d));
```
Did I mention op is type-safe? Yes, you can only write alternated bignums and operations. You can't make syntax mistakes here.
That's all, folks. But there are some extra gifts.

Don't forget to check the [Examples](Examples.md) page to see more...er...examples
## Extras ##

Sometimes I found myself trying to do sums with lots of members. For this cases I wrote a **sum()** function that allows me to do that with a syntax nicer than both
```
BigDecimal result = a1.add(a2).add(a3)
```
or
```
BigDecimal result = op(a1, PLUS, a2, PLUS, a3);
```
The **sum()** method is already (static) imported, you can write
```
BigDecimal result = sum(a1, a2, a3);
```
There is a symmetric **prod()** method, I've never really used.
Also there are some simple operators (sgn, neg, abs, max, min) I found easier to read as functions. So instead of writing
```
BigDecimal result = a1.max(a2).max(a3);//this is disgusting!
```
you can have
```
BigDecimal result = max(a1, a2, a3);// as nature intended
```
And also instead of
```
BigDecimal result1 = a.negate();
BigDecimal result2 = a.signum(); //what?
BigDecimal result3 = a.abs();
```
you'll have
```
BigDecimal result1 = neg(a);
BigDecimal result2 = sgn(a); //that's what I'm talking about
BigDecimal result3 = abs(a);
```

## Constructing big numbers ##

As you all should know, when you create a `Double a = 0.3;` this is not exactly 0.3 but something like 0.299999999999999988897769753748434595763683319091796875. If you work with Double accuracy this is good enough, but when you start making conversions between doubles and BigDecimals this is really annoying. We all know we should probably always use `new BigDecimal("0.3")` or   `BigDecimal.valueOf(0.3)` but code like `new BigDecimal(ammount)` when ammount is a double appears all the time.
Well, fear no more. Just add another static import
```
import static org.bignumberutils.BigDecimalConstructors.*;
```
And you will get lots of methods, all called `BD(something...)` that will not only allow you to type a lot of less code, but also always call the right constructor.
There is also a symmetric BigIntegerConstructors with a bunch of `BI(whatever)`methods, but it is not that useful.
So, instead of
```
BigDecimal a = new BigDecimal(doubleAmmount);//WRONG!!!!!
BigDecimal b = new BigDecimal(intAmmount);
```
use
```
BigDecimal a = BD(doubleAmmount);
BigDecimal b = BD(intAmmount);
```
Less typing and less error prone.

## Comparing ##

This is the last thing, I swear.
When you have two BigNumbers (or two instances of `class Whatever implements Comparable<Whatever>`) and you need to do something when one of them is greater than the other you usually write something like
```
if(bigDecimal1.compareTo(bigDecimal2) > 0){
   //whatever
}
```
This is idiomatic java, and we are all used to it, but when you show the code to a non-java-programmer, like a businesses annalist or someone more human, they always find it almost unreadable. And they are right.
Well, just another static import
```
import static org.bignumberutils.utils.Comparables.is;
```
There is just onw method added, it is called **is(Something comparable)** and now you can write
```
if (is(bigDecimal1).greaterThan(bigDecimal2)) {
   //whatever
}
```
You see? Now even human resources people can read that.
Of course you have greaterThan, greaterOrEqualThan, smallerThan, smallerOrEqualThan and it's type-safe, you can't compare a BigInteger with anything but another BigInteger.


I'm not sure if this utility class belongs here, but I'm not going to start a new project for this class only. It would be a nice addition as a defender method in Java8, I think.