package org.bescala.projecteuler.problems

import org.bescala.projecteuler.EulerSuite
import org.bescala.projecteuler.ProjectEuler._


class Problem006 extends EulerSuite {

  /**
   * Find the difference between the sum of the squares of
   * the first one hundred natural numbers and the square of the sum
   */
  euler(problem(6)) {
    val sumSquares = List.range(0, 100).foldLeft(0)((sum,nr) => sum + nr*nr)
    sumSquares - (sumSquares*sumSquares)
  }
}