package org.bescala.projecteuler

import org.scalatest.events.{TestPending, TestFailed, TestIgnored, Event}
import org.scalatest._

class EulerSpec extends Spec {


  type HasTestName = {
    val testName: String
  }

  private class ReportToLeonhard(other:Reporter) extends Reporter {

    def failure(event:HasTestName) {
      info("*****************************************")
      info("")
      info(Leonhard asks event.testName)
      info("")
      info("*****************************************")
    }

    override def apply(event: Event): Unit = {
      event match {
        case e: TestIgnored =>
          failure(event.asInstanceOf[HasTestName])
        case e: TestFailed =>
          failure(event.asInstanceOf[HasTestName])
        case e: TestPending =>
          failure(event.asInstanceOf[HasTestName])

        case _ =>
          other(event)
      }
    }
  }


  override def run(testName: Option[String], args: Args): Status = {
    super.run(
      testName,
      args.copy(
        stopper = Leonhard,
        reporter = new ReportToLeonhard(args.reporter)
      )
    )
  }
}
