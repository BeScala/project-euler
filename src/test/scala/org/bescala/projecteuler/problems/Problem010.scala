package org.bescala.projecteuler.problems

import org.bescala.projecteuler._
import org.bescala.projecteuler.ProjectEuler._

class Problem010 extends EulerSuite {

  /**
   * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
   * Find the sum of all the primes below two million
   */
  euler(problem(10)) {
      // the conversion to Long is necessary to avoid overflow
      Stream.from(2).filter(isPrime).takeWhile(_ < 2000000).map(_.toLong).sum
  }
}