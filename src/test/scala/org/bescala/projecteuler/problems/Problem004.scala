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
    def find(start: BigInt): Long = {
      var max = BigInt(1)
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

  /*
  euler(problem(4), "PalinSamdrome") {
    object PalinSamdrome {
      val N = 99999999
      val N2 = (N * N).toString
      val palindrome: (Long, Long, Long) =
        if (N2 == N2.reverse) (N * N, N, N)
        else {
          (Iterator.from(N, -1)
            .map { n => (n - 1, n)}
            .flatMap { case (n1, n2) =>
              (0L to 1) flatMap { deltaOne =>
              (0L to N - n2 + deltaOne) map { d2 => (n1 - d2, n2 - deltaOne + d2)}
            }
          })
            .map { case (n1, n2) => (n1 * n2, n1, n2)}
            .find { case (palindromeCandidate, _, _) => palindromeCandidate.toString == palindromeCandidate.toString.reverse}
            .get
        }
    }
    //println(PalinSamdrome.palindrome.toString)
    PalinSamdrome.palindrome._1
  }
  */

  euler(problem(4), "Sam's PalinSamdrome") {
    def find(start: Long) = {
      (for {
        n2 <- Iterator.from(start.toInt, -1)
        n1 = n2 - 1
        deltaOne <- 0L to 1
        d2 <- 0L to (start - n2 + deltaOne)
        i = n1 - d2
        j = n2 - deltaOne + d2
      } yield i * j)
        .find(p => p.toString == p.toString.reverse)
        .get
    }
    find(start)
  }
}
