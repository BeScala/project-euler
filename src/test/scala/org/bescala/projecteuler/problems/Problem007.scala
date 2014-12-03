package org.bescala.projecteuler.problems

import org.bescala.projecteuler._
import org.bescala.projecteuler.ProjectEuler._

import scala.annotation.tailrec

class Problem007 extends EulerSuite {

  /**
   * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
   * What is the 10 001st prime number?
   */

  euler(problem(7), "tailrec") {
    def prime(nth: Int) = {
      @tailrec def prime_(count: Int, lastPrime: Int): Int = {
        if (count == 0)
          lastPrime
        else
          prime_(count - 1, nextPrime(lastPrime))
      }
      prime_(nth - 1, nextPrime(1))
    }
    prime(10001)
  }

  euler(problem(7), "stream") {
    def primes = {
      def nextPrimes(n: Long): Stream[Long] = {
        val np = nextPrime(n.toInt)
        np #:: nextPrimes(np)
      }
      2L #:: nextPrimes(2)
    }
    primes(10001 - 1)
  }

  euler(problem(7), "mverbist") {
    Stream.from(2).filter(isPrime)(10000)
  }

  euler(problem(7), "LucDupAtGitHub no sieve") {
    import math._
    def squareRootOf(z: Int) = round(floor(sqrt(z.toDouble))).toInt
    def isPrime(z: Int): Boolean = (2 to squareRootOf(z)).forall(z % _ != 0)

    Stream.from(2).filter(isPrime).drop(10000).head
  }

  // Stack overflows
  euler(problem(7), "LucDupAtGitHub with sieve") {
    def sieve(stream: Stream[Int], l: Int): Int = {
      @tailrec  def loop(stream: Stream[Int])(acc: Int, m: Int): Int = {
        if (m == 0)
          acc
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