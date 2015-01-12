package org.bescala.projecteuler.problems

import org.bescala.projecteuler.EulerSuite
import org.bescala.projecteuler.ProjectEuler._
import org.bescala.projecteuler.models.Range

class Problem001 extends EulerSuite {

  /**
   * Multiples of 3 and 5
   * If we list all the natural numbers below 10 that are multiples of 3 or 5,
   * we get 3, 5, 6 and 9. The sum of these multiples is 23.
   * Find the sum of all the multiples of 3 or 5 below 1000.
   */
  euler(problem(1)) {
    val range = Range.from(1).until(1000)
    range.withFilter { n => 
      n % 3 == 0 || n % 5 == 0
    }.sum
  }

  euler(problem(1), "using scala collections") {
    (1 until 1000).filter { n =>
      n % 3 == 0 || n % 5 == 0
    }.sum
  }


  euler(problem(1), "just loop it") {

    val filter = (n:Int) => n % 3 == 0 || n % 5 == 0

    def loop(n:Int, acc:Int = 0) : Int = {

      if (filter(n) && n < 1000)
        loop(n + 1, acc + n)
      else if (n < 1000)
        loop(n + 1, acc)
      else acc
    }

    loop(1)
  }
}