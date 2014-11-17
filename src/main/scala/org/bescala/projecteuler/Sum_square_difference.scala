package org.bescala.projecteuler

/** Problem 6 - Sum square difference

  The sum of the squares of the first ten natural numbers is,

  1² + 2² + ... + 10² = 385
  The square of the sum of the first ten natural numbers is,

  (1 + 2 + ... + 10)² = 552 = 3025

  Hence the difference between the sum of the squares of the first ten
  natural numbers and the square of the sum is 3025 − 385 = 2640.

  Find the difference between the sum of the squares of the first one
  hundred natural numbers and the square of the sum.

  */

object Sum_square_difference {

  // TODO: Implement function 'sumSqNum' to achieve to goal set in problem description
  def sumSqNum(n: Int): Long = ???

  val solution = sumSqNum(100)

}
object P006_Sum_square_difference extends App {
  import Sum_square_difference._

  println(solution)
}
