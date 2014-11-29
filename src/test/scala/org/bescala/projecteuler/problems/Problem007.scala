package org.bescala.projecteuler.problems

import org.bescala.projecteuler.EulerSuite
import org.bescala.projecteuler.ProjectEuler._
import org.bescala.projecteuler.isPrime

class Problem007 extends EulerSuite {

  /**
   * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
   * What is the 10 001st prime number?
   */
  euler(problem(7)) {
    Stream.from(2).filter(isPrime)(10000)
  }
}
