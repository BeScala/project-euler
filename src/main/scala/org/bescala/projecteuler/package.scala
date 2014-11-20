package org.bescala

import scala.annotation.tailrec

/**
 * Created by ericloots on 16/11/14.
 */
package object projecteuler {

  object Integers {
    private def nextInt(n: Int): Stream[Int] = n #:: nextInt(n + 1)
    lazy val positiveIntegers: Stream[Int] = 1 #:: nextInt(2)
  }

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
    pf_(2, n, Vector.empty)
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
    pf_(2, n, PrimeFactors(Vector.empty))
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
            if (start % i == 0) false else chk_(i - 1)
          }
        }
        //println(s"Check prime($s)")
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
  def nextPrime(number: Int): Int = {
    number match {
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
    genIter(1, Vector.empty)
  }

  def triangleNum(n: Long): Long = n * (n + 1) / 2

  def isTriangleNum(triangle: Long): Boolean = {
    if ( triangle == 1 ) true
    else {
      val n = math.sqrt(triangle * 2).floor.toLong
      triangleNum(n) == triangle
    }
  }

  def pentagonalNum(n: Long): Long = n * (3 * n - 1) / 2

  def isPentagonalNum(penta: Long): Boolean = {
    if ( penta == 1 ) true
    else {
      val n = math.sqrt(penta * 2 / 3).ceil.toLong
      pentagonalNum(n) == penta
    }
  }

  def pentagonalNumIndex(penta: Long): Option[Long] = {
    if (penta == 1 ) Some(1L)
    else {
      val n = math.sqrt(penta * 2 / 3).ceil.toLong
      if (pentagonalNum(n) == penta) Some(n) else None
    }
  }

  def hexagonalNum(n: Long): Long = n * (2 * n - 1)

  def isHexagonalNum(hexa: Long): Boolean = {
    if ( hexa == 1 ) true
    else {
      val n = math.sqrt(hexa / 2).ceil.toLong
      hexagonalNum(n) == hexa
    }
  }

  def pack[T](l: Seq[T]): Seq[Seq[T]] = {
    @annotation.tailrec
    def pack_(l: Seq[T], acc: Seq[Seq[T]]): Seq[Seq[T]] = {
      l match {
        case Nil     => acc
        case x +: xs =>
          val (hl, tl) = l span(elem => x == elem)
          pack_(tl, hl +: acc)
      }
    }

    pack_(l, Seq.empty[Seq[T]]).reverse
  }

  def encode[T](l: Seq[T]): Seq[(T, Int)] = {
    pack(l) map (x => (x.head, x.length))
  }
}
