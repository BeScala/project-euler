package org.bescala.projecteuler.problems

import org.bescala.projecteuler.EulerSuite
import org.bescala.projecteuler.ProjectEuler._

import scala.annotation.tailrec

class Problem015  extends EulerSuite {

  /**
   * Starting in the top left corner of a 2×2 grid, and only being able to move to the right and down,
   * there are exactly 6 routes to the bottom right corner.
   *
   * How many such routes are there through a 20×20 grid?
   */
//  val bruteForceSolution = s"brute force solution for problem ${15}"
  //
  //  euler(problem(15), bruteForceSolution) {
  //
  //    def bruteForce(left: Long, down: Long): Long =
  //      if (left == 1) down + 1
  //      else if (down == 1) left + 1
  //      else bruteForce(left - 1, down) + bruteForce(left, down - 1)
  //
  //    bruteForce(20, 20)
  //
  //  }

  val moreCleverSolution = s"more clever solution for problem ${15}"

  euler(problem(15), moreCleverSolution) {

    def fac(n: Int) = {
      @tailrec
      def loop(n: Int, acc: BigInt): BigInt =
        if (n <= 1) acc
        else loop(n - 1, n * acc)
      loop(n, 1)
    }

    def moreClever(z: Int) = {
      val fac_z = fac(z)
      java.lang.Long.parseLong((fac(2 * z) / (fac_z * fac_z)).toString)
    }

    moreClever(20)

  }
}