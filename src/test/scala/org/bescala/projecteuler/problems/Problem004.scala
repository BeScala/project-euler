package org.bescala.projecteuler.problems


import org.bescala.projecteuler.EulerSuite
import org.bescala.projecteuler.ProjectEuler._

class Problem004 extends EulerSuite {

 /**
  * A palindromic number reads the same both ways. The largest palindrome made from the product
  * of two 2-digit numbers is 9009 = 91 x 99.
  *
  * Task: Find the largest palindrome made from the product of two 3-digit numbers.
  *
  * Note: The stated problem can be solved for numbers with a larger number of digits.
  *
  * For example, for 5 digit numbers, the largest palindrome is 9966006699 = 99681 * 99979 and
  * this solution can be determined programmatically in under a second.
  *
  * In fact, it is possible to calculate the value of these palindrome for the product of two
  * numbers up-to 8 digits in around 5 seconds.
  *
  * Finding the right strategy for finding a fast solution is not straightforward. Use experiments to
  *find a good strategy. The 'fast' solution doesn't require tons of math...
  *
  */
  euler(problem(4), "vrobinho: the Java way") {
    var ret = 0
    var prod: Int= 0
    for (j <- 100 to 999 ; i <- 100 to 999) {
      prod = j * i
      ret = if(isPalindromic(prod.toString) && prod > ret) prod else ret
    }
    ret
  }

  euler(problem(4), "vrobinho: more Scala minded but not optimal") {
    (1 to 999).combinations(2).toList.map(x => x(0) * x(1)).filter{x => isPalindromic(x.toString)}.max
  }
  
  def isPalindromic(s: String): Boolean = {
    s == s.reverse
  }
}
