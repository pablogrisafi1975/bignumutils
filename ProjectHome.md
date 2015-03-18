This page is ketp here for historical reasons. All this information should be in README.md 
# bignumutils #

This mini dsl will allow you to work with BigDecimal and BigInteger almost as it Java had operator overload. Well, at least code is more readable and you will use mathematical operator precedence.

---

## Project status at 2010-02-07 ##
### Good points: ###
  * It is usable.
  * It passes all tests, see [surefire test report](http://bignumutils.googlecode.com/svn/trunk/BigNumUtils/docs/surefire/surefire-report.html).
  * It has great coverage, see [coverage report](http://bignumutils.googlecode.com/svn/trunk/BigNumUtils/docs/cobertura/index.html).
  * It is just 5 classes or so, so you will be able to fix it if anything does not work as expected.
### Bad points: ###
  * No one uses it yet besides me, so I'm probably missing something important.

See ProsAndCons to more detailed explanations.

---

## Summary ##
When you start working with BigDecimal and BigInteger (for now on lets call it BigNumbers) in java you found out the lack of operator overloading is at the very least annoying.

Instead of writing
```
double a = b + c * d
```
you need to write something like
```
BigDecimal a = b.add(c).multiply(d);
```
This is not only awful and verbose, it is also **wrong**! You got it? Yes! In standard mathematical _infix_ notation, `*` has more precedence than +, so
```
b + c * d 
```
means
```
b + ( c * d ) //multiplication comes first!
```
But in object oriented notation **there is no precedence** and methods are evaluated from left to right, so
```
b.add(c).multiply(d)
```
means
```
(b.add(c)).multiply(d) //add comes first!
```
which is a totally different thing.
When the formula gets bigger and include parenthesis and stuff it is very hard to transform from infix to OO. Try to deal with this monster, taken from http://stackoverflow.com/questions/8202946/bigdecimal-symbols-parenthesis:
```
 ((1.00 / f1) * ((((4.00 / (ak1 + 1.00)) - (2.00 / (ak1 + 4.00))) - (1.00 / (ak1 + 5.00))) - (1 / (ak1 + 6.00))));
```
Well, bignumutils allows you to write
```
//a = b + c * d
BigDecimal a = b.add((c.multiply(d));
```
as
```
//a = b + c * d
BigDecimal a = op(b, PLUS, c, TIMES, d);
```
You see the difference? It respects operator precedence, so transforming a formula from standard infix notation to bignumutils notation is almost a search and replace.
Check [UserManual](UserManual.md) to see how to make it work, [History](History.md) to see how I came up to this solution and get a better understanding of how it works, [DevLog](DevLog.md) to check the progress of the project, [Examples](Examples.md) to see some translation examples like how to transform that big formula I showed you a couple of paragraphs ago, or [RandomWikiPage](http://en.wikipedia.org/wiki/Special:Random) if you don't like this project at all and want to read about something totally different.
You can check the JavaDoc [here](http://bignumutils.googlecode.com/svn/trunk/BigNumUtils/docs/apidocs/index.html)
