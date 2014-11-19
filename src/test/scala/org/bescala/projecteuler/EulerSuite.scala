package org.bescala.projecteuler

import org.scalatest._
import org.scalatest.events.{TestPending, TestFailed, TestIgnored, Event}

class EulerSuite extends FunSuite {

  def euler(problem:EulerProblem)(solution: => Long) : Unit = {
    test(problem.description) {
      info("------------------------------------------------------")
      val result = solution
      val success = problem.checkResult(result)
      if (success) {
        info(s"your solution: $result")
      } else {
        fail(s"Your solution yields result: $result, but this is not the correct answer!!!")
      }
      info("------------------------------------------------------")
    }
  }

  def  TODO : Long = throw new TestPendingException

}
