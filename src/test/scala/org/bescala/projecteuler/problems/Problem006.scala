package org.bescala.projecteuler.problems

import org.bescala.projecteuler.EulerSuite
import org.bescala.projecteuler.ProjectEuler._

trait TestParams006 {
  val N: Long = 100L
}

class Problem006 extends EulerSuite with TestParams006 {

  /**
   * Find the difference between the sum of the squares of
   * the first one hundred natural numbers and the square of the sum
   */
  euler(problem(6), "eloots-1") {
    val nums = 1L to N
    val sumNums = nums.sum
    val sumNumsSquared = nums map { x => x * x } sum

    sumNums * sumNums - sumNumsSquared
  }

  euler(problem(6), "eloots-2") {
    // Calculate sum of numbers and their squares in one pass using fold
    val (sumX, sumX2) = (1L to N foldLeft (0L,0L)) { case ((pSumX, pSumX2), x) => (pSumX + x, pSumX2 + x * x) }

    sumX * sumX - sumX2
  }
}