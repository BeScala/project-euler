package org.bescala.projecteuler.problems

import org.bescala.projecteuler.EulerSuite
import org.bescala.projecteuler.ProjectEuler._

import scala.annotation.tailrec

class Problem003 extends EulerSuite {
  /**
   * The prime factors of 13195 are 5, 7, 13 and 29.
   * What is the largest prime factor of the number 600851475143?
   */

  val someSolution = s"some solution for problem ${3}"

  euler(problem(3), someSolution) {

    def sieve(stream: Stream[Int], z: Long): Int = {

      def divideByPowersOf(p: Int)(y: Long) = {
        @tailrec
        def loop(acc: Long): Long =
          if (acc % p == 0) loop(acc / p)
          else acc
        loop(y)
      }


      @tailrec
      def loop(stream: Stream[Int], y: Long)(acc: Int): Int =
        if (y == 1L) {
          acc
        }
        else {
          val p = stream.head
          loop(stream.filter(_ % p != 0), divideByPowersOf(p)(y))(p)
        }

      loop(stream, z)(2)
    }

    sieve(Stream.from(2), 600851475143L)

  }

}
