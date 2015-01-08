package org.bescala.projecteuler.problems

import org.bescala.projecteuler.EulerSuite
import org.bescala.projecteuler.ProjectEuler._

class Problem003 extends EulerSuite {
  /**
   * The prime factors of 13195 are 5, 7, 13 and 29.
   * What is the largest prime factor of the number 600851475143?
   */

  euler(problem(3), "uber-brute force") {

    import math.{sqrt, ceil}

    def nonTrivialDivisorsOf(n: Long) =
      (2L to ceil(sqrt(n)).toLong).filter(n % _ == 0)

    def isPrime(n: Long) =
      nonTrivialDivisorsOf(n).isEmpty

    val N = 600851475143L
    val factors = nonTrivialDivisorsOf(N).filter(isPrime)

    factors.max
  }


  /*euler(problem(3), "direct iterator") {

    // The iterator with vars seems contrary to Scala's functional style
    // And incredibly, the brute force solution is much faster

    val primes = new Iterator[Int] {
      var i = 1
      var primesSoFar = List[Int]()
      def hasNext = true
      def next(): Int = {
        i += 1
        while (primesSoFar.exists(i % _ == 0))
          i += 1
        primesSoFar = i :: primesSoFar
        i
      }
    }

    import math.{sqrt, ceil}

    val N = 12345L //600851475143L
    val maxRelevantFactor = ceil(sqrt(N)).toLong
    primes.takeWhile(_ <= maxRelevantFactor).filter(N % _ == 0).max
  }*/


  euler(problem(3), "Sieve of Eratosthenes") {

    // Incredibly, Solution 1 is still faster
    def primesUpTo(N: Int) = {
      require(N >= 2)
      val isPrime = Array.fill(N+1)(true)

      isPrime(0) = false
      isPrime(1) = false
      isPrime(2) = true
      for (i <- 2 to N if isPrime(i))
        for (j <- 2*i to N by i)
          isPrime(j) = false

      (1 to N).filter(isPrime(_))
    }

    import math.{sqrt, ceil}

    val N = 600851475143L
    val maxRelevantFactor = ceil(sqrt(N)).toInt
    primesUpTo(maxRelevantFactor).filter(N % _ == 0).max
  }


  euler(problem(3), "prime factorization") {

    // Like the second solution, except that as we find prime factors,
    // we divide N by them.  In that way, we end up producing the prime
    // factorization of N with only a little effort

    def largestPrimeFactorOf(N: Long) = {

      val primes = new Iterator[Int] {
        var i = 1
        var primesSoFar = List[Int]()
        def hasNext = true
        def next(): Int = {
          i += 1
          while (primesSoFar.exists(i % _ == 0))
            i += 1
          primesSoFar = i :: primesSoFar
          i
        }
      }

      def largestPrimeFactorLoop(curPrime: Int, largestSoFar:Int, remainder: Long): Int =
        if (curPrime > remainder)
          largestSoFar
        else if (remainder % curPrime == 0) {
          // curPrime is a factor
          largestPrimeFactorLoop(curPrime, curPrime, remainder / curPrime)
        } else {
          // all primes <= curPrime are not factors of remainder
          largestPrimeFactorLoop(primes.next(), largestSoFar, remainder)
        }

      largestPrimeFactorLoop(primes.next(), 1, N)
    }

    largestPrimeFactorOf(600851475143L)
  }


  euler(problem(3), "improved prime factorization") {

    // Modified previous after seeing the comments on Project Euler:
    // as long as you divide out all primes factors below n before trying n+1,
    // then the only factors you will find in the loop below will automatically
    // be prime

    def largestPrimeFactorOf(N: Long) = {

      def loop(candidate: Int, largestSoFar:Int, remainder: Long): Int =
        if (candidate > remainder)
          largestSoFar
        else if (remainder % candidate == 0) {
          // candidate is a (prime) factor
          loop(candidate, candidate, remainder / candidate)
        } else {
          // all primes <= curPrime are not factors of remainder
          loop(if (candidate > 2) candidate + 2 else candidate + 1, largestSoFar, remainder)
        }

      loop(2, 1, N)
    }

    largestPrimeFactorOf(600851475143L)
  }
}
