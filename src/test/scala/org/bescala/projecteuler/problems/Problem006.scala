package org.bescala.projecteuler.problems

import org.bescala.projecteuler.EulerSuite
import org.bescala.projecteuler.ProjectEuler._

class Problem006 extends EulerSuite {

  /**
   * Find the difference between the sum of the squares of
   * the first one hundred natural numbers and the square of the sum
   */
  val someSolution = s"some solution for problem ${6}"

  euler(problem(6), someSolution) {

    def squareOfSumOfNumbersTo(z: Int) = {
      val sumOfNumbers =
        (1 to z).foldLeft(0)(_ + _)
      sumOfNumbers * sumOfNumbers
    }

    def sumOfSquaresOfNumbersTo(z: Int) =
      (1 to z).map { z =>
        z * z
      }.foldLeft(0)(_ + _)

    squareOfSumOfNumbersTo(100) - sumOfSquaresOfNumbersTo(100)

  }
}