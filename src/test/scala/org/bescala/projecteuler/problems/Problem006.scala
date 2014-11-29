package org.bescala.projecteuler.problems

import org.bescala.projecteuler.EulerSuite
import org.bescala.projecteuler.ProjectEuler._

class Problem006 extends EulerSuite {

  /**
   * Find the difference between the sum of the squares of
   * the first one hundred natural numbers and the square of the sum
   */
  euler(problem(6)) {
        val sum1 = (1 to 100).sum
        sum1 * sum1 - (1 to 100).map(x => x*x).sum
  }
}
