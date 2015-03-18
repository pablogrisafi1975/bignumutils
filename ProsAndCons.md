# Pros And Cons #

As I told you before

### Good points: ###
  * It is usable.
  * It passes all tests, see <a href="http://htmlpreview.github.io/?https://raw.githubusercontent.com/pablogrisafi1975/bignumutils/master/BigNumUtils/docs/surefire/surefire-report.html" target="_blank">surefire test report</a>.
  * It has great coverage, see <a href="http://htmlpreview.github.io/?https://raw.githubusercontent.com/pablogrisafi1975/bignumutils/master/BigNumUtils/docs/cobertura/index.html" target="_blank">coverage report</a>.
  * It is just 5 classes or so, so you will be able to fix it if anything does not work as expected.

### Bad points: ###
  * No one uses it yet besides me, so I'm probably missing something important.

But there are more bad points I hide...muahahaha! None is too bad

  1. There is a lot of code duplication between BigInteger and BigDecimal related classes. I should get rid of it
  1. All classes are "static import oriented", so they are not an example of good design, or even following good naming conventions
  1. I really don't care about BigInteger stuff. It works, but I don't use it. Main focus is in BigDecimal
  1. This library is not mean to be extensible, you can not add your own operations. Of course, since it is so small, it is really easy to just change the source code and that's it.
  1. The more important: There is no way to specify a MathContext for operations. You don't know what a MathContext is? So yo probably don't care. Let me explain to you: A math context basically tells you the accuracy you will have in the operation, and how the numbers should be rounded. By default, it uses infinite accuracy, and so no rounding is needed. I'm using this default everywhere BUT when making divisions, I'm changing a specific MathContext.DECIMAL128. Why? because if you make op(BD(1), DIV, BD(3)) with infinite accuracy you will get an ArithmeticException, since there is no way to represent 1/3 as a BigDecimal. And I don't care about perfection, my financial operations are good enough with MathContext.DECIMAL128, and I don't like to have a potential ArithmeticException in every division.
