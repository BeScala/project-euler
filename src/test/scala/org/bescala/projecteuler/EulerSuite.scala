package org.bescala.projecteuler

import org.scalatest._

import scala.annotation.tailrec

class EulerSuite extends FunSuite {
  def toReadableTime(nanos: Long) = {
    type TimeMeasure = (Long, String)
    val conversions: Seq[TimeMeasure] = Seq((1000L, "µs"), (1000L, "ms"), (1000L, "s"), (60L, "m"))
    @tailrec def toReadableTime_(current: TimeMeasure, restConversions: Seq[TimeMeasure] = conversions): TimeMeasure  =
      if (restConversions.isEmpty || current._1 < restConversions.head._1)
        current
      else
        toReadableTime_((Math.round(current._1.toDouble / restConversions.head._1), restConversions.head._2), restConversions.tail)
    val converted = toReadableTime_((nanos, "ns"))
    s"${converted._1}${converted._2}"
  }

  def euler(problem:EulerProblem, alternative: String = "")(solution: => Long) : Unit = {

    val alt = if (alternative.isEmpty) "" else s"($alternative)"

    test(s"Problem #${problem.number} $alt") {
      info("")
      info("Description:")
      problem.description.trim.split("\r\n|\r|\n").foreach { line =>
        info(s"  $line")
      }
      info("------------------------------------------------------")
      info(alternative)
      val t0 = System.nanoTime()
      val result = solution
      val t1 = System.nanoTime()

      val elapsedTime = t1 - t0
      info(s"Elapsed time: ${toReadableTime(elapsedTime)}")

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
