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
  euler(problem(1), "eloots") {
    val multOf3 = (3 until 1000 by 3).toSet
    val multOf5 = (5 until 1000 by 5).toSet
    (multOf3 ++ multOf5).sum
  }
}