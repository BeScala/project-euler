package org.bescala

/**
 * Helper methods and objects that can be useful while solving problems in the project Euler
 */
package object projecteuler {

  /** Provides a Stream of positive numbers starting at 1
    *
    *  To use it, simply do the following import
    *
    *    import Integers.positiveIntegers
    *
    *  and start using the positiveIntegers stream
    *
    * Example:
    * {{{
    *   scala> import Integers.positiveIntegers
    *   import Integers.positiveIntegers
    *   scala> positiveIntegers.takeWhile(n => n <= 5).foreach(println)
    *   1
    *   2
    *   3
    *   4
    *   5
    * }}}
    */
  object Integers {
    private def nextInt(n: Int): Stream[Int] = n #:: nextInt(n + 1)
    lazy val positiveIntegers: Stream[Int] = 1 #:: nextInt(2)
  }

  /** Calculates the factorial of a number
    *
    *{{{
    *   scala> fact(12)
    *   res2: Long = 479001600
    *}}}
    *
    * @param n  number for which to calculate the factorial
    * @return   factorial(n)
    */
  def fact(n: Int): Long = {
    def fact_(n: Int, acc: Long): Long = {
      if (n == 1) acc else fact_(n - 1, n * acc)
    }
    if (n == 0) 1 else fact_(n, 1L)
  }

  /** Calculates the greatest common divisor of two given numbers
    *
    *{{{
    * scala> gcd(24L, 16)
    * res0: Long = 8
    *}}}
    *
    * @param a first number
    * @param b second number
    * @return  greatest common divisor of a & b
    */
  def gcd(a: Long, b: Long): Long = {
    if( b == 0) a else gcd(b, a % b)
  }

  /** Calculates the greatest common divisor of two given numbers
    *
    *{{{
    * scala> gcd(24, 16)
    * res0: Int = 8
    *}}}
    *
    * @param a first number
    * @param b second number
    * @return  greatest common divisor of a & b
    */
  def gcd(a: Int, b: Int): Int = {
    if( b == 0) a else gcd(b, a % b)
  }

  /** Calculates the prime factors for a given number
    *
    * Note that the fundamental theorem of arithmetic says that every positive number
    * has a single and unique factorization in terms of prime numbers
    *
    *{{{
    *   scala> primeFactors(24)
    *   res0: Vector[Int] = Vector(3, 2, 2, 2)
    *
    *   scala> primeFactors(1238484)
    *   res1: Vector[Int] = Vector(467, 17, 13, 3, 2, 2)
    *}}}
    *
    * @param n number to decompose in prime factors
    * @return  a Vector holding the prime factors
    */
  def primeFactors(n: Long): Vector[Int] = {
    def pf_(curPrime: Int, n: Long, curPrimes: Vector[Int]): Vector[Int] = {
      if (n == 1) curPrimes else {
        if ( n % curPrime == 0 ) pf_(curPrime, n / curPrime, curPrime +: curPrimes)
        else if ( curPrime == 2) pf_(curPrime + 1, n, curPrimes) else pf_(curPrime + 2, n, curPrimes)
      }
    }
    pf_(2, n, Vector.empty)
  }

  case class PrimeFactors(pf: Vector[Int])

  /** Calculates the prime factors for a given number - an alternative implementation that uses
    *  pattern matching instead of nested if/else expressions
    *
    *{{{
    *   scala> primeFactorsBis(24)
    *   res0: org.bescala.projecteuler.PrimeFactors = PrimeFactors(Vector(3, 2, 2, 2))
    *
    *   scala> primeFactorsBis(1238484)
    *   res1: org.bescala.projecteuler.PrimeFactors = PrimeFactors(Vector(467, 17, 13, 3, 2, 2))
    *}}}
    *
    * @param n number to decompose in prime factors
    * @return  a case class PrimeFactors with a single field pf that holds the prime factors in a Vector
    */
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

  /** Checks if a given number is prime
    *
    *{{{
    *   scala> Vector(1 to 8: _*) map { n => (n, isPrime(n)) } foreach println
    *   (1,false)
    *   (2,true)
    *   (3,true)
    *   (4,false)
    *   (5,true)
    *   (6,false)
    *   (7,true)
    *   (8,false)
    *}}}
    *
    *
    * @param n Number to be checked
    * @return  true if n is prime, false if n isn't prime
    */
  def isPrime(n: Int): Boolean = {
    if (n <= 1) false
    else {
      if (n == 2) true
      else {
        if (n % 2 == 0) false
        val s = Math.sqrt(n).ceil.toLong
        @annotation.tailrec
        def chk_(i: Long): Boolean = {
          if (i == 1) true
          else {
            if (n % i == 0) false else chk_(i - 1)
          }
        }
        //println(s"Check prime($s)")
        chk_(s)
      }
    }
  }

  /** Calculates all numbers that divide entirely into a given given number
    *
    *
    * {{{
    *   scala> divisors(8)
    *   res0: Vector[Int] = Vector(1, 2, 4)
    *
    *   scala> divisors(34)
    *   res1: Vector[Int] = Vector(1, 17, 2)
    *
    *   scala> divisors(343)
    *   res2: Vector[Int] = Vector(1, 7, 49)
    *
    *   scala> divisors(344)
    *   res3: Vector[Int] = Vector(1, 43, 2, 86, 4, 172, 8)
    * }}}
    *
    * @param n number for which to calculate the divisors
    * @return  a Vector holding all the divisors
    */
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

  /** Calculates the sum of all divisors of a given number
    *
    *{{{
    *   scala> sumOfDivisors(8)
    *   res1: Int = 7
    *
    *   scala> sumOfDivisors(34)
    *   res2: Int = 20
    *
    *   scala> sumOfDivisors(343)
    *   res3: Int = 57
    *
    *   scala> sumOfDivisors(344)
    *   res4: Int = 316
    *}}}
    *
    * @param n number for which to calculate the sum of its divisors
    * @return  sum of all divisors
    */
  def sumOfDivisors(n: Int): Int = divisors(n).sum

  /** For a given number n, return the first prime that is larger than n
    * note that the only condition that n needs to meet is that it should by greater than or equal to 0
    *
    *{{{
    *   scala> nextPrime(1)
    *   res0: Int = 2
    *
    *   scala> nextPrime(2)
    *   res1: Int = 3
    *
    *   scala> nextPrime(3)
    *   res2: Int = 5
    *
    *   scala> nextPrime(4)
    *   res3: Int = 5
    *
    *   scala> nextPrime(5)
    *   res4: Int = 7
    *}}}
    *
    * @param number starting number
    * @return       first prime larger than number
    */
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

  /** Generates all prime number 1 ... N with N smaller than a given number
    *
    * Examples (reformated output for clarity)
    *
    *{{{
    *   scala> genPrimes(99)
    *   res0: Vector[Int] = Vector(2, 3, 5, 7, 11, 13, 17, 19, 23,
    *                              29, 31, 37, 41, 43, 47, 53, 59,
    *                              61, 67, 71, 73, 79, 83, 89, 97)
    *
    *   scala> genPrimes(97)
    *   res1: Vector[Int] = Vector(2, 3, 5, 7, 11, 13, 17, 19, 23,
    *                              29, 31, 37, 41, 43, 47, 53, 59,
    *                              61, 67, 71, 73, 79, 83, 89)
    *}}}
    *
    * @param primeCutOff generator cut-off value
    * @return all primes smaller than primeCutOff
    */
  def genPrimes(primeCutOff: Int): Vector[Int] = {
    def genIter(curPrime: Int, primes: Vector[Int]): Vector[Int] = {
      val np = nextPrime(curPrime)
      if ( np >= primeCutOff ) primes else genIter(np, primes :+ np)
    }
    genIter(1, Vector.empty)
  }

  /** Calculates the n-th triangle number
    *
    * the n-th (n >= 1) triangle number is calculated as n * (n + 1) / 2
    *
    * Example:
    *
    * {{{
    *   scala> for ( tnum <- Vector(1 to 10: _*) ) println(triangleNum(tnum))
    *   1
    *   3
    *   6
    *   10
    *   15
    *   21
    *   28
    *   36
    *   45
    *   55
    * }}}
    *
    * @param n triangle number index
    * @return  n-th triangle number
    */
  def triangleNum(n: Long): Long = n * (n + 1) / 2

  /** Checks if a given number is a triangle number
    *
    * Example:
    *
    * {{{
    * scala> Vector(1 to 55: _*) filter (isTriangleNum(_))
    * res0: scala.collection.immutable.Vector[Int] = Vector(1, 3, 6, 10, 15, 21, 28, 36, 45, 55)
    * }}}
    *
    * @param triangle number to check
    * @return true if given number is a triangle number, false otherwise
    */
  def isTriangleNum(triangle: Long): Boolean = {
    if ( triangle == 1 ) true
    else {
      val n = math.sqrt(triangle * 2).floor.toLong
      triangleNum(n) == triangle
    }
  }

  /** Calculates the n-th pentagonal number
    *
    * the n-th (n >= 1) pentagonal number is calculated as n * (3 * n - 1) / 2
    *
    * Example:
    *
    * {{{
    *   scala> for ( pnum <- Vector(1 to 10: _*) ) println(pentagonalNum(pnum))
    *   1
    *   5
    *   12
    *   22
    *   35
    *   51
    *   70
    *   92
    *   117
    *   145
    * }}}
    *
    * @param n pentagonal number index
    * @return n-th pentagonal number
    */
  def pentagonalNum(n: Long): Long = n * (3 * n - 1) / 2

  /** Check if a given number is a pentagonal number
   *
   * @param penta number to check
   * @return true if given number is a pentagonal number, false otherwise
   */
  def isPentagonalNum(penta: Long): Boolean = {
    if ( penta == 1 ) true
    else {
      val n = math.sqrt(penta * 2 / 3).ceil.toLong
      pentagonalNum(n) == penta
    }
  }

  /** If a given number is a pentagonal number, return its index as an Option, if it isn't return None
    *
    * Example:
    *
    * {{{
    *   scala> Vector(1 to 12: _*) foreach(n => println(pentagonalNumIndex(n)))
    *   Some(1)
    *   None
    *   None
    *   None
    *   Some(2)
    *   None
    *   None
    *   None
    *   None
    *   None
    *   None
    *   Some(3)
    * }}}
    *
    * @param penta Number for which to calculate its pentagonal number index
    * @return Some(n) is penta is a pentagonal number, None otherwise
    */
  def pentagonalNumIndex(penta: Long): Option[Long] = {
    if (penta == 1 ) Some(1L)
    else {
      val n = math.sqrt(penta * 2 / 3).ceil.toLong
      if (pentagonalNum(n) == penta) Some(n) else None
    }
  }

  /** Calculates the n-th hexagonal number
   *
   * the n-th (n >= 1) pentagonal number is calculated as n * (2 * n - 1)
   *
   * @param n hexagonal number index
   * @return  n-th hexagonal number
   */
  def hexagonalNum(n: Long): Long = n * (2 * n - 1)

  /** Check if a given number is a hexagonal number
   *
   * @param hexa number to check
   * @return true if given number is a hexagonal number, false otherwise
   */
  def isHexagonalNum(hexa: Long): Boolean = {
    if ( hexa == 1 ) true
    else {
      val n = math.sqrt(hexa / 2).ceil.toLong
      hexagonalNum(n) == hexa
    }
  }

  /** Pack a sequence of repeating elements in a given sequence of elements into a sequence of
    *  sequences holding the repeating elements.
    *
    *  A simple example for illustration purposes:
    *
    * {{{
    *  scala> pack(List(1, 1, 2, 3, 4, 4, 4, 2, 2, 2, 2, 2, 6, 7, 7))
    *  res0: Seq[Seq[Int]] = List(List(1, 1), List(2), List(3), List(4, 4, 4), List(2, 2, 2, 2, 2), List(6), List(7, 7))
    * }}}
    *
    * @param sequence Sequence of elements to be packed in function of element value repetition
    * @tparam T sequence element type
    * @return packed sequence
    */
  def pack[T](sequence: Seq[T]): Seq[Seq[T]] = {
    @annotation.tailrec
    def pack_(l: Seq[T], acc: Seq[Seq[T]]): Seq[Seq[T]] = {
      l match {
        case Nil     => acc
        case x +: xs =>
          val (hl, tl) = l span(elem => x == elem)
          pack_(tl, hl +: acc)
      }
    }

    pack_(sequence, Seq.empty[Seq[T]]).reverse
  }

  /** Encodes a sequence of repeating elements in a given sequence of elements into a sequence of
    *  tuples holding the element value and its repetition count
    *
    *  A simple example for illustration purposes:
    *
    * {{{
    *  scala> encode(List(1, 1, 2, 3, 4, 4, 4, 2, 2, 2, 2, 2, 6, 7, 7))
    *  res0: Seq[(Int, Int)] = List((1,2), (2,1), (3,1), (4,3), (2,5), (6,1), (7,2))
    * }}}
    *
    * @param sequence Sequence of elements to be encoded in function of element value repetition
    * @tparam T sequence element type
    * @return encoded sequence
    */
  def encode[T](sequence: Seq[T]): Seq[(T, Int)] = {
    pack(sequence) map (x => (x.head, x.length))
  }
}
