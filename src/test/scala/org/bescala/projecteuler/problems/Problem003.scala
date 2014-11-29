package org.bescala.projecteuler.problems

import org.bescala.projecteuler.EulerSuite
import org.bescala.projecteuler.ProjectEuler._

class Problem003 extends EulerSuite {
  /**
   * The prime factors of 13195 are 5, 7, 13 and 29.
   * What is the largest prime factor of the number 600851475143?
   */
  val someSolution = s"some solution for problem ${3}"

  euler(problem(3), someSolution) {

    import math._

    def squareRootOf(z: Long) =
      round(floor(sqrt(z.toDouble)))

    val z = 600851475143L
    (2L to squareRootOf(z)).filter(y => z % y == 0 && (2L to squareRootOf(y)).forall(y % _ != 0)).last
  }

}
