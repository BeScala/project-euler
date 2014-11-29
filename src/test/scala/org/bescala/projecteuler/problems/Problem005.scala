package org.bescala.projecteuler.problems


import org.bescala.projecteuler.EulerSuite
import org.bescala.projecteuler.ProjectEuler._

import scala.annotation.tailrec

class Problem005 extends EulerSuite {

  /**
   * 2520 is the smallest number that can be divided by each of the numbers from 1 to 10 without any remainder.
   * What is the smallest positive number that is evenly divisible by all of the numbers from 1 to 20?
   *
   * TIP:
   * This problem can be easily by some observations
   * sequence 1 .. 20 contains the following prime numbers:
   *
   * 2 3 5 7 11 13 17 and 19
   *
   * So, the number should at least have these as factors, the remaining numbers can be written in terms
   * of prime factors as follows:
   *
   * 4  = 2 * 2         // need at least one extra 2
   * 6  = 2 * 3         // already covered
   * 8  = 2 * 2 * 2     // need another extra 2
   * 9  = 3 * 3         // need at least one extra 3
   * 10 = 2 * 5         // already covered
   * 12 = 2 * 2 * 3     // already covered if we cover previous cases
   * 14 = 2 * 7         // already covered
   * 15 = 3 * 5         // already covered
   * 16 = 2 * 2 * 2 * 2 // need another extra 2
   * 18 = 2 * 3 * 3     // already covered if we cover previous cases
   * 20 = 2 * 2 * 5     // already covered if we cover previous cases
   */
  val someSolution = s"some solution for problem ${5}"

  euler(problem(5), someSolution) {

    import math._

    def squareRootOf(z: Int) =
      round(floor(sqrt(z.toDouble))).toInt

    def isPrime(z: Int): Boolean =
      (2 to squareRootOf(z)).forall(z % _ != 0)

    def allPrimesTo(z: Int) =
      (2 to z).filter(isPrime)

    def factorizeAllNumbersTo(z: Int) =
      (2 to z).map { y =>
        allPrimesTo(z).map { p =>
          @tailrec
          def loop(y: Int)(x: Int): Int =
            if (y % p != 0) x
            else loop(y / p)(p * x)
          loop(y)(1)
        }
      }

    def smallestMultipleOfAllNumbersTo(z: Int) =
      factorizeAllNumbersTo(z).foldLeft(Vector.fill(z)(1)) { (vz, vy) =>
        vz.zip(vy).map {
          case (z, y) => max(z, y)
        }
      }.foldLeft(1)(_ * _)

    smallestMultipleOfAllNumbersTo(20)

  }

}