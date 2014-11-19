package org.bescala.projecteuler

import org.scalatest._

class EulerSuite extends FunSuite {

  def euler(problem:EulerProblem)(solution: => Long) : Unit = {
    test(s"problem - ${problem.number}") {
      info("")
      info("Description:")
      problem.description.trim.split("\r\n|\r|\n").foreach { line =>
        info(s"  $line")
      }
      info("------------------------------------------------------")
      info("")
      val result = solution
      val success = problem.checkResult(result)
      if (success) {
        info(s"your solution: $result")
      } else {
        fail(s"Your solution yields result: $result, but this is not the correct answer!!!")
      }
      info("")
    }
  }

  def  TODO : Long = throw new TestPendingException
}
