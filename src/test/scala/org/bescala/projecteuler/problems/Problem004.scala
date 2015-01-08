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

  euler(problem(4), "uber-brute force") {

    def isPalindrome(i: Int) = i.toString.reverse == i.toString

    val palindromes =
      for (
        i <- 100 to 999;
        j <- i to 999;
        prod = i * j;
        if isPalindrome(prod)
      )
      yield prod

    palindromes.max
  }


  euler(problem(4), "prune loops that won't improve latest guess") {

    def isPalindrome(i: Int) = i.toString.reverse == i.toString

    def largestPalindromeOuterLoop(i: Int, largestSoFar: Int): Int =
      if (i >= 1000)
        largestSoFar
      else
        largestPalindromeOuterLoop(i+1, largestPalindromeInnerLoop(i, i max (largestSoFar / i), largestSoFar))

    def largestPalindromeInnerLoop(i: Int, j: Int, largestSoFar: Int): Int =
      if (j >= 1000)
        largestSoFar
      else {
        val prod = i * j
        if (isPalindrome(prod)) {
          largestPalindromeInnerLoop(i, j + 1, prod)
        } else
          largestPalindromeInnerLoop(i, j + 1, largestSoFar)
      }

    def largestPalindrome = largestPalindromeOuterLoop(100, 1)

    largestPalindrome
  }


  euler(problem(4), "as before, but loop order reversed") {

    // After skimming notes from Project Euler, makes sense to invert the
    // order of the loops (i.e., go from 999 to 100) to find the
    // largest palindrome more quickly

    def isPalindrome(i: Int) = i.toString.reverse == i.toString

    def largestPalindromeOuterLoop(i: Int, largestSoFar: Int): Int =
      if (i < 100)
        largestSoFar
      else
        largestPalindromeOuterLoop(i-1, largestPalindromeInnerLoop(i, i, largestSoFar))

    def largestPalindromeInnerLoop(i: Int, j: Int, largestSoFar: Int): Int =
      if (j < 100 || j < (largestSoFar / i))
        largestSoFar
      else {
        val prod = i * j
        if (isPalindrome(prod)) {
          println(i +" * "+ j +" = "+ prod)
          largestPalindromeInnerLoop(i, j - 1, prod)
        } else
          largestPalindromeInnerLoop(i, j - 1, largestSoFar)
      }

    def largestPalindrome = largestPalindromeOuterLoop(999, 1)

    largestPalindrome
  }
}
