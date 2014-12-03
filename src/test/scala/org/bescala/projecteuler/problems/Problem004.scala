package org.bescala.projecteuler.problems


import org.bescala.projecteuler.EulerSuite
import org.bescala.projecteuler.ProjectEuler._

import scala.annotation.tailrec

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

  def isPalindromic(n: BigInt) = {
    val s = n.toString
    @tailrec def isPalindromicImpl(leftIndex: Int, rightIndex: Int): Boolean = {
      if (leftIndex >= rightIndex) true
      else (s.charAt(leftIndex) == s.charAt(rightIndex)) && isPalindromicImpl(leftIndex + 1, rightIndex - 1)
    }
    isPalindromicImpl(0, s.length - 1)
  }

  val start = 999L

  euler(problem(4), "nested loops") {
    var max = BigInt(1)
    def find(start: BigInt): Long = {
      for (i <- start to 1 by -1) {
        for (j <- start to i by -1) {
          val n = i * j
          if (n > max && isPalindromic(n)) {
            //println(s"$i x $j = $n")
            max = n
          }
        }
      }
      max.toLong
    }
    find(start)
  }

  euler(problem(4), "flatMap that shit") {
    def find(start: BigInt): Long = {
      var max = BigInt(1)
      (start to 1 by -1).flatMap { i =>
        (start to i by -1).map { j =>
          val n = i * j
          if (n > max && isPalindromic(n)) {
            //println(s"$i x $j = $n")
            max = n
            n
          }
        }
      }
      max.toLong
    }
    find(start)
  }

  euler(problem(4), "for-comprehension (with a trick)") {
    def find(start: BigInt): Long = {
      var max = BigInt(1)
      (for {
        i <- (start to 1 by -1)
        j <- (start to i by -1)
        n = i * j
        if n > max && isPalindromic(n) && { max = n; true } // a trick to set the var max in the closure only if the tests succeed
        //max = n // Will not work since the max here is local in the inner map, and not the var max in the closure!
      } yield max.toLong).last
    }
    find(start)
  }

  euler(problem(4), "from largest to smallest fastest solution") {
    def find(start: BigInt): Long = {
      for (n <- start to 1 by -1) {
        for (k <- 0 to 1) {
          var j = n - k
          for (i <- n to start) {
            val p = i * j
            if (isPalindromic(p)) {
              //println(s"$i x $j = $p")
              return p.toLong
            }
            j -= 1
          }
        }
      }
      1L
    }
    find(start)
  }
}
