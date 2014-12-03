package org.bescala.projecteuler.problems

import org.bescala.projecteuler.EulerSuite
import org.bescala.projecteuler.ProjectEuler._


class Problem004 extends EulerSuite {

  val someSolution = s"some solution for problem ${4}"

  euler(problem(4), someSolution) {

    def isPalindrome(z: Int) =
      z.toString.reverse == z.toString

    (1 to 999).flatMap { z =>
      (1 to 999).map { y =>
        z * y
      }
    }.filter(isPalindrome).max
  }

}
