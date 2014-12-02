package org.bescala.projecteuler.problems

import org.bescala.projecteuler.EulerSuite
import org.bescala.projecteuler.ProjectEuler._

import scala.annotation.tailrec
import scala.annotation.tailrec

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

    def isPrime(z: Long): Boolean =
      (2L to squareRootOf(z)).forall(z % _ != 0)

    def natsFrom(z: Long): Stream[Long] = {
      def loop(z: Long): Stream[Long] =
        z #:: loop(z + 1)
      loop(z)
    }

    val primes =
      natsFrom(2L).filter(isPrime)

    primes.take(882).filter(600851475143L % _ == 0).toList.last

  }

}
