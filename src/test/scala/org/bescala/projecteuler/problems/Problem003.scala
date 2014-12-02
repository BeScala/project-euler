package org.bescala.projecteuler.problems

import org.bescala.projecteuler.EulerSuite
import org.bescala.projecteuler.ProjectEuler._

import scala.annotation.tailrec

class Problem003 extends EulerSuite {
  /**
   * The prime factors of 13195 are 5, 7, 13 and 29.
   * What is the largest prime factor of the number 600851475143?
   */

  object PrimeFast {
    import scala.collection.mutable.Map

    def fastPrimes: Stream[Long] = {
      2 #:: sieve(3, Map(9L -> 6L))
    }

    private def sieve(p: Long, pQ: Map[Long, Long]): Stream[Long] = {
      p #:: sieve(nextPrime(p + 2, pQ), pQ)
    }
    private def nextCompositeNumber(x:Long, step: Long, pQ: Map[Long, Long]): Unit = {
      pQ.get(x) match {
        case Some(_) =>
          nextCompositeNumber(x + step, step, pQ)
        case None =>
          pQ(x) = step
      }
    }
    private def nextPrime(candidate: Long, pQ: Map[Long, Long]): Long = {
      pQ.get(candidate) match {
        case Some(step) =>
          pQ -= candidate
          nextCompositeNumber(candidate + step, step, pQ)
          nextPrime(candidate + 2, pQ)
        case None =>
          pQ(candidate * candidate) = candidate * 2
          candidate
      }
    }
  }

  euler(problem(3), "eloots implicit") {
    @tailrec
    def largestPrimeFactor(n: Long)(implicit primes: Stream[Long]): Long = {
      if (n == 1) primes.head
      else {
        if (n % primes.head == 0) largestPrimeFactor(n / primes.head)
        else largestPrimeFactor(n)(primes.tail)
      }
    }
    implicit val myFavoritePrimes_NameDoesntMatter = PrimeFast.fastPrimes()
    largestPrimeFactor(600851475143L)
  }

  euler(problem(3), "cfr eloots but no implicit") {
    def largestPrimeFactor(n: Long): Long = {
      @tailrec def largestPrimeFactor_(n: Long, primesToConsider: Stream[Long]): Long = {
        val currentPrime = primesToConsider.head
        if (n == 1)
          currentPrime
        else if (n % currentPrime == 0)
          largestPrimeFactor_(n / currentPrime, primesToConsider)
        else
          largestPrimeFactor_(n, primesToConsider.tail)
      }
      largestPrimeFactor_(n, PrimeFast.fastPrimes)
    }
    largestPrimeFactor(600851475143L)
  }

  euler(problem(3), "cfr eloots but no implicit and with 'mem-leaking'") {
    val allPrimes = PrimeFast.fastPrimes
    def largestPrimeFactor(n: Long): Long = {
      @tailrec def largestPrimeFactor_(n: Long, primesToConsider: Stream[Long]): Long = {
        val currentPrime = primesToConsider.head
        if (n == 1)
          currentPrime
        else if (n % currentPrime == 0)
          largestPrimeFactor_(n / currentPrime, primesToConsider)
        else
          largestPrimeFactor_(n, primesToConsider.tail)
      }
      largestPrimeFactor_(n, allPrimes)
    }
    largestPrimeFactor(600851475143L)
  }
}
