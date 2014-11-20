package org.bescala.projecteuler.problems

import org.bescala.projecteuler.{EulerProblem, EulerSuite}

class Problem000 extends EulerSuite {

  val demoProblem = new EulerProblem {
    override def number: Int = 0
    override def description: String =
      """
        |Find the sum of all numbers from 1 to 100
        |that are NOT multiples of 7
      """.stripMargin
    override def checkResult(answer: Long): Boolean = answer == 4315L
  }


  /**
   * Find the sum of all numbers from 1 to 100 that are not multiple of 7
   */
  euler(demoProblem) {
    (1 to 100).filter( _ % 7 != 0).sum
  }
}
