package org.bescala.projecteuler.problems

import org.bescala.projecteuler.EulerSuite
import org.bescala.projecteuler.ProjectEuler._

import scala.annotation.tailrec

class Problem010 extends EulerSuite {

  /**
   * The sum of the primes below 10 is 2 + 3 + 5 + 7 = 17.
   * Find the sum of all the primes below two million
   */
  val someSolution = s"some solution for problem ${10}"

  euler(problem(10), someSolution) {
    import math._

    def isPrime(z: Long): Boolean =
      (2L to round(floor(sqrt(z.toDouble)))).forall(z % _ != 0)

    def natsFrom(l: Long): Stream[Long] = {
      def loop(z: Long): Stream[Long] =
        z #:: loop(z + 1)
      loop(l)
    }

    val primes =
      natsFrom(2L).filter(isPrime)

    primes.takeWhile(_ < 2000000L).foldLeft(0L)(_ + _)

  }

  val otherSolution = s"other solution for problem ${10}"

  euler(problem(10), otherSolution) {
    import math._

    def isPrime(z: Long): Boolean =
      (2L to round(floor(sqrt(z.toDouble)))).forall(z % _ != 0)

    val primes =
      (2L to 2000000L).filter(isPrime)

    primes.foldLeft(0L)(_ + _)

  }

}