package org.bescala.projecteuler

import org.bescala.projecteuler.models.Range

object Main extends App {

  val range = Range(1, 10)
  show(range)

  def show(range:Range) = println(s"list: ${range.toList} => sum: ${range.sum} ")
}
