package org.bescala

/**
 * Created by ericloots on 16/11/14.
 */
package object projecteuler {
  case class PrimeFactors(pf: Vector[Int])

  def fact(n: Int): Long = {
    def fact_(n: Int, acc: Long): Long = {
      if (n == 1) acc else fact_(n - 1, n * acc)
    }
    if (n == 0) 1 else fact_(n, 1L)
  }

  def gcd(a: Long, b: Long): Long = {
    if( b == 0) a else gcd(b, a % b)
  }

  def gcd(a: Int, b: Int): Int = {
    if( b == 0) a else gcd(b, a % b)
  }

  def primeFactors(n: Long): Vector[Int] = {
    def pf_(curPrime: Int, n: Long, curPrimes: Vector[Int]): Vector[Int] = {
      if (n == 1) curPrimes else {
        if ( n % curPrime == 0 ) pf_(curPrime, n / curPrime, curPrime +: curPrimes)
        else if ( curPrime == 2) pf_(curPrime + 1, n, curPrimes) else pf_(curPrime + 2, n, curPrimes)
      }
    }
    pf_(2, n, Vector.empty[Int])
  }

  def primeFactorsBis(n: Long): PrimeFactors = {
    def pf_(curPrime: Int, n: Long, curPrimes: PrimeFactors): PrimeFactors = {
      n match {
        case 1 =>
          curPrimes
        case _ if n % curPrime == 0 =>
          pf_(curPrime, n / curPrime, PrimeFactors(curPrime +: curPrimes.pf))
        case _ if curPrime == 2 =>
          pf_(curPrime + 1, n, curPrimes)
        case _ =>
          pf_(curPrime + 2, n, curPrimes)
      }
    }
    pf_(2, n, PrimeFactors(Vector.empty[Int]))
  }

  def isPrime(start: Int): Boolean = {
    if (start <= 1) false
    else {
      if (start == 2) true
      else {
        if (start % 2 == 0) false
        val s = Math.sqrt(start).ceil.toLong
        @annotation.tailrec
        def chk_(i: Long): Boolean = {
          if (i == 1) true
          else {
            if (start % i == 0) false else (chk_(i - 1))
          }
        }
        chk_(s)
      }
    }
  }

  def divisors(n: Int): Vector[Int] = {
    val primeFS = primeFactors(n)
    if (primeFS == Vector(n)) Vector(1)
    else {
      val numPF = primeFS.length
      1 +: (2 to numPF - 1).toVector.foldLeft(primeFS.distinct) {
        case (acc, ncomb) => acc ++ primeFS.combinations(ncomb).map(_.product)
      }
    }
  }

  def sumOfDivisors(n: Int): Int = divisors(n).sum

  @annotation.tailrec
  def nextPrime(n: Int): Int = {
    n match {
      case 0 | 1 => 2
      case 2     => 3
      case n if n % 2 == 0 => if (isPrime(n + 1)) n + 1 else nextPrime(n + 3)
      case n if isPrime(n + 2) => n + 2
      case n => nextPrime(n + 2)
    }
  }

  def genPrimes(primeCutOff: Int): Vector[Int] = {
    def genIter(curPrime: Int, primes: Vector[Int]): Vector[Int] = {
      val np = nextPrime(curPrime)
      if ( np >= primeCutOff ) primes else genIter(np, primes :+ np)
    }
    genIter(1, Vector[Int]())
  }

  def pentaNum(n: Long): Long = n * (3 * n - 1) / 2

  def isPentaNum(penta: Long): Boolean = {
    val n = math.sqrt(penta * 2 / 3).ceil.toLong
    pentaNum(n) == penta
  }

  def pentaNumIndex(penta: Long): Option[Long] = {
    val n = math.sqrt(penta * 2 / 3).ceil.toLong
    if ( pentaNum(n) == penta) Some(n) else None
  }

}
