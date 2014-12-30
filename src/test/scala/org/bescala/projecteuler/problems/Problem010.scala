package org.bescala.projecteuler.problems

import org.bescala.projecteuler.EulerSuite
import org.bescala.projecteuler.ProjectEuler._
import org.bescala.projecteuler._

class Problem010 extends EulerSuite {

  /**
   * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
   * Find the sum of all the primes below two million
   */
  euler(problem(10)) {
    var sum: Long = 0
    for (i <- 1 to 2000000) {
      if (isPrime(i)) sum += i
    }
    sum
  }
}