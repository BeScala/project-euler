package org.bescala.projecteuler

import org.scalatest._

class EulerSuite extends FunSuite {

  def euler(problem:EulerProblem, alternative: String = "")(solution: => Long) : Unit = {

    val alt = if (alternative.isEmpty) "" else s"($alternative)"

    test(s"Problem #${problem.number} $alt") {
      info("")
      info("Description:")
      problem.description.trim.split("\r\n|\r|\n").foreach { line =>
        info(s"  $line")
      }
      info("------------------------------------------------------")
      info("")
      val t0 = System.nanoTime()
      val result = solution
      val t1 = System.nanoTime()

      val elapsedTime = t1 - t0
      val elapsedTimeSec = elapsedTime / 1000000000.0
      info(s"Elapsed time: ${t1 - t0}ns - ${elapsedTimeSec}s")

      if (problem.checkResult(result)) {
        info(s"Your solution: $result")
      } else {
        fail(s"Your solution yields result: $result, but this is not the correct answer!!!")
      }
      info("")
    }
  }

  def  TODO : Nothing = throw new TestPendingException
}
