package org.bescala.projecteuler

/** Problem 47 - Distinct prime factors

  The first two consecutive numbers to have two distinct prime factors are:

    14 = 2 × 7
    15 = 3 × 5

  The first three consecutive numbers to have three distinct prime factors are:

    644 = 2² × 7 × 23
    645 = 3 × 5 × 43
    646 = 2 × 17 × 19.

  Find the first four consecutive integers to have four distinct prime factors. What is the first of these numbers?

  */

object Distinct_primes_factors {

  // TODO: Implement function 'check' to achieve goal set in problem description
  def check(n: Int): Boolean = ???

  def nextInt(n: Int): Stream[Int] = n #:: nextInt(n + 1)

  lazy val ints: Stream[Int] = 1 #:: nextInt(2)

  val solution = ints find (n => check(n))

}

object P047_Distinct_primes_factors extends App {
  import Distinct_primes_factors._

  println(solution)
}
