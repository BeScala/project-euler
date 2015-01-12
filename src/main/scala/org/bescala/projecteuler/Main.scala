package org.bescala.projecteuler

import org.bescala.projecteuler.models.Range

object Main extends App {

  val range = Range.from(1).to(10)
  show(range)

  val filtered = range.withFilter { n =>
      n % 2 == 0 || n == 1
    }

  show(filtered)

  def show(range:Range) = println(s"$range => list: ${range.toList} => sum: ${range.sum} ")

}
