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

    def natsFrom(z: Long): Stream[Long] = {
      def loop(z: Long): Stream[Long] =
        z #:: loop(z + 1)
      loop(z)
    }

    def sieve(stream: Stream[Long], z: Long): Long = {
      @tailrec
      def loop(stream: Stream[Long], y: Long)(acc: Long): Long = {
        if (y == 1L) {
          acc
        }
        else {
          val p = stream.head
          loop(stream.filter(_ % p != 0), if (y % p == 0) y / p else y)(p)
        }
      }
      loop(stream, z)(2L)
    }

    sieve(natsFrom(2), 600851475143L)

  }

}
