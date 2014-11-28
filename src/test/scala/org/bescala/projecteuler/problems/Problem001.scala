package org.bescala.projecteuler.problems

import org.bescala.projecteuler.EulerSuite
import org.bescala.projecteuler.ProjectEuler._

class Problem001 extends EulerSuite {

  /**
   * Multiples of 3 and 5
   * If we list all the natural numbers below 10 that are multiples of 3 or 5,
   * we get 3, 5, 6 and 9. The sum of these multiples is 23.
   * Find the sum of all the multiples of 3 or 5 below 1000.
   */
  euler(problem(1), "using math properties") {

    // Count the number of multiples below 3, 5 and 15
    val count3 = (1000-1) / 3
    val count5 = (1000-1) / 5
    val count15 = (1000-1) / 15

    // Use the math property: sum(1..n) = (n * n+1) / 2
    (( count3 * (count3 + 1) * 3 ) +
     ( count5 * (count5 + 1) * 5 ) -
     ( count15 * (count15 + 1) * 15 )) / 2
  }

  euler(problem(1), "implicit class showcase") {

    /* Let's add integer extensions as an implicit class show case
     */
    implicit class IntegerExtensions(value: Int) {

      /* Returns the sum of integers from 1 to value using mathematical properties.
       */
      def sumSuite: Int = value * (value + 1) / 2

      /* Returns the number of multiples of value below a given value
       */
      def numberMultiplesBelow(other: Int) = (other-1) / value
    }


    (3.numberMultiplesBelow(1000)).sumSuite * 3 +
    (5.numberMultiplesBelow(1000)).sumSuite * 5 -
    (15.numberMultiplesBelow(1000)).sumSuite * 15
  }
}