package org.bescala.projecteuler.problems

import org.bescala.projecteuler.EulerSuite
import org.bescala.projecteuler.ProjectEuler._
import org.bescala.projecteuler._

class Problem007 extends EulerSuite {

  /**
   * By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.
   * What is the 10 001st prime number?
   */
  euler(problem(7)) {
    findNthPrime(10001)
  }

  /**
   * * 
   * @param n an integer
   * @return a list containing the n first prime numbers sorted decreasingly
   */
  def findFirstPrimes(n: Int): List[Int] = {
    def doFind(a: Int, b: Int, l:List[Int]): List[Int] = {
      if(l.size == a) l
      else if (isPrime(b)) doFind(a, b+1, b::l)
      else doFind (a, b+1, l)
    }
    doFind(n, 2, List())
  }

  /**
   *
   * @param n an integer
   * @return the nth prime number
   */
  def findNthPrime(n: Int) = {
    findFirstPrimes(n).head
  }
}