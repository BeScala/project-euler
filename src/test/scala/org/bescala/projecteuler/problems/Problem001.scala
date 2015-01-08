package org.bescala.projecteuler.problems

import org.bescala.projecteuler.EulerSuite
import org.bescala.projecteuler.ProjectEuler._

class Problem001 extends EulerSuite {

  /**
   * Multiples of 3 and 5
   * If we list all the natural numbers below 10 that are multiples of 3 or 5,
   * we get 3, 5, 6 and 9. The sum of these multiples is 23.
   * Find the sum of all the multiples of 3 or 5 below 1000.
  */

  euler(problem(1), "brute force") {

    def multiplesOf(n: Int, max: Int = 1000) =
      (1 until max).filter(_ % n == 0).toSet

    (multiplesOf(3) union multiplesOf(5)).sum
  }

  euler(problem(1), "even more brute force") {

    val numbers =
      for (i <- 1 until 1000 if ((i % 3 == 0) || (i % 5 == 0)))
      yield i

    numbers.sum
  }

  euler(problem(1), "inclusion-exclusion") {

    def multiplesOf(n: Int, max: Int = 1000) =
      (1 until max).filter(_ % n == 0)

    multiplesOf(3).sum + multiplesOf(5).sum - multiplesOf(15).sum
  }

  euler(problem(1), "direct") {
    // Calculate sum using arithmetic progression
    // instead of explicitly enumerating multiples

    // Sum of multiples of k in the range [1, max)
    def sumOfMultiplesOf(d: Int, max: Int = 1000) = {
      if (max <= d)
        0
      else {
        val m = (max - 1) - ((max - 1) % d)  // last multiple of d in range [1, max)

        // S_n     = a + (a+d) + (a+2d) + ... + (a + (n-1)d)
        //         = (1/2) n (2a + (n-1) d)
        //         = n * (a + a+(n-1)d)/2 = n * (first term + last term)/2
        //
        // Here, a = d, so S_n = d * n * (n+1) / 2

        val n = m / d
        (d * n * (n + 1)) / 2
      }
    }

    sumOfMultiplesOf(3) + sumOfMultiplesOf(5) - sumOfMultiplesOf(15)
  }
}
