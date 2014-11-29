package org.bescala.projecteuler.problems

import org.bescala.projecteuler.EulerSuite
import org.bescala.projecteuler.ProjectEuler._

class Problem009  extends EulerSuite {

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
   *    a^2 + b^2 = c^2
   *    a + b + c = 1000
   *
   *  and eliminate c =>
   *
   *      1000^2 - 2000 * b
   *  a = -----------------
   *         2000 - 2 * b
   *
   *  Because a and b are positive integers => divider in the above equation has to divide evenly into
   *  the numerator -and- 1000^2 - 2000 * b has to be positive
   */
  euler(problem(9)) {
      (for {
          a <- 1 to 999
          b <- a to 999
          c <- b to 999 filter (c1 => a + b + c1 == 1000 && a*a + b*b == c1*c1)
      } yield (a * b * c)).head
  }
}