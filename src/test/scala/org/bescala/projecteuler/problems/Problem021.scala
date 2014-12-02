package org.bescala.projecteuler.problems

import org.bescala.projecteuler.EulerSuite
import org.bescala.projecteuler.ProjectEuler._

class Problem021 extends EulerSuite {

  /**
   * Let d(n) be defined as the sum of proper divisors of n (numbers less than n which divide evenly into n).
   * If d(a) = b and d(b) = a, where a ≠ b, then a and b are an amicable pair and each of a and b are called amicable numbers.
   *
   * For example, the proper divisors of 220 are 1, 2, 4, 5, 10, 11, 20, 22, 44, 55 and 110; therefore d(220) = 284.
   * The proper divisors of 284 are 1, 2, 4, 71 and 142; so d(284) = 220.
   *
   * Evaluate the sum of all the amicable numbers under 10000.
   */
  val someSolution = s"some solution for problem ${21}"

  euler(problem(21), someSolution) {

    def sumOfProperDivisorsOf(z: Int) =
      (1 to z / 2).filter(z % _ == 0).foldLeft(0)(_ + _)

    (2 to 10000).map { z =>
      val s_z = sumOfProperDivisorsOf(z)
      val s__s_z = sumOfProperDivisorsOf(s_z)
      (z, s_z, s__s_z)
    }.filter {
      case (z, s_z, s__s_z) =>
        z == s__s_z && z < s_z
    }.map {
      case (z, y, _) => z + y
    }.foldLeft(0)(_ + _)

  }
}