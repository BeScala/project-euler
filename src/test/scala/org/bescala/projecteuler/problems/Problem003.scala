package org.bescala.projecteuler.problems

import org.bescala.projecteuler.EulerSuite
import org.bescala.projecteuler.ProjectEuler._
import org.bescala.projecteuler._

class Problem003 extends EulerSuite {
  /**
   * The prime factors of 13195 are 5, 7, 13 and 29.
   * What is the largest prime factor of the number 600851475143?
   */
  euler(problem(3)) {
    primeFactors(600851475143L).max
  }
}
