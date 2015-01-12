package org.bescala.projecteuler

import org.bescala.projecteuler.models.Range

object Main extends App {

  val range = Range(1, 10).withFilter(n => n % 5  == 0 || n % 3 == 0 )
  show(range)

  def show(range:Range) = println(s"list: ${range.toList} => sum: ${range.sum} ")
}
