package org.bescala.projecteuler.problems

import org.bescala.projecteuler.EulerSuite
import org.bescala.projecteuler.ProjectEuler._

import scala.annotation.tailrec

class Problem001 extends EulerSuite {

  /**
   * Multiples of 3 and 5
   * If we list all the natural numbers below 10 that are multiples of 3 or 5,
   * we get 3, 5, 6 and 9. The sum of these multiples is 23.
   * Find the sum of all the multiples of 3 or 5 below 1000.
   */

  euler(problem(1), "simple") {
    // we first construct a Range, and then filter O(n), and sum O(n * 8 / 15)
    (1 until 1000).filter(l => l % 3 == 0 || l % 5 == 0).sum
  }

  euler(problem(1), "tailrecursive with accumulator") {
    // O(n)
    @tailrec def calculate(n: Int, sum: Long = 0L): Long = {
      if (n >= 1000)
        sum
      else
        calculate(n + 1, (if (n % 3 == 0 || n % 5 == 0) n else 0) + sum)
    }
    calculate(1)
  }

  euler(problem(1), "tdeconin") {
    val upperLimit = 1000
    val divisors = Seq(3,5)
    Stream.from(1)
      .takeWhile(i => i < upperLimit)
      .foldLeft (0) {
      case (acc, i) if (divisors.exists{ d => (i % d) == 0 }) => acc + i
      case (acc, _) => acc
    }
  }

  euler(problem(1), "withFilter") {
    var result = 0
    (1 until 1000).withFilter(l => l % 3 == 0 || l % 5 == 0).foreach(result += _)
    result
  }

}