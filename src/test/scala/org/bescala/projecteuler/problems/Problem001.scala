package org.bescala.projecteuler.problems

import org.bescala.projecteuler.EulerSuite
import org.bescala.projecteuler.ProjectEuler._

import scala.collection.immutable.Stream

class Problem001 extends EulerSuite {

  /**
   * Multiples of 3 and 5
   * If we list all the natural numbers below 10 that are multiples of 3 or 5,
   * we get 3, 5, 6 and 9. The sum of these multiples is 23.
   * Find the sum of all the multiples of 3 or 5 below 1000.
   */
  val simpleSolution = s"simple solution for problem ${1}"

  euler(problem(1), simpleSolution) {
    (1 until 1000).filter { z =>
      z % 3 == 0 || z % 5 == 0
    }.foldLeft(0) { (z, y) =>
      z + y
    }
  }

  // thanks to @tdeconin
  val streamBasedSolution = s"stream based solution for problem ${1}"

  euler(problem(1), streamBasedSolution) {
    val upperLimit = 1000
    val divisors = Seq(3, 5)
    Stream.from(1)
      .takeWhile(_ < upperLimit)
      .foldLeft(0) {
      case (acc, i) if (divisors.exists(i % _ == 0)) => acc + i
      case (acc, _) => acc
    }
  }
}