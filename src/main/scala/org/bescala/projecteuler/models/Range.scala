package org.bescala.projecteuler.models


import scala.annotation.tailrec


/**
 * This is a over-engineered modelization to support the rather
 * simple solution for Problem001.
 *
 * The sole purpose of this model is to illustrate some Scala language features.
 */
trait Range {

  val begin:Int
  val end:Int

  def sum: Int = {
    @tailrec
    def loop(num:Int, acc:Int) : Int = {
      if (num > end) acc
      else if (num == end) num + acc
      else loop(next(num), num + acc)
    }

    loop(begin, 0)
  }

  def toList : List[Int] = {
    @tailrec
    def loop(num:Int, acc:List[Int]) : List[Int] = {
      if (num > end) acc
      else if (num == end) acc :+ num
      else loop(next(num), acc :+ num)
    }

    loop(begin, List())
  }

  protected def next(n:Int) : Int = n + 1
  protected def previous(n:Int) : Int = n - 1


}

object Range {
  def apply(start: Int, end: Int) = TotalRange(start, end)
}

case class TotalRange(begin:Int, end:Int) extends Range

