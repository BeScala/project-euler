package org.bescala.projecteuler.problems

import org.bescala.projecteuler.EulerSuite
import org.bescala.projecteuler.ProjectEuler._

import scala.annotation.tailrec

class Problem007 extends EulerSuite {

  /**
   * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
   * What is the 10 001st prime number?
   */
  val someSolution = s"some solution for problem ${7}"

  euler(problem(7), someSolution) {

    import math._

    def squareRootOf(z: Int) =
      round(floor(sqrt(z.toDouble))).toInt

    def isPrime(z: Int): Boolean =
      (2 to squareRootOf(z)).forall(z % _ != 0)

    def natsFrom(i: Int): Stream[Int] = {
      def loop(z: Int): Stream[Int] =
        z #:: loop(z + 1)
      loop(i)
    }

    val primes =
      natsFrom(2).filter(isPrime)

    primes(10000)

  }

  val otherSolution = s"other solution for problem ${7}"

  euler(problem(7), otherSolution) {

    def sieve(stream: Stream[Int], l: Int): Int = {
      @tailrec
      def loop(stream: Stream[Int])(acc: Int, m: Int): Int = {
        if (m == 0) {
          acc
        }
        else {
          val p = stream.head
          loop(stream.filter(_ % p != 0))(p, m - 1)
        }
      }
      loop(stream)(2, l)
    }

    sieve(Stream.from(2), 10001)

  }


}