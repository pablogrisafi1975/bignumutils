# Introduction #
## It was the best of times, it was the worst of times ##
I as a happy man, working in a java server side project. I had business analysts that told me how to calculate values using formulas like
```
a = b + c * d;
```
and then I copied that to java and more or less everything worked. If the result wasn't the expected, the cause was very likely to be an analyst error. I just showed them the code, they were able to understand it and point if I made a mistake or the original formula was wrong.

## Winter is coming ##
Then one time someone felt that doubles were not enough to handle the precision we needed and we switched from Double to BigDecimal.
Oh the pain!
Lack of operator overloading and precedence handling made me make lots of mistakes. Now, when a result wasn't what we expected, the most probably cause was an error in translation from infix to object oriented notation. To make things worst, business analyst were not able to read my code anymore.

## Somebody help me ##
I supposed someone has already created a infix to OO translator, and I asked for that in [StackOverflow](http://stackoverflow.com/questions/8636228/bigdecimal-notation-eclipse-plugin-or-nice-external-tool)...but no.

After tons of search, I found this project called [birpn](http://code.google.com/p/birpn/) which allows you to write BigInteger operations in Reverse Polish Notation ...It was not what I expected, since I wanted to use infix notation and work with BigDecimal, but showed me how it can be done.

## Inception ##
I started fighting with [Shunting yard algorithm](http://en.wikipedia.org/wiki/Shunting-yard_algorithm) and it was way out of my league. I was about to let it go and then I realize I didn't need a full lexical parser/interpreter...I have java working for me! So I wrote the minimal code that could handle operator precedence and (after a few tries) it worked like a charm.
For more info, go to the UserManual