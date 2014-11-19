package org.bescala.projecteuler

import org.scalatest.Stopper

object Leonhard extends Stopper {
  
  var studentNeedsToAnswer = false


  def asks(problemDescription: String): String = {
    requestStop()
    problemDescription
  }

  override def stopRequested: Boolean = studentNeedsToAnswer
  override def requestStop(): Unit = {
    studentNeedsToAnswer = true
  }

}
