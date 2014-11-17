package org.bescala.projecteuler

/** Problem 7 - 10001-st prime

  By listing the first six prime numbers: 2, 3, 5, 7, 11, and 13, we can see that the 6th prime is 13.

  What is the 10 001st prime number?

  */

object _10001st_prime {

  // TODO: Implement function 'nthPrime' to achieve goal set in problem description
  def nthPrime(n: Int): Int = ???

  val solution = nthPrime(10001)

}

object P007_10001st_prime extends App {
  import _10001st_prime._

  println(solution)
}