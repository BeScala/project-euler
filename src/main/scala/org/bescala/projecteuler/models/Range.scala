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
  assert(begin <= end, "The beginning of Range must be before or equal to its end")

  // by default returns always true
  def filterFunc = (n:Int) => true
  def withFilter(filter: (Int) => Boolean) : Range

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


  override def toString = s"Range(begin = $begin, end = $end)"

  protected def next(n:Int) : Int = {

    @tailrec
    def incAndFilter(n:Int) : Int = {

      val incremented = n + 1

      if (filterFunc(incremented)) {
        incremented
      } else {
        incAndFilter(incremented)
      }
    }

    incAndFilter(n)
  }

  def previous(n:Int) : Int = {

    @tailrec
    def incAndFilter(n:Int) : Int = {

      val decremented = n - 1

      if (filterFunc(decremented)) {
        decremented
      } else {
        incAndFilter(decremented)
      }
    }

    incAndFilter(n)
  }


}

object Range {
  def apply(start: Int, end: Int) = TotalRange(start, end)
}

case class TotalRange(begin:Int, end:Int) extends Range {
  def withFilter(filter: (Int) => Boolean) : Range = FilteredRange(this, filter)
}


case class FilteredRange(range:Range, override val filterFunc : Int => Boolean) extends Range {

  val begin = if (filterFunc(range.begin)) range.begin else next(range.begin)
  val end = if(filterFunc(range.end)) range.end else previous(range.end)

  def withFilter(filter: (Int) => Boolean) : Range = {
    val newFilter = (n:Int) => filterFunc(n) && filter(n)
    FilteredRange(this, newFilter)
  }

}