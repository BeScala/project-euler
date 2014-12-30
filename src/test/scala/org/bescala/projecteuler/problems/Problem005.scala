package org.bescala.projecteuler.problems


import org.bescala.projecteuler.EulerSuite
import org.bescala.projecteuler.ProjectEuler._

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
  euler(problem(5)) {
    var i: Int = 0
    do {
      i += 1
    } while (! isDivisible(i, (1 to 20).toList))
    i
  }

  euler(problem(5), "Alt #1") {
    findSmallestDivisibleBy((1 to 20).toList)
  }

  /**
   *
   * @param n an integer
   * @param l a list of integer
   * @return true is n divides evenly in each integer contained in l
   *         false otherwise
   *
   */
  def isDivisible(n: Int, l: List[Int]) : Boolean = {
    def isDivisibleAcc(n: Int, l: List[Int], b: Boolean) : Boolean = {
      if (l.isEmpty || !b) b
      else isDivisibleAcc(n, l.tail, b && (n % l.head == 0))
    }
    isDivisibleAcc(n, l, b = true)
  }

  /**
   *
   * @param l a list of integers
   * @return the smallest natural number that divides evenly in each integer contained in l
   */
  def findSmallestDivisibleBy(l: List[Int]) : Long = {
    def find(n: Int, l: List[Int]) : Long = {
      if (isDivisible(n, l)) n
      else find(n+1,l)
    }
    find(1, l)
  }

}