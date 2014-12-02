package org.bescala.projecteuler.problems

import org.bescala.projecteuler.EulerSuite
import org.bescala.projecteuler.ProjectEuler._

class Problem009 extends EulerSuite {

  /**
   * A Pythagorean triplet is a set of three natural numbers, a < b < c, for which,
   * a^2 + b^2 = c^2
   * For example, 3^2 + 4^2 = 9 + 16 = 25 = 5^2.
   *
   * There exists exactly one Pythagorean triplet for which a + b + c = 1000.
   *
   *
   * TIP:
   * Instead of applying brute force, use two equations:
   *
   * a^2 + b^2 = c^2
   * a + b + c = 1000
   *
   * and eliminate c =>
   *
   * 1000^2 - 2000 * b
   * a = -----------------
   * 2000 - 2 * b
   *
   * Because a and b are positive integers => divider in the above equation has to divide evenly into
   * the numerator -and- 1000^2 - 2000 * b has to be positive
   */
  val someSolution = s"some solution for problem ${9}"

  euler(problem(9), someSolution) {

    def pythagoreanTripletWithSum(z: Int) = {
      val a_n_b =
        (1 to (z - 1)).filter { b =>
          (z * z - 2 * z * b) % (2 * z - 2 * b) == 0
        }

      val a = a_n_b(0)
      val b = a_n_b(1)
      val c = z - (a + b)

      (a, b, c)
    }

    pythagoreanTripletWithSum(1000) match {
      case (a, b, c) => a * b * c
    }

  }
}