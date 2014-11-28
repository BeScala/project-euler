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

  euler(problem(1), "simple") {
    // we first construct a Range, and then filter O(n), and sum O(n) + O(n * 8 / 15)
    (1 until 1000).filter(l => l % 3 == 0 || l % 5 == 0).sum
  }
}